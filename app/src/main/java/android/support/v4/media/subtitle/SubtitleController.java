package android.support.v4.media.subtitle;

import android.content.Context;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.media.subtitle.SubtitleTrack;
import android.view.accessibility.CaptioningManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

@RequiresApi(28)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes7.dex */
public class SubtitleController {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int WHAT_HIDE = 2;
    private static final int WHAT_SELECT_DEFAULT_TRACK = 4;
    private static final int WHAT_SELECT_TRACK = 3;
    private static final int WHAT_SHOW = 1;
    private Anchor mAnchor;
    private final Handler.Callback mCallback;
    private CaptioningManager.CaptioningChangeListener mCaptioningChangeListener;
    private CaptioningManager mCaptioningManager;
    private Handler mHandler;
    private Listener mListener;
    private ArrayList<Renderer> mRenderers;
    private final Object mRenderersLock;
    private SubtitleTrack mSelectedTrack;
    private boolean mShowing;
    private MediaTimeProvider mTimeProvider;
    private boolean mTrackIsExplicit;
    private ArrayList<SubtitleTrack> mTracks;
    private final Object mTracksLock;
    private boolean mVisibilityIsExplicit;

    /* loaded from: classes7.dex */
    public interface Anchor {
        Looper getSubtitleLooper();

        void setSubtitleWidget(SubtitleTrack.RenderingWidget renderingWidget);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public interface Listener {
        void onSubtitleTrackSelected(SubtitleTrack subtitleTrack);
    }

    /* loaded from: classes7.dex */
    public static abstract class Renderer {
        public abstract SubtitleTrack createTrack(MediaFormat mediaFormat);

        public abstract boolean supports(MediaFormat mediaFormat);
    }

    public SubtitleController(Context context) {
        this(context, null, null);
    }

