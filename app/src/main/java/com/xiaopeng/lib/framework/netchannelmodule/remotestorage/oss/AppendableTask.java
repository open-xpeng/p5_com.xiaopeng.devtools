package com.xiaopeng.lib.framework.netchannelmodule.remotestorage.oss;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.alibaba.sdk.android.oss.model.AppendObjectRequest;
import com.alibaba.sdk.android.oss.model.AppendObjectResult;
import com.alibaba.sdk.android.oss.model.HeadObjectRequest;
import com.alibaba.sdk.android.oss.model.HeadObjectResult;
import com.xiaopeng.datalog.a;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IDataLog;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.StorageException;
import com.xiaopeng.lib.framework.netchannelmodule.common.GlobalConfig;
import com.xiaopeng.lib.framework.netchannelmodule.remotestorage.exception.StorageExceptionImpl;
import com.xiaopeng.lib.framework.netchannelmodule.remotestorage.statistic.StorageCounter;
import com.xiaopeng.lib.framework.netchannelmodule.remotestorage.token.Token;
import com.xiaopeng.lib.framework.netchannelmodule.remotestorage.token.TokenRetriever;
import com.xiaopeng.lib.utils.c.b;
import com.xiaopeng.lib.utils.c.c;
import com.xiaopeng.lib.utils.j;

/* loaded from: classes12.dex */
public class AppendableTask extends BaseOssTask {
    private static int STATUS_OK = 200;
    private static final String TAG = "NetChannel-AppendableTask";
    private byte[] mUploadContent;

    public AppendableTask(Bucket bucket) {
        super(bucket);
    }

    @Override // com.xiaopeng.lib.framework.netchannelmodule.remotestorage.oss.BaseOssTask
    public void performRealTask() {
        TokenRetriever.getInstance().getTokenWithCallback(new FutureTaskCallback());
    }

    public AppendableTask append(byte[] bArr) throws StorageException {
        if (bArr == null || bArr.length == 0) {
            throw new StorageExceptionImpl(4);
        }
        this.mUploadContent = bArr;
        return this;
    }

    @Override // com.xiaopeng.lib.framework.netchannelmodule.remotestorage.oss.BaseOssTask
    public BaseOssTask build() throws StorageException {
        if (this.mUploadContent == null || this.mUploadContent.length == 0) {
            throw new StorageExceptionImpl(4);
        }
        return super.build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeAppendTask(@NonNull Token token) {
        final OSS createOssClient = createOssClient(token.accessKeyId(), token.acessKeySecret(), token.securityToken());
        j.d(new Runnable() { // from class: com.xiaopeng.lib.framework.netchannelmodule.remotestorage.oss.AppendableTask.1
            @Override // java.lang.Runnable
            public void run() {
                AppendableTask.this.appendToOssObject(createOssClient);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void appendToOssObject(OSS oss) {
        if (b.pn() && Thread.currentThread() == Looper.getMainLooper().getThread()) {
            throw new RuntimeException("Not allow to run in main thread.");
        }
        try {
            long tryToGetExistingObjectLength = tryToGetExistingObjectLength(oss);
            AppendObjectRequest appendObjectRequest = new AppendObjectRequest(bucketRootName(), this.mRemoteObjectKey, this.mUploadContent);
            appendObjectRequest.setPosition(tryToGetExistingObjectLength);
            AppendObjectResult appendObject = oss.appendObject(appendObjectRequest);
            if (STATUS_OK == appendObject.getStatusCode()) {
                if (!c.pq()) {
                    IDataLog iDataLog = (IDataLog) Module.get(a.class).get(IDataLog.class);
                    iDataLog.sendStatData(iDataLog.buildStat().setEventName(GlobalConfig.EVENT_NAME_SUCCESS).setProperty("pack", GlobalConfig.getApplicationSimpleName()).setProperty("method", RequestParameters.SUBRESOURCE_APPEND).setProperty("localSize", Integer.valueOf(this.mUploadContent.length)).setProperty("uploadPath", this.mRemoteObjectKey).setProperty("requestId", appendObject.getRequestId()).build());
                }
                doSuccess();
                StorageCounter.getInstance().increaseSucceedWithSize(this.mUploadContent.length);
                return;
            }
            String valueOf = String.valueOf(appendObject.getStatusCode());
            if (!c.pq()) {
                IDataLog iDataLog2 = (IDataLog) Module.get(a.class).get(IDataLog.class);
                iDataLog2.sendStatData(iDataLog2.buildStat().setEventName(GlobalConfig.EVENT_NAME_FAIL).setProperty("pack", GlobalConfig.getApplicationSimpleName()).setProperty("method", RequestParameters.SUBRESOURCE_APPEND).setProperty("localSize", Integer.valueOf(this.mUploadContent.length)).setProperty("uploadPath", this.mRemoteObjectKey).setProperty("failReason", valueOf).setProperty(RequestParameters.UPLOAD_ID, (String) null).build());
            }
            com.xiaopeng.lib.utils.c.a(TAG, "append error!", valueOf);
            doFailure(new StorageExceptionImpl(1025, valueOf));
            StorageCounter.getInstance().increaseFailureWithCode(valueOf, 0L);
        } catch (Exception e) {
            if (!StorageCounter.isInternationVersion()) {
                IDataLog iDataLog3 = (IDataLog) Module.get(a.class).get(IDataLog.class);
                iDataLog3.sendStatData(iDataLog3.buildStat().setEventName(GlobalConfig.EVENT_NAME_FAIL).setProperty("pack", GlobalConfig.getApplicationSimpleName()).setProperty("method", RequestParameters.SUBRESOURCE_APPEND).setProperty("localPath", this.mLocalFilePath).setProperty("localSize", Integer.valueOf(this.mUploadContent.length)).setProperty("uploadPath", this.mRemoteObjectKey).setProperty("failReason", e.toString()).setProperty(RequestParameters.UPLOAD_ID, (String) null).build());
            }
            com.xiaopeng.lib.utils.c.c(TAG, "append error!", e);
            doFailure(new StorageExceptionImpl(1025, e.toString()));
            StorageCounter.getInstance().increaseFailureWithCode(e.getMessage(), 0L);
        }
    }

    private long tryToGetExistingObjectLength(OSS oss) {
        try {
            HeadObjectResult headObject = oss.headObject(new HeadObjectRequest(bucketRootName(), this.mRemoteObjectKey));
            if (STATUS_OK != headObject.getStatusCode()) {
                return 0L;
            }
            return headObject.getMetadata().getContentLength();
        } catch (Exception e) {
            return 0L;
        }
    }

    /* loaded from: classes12.dex */
    private class FutureTaskCallback implements TokenRetriever.IRetrievingCallback {
        private FutureTaskCallback() {
        }

        @Override // com.xiaopeng.lib.framework.netchannelmodule.remotestorage.token.TokenRetriever.IRetrievingCallback
        public void onFailure(StorageException storageException) {
            AppendableTask.this.doFailure(storageException);
            StorageCounter.getInstance().increaseFailureWithCode(String.valueOf((int) StorageException.REASON_GET_TOKEN_ERROR), 0L);
        }

        @Override // com.xiaopeng.lib.framework.netchannelmodule.remotestorage.token.TokenRetriever.IRetrievingCallback
        public void onSuccess(@NonNull Token token) {
            AppendableTask.this.executeAppendTask(token);
        }
    }
}
