package com.xiaopeng.lib.framework.netchannelmodule.remotestorage.oss;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.StorageException;
import com.xiaopeng.lib.framework.netchannelmodule.remotestorage.exception.StorageExceptionImpl;
import com.xiaopeng.lib.framework.netchannelmodule.remotestorage.statistic.StorageCounter;
import com.xiaopeng.lib.framework.netchannelmodule.remotestorage.token.Token;
import com.xiaopeng.lib.framework.netchannelmodule.remotestorage.token.TokenRetriever;
import com.xiaopeng.lib.utils.b;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes12.dex */
public class DownloadTask extends BaseOssTask {
    private final int SUCCESS_CODE;
    private final String TAG;
    private final int UNKNOWN_LENGTH;

    public DownloadTask(Bucket bucket) {
        super(bucket);
        this.TAG = "NetChannel-DownloadTask";
        this.SUCCESS_CODE = 0;
        this.UNKNOWN_LENGTH = -1;
    }

    @Override // com.xiaopeng.lib.framework.netchannelmodule.remotestorage.oss.BaseOssTask
    public DownloadTask build() throws StorageException {
        super.build();
        this.mLocalFileSize = 0L;
        return this;
    }

    @Override // com.xiaopeng.lib.framework.netchannelmodule.remotestorage.oss.BaseOssTask
    public void performRealTask() {
        TokenRetriever.getInstance().getTokenWithCallback(new FutureTaskCallback());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadFile(OSS oss) {
        oss.asyncGetObject(new GetObjectRequest(bucketRootName(), this.mRemoteObjectKey), new OSSCompletedCallback<GetObjectRequest, GetObjectResult>() { // from class: com.xiaopeng.lib.framework.netchannelmodule.remotestorage.oss.DownloadTask.1
            @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
            public void onSuccess(GetObjectRequest getObjectRequest, GetObjectResult getObjectResult) {
                int i;
                String str = "";
                try {
                    DownloadTask.this.createFileAsNeeded();
                    i = DownloadTask.this.writeContentToFile(getObjectResult);
                } catch (Exception e) {
                    i = 515;
                    str = "Failed to write file:" + DownloadTask.this.mLocalFilePath + ", \n" + e;
                    c.i("NetChannel-DownloadTask", str);
                }
                if (i == 0) {
                    c.e("Succeed to download file to :" + DownloadTask.this.mLocalFilePath + ", size:" + DownloadTask.this.mLocalFileSize);
                    DownloadTask.this.doSuccess();
                    StorageCounter.getInstance().increaseSucceedWithSize(DownloadTask.this.mLocalFileSize);
                    return;
                }
                b.bn(DownloadTask.this.mLocalFilePath);
                DownloadTask.this.doFailure(new StorageExceptionImpl(i, str));
            }

            @Override // com.alibaba.sdk.android.oss.callback.OSSCompletedCallback
            public void onFailure(GetObjectRequest getObjectRequest, ClientException clientException, ServiceException serviceException) {
                int i;
                String str = "clientException:" + clientException + " serviceException:" + serviceException;
                if (serviceException != null) {
                    i = serviceException.getStatusCode();
                } else {
                    i = StorageException.REASON_DOWNLOAD_ERROR;
                }
                DownloadTask.this.doFailure(new StorageExceptionImpl(i, str));
                StorageCounter.getInstance().increaseFailureWithCode(serviceException.getErrorCode(), 0L);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int writeContentToFile(GetObjectResult getObjectResult) {
        Closeable closeable;
        File file = new File(this.mLocalFilePath);
        Closeable closeable2 = null;
        int i = 0;
        try {
            byte[] bArr = new byte[4096];
            InputStream objectContent = getObjectResult.getObjectContent();
            try {
                closeable = new FileOutputStream(file, false);
                while (true) {
                    try {
                        int read = objectContent.read(bArr, 0, bArr.length);
                        if (read <= 0) {
                            break;
                        }
                        closeable.write(bArr, 0, read);
                        this.mLocalFileSize += read;
                    } catch (IOException e) {
                        e = e;
                        closeable2 = objectContent;
                        i = StorageException.REASON_DOWNLOAD_ERROR;
                        try {
                            c.i("NetChannel-DownloadTask", "Failed to download file:" + e);
                            closeStream(closeable2);
                            closeStream(closeable);
                            return getObjectResult.getContentLength() == -1 ? i : i;
                        } catch (Throwable th) {
                            th = th;
                            closeStream(closeable2);
                            closeStream(closeable);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        closeable2 = objectContent;
                        closeStream(closeable2);
                        closeStream(closeable);
                        throw th;
                    }
                }
                closeable.flush();
                closeStream(objectContent);
            } catch (IOException e2) {
                e = e2;
                closeable = null;
            } catch (Throwable th3) {
                th = th3;
                closeable = null;
            }
        } catch (IOException e3) {
            e = e3;
            closeable = null;
        } catch (Throwable th4) {
            th = th4;
            closeable = null;
        }
        closeStream(closeable);
        if (getObjectResult.getContentLength() == -1 && this.mLocalFileSize != getObjectResult.getContentLength()) {
            c.f("NetChannel-DownloadTask", "Not download completed. Expected:" + getObjectResult.getContentLength() + ", but actual:" + this.mLocalFileSize);
            return StorageException.REASON_DOWNLOAD_INCOMPLETE;
        }
    }

    private void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                c.i("NetChannel-DownloadTask", "Failed to close stream: " + e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createFileAsNeeded() throws IOException {
        File file = new File(this.mLocalFilePath);
        if (file.exists()) {
            b.bn(this.mLocalFilePath);
        }
        File parentFile = file.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            throw new IllegalStateException("Couldn't create dir: " + parentFile);
        }
        file.createNewFile();
    }

    /* loaded from: classes12.dex */
    private class FutureTaskCallback implements TokenRetriever.IRetrievingCallback {
        private FutureTaskCallback() {
        }

        @Override // com.xiaopeng.lib.framework.netchannelmodule.remotestorage.token.TokenRetriever.IRetrievingCallback
        public void onFailure(StorageException storageException) {
            DownloadTask.this.doFailure(storageException);
            StorageCounter.getInstance().increaseFailureWithCode(String.valueOf((int) StorageException.REASON_GET_TOKEN_ERROR), 0L);
        }

        @Override // com.xiaopeng.lib.framework.netchannelmodule.remotestorage.token.TokenRetriever.IRetrievingCallback
        public void onSuccess(Token token) {
            final OSS createOssClient = DownloadTask.this.createOssClient(token.accessKeyId(), token.acessKeySecret(), token.securityToken());
            j.d(new Runnable() { // from class: com.xiaopeng.lib.framework.netchannelmodule.remotestorage.oss.DownloadTask.FutureTaskCallback.1
                @Override // java.lang.Runnable
                public void run() {
                    DownloadTask.this.downloadFile(createOssClient);
                }
            });
        }
    }
}
