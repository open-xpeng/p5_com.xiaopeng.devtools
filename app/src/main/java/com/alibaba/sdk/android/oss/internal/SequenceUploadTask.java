package com.alibaba.sdk.android.oss.internal;

import android.text.TextUtils;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.OSSSharedPreferences;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadRequest;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.PartETag;
import com.alibaba.sdk.android.oss.model.ResumableUploadRequest;
import com.alibaba.sdk.android.oss.model.ResumableUploadResult;
import com.alibaba.sdk.android.oss.network.ExecutionContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes11.dex */
public class SequenceUploadTask extends BaseMultipartUploadTask<ResumableUploadRequest, ResumableUploadResult> implements Callable<ResumableUploadResult> {
    private List<Integer> mAlreadyUploadIndex;
    private File mCRC64RecordFile;
    private long mFirstPartSize;
    private File mRecordFile;
    private OSSSharedPreferences mSp;

    public SequenceUploadTask(ResumableUploadRequest resumableUploadRequest, OSSCompletedCallback<ResumableUploadRequest, ResumableUploadResult> oSSCompletedCallback, ExecutionContext executionContext, InternalRequestOperation internalRequestOperation) {
        super(internalRequestOperation, resumableUploadRequest, oSSCompletedCallback, executionContext);
        this.mAlreadyUploadIndex = new ArrayList();
        this.mSp = OSSSharedPreferences.instance(this.mContext.getApplicationContext());
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0187 A[Catch: ServiceException -> 0x01f3, ClientException -> 0x01f5, TryCatch #0 {ClientException -> 0x01f5, blocks: (B:49:0x016e, B:50:0x017c, B:51:0x0181, B:53:0x0187, B:55:0x01a3, B:57:0x01a9, B:59:0x01b7, B:60:0x01cc, B:62:0x01e9), top: B:92:0x016e }] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x024b  */
    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void initMultipartUploadId() throws java.io.IOException, com.alibaba.sdk.android.oss.ClientException, com.alibaba.sdk.android.oss.ServiceException {
        /*
            Method dump skipped, instructions count: 671
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.oss.internal.SequenceUploadTask.initMultipartUploadId():void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    public ResumableUploadResult doMultipartUpload() throws IOException, ClientException, ServiceException, InterruptedException {
        long j = this.mUploadedLength;
        checkCancel();
        int i = this.mPartAttr[0];
        int i2 = this.mPartAttr[1];
        if (this.mPartETags.size() > 0 && this.mAlreadyUploadIndex.size() > 0) {
            if (this.mUploadedLength > this.mFileLength) {
                throw new ClientException("The uploading file is inconsistent with before");
            }
            if (this.mFirstPartSize != i) {
                throw new ClientException("The part size setting is inconsistent with before");
            }
            long j2 = this.mUploadedLength;
            if (!TextUtils.isEmpty(this.mSp.getStringValue(this.mUploadId))) {
                j2 = Long.valueOf(this.mSp.getStringValue(this.mUploadId)).longValue();
            }
            long j3 = j2;
            if (this.mProgressCallback != null) {
                this.mProgressCallback.onProgress(this.mRequest, j3, this.mFileLength);
            }
            this.mSp.removeKey(this.mUploadId);
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (this.mAlreadyUploadIndex.size() == 0 || !this.mAlreadyUploadIndex.contains(Integer.valueOf(i3 + 1))) {
                if (i3 == i2 - 1) {
                    i = (int) (this.mFileLength - j);
                }
                OSSLog.logDebug("upload part readByte : " + i);
                j += (long) i;
                uploadPart(i3, i, i2);
                if (this.mUploadException != null) {
                    break;
                }
            }
        }
        checkException();
        CompleteMultipartUploadResult completeMultipartUploadResult = completeMultipartUploadResult();
        ResumableUploadResult resumableUploadResult = null;
        if (completeMultipartUploadResult != null) {
            resumableUploadResult = new ResumableUploadResult(completeMultipartUploadResult);
        }
        if (this.mRecordFile != null) {
            this.mRecordFile.delete();
        }
        if (this.mCRC64RecordFile != null) {
            this.mCRC64RecordFile.delete();
        }
        return resumableUploadResult;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x01d4 A[Catch: IOException -> 0x01d0, TryCatch #6 {IOException -> 0x01d0, blocks: (B:97:0x01cc, B:101:0x01d4, B:103:0x01d9), top: B:110:0x01cc }] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x01d9 A[Catch: IOException -> 0x01d0, TRY_LEAVE, TryCatch #6 {IOException -> 0x01d0, blocks: (B:97:0x01cc, B:101:0x01d4, B:103:0x01d9), top: B:110:0x01cc }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01cc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:122:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:123:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x014b A[Catch: IOException -> 0x014f, TRY_ENTER, TryCatch #5 {IOException -> 0x014f, blocks: (B:37:0x00fd, B:39:0x0102, B:41:0x0107, B:67:0x014b, B:71:0x0153, B:73:0x0158, B:87:0x01b7, B:89:0x01bc, B:91:0x01c1), top: B:109:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0153 A[Catch: IOException -> 0x014f, TryCatch #5 {IOException -> 0x014f, blocks: (B:37:0x00fd, B:39:0x0102, B:41:0x0107, B:67:0x014b, B:71:0x0153, B:73:0x0158, B:87:0x01b7, B:89:0x01bc, B:91:0x01c1), top: B:109:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0158 A[Catch: IOException -> 0x014f, TRY_LEAVE, TryCatch #5 {IOException -> 0x014f, blocks: (B:37:0x00fd, B:39:0x0102, B:41:0x0107, B:67:0x014b, B:71:0x0153, B:73:0x0158, B:87:0x01b7, B:89:0x01bc, B:91:0x01c1), top: B:109:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x016e A[Catch: all -> 0x01c6, TryCatch #3 {all -> 0x01c6, blocks: (B:65:0x0146, B:79:0x0166, B:81:0x016e, B:82:0x0172, B:84:0x018c, B:85:0x01aa), top: B:108:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0172 A[Catch: all -> 0x01c6, TryCatch #3 {all -> 0x01c6, blocks: (B:65:0x0146, B:79:0x0166, B:81:0x016e, B:82:0x0172, B:84:0x018c, B:85:0x01aa), top: B:108:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01b7 A[Catch: IOException -> 0x014f, TRY_ENTER, TryCatch #5 {IOException -> 0x014f, blocks: (B:37:0x00fd, B:39:0x0102, B:41:0x0107, B:67:0x014b, B:71:0x0153, B:73:0x0158, B:87:0x01b7, B:89:0x01bc, B:91:0x01c1), top: B:109:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01bc A[Catch: IOException -> 0x014f, TryCatch #5 {IOException -> 0x014f, blocks: (B:37:0x00fd, B:39:0x0102, B:41:0x0107, B:67:0x014b, B:71:0x0153, B:73:0x0158, B:87:0x01b7, B:89:0x01bc, B:91:0x01c1), top: B:109:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01c1 A[Catch: IOException -> 0x014f, TRY_LEAVE, TryCatch #5 {IOException -> 0x014f, blocks: (B:37:0x00fd, B:39:0x0102, B:41:0x0107, B:67:0x014b, B:71:0x0153, B:73:0x0158, B:87:0x01b7, B:89:0x01bc, B:91:0x01c1), top: B:109:0x000a }] */
    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void uploadPart(int r16, int r17, int r18) {
        /*
            Method dump skipped, instructions count: 484
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.sdk.android.oss.internal.SequenceUploadTask.uploadPart(int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    public void checkException() throws IOException, ServiceException, ClientException {
        if (this.mContext.getCancellationHandler().isCancelled()) {
            if (((ResumableUploadRequest) this.mRequest).deleteUploadOnCancelling().booleanValue()) {
                abortThisUpload();
                if (this.mRecordFile != null) {
                    this.mRecordFile.delete();
                }
            } else if (this.mPartETags != null && this.mPartETags.size() > 0 && this.mCheckCRC64 && ((ResumableUploadRequest) this.mRequest).getRecordDirectory() != null) {
                HashMap hashMap = new HashMap();
                for (PartETag partETag : this.mPartETags) {
                    hashMap.put(Integer.valueOf(partETag.getPartNumber()), Long.valueOf(partETag.getCRC64()));
                }
                ObjectOutputStream objectOutputStream = null;
                try {
                    try {
                        this.mCRC64RecordFile = new File(((ResumableUploadRequest) this.mRequest).getRecordDirectory() + File.separator + this.mUploadId);
                        if (!this.mCRC64RecordFile.exists()) {
                            this.mCRC64RecordFile.createNewFile();
                        }
                        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream(this.mCRC64RecordFile));
                        try {
                            objectOutputStream2.writeObject(hashMap);
                            objectOutputStream2.close();
                        } catch (IOException e) {
                            e = e;
                            objectOutputStream = objectOutputStream2;
                            OSSLog.logThrowable2Local(e);
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }
                            super.checkException();
                        } catch (Throwable th) {
                            th = th;
                            objectOutputStream = objectOutputStream2;
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }
                            throw th;
                        }
                    } catch (IOException e2) {
                        e = e2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }
        super.checkException();
    }

    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    protected void abortThisUpload() {
        if (this.mUploadId != null) {
            this.mApiOperation.abortMultipartUpload(new AbortMultipartUploadRequest(((ResumableUploadRequest) this.mRequest).getBucketName(), ((ResumableUploadRequest) this.mRequest).getObjectKey(), this.mUploadId), null).waitUntilFinished();
        }
    }

    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    protected void processException(Exception exc) {
        if (this.mUploadException == null || !exc.getMessage().equals(this.mUploadException.getMessage())) {
            this.mUploadException = exc;
        }
        OSSLog.logThrowable2Local(exc);
        if (this.mContext.getCancellationHandler().isCancelled() && !this.mIsCancel) {
            this.mIsCancel = true;
        }
    }

    @Override // com.alibaba.sdk.android.oss.internal.BaseMultipartUploadTask
    protected void uploadPartFinish(PartETag partETag) throws Exception {
        if (this.mContext.getCancellationHandler().isCancelled() && !this.mSp.contains(this.mUploadId)) {
            this.mSp.setStringValue(this.mUploadId, String.valueOf(this.mUploadedLength));
            onProgressCallback(this.mRequest, this.mUploadedLength, this.mFileLength);
        }
    }
}
