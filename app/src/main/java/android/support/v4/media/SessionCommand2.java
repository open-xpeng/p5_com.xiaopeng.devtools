package android.support.v4.media;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.text.TextUtils;

/* loaded from: classes7.dex */
public final class SessionCommand2 {
    public static final int COMMAND_CODE_CUSTOM = 0;
    public static final int COMMAND_CODE_LIBRARY_GET_CHILDREN = 29;
    public static final int COMMAND_CODE_LIBRARY_GET_ITEM = 30;
    public static final int COMMAND_CODE_LIBRARY_GET_LIBRARY_ROOT = 31;
    public static final int COMMAND_CODE_LIBRARY_GET_SEARCH_RESULT = 32;
    public static final int COMMAND_CODE_LIBRARY_SEARCH = 33;
    public static final int COMMAND_CODE_LIBRARY_SUBSCRIBE = 34;
    public static final int COMMAND_CODE_LIBRARY_UNSUBSCRIBE = 35;
    public static final int COMMAND_CODE_PLAYBACK_PAUSE = 2;
    public static final int COMMAND_CODE_PLAYBACK_PLAY = 1;
    public static final int COMMAND_CODE_PLAYBACK_PREPARE = 6;
    public static final int COMMAND_CODE_PLAYBACK_RESET = 3;
    public static final int COMMAND_CODE_PLAYBACK_SEEK_TO = 9;
    public static final int COMMAND_CODE_PLAYBACK_SET_SPEED = 39;
    public static final int COMMAND_CODE_PLAYLIST_ADD_ITEM = 15;
    public static final int COMMAND_CODE_PLAYLIST_GET_CURRENT_MEDIA_ITEM = 20;
    public static final int COMMAND_CODE_PLAYLIST_GET_LIST = 18;
    public static final int COMMAND_CODE_PLAYLIST_GET_LIST_METADATA = 20;
    public static final int COMMAND_CODE_PLAYLIST_REMOVE_ITEM = 16;
    public static final int COMMAND_CODE_PLAYLIST_REPLACE_ITEM = 17;
    public static final int COMMAND_CODE_PLAYLIST_SET_LIST = 19;
    public static final int COMMAND_CODE_PLAYLIST_SET_LIST_METADATA = 21;
    public static final int COMMAND_CODE_PLAYLIST_SET_REPEAT_MODE = 14;
    public static final int COMMAND_CODE_PLAYLIST_SET_SHUFFLE_MODE = 13;
    public static final int COMMAND_CODE_PLAYLIST_SKIP_TO_NEXT_ITEM = 4;
    public static final int COMMAND_CODE_PLAYLIST_SKIP_TO_PLAYLIST_ITEM = 12;
    public static final int COMMAND_CODE_PLAYLIST_SKIP_TO_PREV_ITEM = 5;
    public static final int COMMAND_CODE_SESSION_FAST_FORWARD = 7;
    public static final int COMMAND_CODE_SESSION_PLAY_FROM_MEDIA_ID = 22;
    public static final int COMMAND_CODE_SESSION_PLAY_FROM_SEARCH = 24;
    public static final int COMMAND_CODE_SESSION_PLAY_FROM_URI = 23;
    public static final int COMMAND_CODE_SESSION_PREPARE_FROM_MEDIA_ID = 25;
    public static final int COMMAND_CODE_SESSION_PREPARE_FROM_SEARCH = 27;
    public static final int COMMAND_CODE_SESSION_PREPARE_FROM_URI = 26;
    public static final int COMMAND_CODE_SESSION_REWIND = 8;
    public static final int COMMAND_CODE_SESSION_SELECT_ROUTE = 38;
    public static final int COMMAND_CODE_SESSION_SET_RATING = 28;
    public static final int COMMAND_CODE_SESSION_SUBSCRIBE_ROUTES_INFO = 36;
    public static final int COMMAND_CODE_SESSION_UNSUBSCRIBE_ROUTES_INFO = 37;
    public static final int COMMAND_CODE_VOLUME_ADJUST_VOLUME = 11;
    public static final int COMMAND_CODE_VOLUME_SET_VOLUME = 10;
    private static final String KEY_COMMAND_CODE = "android.media.media_session2.command.command_code";
    private static final String KEY_COMMAND_CUSTOM_COMMAND = "android.media.media_session2.command.custom_command";
    private static final String KEY_COMMAND_EXTRAS = "android.media.media_session2.command.extras";
    private final int mCommandCode;
    private final String mCustomCommand;
    private final Bundle mExtras;

    public SessionCommand2(int i) {
        if (i == 0) {
            throw new IllegalArgumentException("commandCode shouldn't be COMMAND_CODE_CUSTOM");
        }
        this.mCommandCode = i;
        this.mCustomCommand = null;
        this.mExtras = null;
    }

    public SessionCommand2(@NonNull String str, @Nullable Bundle bundle) {
        if (str == null) {
            throw new IllegalArgumentException("action shouldn't be null");
        }
        this.mCommandCode = 0;
        this.mCustomCommand = str;
        this.mExtras = bundle;
    }

    public int getCommandCode() {
        return this.mCommandCode;
    }

    @Nullable
    public String getCustomCommand() {
        return this.mCustomCommand;
    }

    @Nullable
    public Bundle getExtras() {
        return this.mExtras;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_COMMAND_CODE, this.mCommandCode);
        bundle.putString(KEY_COMMAND_CUSTOM_COMMAND, this.mCustomCommand);
        bundle.putBundle(KEY_COMMAND_EXTRAS, this.mExtras);
        return bundle;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static SessionCommand2 fromBundle(@NonNull Bundle bundle) {
        if (bundle == null) {
            throw new IllegalArgumentException("command shouldn't be null");
        }
        int i = bundle.getInt(KEY_COMMAND_CODE);
        if (i != 0) {
            return new SessionCommand2(i);
        }
        String string = bundle.getString(KEY_COMMAND_CUSTOM_COMMAND);
        if (string == null) {
            return null;
        }
        return new SessionCommand2(string, bundle.getBundle(KEY_COMMAND_EXTRAS));
    }

    public boolean equals(Object obj) {
        if (obj instanceof SessionCommand2) {
            SessionCommand2 sessionCommand2 = (SessionCommand2) obj;
            return this.mCommandCode == sessionCommand2.mCommandCode && TextUtils.equals(this.mCustomCommand, sessionCommand2.mCustomCommand);
        }
        return false;
    }

    public int hashCode() {
        return ((this.mCustomCommand != null ? this.mCustomCommand.hashCode() : 0) * 31) + this.mCommandCode;
    }
}
