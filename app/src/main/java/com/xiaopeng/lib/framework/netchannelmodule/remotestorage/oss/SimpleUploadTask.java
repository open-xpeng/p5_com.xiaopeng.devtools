package com.xiaopeng.lib.framework.netchannelmodule.remotestorage.oss;

import android.support.annotation.NonNull;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.alibaba.sdk.android.oss.common.utils.IOUtils;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.PartETag;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.alibaba.sdk.android.oss.model.UploadPartRequest;
import com.xiaopeng.datalog.a;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.datalogmodule.IDataLog;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.StorageException;
import com.xiaopeng.lib.framework.netchannelmodule.common.GlobalConfig;
import com.xiaopeng.lib.framework.netchannelmodule.remotestorage.exception.StorageExceptionImpl;
import com.xiaopeng.lib.framework.netchannelmodule.remotestorage.statistic.StorageCounter;
import com.xiaopeng.lib.framework.netchannelmodule.remotestorage.token.Token;
import com.xiaopeng.lib.framework.netchannelmodule.remotestorage.token.TokenRetriever;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/* loaded from: classes12.dex */
public class SimpleUploadTask extends BaseOssTask {
    private static final int EACH_PARTITION_SIZE = 5242880;
    private static final String TAG = "NetChannel-BaseOssTask-SimpleUploadTask";

    public SimpleUploadTask(Bucket bucket) {
        super(bucket);
    }

    @Override // com.xiaopeng.lib.framework.netchannelmodule.remotestorage.oss.BaseOssTask
    public void performRealTask() {
        TokenRetriever.getInstance().getTokenWithCallback(new FutureTaskCallback());
    }

