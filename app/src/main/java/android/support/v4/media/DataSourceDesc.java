package android.support.v4.media;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Preconditions;
import java.io.FileDescriptor;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public final class DataSourceDesc {
    public static final long FD_LENGTH_UNKNOWN = 576460752303423487L;
    private static final long LONG_MAX = 576460752303423487L;
    public static final long POSITION_UNKNOWN = 576460752303423487L;
    public static final int TYPE_CALLBACK = 1;
    public static final int TYPE_FD = 2;
    public static final int TYPE_NONE = 0;
    public static final int TYPE_URI = 3;
    private long mEndPositionMs;
    private FileDescriptor mFD;
    private long mFDLength;
    private long mFDOffset;
    private Media2DataSource mMedia2DataSource;
    private String mMediaId;
    private long mStartPositionMs;
    private int mType;
    private Uri mUri;
    private Context mUriContext;
    private List<HttpCookie> mUriCookies;
    private Map<String, String> mUriHeader;

    private DataSourceDesc() {
        this.mType = 0;
        this.mFDOffset = 0L;
        this.mFDLength = 576460752303423487L;
        this.mStartPositionMs = 0L;
        this.mEndPositionMs = 576460752303423487L;
    }

    @Nullable
    public String getMediaId() {
        return this.mMediaId;
    }

    public long getStartPosition() {
        return this.mStartPositionMs;
    }

    public long getEndPosition() {
        return this.mEndPositionMs;
    }

    public int getType() {
        return this.mType;
    }

    @Nullable
    public Media2DataSource getMedia2DataSource() {
        return this.mMedia2DataSource;
    }

    @Nullable
    public FileDescriptor getFileDescriptor() {
        return this.mFD;
    }

    public long getFileDescriptorOffset() {
        return this.mFDOffset;
    }

    public long getFileDescriptorLength() {
        return this.mFDLength;
    }

    @Nullable
    public Uri getUri() {
        return this.mUri;
    }

    @Nullable
    public Map<String, String> getUriHeaders() {
        if (this.mUriHeader == null) {
            return null;
        }
        return new HashMap(this.mUriHeader);
    }

    @Nullable
    public List<HttpCookie> getUriCookies() {
        if (this.mUriCookies == null) {
            return null;
        }
        return new ArrayList(this.mUriCookies);
    }

    @Nullable
    public Context getUriContext() {
        return this.mUriContext;
    }

    /* loaded from: classes7.dex */
    public static class Builder {
        private long mEndPositionMs;
        private FileDescriptor mFD;
        private long mFDLength;
        private long mFDOffset;
        private Media2DataSource mMedia2DataSource;
        private String mMediaId;
        private long mStartPositionMs;
        private int mType;
        private Uri mUri;
        private Context mUriContext;
        private List<HttpCookie> mUriCookies;
        private Map<String, String> mUriHeader;

        public Builder() {
            this.mType = 0;
            this.mFDOffset = 0L;
            this.mFDLength = 576460752303423487L;
            this.mStartPositionMs = 0L;
            this.mEndPositionMs = 576460752303423487L;
        }

        public Builder(@NonNull DataSourceDesc dataSourceDesc) {
            this.mType = 0;
            this.mFDOffset = 0L;
            this.mFDLength = 576460752303423487L;
            this.mStartPositionMs = 0L;
            this.mEndPositionMs = 576460752303423487L;
            this.mType = dataSourceDesc.mType;
            this.mMedia2DataSource = dataSourceDesc.mMedia2DataSource;
            this.mFD = dataSourceDesc.mFD;
            this.mFDOffset = dataSourceDesc.mFDOffset;
            this.mFDLength = dataSourceDesc.mFDLength;
            this.mUri = dataSourceDesc.mUri;
            this.mUriHeader = dataSourceDesc.mUriHeader;
            this.mUriCookies = dataSourceDesc.mUriCookies;
            this.mUriContext = dataSourceDesc.mUriContext;
            this.mMediaId = dataSourceDesc.mMediaId;
            this.mStartPositionMs = dataSourceDesc.mStartPositionMs;
            this.mEndPositionMs = dataSourceDesc.mEndPositionMs;
        }

        @NonNull
        public DataSourceDesc build() {
            if (this.mType != 1 && this.mType != 2 && this.mType != 3) {
                throw new IllegalStateException("Illegal type: " + this.mType);
            } else if (this.mStartPositionMs > this.mEndPositionMs) {
                throw new IllegalStateException("Illegal start/end position: " + this.mStartPositionMs + " : " + this.mEndPositionMs);
            } else {
                DataSourceDesc dataSourceDesc = new DataSourceDesc();
                dataSourceDesc.mType = this.mType;
                dataSourceDesc.mMedia2DataSource = this.mMedia2DataSource;
                dataSourceDesc.mFD = this.mFD;
                dataSourceDesc.mFDOffset = this.mFDOffset;
                dataSourceDesc.mFDLength = this.mFDLength;
                dataSourceDesc.mUri = this.mUri;
                dataSourceDesc.mUriHeader = this.mUriHeader;
                dataSourceDesc.mUriCookies = this.mUriCookies;
                dataSourceDesc.mUriContext = this.mUriContext;
                dataSourceDesc.mMediaId = this.mMediaId;
                dataSourceDesc.mStartPositionMs = this.mStartPositionMs;
                dataSourceDesc.mEndPositionMs = this.mEndPositionMs;
                return dataSourceDesc;
            }
        }

        @NonNull
        public Builder setMediaId(String str) {
            this.mMediaId = str;
            return this;
        }

        @NonNull
        public Builder setStartPosition(long j) {
            if (j < 0) {
                j = 0;
            }
            this.mStartPositionMs = j;
            return this;
        }

        @NonNull
        public Builder setEndPosition(long j) {
            if (j < 0) {
                j = 576460752303423487L;
            }
            this.mEndPositionMs = j;
            return this;
        }

        @NonNull
        public Builder setDataSource(@NonNull Media2DataSource media2DataSource) {
            Preconditions.checkNotNull(media2DataSource);
            resetDataSource();
            this.mType = 1;
            this.mMedia2DataSource = media2DataSource;
            return this;
        }

        @NonNull
        public Builder setDataSource(@NonNull FileDescriptor fileDescriptor) {
            Preconditions.checkNotNull(fileDescriptor);
            resetDataSource();
            this.mType = 2;
            this.mFD = fileDescriptor;
            return this;
        }

        @NonNull
        public Builder setDataSource(@NonNull FileDescriptor fileDescriptor, long j, long j2) {
            Preconditions.checkNotNull(fileDescriptor);
            if (j < 0) {
                j = 0;
            }
            if (j2 < 0) {
                j2 = 576460752303423487L;
            }
            resetDataSource();
            this.mType = 2;
            this.mFD = fileDescriptor;
            this.mFDOffset = j;
            this.mFDLength = j2;
            return this;
        }

        @NonNull
        public Builder setDataSource(@NonNull Context context, @NonNull Uri uri) {
            Preconditions.checkNotNull(context, "context cannot be null");
            Preconditions.checkNotNull(uri, "uri cannot be null");
            resetDataSource();
            this.mType = 3;
            this.mUri = uri;
            this.mUriContext = context;
            return this;
        }

        @NonNull
        public Builder setDataSource(@NonNull Context context, @NonNull Uri uri, @Nullable Map<String, String> map, @Nullable List<HttpCookie> list) {
            CookieHandler cookieHandler;
            Preconditions.checkNotNull(context, "context cannot be null");
            Preconditions.checkNotNull(uri);
            if (list != null && (cookieHandler = CookieHandler.getDefault()) != null && !(cookieHandler instanceof CookieManager)) {
                throw new IllegalArgumentException("The cookie handler has to be of CookieManager type when cookies are provided.");
            }
            resetDataSource();
            this.mType = 3;
            this.mUri = uri;
            if (map != null) {
                this.mUriHeader = new HashMap(map);
            }
            if (list != null) {
                this.mUriCookies = new ArrayList(list);
            }
            this.mUriContext = context;
            return this;
        }

        private void resetDataSource() {
            this.mType = 0;
            this.mMedia2DataSource = null;
            this.mFD = null;
            this.mFDOffset = 0L;
            this.mFDLength = 576460752303423487L;
            this.mUri = null;
            this.mUriHeader = null;
            this.mUriCookies = null;
            this.mUriContext = null;
        }
    }
}
