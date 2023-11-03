package android.support.v4.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.support.annotation.GuardedBy;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.ObjectsCompat;
import android.util.Log;

@VisibleForTesting(otherwise = 3)
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes7.dex */
public class AudioFocusHandler {
    private static final boolean DEBUG = false;
    private static final String TAG = "AudioFocusHandler";
    private final AudioFocusHandlerImpl mImpl;

    /* loaded from: classes7.dex */
    interface AudioFocusHandlerImpl {
        void close();

        boolean onPauseRequested();

        boolean onPlayRequested();

        void onPlayerStateChanged(int i);

        void sendIntent(Intent intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AudioFocusHandler(Context context, MediaSession2 mediaSession2) {
        this.mImpl = new AudioFocusHandlerImplBase(context, mediaSession2);
    }

    public boolean onPlayRequested() {
        return this.mImpl.onPlayRequested();
    }

    public boolean onPauseRequested() {
        return this.mImpl.onPauseRequested();
    }

    public void onPlayerStateChanged(int i) {
        this.mImpl.onPlayerStateChanged(i);
    }

    public void close() {
        this.mImpl.close();
    }

    public void sendIntent(Intent intent) {
        this.mImpl.sendIntent(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static class AudioFocusHandlerImplBase implements AudioFocusHandlerImpl {
        private static final float VOLUME_DUCK_FACTOR = 0.2f;
        @GuardedBy("mLock")
        private AudioAttributesCompat mAudioAttributes;
        private final AudioManager mAudioManager;
        @GuardedBy("mLock")
        private boolean mHasAudioFocus;
        @GuardedBy("mLock")
        private boolean mHasRegisteredReceiver;
        @GuardedBy("mLock")
        private boolean mResumeWhenAudioFocusGain;
        private final MediaSession2 mSession;
        private final BroadcastReceiver mBecomingNoisyIntentReceiver = new NoisyIntentReceiver();
        private final IntentFilter mIntentFilter = new IntentFilter("android.media.AUDIO_BECOMING_NOISY");
        private final AudioManager.OnAudioFocusChangeListener mAudioFocusListener = new AudioFocusListener();
        private final Object mLock = new Object();

        AudioFocusHandlerImplBase(Context context, MediaSession2 mediaSession2) {
            this.mSession = mediaSession2;
            this.mAudioManager = (AudioManager) context.getSystemService("audio");
        }

        private void updateAudioAttributesIfNeeded() {
            BaseMediaPlayer player;
            AudioAttributesCompat audioAttributesCompat = null;
            if (this.mSession.getVolumeProvider() == null && (player = this.mSession.getPlayer()) != null) {
                audioAttributesCompat = player.getAudioAttributes();
            }
            synchronized (this.mLock) {
                if (ObjectsCompat.equals(audioAttributesCompat, this.mAudioAttributes)) {
                    return;
                }
                this.mAudioAttributes = audioAttributesCompat;
                if (this.mHasAudioFocus) {
                    this.mHasAudioFocus = requestAudioFocusLocked();
                    if (!this.mHasAudioFocus) {
                        Log.w(AudioFocusHandler.TAG, "Failed to regain audio focus.");
                    }
                }
            }
        }

        @Override // android.support.v4.media.AudioFocusHandler.AudioFocusHandlerImpl
        public boolean onPlayRequested() {
            updateAudioAttributesIfNeeded();
            synchronized (this.mLock) {
                if (!requestAudioFocusLocked()) {
                    return false;
                }
                return true;
            }
        }

        @Override // android.support.v4.media.AudioFocusHandler.AudioFocusHandlerImpl
        public boolean onPauseRequested() {
            synchronized (this.mLock) {
                this.mResumeWhenAudioFocusGain = false;
            }
            return true;
        }

        @Override // android.support.v4.media.AudioFocusHandler.AudioFocusHandlerImpl
        public void onPlayerStateChanged(int i) {
            synchronized (this.mLock) {
                switch (i) {
                    case 0:
                        abandonAudioFocusLocked();
                        break;
                    case 1:
                        updateAudioAttributesIfNeeded();
                        unregisterReceiverLocked();
                        break;
                    case 2:
                        updateAudioAttributesIfNeeded();
                        registerReceiverLocked();
                        break;
                    case 3:
                        abandonAudioFocusLocked();
                        unregisterReceiverLocked();
                        break;
                }
            }
        }

        @Override // android.support.v4.media.AudioFocusHandler.AudioFocusHandlerImpl
        public void close() {
            synchronized (this.mLock) {
                unregisterReceiverLocked();
                abandonAudioFocusLocked();
            }
        }

        @Override // android.support.v4.media.AudioFocusHandler.AudioFocusHandlerImpl
        public void sendIntent(Intent intent) {
            this.mBecomingNoisyIntentReceiver.onReceive(this.mSession.getContext(), intent);
        }

        @GuardedBy("mLock")
        private boolean requestAudioFocusLocked() {
            int convertAudioAttributesToFocusGainLocked = convertAudioAttributesToFocusGainLocked();
            if (convertAudioAttributesToFocusGainLocked == 0) {
                return true;
            }
            int requestAudioFocus = this.mAudioManager.requestAudioFocus(this.mAudioFocusListener, this.mAudioAttributes.getVolumeControlStream(), convertAudioAttributesToFocusGainLocked);
            if (requestAudioFocus == 1) {
                this.mHasAudioFocus = true;
            } else {
                Log.w(AudioFocusHandler.TAG, "requestAudioFocus(" + convertAudioAttributesToFocusGainLocked + ") failed (return=" + requestAudioFocus + ") playback wouldn't start.");
                this.mHasAudioFocus = false;
            }
            this.mResumeWhenAudioFocusGain = false;
            return this.mHasAudioFocus;
        }

        @GuardedBy("mLock")
        private void abandonAudioFocusLocked() {
            if (!this.mHasAudioFocus) {
                return;
            }
            this.mAudioManager.abandonAudioFocus(this.mAudioFocusListener);
            this.mHasAudioFocus = false;
            this.mResumeWhenAudioFocusGain = false;
        }

        @GuardedBy("mLock")
        private void registerReceiverLocked() {
            if (this.mHasRegisteredReceiver) {
                return;
            }
            this.mSession.getContext().registerReceiver(this.mBecomingNoisyIntentReceiver, this.mIntentFilter);
            this.mHasRegisteredReceiver = true;
        }

        @GuardedBy("mLock")
        private void unregisterReceiverLocked() {
            if (!this.mHasRegisteredReceiver) {
                return;
            }
            this.mSession.getContext().unregisterReceiver(this.mBecomingNoisyIntentReceiver);
            this.mHasRegisteredReceiver = false;
        }

        @GuardedBy("mLock")
        private int convertAudioAttributesToFocusGainLocked() {
            AudioAttributesCompat audioAttributesCompat = this.mAudioAttributes;
            if (audioAttributesCompat == null) {
                return 0;
            }
            switch (audioAttributesCompat.getUsage()) {
                case 0:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 16:
                    return 3;
                case 1:
                case 14:
                    return 1;
                case 2:
                case 3:
                case 4:
                    return 2;
                case 15:
                default:
                    return 0;
            }
        }

        /* loaded from: classes7.dex */
        private class NoisyIntentReceiver extends BroadcastReceiver {
            private NoisyIntentReceiver() {
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                BaseMediaPlayer player;
                synchronized (AudioFocusHandlerImplBase.this.mLock) {
                    if (AudioFocusHandlerImplBase.this.mHasRegisteredReceiver) {
                        if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
                            synchronized (AudioFocusHandlerImplBase.this.mLock) {
                                if (AudioFocusHandlerImplBase.this.mAudioAttributes == null) {
                                    return;
                                }
                                int usage = AudioFocusHandlerImplBase.this.mAudioAttributes.getUsage();
                                if (usage == 1) {
                                    AudioFocusHandlerImplBase.this.mSession.pause();
                                } else if (usage == 14 && (player = AudioFocusHandlerImplBase.this.mSession.getPlayer()) != null) {
                                    player.setPlayerVolume(player.getPlayerVolume() * 0.2f);
                                }
                            }
                        }
                    }
                }
            }
        }

        /* loaded from: classes7.dex */
        private class AudioFocusListener implements AudioManager.OnAudioFocusChangeListener {
            private float mPlayerDuckingVolume;
            private float mPlayerVolumeBeforeDucking;

            private AudioFocusListener() {
            }

            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public void onAudioFocusChange(int i) {
                if (i != 1) {
                    switch (i) {
                        case -3:
                            synchronized (AudioFocusHandlerImplBase.this.mLock) {
                                if (AudioFocusHandlerImplBase.this.mAudioAttributes != null) {
                                    if (AudioFocusHandlerImplBase.this.mAudioAttributes.getContentType() == 1) {
                                        AudioFocusHandlerImplBase.this.mSession.pause();
                                    } else {
                                        BaseMediaPlayer player = AudioFocusHandlerImplBase.this.mSession.getPlayer();
                                        if (player != null) {
                                            float playerVolume = player.getPlayerVolume();
                                            float f = 0.2f * playerVolume;
                                            synchronized (AudioFocusHandlerImplBase.this.mLock) {
                                                this.mPlayerVolumeBeforeDucking = playerVolume;
                                                this.mPlayerDuckingVolume = f;
                                            }
                                            player.setPlayerVolume(f);
                                        }
                                    }
                                    return;
                                }
                                return;
                            }
                        case -2:
                            AudioFocusHandlerImplBase.this.mSession.pause();
                            synchronized (AudioFocusHandlerImplBase.this.mLock) {
                                AudioFocusHandlerImplBase.this.mResumeWhenAudioFocusGain = true;
                            }
                            return;
                        case -1:
                            AudioFocusHandlerImplBase.this.mSession.pause();
                            synchronized (AudioFocusHandlerImplBase.this.mLock) {
                                AudioFocusHandlerImplBase.this.mResumeWhenAudioFocusGain = false;
                            }
                            return;
                        default:
                            return;
                    }
                } else if (AudioFocusHandlerImplBase.this.mSession.getPlayerState() == 1) {
                    synchronized (AudioFocusHandlerImplBase.this.mLock) {
                        if (AudioFocusHandlerImplBase.this.mResumeWhenAudioFocusGain) {
                            AudioFocusHandlerImplBase.this.mSession.play();
                        }
                    }
                } else {
                    BaseMediaPlayer player2 = AudioFocusHandlerImplBase.this.mSession.getPlayer();
                    if (player2 != null) {
                        float playerVolume2 = player2.getPlayerVolume();
                        synchronized (AudioFocusHandlerImplBase.this.mLock) {
                            if (playerVolume2 == this.mPlayerDuckingVolume) {
                                player2.setPlayerVolume(this.mPlayerVolumeBeforeDucking);
                            }
                        }
                    }
                }
            }
        }
    }
}
