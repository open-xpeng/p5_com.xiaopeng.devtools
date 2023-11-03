package com.lzy.okgo.model;

import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.lzy.okgo.f.b;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okhttp3.MediaType;

/* loaded from: classes11.dex */
public class HttpParams implements Serializable {
    public static final MediaType mo = MediaType.parse("text/plain;charset=utf-8");
    public static final MediaType mp = MediaType.parse("application/json;charset=utf-8");
    public static final MediaType mq = MediaType.parse(OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE);
    private static final long serialVersionUID = 7369819159227055048L;
    public LinkedHashMap<String, List<FileWrapper>> fileParamsMap;
    public LinkedHashMap<String, List<String>> urlParamsMap;

    public HttpParams() {
        init();
    }

    private void init() {
        this.urlParamsMap = new LinkedHashMap<>();
        this.fileParamsMap = new LinkedHashMap<>();
    }

    public void a(HttpParams httpParams) {
        if (httpParams != null) {
            if (httpParams.urlParamsMap != null && !httpParams.urlParamsMap.isEmpty()) {
                this.urlParamsMap.putAll(httpParams.urlParamsMap);
            }
            if (httpParams.fileParamsMap == null || httpParams.fileParamsMap.isEmpty()) {
                return;
            }
            this.fileParamsMap.putAll(httpParams.fileParamsMap);
        }
    }

    public void a(Map<String, String> map, boolean... zArr) {
        if (map == null || map.isEmpty()) {
            return;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            a(entry.getKey(), entry.getValue(), zArr);
        }
    }

    public void a(String str, String str2, boolean... zArr) {
        if (zArr != null && zArr.length > 0) {
            put(str, str2, zArr[0]);
        } else {
            put(str, str2, true);
        }
    }

    public void a(String str, int i, boolean... zArr) {
        if (zArr != null && zArr.length > 0) {
            put(str, String.valueOf(i), zArr[0]);
        } else {
            put(str, String.valueOf(i), true);
        }
    }

    public void a(String str, float f, boolean... zArr) {
        if (zArr != null && zArr.length > 0) {
            put(str, String.valueOf(f), zArr[0]);
        } else {
            put(str, String.valueOf(f), true);
        }
    }

    public void a(String str, boolean z, boolean... zArr) {
        if (zArr != null && zArr.length > 0) {
            put(str, String.valueOf(z), zArr[0]);
        } else {
            put(str, String.valueOf(z), true);
        }
    }

    private void put(String str, String str2, boolean z) {
        if (str != null && str2 != null) {
            List<String> list = this.urlParamsMap.get(str);
            if (list == null) {
                list = new ArrayList<>();
                this.urlParamsMap.put(str, list);
            }
            if (z) {
                list.clear();
            }
            list.add(str2);
        }
    }

    public void a(String str, File file) {
        a(str, file, file.getName());
    }

    public void a(String str, File file, String str2) {
        a(str, file, str2, b.aS(str2));
    }

    public void a(String str, File file, String str2, MediaType mediaType) {
        if (str != null) {
            List<FileWrapper> list = this.fileParamsMap.get(str);
            if (list == null) {
                list = new ArrayList<>();
                this.fileParamsMap.put(str, list);
            }
            list.add(new FileWrapper(file, str2, mediaType));
        }
    }

    public void aN(String str) {
        this.urlParamsMap.remove(str);
    }

    public void removeFile(String str) {
        this.fileParamsMap.remove(str);
    }

    public void remove(String str) {
        aN(str);
        removeFile(str);
    }

    public void clear() {
        this.urlParamsMap.clear();
        this.fileParamsMap.clear();
    }

    /* loaded from: classes11.dex */
    public static class FileWrapper implements Serializable {
        private static final long serialVersionUID = -2356139899636767776L;
        public transient MediaType contentType;
        public File file;
        public String fileName;
        public long fileSize;

        public FileWrapper(File file, String str, MediaType mediaType) {
            this.file = file;
            this.fileName = str;
            this.contentType = mediaType;
            this.fileSize = file.length();
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.defaultWriteObject();
            objectOutputStream.writeObject(this.contentType.toString());
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            objectInputStream.defaultReadObject();
            this.contentType = MediaType.parse((String) objectInputStream.readObject());
        }

        public String toString() {
            return "FileWrapper{file=" + this.file + ", fileName=" + this.fileName + ", contentType=" + this.contentType + ", fileSize=" + this.fileSize + "}";
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : this.urlParamsMap.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
        }
        for (Map.Entry<String, List<FileWrapper>> entry2 : this.fileParamsMap.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(entry2.getKey());
            sb.append("=");
            sb.append(entry2.getValue());
        }
        return sb.toString();
    }
}
