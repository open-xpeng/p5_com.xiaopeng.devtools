package android.support.v4.media;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes7.dex */
public final class SessionCommandGroup2 {
    private static final String KEY_COMMANDS = "android.media.mediasession2.commandgroup.commands";
    private static final String PREFIX_COMMAND_CODE = "COMMAND_CODE_";
    private static final String PREFIX_COMMAND_CODE_PLAYBACK = "COMMAND_CODE_PLAYBACK_";
    private static final String PREFIX_COMMAND_CODE_PLAYLIST = "COMMAND_CODE_PLAYLIST_";
    private static final String PREFIX_COMMAND_CODE_VOLUME = "COMMAND_CODE_VOLUME_";
    private static final String TAG = "SessionCommandGroup2";
    private Set<SessionCommand2> mCommands = new HashSet();

    public SessionCommandGroup2() {
    }

    public SessionCommandGroup2(@Nullable SessionCommandGroup2 sessionCommandGroup2) {
        if (sessionCommandGroup2 != null) {
            this.mCommands.addAll(sessionCommandGroup2.mCommands);
        }
    }

    public void addCommand(@NonNull SessionCommand2 sessionCommand2) {
        if (sessionCommand2 == null) {
            throw new IllegalArgumentException("command shouldn't be null");
        }
        this.mCommands.add(sessionCommand2);
    }

    public void addCommand(int i) {
        if (i == 0) {
            throw new IllegalArgumentException("command shouldn't be null");
        }
        this.mCommands.add(new SessionCommand2(i));
    }

    public void addAllPredefinedCommands() {
        addCommandsWithPrefix(PREFIX_COMMAND_CODE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addAllPlaybackCommands() {
        addCommandsWithPrefix(PREFIX_COMMAND_CODE_PLAYBACK);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addAllPlaylistCommands() {
        addCommandsWithPrefix(PREFIX_COMMAND_CODE_PLAYLIST);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void addAllVolumeCommands() {
        addCommandsWithPrefix(PREFIX_COMMAND_CODE_VOLUME);
    }

    private void addCommandsWithPrefix(String str) {
        Field[] fields = SessionCommand2.class.getFields();
        if (fields != null) {
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getName().startsWith(str) && !fields[i].getName().equals("COMMAND_CODE_CUSTOM")) {
                    try {
                        this.mCommands.add(new SessionCommand2(fields[i].getInt(null)));
                    } catch (IllegalAccessException e) {
                        Log.w(TAG, "Unexpected " + fields[i] + " in MediaSession2");
                    }
                }
            }
        }
    }

    public void removeCommand(@NonNull SessionCommand2 sessionCommand2) {
        if (sessionCommand2 == null) {
            throw new IllegalArgumentException("command shouldn't be null");
        }
        this.mCommands.remove(sessionCommand2);
    }

    public void removeCommand(int i) {
        if (i == 0) {
            throw new IllegalArgumentException("commandCode shouldn't be COMMAND_CODE_CUSTOM");
        }
        this.mCommands.remove(new SessionCommand2(i));
    }

    public boolean hasCommand(@NonNull SessionCommand2 sessionCommand2) {
        if (sessionCommand2 == null) {
            throw new IllegalArgumentException("command shouldn't be null");
        }
        return this.mCommands.contains(sessionCommand2);
    }

    public boolean hasCommand(int i) {
        if (i == 0) {
            throw new IllegalArgumentException("Use hasCommand(Command) for custom command");
        }
        for (SessionCommand2 sessionCommand2 : this.mCommands) {
            if (sessionCommand2.getCommandCode() == i) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    public Set<SessionCommand2> getCommands() {
        return new HashSet(this.mCommands);
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Bundle toBundle() {
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        for (SessionCommand2 sessionCommand2 : this.mCommands) {
            arrayList.add(sessionCommand2.toBundle());
        }
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(KEY_COMMANDS, arrayList);
        return bundle;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static SessionCommandGroup2 fromBundle(Bundle bundle) {
        ArrayList parcelableArrayList;
        SessionCommand2 fromBundle;
        if (bundle == null || (parcelableArrayList = bundle.getParcelableArrayList(KEY_COMMANDS)) == null) {
            return null;
        }
        SessionCommandGroup2 sessionCommandGroup2 = new SessionCommandGroup2();
        for (int i = 0; i < parcelableArrayList.size(); i++) {
            Parcelable parcelable = (Parcelable) parcelableArrayList.get(i);
            if ((parcelable instanceof Bundle) && (fromBundle = SessionCommand2.fromBundle((Bundle) parcelable)) != null) {
                sessionCommandGroup2.addCommand(fromBundle);
            }
        }
        return sessionCommandGroup2;
    }
}