    public SubtitleController(Context context, MediaTimeProvider mediaTimeProvider, Listener listener) {
        this.mRenderersLock = new Object();
        this.mTracksLock = new Object();
        this.mCallback = new Handler.Callback() { // from class: android.support.v4.media.subtitle.SubtitleController.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        SubtitleController.this.doShow();
                        return true;
                    case 2:
                        SubtitleController.this.doHide();
                        return true;
                    case 3:
                        SubtitleController.this.doSelectTrack((SubtitleTrack) message.obj);
                        return true;
                    case 4:
                        SubtitleController.this.doSelectDefaultTrack();
                        return true;
                    default:
                        return false;
                }
            }
        };
        this.mCaptioningChangeListener = new CaptioningManager.CaptioningChangeListener() { // from class: android.support.v4.media.subtitle.SubtitleController.2
            @Override // android.view.accessibility.CaptioningManager.CaptioningChangeListener
            public void onEnabledChanged(boolean z) {
                SubtitleController.this.selectDefaultTrack();
            }

            @Override // android.view.accessibility.CaptioningManager.CaptioningChangeListener
            public void onLocaleChanged(Locale locale) {
                SubtitleController.this.selectDefaultTrack();
            }
        };
        this.mTrackIsExplicit = false;
        this.mVisibilityIsExplicit = false;
        this.mTimeProvider = mediaTimeProvider;
        this.mListener = listener;
        this.mRenderers = new ArrayList<>();
        this.mShowing = false;
        this.mTracks = new ArrayList<>();
        this.mCaptioningManager = (CaptioningManager) context.getSystemService("captioning");
    }

    protected void finalize() throws Throwable {
        this.mCaptioningManager.removeCaptioningChangeListener(this.mCaptioningChangeListener);
        super.finalize();
    }

    public SubtitleTrack[] getTracks() {
        SubtitleTrack[] subtitleTrackArr;
        synchronized (this.mTracksLock) {
            subtitleTrackArr = new SubtitleTrack[this.mTracks.size()];
            this.mTracks.toArray(subtitleTrackArr);
        }
        return subtitleTrackArr;
    }

    public SubtitleTrack getSelectedTrack() {
        return this.mSelectedTrack;
    }

    private SubtitleTrack.RenderingWidget getRenderingWidget() {
        if (this.mSelectedTrack == null) {
            return null;
        }
        return this.mSelectedTrack.getRenderingWidget();
    }

    public boolean selectTrack(SubtitleTrack subtitleTrack) {
        if (subtitleTrack != null && !this.mTracks.contains(subtitleTrack)) {
            return false;
        }
        processOnAnchor(this.mHandler.obtainMessage(3, subtitleTrack));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doSelectTrack(SubtitleTrack subtitleTrack) {
        this.mTrackIsExplicit = true;
        if (this.mSelectedTrack == subtitleTrack) {
            return;
        }
        if (this.mSelectedTrack != null) {
            this.mSelectedTrack.hide();
            this.mSelectedTrack.setTimeProvider(null);
        }
        this.mSelectedTrack = subtitleTrack;
        if (this.mAnchor != null) {
            this.mAnchor.setSubtitleWidget(getRenderingWidget());
        }
        if (this.mSelectedTrack != null) {
            this.mSelectedTrack.setTimeProvider(this.mTimeProvider);
            this.mSelectedTrack.show();
        }
        if (this.mListener != null) {
            this.mListener.onSubtitleTrackSelected(subtitleTrack);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0098  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.support.v4.media.subtitle.SubtitleTrack getDefaultTrack() {
        /*
            r16 = this;
            r0 = r16
            android.view.accessibility.CaptioningManager r1 = r0.mCaptioningManager
            java.util.Locale r1 = r1.getLocale()
            if (r1 != 0) goto L11
            java.util.Locale r2 = java.util.Locale.getDefault()
            goto L12
        L11:
            r2 = r1
        L12:
            android.view.accessibility.CaptioningManager r3 = r0.mCaptioningManager
            boolean r3 = r3.isEnabled()
            r4 = 1
            r3 = r3 ^ r4
            java.lang.Object r5 = r0.mTracksLock
            monitor-enter(r5)
            java.util.ArrayList<android.support.v4.media.subtitle.SubtitleTrack> r0 = r0.mTracks     // Catch: java.lang.Throwable -> Lb6
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> Lb6
            r6 = 0
            r7 = -1
        L25:
            boolean r8 = r0.hasNext()     // Catch: java.lang.Throwable -> Lb6
            if (r8 == 0) goto Lb4
            java.lang.Object r8 = r0.next()     // Catch: java.lang.Throwable -> Lb6
            android.support.v4.media.subtitle.SubtitleTrack r8 = (android.support.v4.media.subtitle.SubtitleTrack) r8     // Catch: java.lang.Throwable -> Lb6
            android.media.MediaFormat r9 = r8.getFormat()     // Catch: java.lang.Throwable -> Lb6
            java.lang.String r10 = "language"
            java.lang.String r10 = r9.getString(r10)     // Catch: java.lang.Throwable -> Lb6
            java.lang.String r11 = "is-forced-subtitle"
            r12 = 0
            int r11 = android.support.v4.media.subtitle.SubtitleController.MediaFormatUtil.getInteger(r9, r11, r12)     // Catch: java.lang.Throwable -> Lb6
            if (r11 == 0) goto L46
            r11 = r4
            goto L48
        L46:
            r11 = r12
        L48:
            java.lang.String r13 = "is-autoselect"
            int r13 = android.support.v4.media.subtitle.SubtitleController.MediaFormatUtil.getInteger(r9, r13, r4)     // Catch: java.lang.Throwable -> Lb6
            if (r13 == 0) goto L52
            r13 = r4
            goto L54
        L52:
            r13 = r12
        L54:
            java.lang.String r14 = "is-default"
            int r9 = android.support.v4.media.subtitle.SubtitleController.MediaFormatUtil.getInteger(r9, r14, r12)     // Catch: java.lang.Throwable -> Lb6
            if (r9 == 0) goto L5e
            r9 = r4
            goto L60
        L5e:
            r9 = r12
        L60:
            if (r2 == 0) goto L85
            java.lang.String r14 = r2.getLanguage()     // Catch: java.lang.Throwable -> Lb6
            java.lang.String r15 = ""
            boolean r14 = r14.equals(r15)     // Catch: java.lang.Throwable -> Lb6
            if (r14 != 0) goto L85
            java.lang.String r14 = r2.getISO3Language()     // Catch: java.lang.Throwable -> Lb6
            boolean r14 = r14.equals(r10)     // Catch: java.lang.Throwable -> Lb6
            if (r14 != 0) goto L85
            java.lang.String r14 = r2.getLanguage()     // Catch: java.lang.Throwable -> Lb6
            boolean r10 = r14.equals(r10)     // Catch: java.lang.Throwable -> Lb6
            if (r10 == 0) goto L83
            goto L85
        L83:
            r10 = r12
            goto L87
        L85:
            r10 = r4
        L87:
            if (r11 == 0) goto L8b
            r14 = r12
            goto L8d
        L8b:
            r14 = 8
        L8d:
            if (r1 != 0) goto L93
            if (r9 == 0) goto L93
            r15 = 4
            goto L94
        L93:
            r15 = r12
        L94:
            int r14 = r14 + r15
            if (r13 == 0) goto L98
            goto L99
        L98:
            r12 = 2
        L99:
            int r14 = r14 + r12
            int r14 = r14 + r10
            if (r3 == 0) goto La0
            if (r11 != 0) goto La0
            goto L25
        La0:
            if (r1 != 0) goto La4
            if (r9 != 0) goto Lac
        La4:
            if (r10 == 0) goto Lb2
            if (r13 != 0) goto Lac
            if (r11 != 0) goto Lac
            if (r1 == 0) goto Lb2
        Lac:
            if (r14 <= r7) goto Lb2
        Lb0:
            r6 = r8
            r7 = r14
        Lb2:
            goto L25
        Lb4:
            monitor-exit(r5)     // Catch: java.lang.Throwable -> Lb6
            return r6
        Lb6:
            r0 = move-exception
            monitor-exit(r5)     // Catch: java.lang.Throwable -> Lb6
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.media.subtitle.SubtitleController.getDefaultTrack():android.support.v4.media.subtitle.SubtitleTrack");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static class MediaFormatUtil {
        MediaFormatUtil() {
        }

        static int getInteger(MediaFormat mediaFormat, String str, int i) {
            try {
                return mediaFormat.getInteger(str);
            } catch (ClassCastException | NullPointerException e) {
                return i;
            }
        }
    }

    public void selectDefaultTrack() {
        processOnAnchor(this.mHandler.obtainMessage(4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doSelectDefaultTrack() {
        if (this.mTrackIsExplicit) {
            if (this.mVisibilityIsExplicit) {
                return;
            }
            if (this.mCaptioningManager.isEnabled() || (this.mSelectedTrack != null && MediaFormatUtil.getInteger(this.mSelectedTrack.getFormat(), "is-forced-subtitle", 0) != 0)) {
                show();
            } else if (this.mSelectedTrack != null && this.mSelectedTrack.getTrackType() == 4) {
                hide();
            }
            this.mVisibilityIsExplicit = false;
        }
        SubtitleTrack defaultTrack = getDefaultTrack();
        if (defaultTrack != null) {
            selectTrack(defaultTrack);
            this.mTrackIsExplicit = false;
            if (!this.mVisibilityIsExplicit) {
                show();
                this.mVisibilityIsExplicit = false;
            }
        }
    }

    public void reset() {
        checkAnchorLooper();
        hide();
        selectTrack(null);
        this.mTracks.clear();
        this.mTrackIsExplicit = false;
        this.mVisibilityIsExplicit = false;
        this.mCaptioningManager.removeCaptioningChangeListener(this.mCaptioningChangeListener);
    }

    public SubtitleTrack addTrack(MediaFormat mediaFormat) {
        SubtitleTrack createTrack;
        synchronized (this.mRenderersLock) {
            Iterator<Renderer> it = this.mRenderers.iterator();
            while (it.hasNext()) {
                Renderer next = it.next();
                if (next.supports(mediaFormat) && (createTrack = next.createTrack(mediaFormat)) != null) {
                    synchronized (this.mTracksLock) {
                        if (this.mTracks.size() == 0) {
                            this.mCaptioningManager.addCaptioningChangeListener(this.mCaptioningChangeListener);
                        }
                        this.mTracks.add(createTrack);
                    }
                    return createTrack;
                }
            }
            return null;
        }
    }

    public void show() {
        processOnAnchor(this.mHandler.obtainMessage(1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doShow() {
        this.mShowing = true;
        this.mVisibilityIsExplicit = true;
        if (this.mSelectedTrack != null) {
            this.mSelectedTrack.show();
        }
    }

    public void hide() {
        processOnAnchor(this.mHandler.obtainMessage(2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doHide() {
        this.mVisibilityIsExplicit = true;
        if (this.mSelectedTrack != null) {
            this.mSelectedTrack.hide();
        }
        this.mShowing = false;
    }

    public void registerRenderer(Renderer renderer) {
        synchronized (this.mRenderersLock) {
            if (!this.mRenderers.contains(renderer)) {
                this.mRenderers.add(renderer);
            }
        }
    }

    public boolean hasRendererFor(MediaFormat mediaFormat) {
        synchronized (this.mRenderersLock) {
            Iterator<Renderer> it = this.mRenderers.iterator();
            while (it.hasNext()) {
                if (it.next().supports(mediaFormat)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void setAnchor(Anchor anchor) {
        if (this.mAnchor == anchor) {
            return;
        }
        if (this.mAnchor != null) {
            checkAnchorLooper();
            this.mAnchor.setSubtitleWidget(null);
        }
        this.mAnchor = anchor;
        this.mHandler = null;
        if (this.mAnchor != null) {
            this.mHandler = new Handler(this.mAnchor.getSubtitleLooper(), this.mCallback);
            checkAnchorLooper();
            this.mAnchor.setSubtitleWidget(getRenderingWidget());
        }
    }

    private void checkAnchorLooper() {
    }

    private void processOnAnchor(Message message) {
        if (Looper.myLooper() == this.mHandler.getLooper()) {
            this.mHandler.dispatchMessage(message);
        } else {
            this.mHandler.sendMessage(message);
        }
    }
}
