package com.xiaopeng.lib.framework.netchannelmodule.remotestorage.aws;

import android.text.TextUtils;
import com.alibaba.sdk.android.oss.common.OSSConstants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.StorageException;
import com.xiaopeng.lib.framework.netchannelmodule.NetworkChannelsEntry;
import com.xiaopeng.lib.framework.netchannelmodule.remotestorage.aws.UploadInfo;
import com.xiaopeng.lib.framework.netchannelmodule.remotestorage.exception.StorageExceptionImpl;
import com.xiaopeng.lib.framework.netchannelmodule.remotestorage.util.EncryptUtil;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import java.io.File;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class SimpleAwsUploadTask extends BaseAwsTask {
    private static final long BUFFER_SIZE = 1024;
    private static final String KEY_CALLBACK_BODY = "callbackBody";
    private static final String KEY_CALLBACK_URL = "callbackUrl";
    private static final String PRE_SIGN_URL = "https://fra-callback-api.xiaopeng.com/xmartFileData";
    private static final String TAG = "NetChannel-SimpleAwsUploadTask";
    private static final String XMART_OSSFILE_URL = "https://fra-bd-callback.xiaopeng.com/oss/xmartData/xmartOssFile";

    public SimpleAwsUploadTask(String str) {
        super(str);
    }

    @Override // com.xiaopeng.lib.framework.netchannelmodule.remotestorage.aws.BaseAwsTask
    public void performRealTask() {
        sendByIHttp(wrapBody());
    }

    private JsonObject wrapBody() {
        String str = this.mRemoteObjectKey;
        String valueOf = String.valueOf(System.currentTimeMillis());
        UploadInfo build = new UploadInfo.Builder().file(str).timestamp(valueOf).sign(createSign(str, valueOf)).build();
        UpdateUploadInfo(build);
        return wrapJsonBody(build);
    }

    private void UpdateUploadInfo(UploadInfo uploadInfo) {
        if (this.mCallbackParams == null || this.mCallbackParams.isEmpty() || this.mCallbackParams.get(KEY_CALLBACK_BODY) == null || !XMART_OSSFILE_URL.equals(this.mCallbackParams.get(KEY_CALLBACK_URL))) {
            return;
        }
        try {
            UploadInfo uploadInfo2 = (UploadInfo) new Gson().fromJson(this.mCallbackParams.get(KEY_CALLBACK_BODY), (Class<Object>) UploadInfo.class);
            if (!TextUtils.isEmpty(uploadInfo2.model)) {
                uploadInfo.app_id = uploadInfo2.app_id;
            }
            if (!TextUtils.isEmpty(uploadInfo2.model)) {
                uploadInfo.model = uploadInfo2.model;
            }
            if (!TextUtils.isEmpty(uploadInfo2.type)) {
                uploadInfo.type = uploadInfo2.type;
            }
            if (!TextUtils.isEmpty(uploadInfo2.v)) {
                uploadInfo.v = uploadInfo2.v;
            }
            if (uploadInfo.file != null && uploadInfo.file.startsWith("fra-xp-log/")) {
                uploadInfo.file = uploadInfo.file.substring(11);
            }
        } catch (Exception e) {
            c.b(TAG, e);
        }
    }

    private JsonObject wrapJsonBody(UploadInfo uploadInfo) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("app_id", uploadInfo.app_id);
        jsonObject.addProperty("file", uploadInfo.file);
        jsonObject.addProperty("model", uploadInfo.model);
        jsonObject.addProperty("type", uploadInfo.type);
        jsonObject.addProperty("v", uploadInfo.v);
        jsonObject.addProperty("timestamp", uploadInfo.timestamp);
        jsonObject.addProperty("sign", uploadInfo.sign);
        if (this.mCallbackParams != null) {
            jsonObject.addProperty("call_back", new JSONObject(this.mCallbackParams).toString());
        }
        return jsonObject;
    }

    private void sendByIHttp(JsonObject jsonObject) {
        ((IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class)).bizHelper().post(PRE_SIGN_URL, jsonObject.toString()).build().execute(new Callback() { // from class: com.xiaopeng.lib.framework.netchannelmodule.remotestorage.aws.SimpleAwsUploadTask.1
            @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
            public void onSuccess(IResponse iResponse) {
                try {
                    String string = new JSONObject(iResponse.body()).getString("url");
                    if (string != null && string.length() > 0) {
                        SimpleAwsUploadTask.this.executeUploadTask(string.replace("https://", "http://"));
                    }
                } catch (Exception e) {
                    SimpleAwsUploadTask.this.doFailure(new StorageExceptionImpl(StorageException.REASON_GET_TOKEN_ERROR, e.getMessage()));
                }
            }

            @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
            public void onFailure(IResponse iResponse) {
                SimpleAwsUploadTask.this.doFailure(new StorageExceptionImpl(StorageException.REASON_GET_TOKEN_ERROR, iResponse.getException().getMessage()));
            }
        });
    }

    private String createSign(String str, String str2) {
        return EncryptUtil.MD5("XP-Appid" + str2 + ("file" + str + "type" + UploadInfo.UPLOAD_TYPE + "v2") + "B638C588DCAD7C1A43E6FB").toLowerCase();
    }

    @Override // com.xiaopeng.lib.framework.netchannelmodule.remotestorage.aws.BaseAwsTask
    public SimpleAwsUploadTask build() throws StorageException {
        super.build();
        File file = new File(this.mLocalFilePath);
        if (!file.exists()) {
            throw new StorageExceptionImpl(513);
        }
        this.mLocalFileSize = file.length();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeUploadTask(final String str) {
        j.d(new Runnable() { // from class: com.xiaopeng.lib.framework.netchannelmodule.remotestorage.aws.SimpleAwsUploadTask.2
            @Override // java.lang.Runnable
            public void run() {
                SimpleAwsUploadTask.this.upload2AwsByNormal(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upload2AwsByNormal(String str) {
        try {
            createAwsUploadClient().newCall(new Request.Builder().url(str).put(createProgressRequestBody(MediaType.parse(OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE), new File(this.mLocalFilePath))).build()).execute();
            doSuccess();
        } catch (Exception e) {
            c.c(TAG, "upload error!", e);
            doFailure(new StorageExceptionImpl(1025, e.getMessage()));
        }
    }

    public RequestBody createProgressRequestBody(final MediaType mediaType, final File file) {
        return new RequestBody() { // from class: com.xiaopeng.lib.framework.netchannelmodule.remotestorage.aws.SimpleAwsUploadTask.3
            @Override // okhttp3.RequestBody
            public MediaType contentType() {
                return mediaType;
            }

            @Override // okhttp3.RequestBody
            public long contentLength() {
                return file.length();
            }

            @Override // okhttp3.RequestBody
            public void writeTo(BufferedSink bufferedSink) throws IOException {
                Source source = Okio.source(file);
                Buffer buffer = new Buffer();
                while (true) {
                    long read = source.read(buffer, 1024L);
                    if (read != -1) {
                        bufferedSink.write(buffer, read);
                    } else {
                        return;
                    }
                }
            }
        };
    }
}