    @Override // com.xiaopeng.lib.framework.netchannelmodule.remotestorage.oss.BaseOssTask
    public SimpleUploadTask build() throws StorageException {
        super.build();
        File file = new File(this.mLocalFilePath);
        if (!file.exists()) {
            throw new StorageExceptionImpl(513);
        }
        this.mLocalFileSize = file.length();
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeUploadTask(@NonNull Token token) throws StorageException {
        final OSS createOssClient = createOssClient(token.accessKeyId(), token.acessKeySecret(), token.securityToken());
        j.d(new Runnable() { // from class: com.xiaopeng.lib.framework.netchannelmodule.remotestorage.oss.SimpleUploadTask.1
            @Override // java.lang.Runnable
            public void run() {
                if (SimpleUploadTask.this.mLocalFileSize > SimpleUploadTask.this.bucketMaxObjectSize()) {
                    SimpleUploadTask.this.upload2OssByPartition(createOssClient);
                } else {
                    SimpleUploadTask.this.upload2OSSByNormal(createOssClient);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upload2OSSByNormal(OSS oss) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketRootName(), this.mRemoteObjectKey, this.mLocalFilePath);
        if (this.mCallbackParams != null) {
            putObjectRequest.setCallbackParam(this.mCallbackParams);
        }
        oss.asyncPutObject(putObjectRequest, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() { // from class: com.xiaopeng.lib.framework.netchannelmodule.remotestorage.oss.SimpleUploadTask.2
            @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
            public void onSuccess(PutObjectRequest putObjectRequest2, PutObjectResult putObjectResult) {
                if (!StorageCounter.isInternationVersion()) {
                    IDataLog iDataLog = (IDataLog) Module.get(a.class).get(IDataLog.class);
                    iDataLog.sendStatData(iDataLog.buildMoleEvent().setEvent(GlobalConfig.EVENT_NAME_SUCCESS).setPageId(GlobalConfig.MOLE_PAGE_ID).setButtonId(GlobalConfig.MOLE_OSS_SUCCEED_BUTTON_ID).setProperty("pack", GlobalConfig.getApplicationSimpleName()).setProperty("method", "normal").setProperty("localPath", SimpleUploadTask.this.mLocalFilePath).setProperty("localSize", Long.valueOf(SimpleUploadTask.this.mLocalFileSize)).setProperty("uploadPath", SimpleUploadTask.this.mRemoteObjectKey).build());
                }
                SimpleUploadTask.this.doSuccess();
                StorageCounter.getInstance().increaseSucceedWithSize(SimpleUploadTask.this.mLocalFileSize);
            }

            @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
            public void onFailure(PutObjectRequest putObjectRequest2, ClientException clientException, ServiceException serviceException) {
                String str = "clientException:" + clientException + " serviceException:" + serviceException;
                if (!StorageCounter.isInternationVersion()) {
                    IDataLog iDataLog = (IDataLog) Module.get(a.class).get(IDataLog.class);
                    iDataLog.sendStatData(iDataLog.buildMoleEvent().setEvent(GlobalConfig.EVENT_NAME_FAIL).setPageId(GlobalConfig.MOLE_PAGE_ID).setButtonId(GlobalConfig.MOLE_OSS_FAILED_BUTTON_ID).setProperty("pack", GlobalConfig.getApplicationSimpleName()).setProperty("method", "normal").setProperty("localPath", SimpleUploadTask.this.mLocalFilePath).setProperty("localSize", Long.valueOf(SimpleUploadTask.this.mLocalFileSize)).setProperty("uploadPath", SimpleUploadTask.this.mRemoteObjectKey).setProperty("failReason", str).build());
                }
                if (clientException != null) {
                    SimpleUploadTask.this.doFailure(new StorageExceptionImpl(1025, str));
                    StorageCounter.getInstance().increaseFailureWithCode(String.valueOf(1025), 0L);
                } else if (serviceException != null) {
                    SimpleUploadTask.this.doFailure(new StorageExceptionImpl(serviceException.getStatusCode(), str));
                    StorageCounter.getInstance().increaseFailureWithCode(serviceException.getErrorCode(), 0L);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upload2OssByPartition(OSS oss) {
        String str;
        FileInputStream fileInputStream;
        try {
            InitiateMultipartUploadResult initMultipartUpload = oss.initMultipartUpload(new InitiateMultipartUploadRequest(bucketRootName(), this.mRemoteObjectKey));
            str = initMultipartUpload.getUploadId();
            long j = 5242880;
            try {
                fileInputStream = new FileInputStream(this.mLocalFilePath);
                try {
                    long j2 = this.mLocalFileSize;
                    ArrayList arrayList = new ArrayList();
                    int i = 1;
                    long j3 = 0;
                    while (j3 < j2) {
                        int min = (int) Math.min(j, j2 - j3);
                        byte[] readStreamAsBytesArray = IOUtils.readStreamAsBytesArray(fileInputStream, min);
                        UploadPartRequest uploadPartRequest = new UploadPartRequest(bucketRootName(), this.mRemoteObjectKey, str, i);
                        uploadPartRequest.setPartContent(readStreamAsBytesArray);
                        arrayList.add(new PartETag(i, oss.uploadPart(uploadPartRequest).getETag()));
                        j3 += min;
                        i++;
                        j = 5242880;
                    }
                    CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(bucketRootName(), this.mRemoteObjectKey, str, arrayList);
                    if (this.mCallbackParams != null) {
                        completeMultipartUploadRequest.setCallbackParam(this.mCallbackParams);
                    }
                    CompleteMultipartUploadResult completeMultipartUpload = oss.completeMultipartUpload(completeMultipartUploadRequest);
                    if (completeMultipartUpload != null && completeMultipartUpload.getLocation() != null) {
                        c.f("multipartUploadWithServerCallback with location:", completeMultipartUpload.getLocation());
                    }
                    closeFileStream(fileInputStream);
                    if (!StorageCounter.isInternationVersion()) {
                        IDataLog iDataLog = (IDataLog) Module.get(a.class).get(IDataLog.class);
                        iDataLog.sendStatData(iDataLog.buildMoleEvent().setEvent(GlobalConfig.EVENT_NAME_SUCCESS).setPageId(GlobalConfig.MOLE_PAGE_ID).setButtonId(GlobalConfig.MOLE_OSS_SUCCEED_BUTTON_ID).setProperty("pack", GlobalConfig.getApplicationSimpleName()).setProperty("method", "partition").setProperty("localPath", this.mLocalFilePath).setProperty("localSize", Long.valueOf(this.mLocalFileSize)).setProperty("uploadPath", this.mRemoteObjectKey).setProperty(RequestParameters.UPLOAD_ID, initMultipartUpload.getUploadId()).setProperty("requestId", initMultipartUpload.getRequestId()).build());
                    }
                    doSuccess();
                    StorageCounter.getInstance().increaseSucceedWithSize(this.mLocalFileSize);
                } catch (Exception e) {
                    e = e;
                    closeFileStream(fileInputStream);
                    if (!StorageCounter.isInternationVersion()) {
                        IDataLog iDataLog2 = (IDataLog) Module.get(a.class).get(IDataLog.class);
                        iDataLog2.sendStatData(iDataLog2.buildMoleEvent().setEvent(GlobalConfig.EVENT_NAME_FAIL).setPageId(GlobalConfig.MOLE_PAGE_ID).setButtonId(GlobalConfig.MOLE_OSS_FAILED_BUTTON_ID).setProperty("pack", GlobalConfig.getApplicationSimpleName()).setProperty("method", "partition").setProperty("localPath", this.mLocalFilePath).setProperty("localSize", Long.valueOf(this.mLocalFileSize)).setProperty("uploadPath", this.mRemoteObjectKey).setProperty("failReason", e.toString()).setProperty(RequestParameters.UPLOAD_ID, str).build());
                    }
                    c.c(TAG, "upload error!", e);
                    doFailure(new StorageExceptionImpl(1025, e.toString()));
                    StorageCounter.getInstance().increaseFailureWithCode(e.getMessage(), 0L);
                }
            } catch (Exception e2) {
                e = e2;
                fileInputStream = null;
            }
        } catch (Exception e3) {
            e = e3;
            str = null;
            fileInputStream = null;
        }
    }

    private void closeFileStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                c.i(TAG, "Failed to close file: " + e);
            }
        }
    }

    /* loaded from: classes12.dex */
    private class FutureTaskCallback implements TokenRetriever.IRetrievingCallback {
        private FutureTaskCallback() {
        }

        @Override // com.xiaopeng.lib.framework.netchannelmodule.remotestorage.token.TokenRetriever.IRetrievingCallback
        public void onFailure(StorageException storageException) {
            if (!StorageCounter.isInternationVersion()) {
                IDataLog iDataLog = (IDataLog) Module.get(a.class).get(IDataLog.class);
                iDataLog.sendStatData(iDataLog.buildMoleEvent().setEvent(GlobalConfig.EVENT_NAME_FAIL).setPageId(GlobalConfig.MOLE_PAGE_ID).setButtonId(GlobalConfig.MOLE_OSS_FAILED_BUTTON_ID).setProperty("pack", GlobalConfig.getApplicationSimpleName()).setProperty("method", "token").setProperty("localPath", SimpleUploadTask.this.mLocalFilePath).setProperty("localSize", Long.valueOf(SimpleUploadTask.this.mLocalFileSize)).setProperty("uploadPath", SimpleUploadTask.this.mRemoteObjectKey).setProperty("failReason", storageException.toString()).build());
            }
            SimpleUploadTask.this.doFailure(storageException);
            StorageCounter.getInstance().increaseFailureWithCode(String.valueOf((int) StorageException.REASON_GET_TOKEN_ERROR), 0L);
        }

        @Override // com.xiaopeng.lib.framework.netchannelmodule.remotestorage.token.TokenRetriever.IRetrievingCallback
        public void onSuccess(@NonNull Token token) {
            try {
                SimpleUploadTask.this.executeUploadTask(token);
            } catch (StorageException e) {
                if (!StorageCounter.isInternationVersion()) {
                    IDataLog iDataLog = (IDataLog) Module.get(a.class).get(IDataLog.class);
                    iDataLog.sendStatData(iDataLog.buildMoleEvent().setEvent(GlobalConfig.EVENT_NAME_FAIL).setPageId(GlobalConfig.MOLE_PAGE_ID).setButtonId(GlobalConfig.MOLE_OSS_FAILED_BUTTON_ID).setProperty("pack", GlobalConfig.getApplicationSimpleName()).setProperty("method", "token").setProperty("localPath", SimpleUploadTask.this.mLocalFilePath).setProperty("localSize", Long.valueOf(SimpleUploadTask.this.mLocalFileSize)).setProperty("uploadPath", SimpleUploadTask.this.mRemoteObjectKey).setProperty("failReason", e.toString()).build());
                }
                SimpleUploadTask.this.doFailure(e);
                StorageCounter.getInstance().increaseFailureWithCode(String.valueOf(e.getReasonCode()), 0L);
            }
        }
    }
}
