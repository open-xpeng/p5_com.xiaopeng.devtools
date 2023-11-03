package com.alibaba.sdk.android.oss.internal;

import android.text.TextUtils;
import android.util.Xml;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.OSSHeaders;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.CRC64;
import com.alibaba.sdk.android.oss.common.utils.DateUtil;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.alibaba.sdk.android.oss.common.utils.OSSUtils;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.AppendObjectResult;
import com.alibaba.sdk.android.oss.model.BucketLifecycleRule;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.CopyObjectResult;
import com.alibaba.sdk.android.oss.model.CreateBucketRequest;
import com.alibaba.sdk.android.oss.model.CreateBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteBucketLifecycleResult;
import com.alibaba.sdk.android.oss.model.DeleteBucketLoggingResult;
import com.alibaba.sdk.android.oss.model.DeleteBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteMultipleObjectResult;
import com.alibaba.sdk.android.oss.model.DeleteObjectResult;
import com.alibaba.sdk.android.oss.model.GetBucketACLResult;
import com.alibaba.sdk.android.oss.model.GetBucketInfoResult;
import com.alibaba.sdk.android.oss.model.GetBucketLifecycleResult;
import com.alibaba.sdk.android.oss.model.GetBucketLoggingResult;
import com.alibaba.sdk.android.oss.model.GetBucketRefererResult;
import com.alibaba.sdk.android.oss.model.GetObjectACLResult;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.GetSymlinkResult;
import com.alibaba.sdk.android.oss.model.HeadObjectResult;
import com.alibaba.sdk.android.oss.model.ImagePersistResult;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.ListBucketsResult;
import com.alibaba.sdk.android.oss.model.ListMultipartUploadsResult;
import com.alibaba.sdk.android.oss.model.ListObjectsResult;
import com.alibaba.sdk.android.oss.model.ListPartsResult;
import com.alibaba.sdk.android.oss.model.OSSBucketSummary;
import com.alibaba.sdk.android.oss.model.OSSObjectSummary;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.Owner;
import com.alibaba.sdk.android.oss.model.PartSummary;
import com.alibaba.sdk.android.oss.model.PutBucketLifecycleResult;
import com.alibaba.sdk.android.oss.model.PutBucketLoggingResult;
import com.alibaba.sdk.android.oss.model.PutBucketRefererResult;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.alibaba.sdk.android.oss.model.PutSymlinkResult;
import com.alibaba.sdk.android.oss.model.RestoreObjectResult;
import com.alibaba.sdk.android.oss.model.TriggerCallbackResult;
import com.alibaba.sdk.android.oss.model.UploadPartResult;
import com.xiaopeng.lib.apirouter.ClientConstants;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes11.dex */
public final class ResponseParsers {
    /* JADX INFO: Access modifiers changed from: private */
    public static CopyObjectResult parseCopyObjectResponseXML(InputStream inputStream, CopyObjectResult copyObjectResult) throws Exception {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "utf-8");
        int eventType = newPullParser.getEventType();
        while (eventType != 1) {
            if (eventType == 2) {
                String name = newPullParser.getName();
                if ("LastModified".equals(name)) {
                    copyObjectResult.setLastModified(DateUtil.parseIso8601Date(newPullParser.nextText()));
                } else if (HttpHeaders.ETAG.equals(name)) {
                    copyObjectResult.setEtag(newPullParser.nextText());
                }
            }
            eventType = newPullParser.next();
            if (eventType == 4) {
                eventType = newPullParser.next();
            }
        }
        return copyObjectResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ListPartsResult parseListPartsResponseXML(InputStream inputStream, ListPartsResult listPartsResult) throws Exception {
        ArrayList arrayList = new ArrayList();
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "utf-8");
        int eventType = newPullParser.getEventType();
        PartSummary partSummary = null;
        while (eventType != 1) {
            switch (eventType) {
                case 2:
                    String name = newPullParser.getName();
                    if ("Bucket".equals(name)) {
                        listPartsResult.setBucketName(newPullParser.nextText());
                        break;
                    } else if ("Key".equals(name)) {
                        listPartsResult.setKey(newPullParser.nextText());
                        break;
                    } else if ("UploadId".equals(name)) {
                        listPartsResult.setUploadId(newPullParser.nextText());
                        break;
                    } else if (!"PartNumberMarker".equals(name)) {
                        if (!"NextPartNumberMarker".equals(name)) {
                            if (!"MaxParts".equals(name)) {
                                if (!"IsTruncated".equals(name)) {
                                    if (CreateBucketRequest.TAB_STORAGECLASS.equals(name)) {
                                        listPartsResult.setStorageClass(newPullParser.nextText());
                                        break;
                                    } else if ("Part".equals(name)) {
                                        partSummary = new PartSummary();
                                        break;
                                    } else if (!"PartNumber".equals(name)) {
                                        if ("LastModified".equals(name)) {
                                            partSummary.setLastModified(DateUtil.parseIso8601Date(newPullParser.nextText()));
                                            break;
                                        } else if (HttpHeaders.ETAG.equals(name)) {
                                            partSummary.setETag(newPullParser.nextText());
                                            break;
                                        } else if ("Size".equals(name)) {
                                            String nextText = newPullParser.nextText();
                                            if (!OSSUtils.isEmptyString(nextText)) {
                                                partSummary.setSize(Long.valueOf(nextText).longValue());
                                                break;
                                            }
                                        }
                                    } else {
                                        String nextText2 = newPullParser.nextText();
                                        if (!OSSUtils.isEmptyString(nextText2)) {
                                            partSummary.setPartNumber(Integer.valueOf(nextText2).intValue());
                                            break;
                                        }
                                    }
                                } else {
                                    String nextText3 = newPullParser.nextText();
                                    if (!OSSUtils.isEmptyString(nextText3)) {
                                        listPartsResult.setTruncated(Boolean.valueOf(nextText3).booleanValue());
                                        break;
                                    }
                                }
                            } else {
                                String nextText4 = newPullParser.nextText();
                                if (!OSSUtils.isEmptyString(nextText4)) {
                                    listPartsResult.setMaxParts(Integer.parseInt(nextText4));
                                    break;
                                }
                            }
                        } else {
                            String nextText5 = newPullParser.nextText();
                            if (!OSSUtils.isEmptyString(nextText5)) {
                                listPartsResult.setNextPartNumberMarker(Integer.parseInt(nextText5));
                                break;
                            }
                        }
                    } else {
                        String nextText6 = newPullParser.nextText();
                        if (!OSSUtils.isEmptyString(nextText6)) {
                            listPartsResult.setPartNumberMarker(Integer.parseInt(nextText6));
                            break;
                        }
                    }
                    break;
                case 3:
                    if ("Part".equals(newPullParser.getName())) {
                        arrayList.add(partSummary);
                        break;
                    }
                    break;
            }
            eventType = newPullParser.next();
            if (eventType == 4) {
                eventType = newPullParser.next();
            }
        }
        if (arrayList.size() > 0) {
            listPartsResult.setParts(arrayList);
        }
        return listPartsResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CompleteMultipartUploadResult parseCompleteMultipartUploadResponseXML(InputStream inputStream, CompleteMultipartUploadResult completeMultipartUploadResult) throws Exception {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "utf-8");
        int eventType = newPullParser.getEventType();
        while (eventType != 1) {
            if (eventType == 2) {
                String name = newPullParser.getName();
                if (HttpHeaders.LOCATION.equals(name)) {
                    completeMultipartUploadResult.setLocation(newPullParser.nextText());
                } else if ("Bucket".equals(name)) {
                    completeMultipartUploadResult.setBucketName(newPullParser.nextText());
                } else if ("Key".equals(name)) {
                    completeMultipartUploadResult.setObjectKey(newPullParser.nextText());
                } else if (HttpHeaders.ETAG.equals(name)) {
                    completeMultipartUploadResult.setETag(newPullParser.nextText());
                }
            }
            eventType = newPullParser.next();
            if (eventType == 4) {
                eventType = newPullParser.next();
            }
        }
        return completeMultipartUploadResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static InitiateMultipartUploadResult parseInitMultipartResponseXML(InputStream inputStream, InitiateMultipartUploadResult initiateMultipartUploadResult) throws Exception {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "utf-8");
        int eventType = newPullParser.getEventType();
        while (eventType != 1) {
            if (eventType == 2) {
                String name = newPullParser.getName();
                if ("Bucket".equals(name)) {
                    initiateMultipartUploadResult.setBucketName(newPullParser.nextText());
                } else if ("Key".equals(name)) {
                    initiateMultipartUploadResult.setObjectKey(newPullParser.nextText());
                } else if ("UploadId".equals(name)) {
                    initiateMultipartUploadResult.setUploadId(newPullParser.nextText());
                }
            }
            eventType = newPullParser.next();
            if (eventType == 4) {
                eventType = newPullParser.next();
            }
        }
        return initiateMultipartUploadResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static GetObjectACLResult parseGetObjectACLResponse(InputStream inputStream, GetObjectACLResult getObjectACLResult) throws Exception {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "utf-8");
        int eventType = newPullParser.getEventType();
        while (eventType != 1) {
            if (eventType == 2) {
                String name = newPullParser.getName();
                if ("Grant".equals(name)) {
                    getObjectACLResult.setObjectACL(newPullParser.nextText());
                } else if (ClientConstants.TRANSACT.ID.equals(name)) {
                    getObjectACLResult.setObjectOwnerID(newPullParser.nextText());
                } else if ("DisplayName".equals(name)) {
                    getObjectACLResult.setObjectOwner(newPullParser.nextText());
                }
            }
            eventType = newPullParser.next();
            if (eventType == 4) {
                eventType = newPullParser.next();
            }
        }
        return getObjectACLResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static GetBucketInfoResult parseGetBucketInfoResponse(InputStream inputStream, GetBucketInfoResult getBucketInfoResult) throws Exception {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "utf-8");
        int eventType = newPullParser.getEventType();
        OSSBucketSummary oSSBucketSummary = null;
        Owner owner = null;
        while (eventType != 1) {
            switch (eventType) {
                case 2:
                    String name = newPullParser.getName();
                    if (name != null) {
                        if ("Owner".equals(name)) {
                            owner = new Owner();
                            break;
                        } else if (ClientConstants.TRANSACT.ID.equals(name)) {
                            if (owner != null) {
                                owner.setId(newPullParser.nextText());
                                break;
                            }
                        } else if ("DisplayName".equals(name)) {
                            if (owner != null) {
                                owner.setDisplayName(newPullParser.nextText());
                                break;
                            }
                        } else if ("Bucket".equals(name)) {
                            oSSBucketSummary = new OSSBucketSummary();
                            break;
                        } else if ("CreationDate".equals(name)) {
                            if (oSSBucketSummary != null) {
                                oSSBucketSummary.createDate = DateUtil.parseIso8601Date(newPullParser.nextText());
                                break;
                            }
                        } else if ("ExtranetEndpoint".equals(name)) {
                            if (oSSBucketSummary != null) {
                                oSSBucketSummary.extranetEndpoint = newPullParser.nextText();
                                break;
                            }
                        } else if ("IntranetEndpoint".equals(name)) {
                            if (oSSBucketSummary != null) {
                                oSSBucketSummary.intranetEndpoint = newPullParser.nextText();
                                break;
                            }
                        } else if (HttpHeaders.LOCATION.equals(name)) {
                            if (oSSBucketSummary != null) {
                                oSSBucketSummary.location = newPullParser.nextText();
                                break;
                            }
                        } else if ("Name".equals(name)) {
                            if (oSSBucketSummary != null) {
                                oSSBucketSummary.name = newPullParser.nextText();
                                break;
                            }
                        } else if (CreateBucketRequest.TAB_STORAGECLASS.equals(name)) {
                            if (oSSBucketSummary != null) {
                                oSSBucketSummary.storageClass = newPullParser.nextText();
                                break;
                            }
                        } else if ("Grant".equals(name) && oSSBucketSummary != null) {
                            oSSBucketSummary.setAcl(newPullParser.nextText());
                            break;
                        }
                    }
                    break;
                case 3:
                    String name2 = newPullParser.getName();
                    if (name2 != null) {
                        if ("Bucket".equals(name2)) {
                            if (oSSBucketSummary != null) {
                                getBucketInfoResult.setBucket(oSSBucketSummary);
                                break;
                            }
                        } else if ("Owner".equals(name2) && oSSBucketSummary != null) {
                            oSSBucketSummary.owner = owner;
                            break;
                        }
                    }
                    break;
            }
            eventType = newPullParser.next();
            if (eventType == 4) {
                eventType = newPullParser.next();
            }
        }
        return getBucketInfoResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static GetBucketACLResult parseGetBucketACLResponse(InputStream inputStream, GetBucketACLResult getBucketACLResult) throws Exception {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "utf-8");
        int eventType = newPullParser.getEventType();
        while (eventType != 1) {
            if (eventType == 2) {
                String name = newPullParser.getName();
                if ("Grant".equals(name)) {
                    getBucketACLResult.setBucketACL(newPullParser.nextText());
                } else if (ClientConstants.TRANSACT.ID.equals(name)) {
                    getBucketACLResult.setBucketOwnerID(newPullParser.nextText());
                } else if ("DisplayName".equals(name)) {
                    getBucketACLResult.setBucketOwner(newPullParser.nextText());
                }
            }
            eventType = newPullParser.next();
            if (eventType == 4) {
                eventType = newPullParser.next();
            }
        }
        return getBucketACLResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static GetBucketRefererResult parseGetBucketRefererResponse(InputStream inputStream, GetBucketRefererResult getBucketRefererResult) throws Exception {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "utf-8");
        int eventType = newPullParser.getEventType();
        while (eventType != 1) {
            if (eventType == 2 && "Referer".equals(newPullParser.getName())) {
                getBucketRefererResult.addReferer(newPullParser.nextText());
            }
            eventType = newPullParser.next();
            if (eventType == 4) {
                eventType = newPullParser.next();
            }
        }
        return getBucketRefererResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static GetBucketLoggingResult parseGetBucketLoggingResponse(InputStream inputStream, GetBucketLoggingResult getBucketLoggingResult) throws Exception {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "utf-8");
        int eventType = newPullParser.getEventType();
        while (eventType != 1) {
            if (eventType == 2) {
                String name = newPullParser.getName();
                if ("LoggingEnabled".equals(name)) {
                    getBucketLoggingResult.setLoggingEnabled(true);
                } else if ("TargetBucket".equals(name)) {
                    getBucketLoggingResult.setTargetBucketName(newPullParser.nextText());
                } else if ("TargetPrefix".equals(name)) {
                    getBucketLoggingResult.setTargetPrefix(newPullParser.nextText());
                }
            }
            eventType = newPullParser.next();
            if (eventType == 4) {
                eventType = newPullParser.next();
            }
        }
        return getBucketLoggingResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static GetBucketLifecycleResult parseGetBucketLifecycleResponse(InputStream inputStream, GetBucketLifecycleResult getBucketLifecycleResult) throws Exception {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "utf-8");
        int eventType = newPullParser.getEventType();
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        BucketLifecycleRule bucketLifecycleRule = null;
        String str = null;
        String str2 = null;
        String str3 = null;
        while (eventType != 1) {
            switch (eventType) {
                case 2:
                    String name = newPullParser.getName();
                    if (!"Rule".equals(name)) {
                        if (!ClientConstants.TRANSACT.ID.equals(name)) {
                            if (!"Prefix".equals(name)) {
                                if (!"Status".equals(name)) {
                                    if (!"Expiration".equals(name)) {
                                        if (!"AbortMultipartUpload".equals(name)) {
                                            if (!"Transition".equals(name)) {
                                                if (!"Days".equals(name)) {
                                                    if (!HttpHeaders.DATE.equals(name)) {
                                                        if (CreateBucketRequest.TAB_STORAGECLASS.equals(name)) {
                                                            String nextText = newPullParser.nextText();
                                                            if (bucketLifecycleRule != null) {
                                                                if ("IA".equals(nextText)) {
                                                                    bucketLifecycleRule.setIADays(str2);
                                                                    bucketLifecycleRule.setIAExpireDate(str3);
                                                                } else if ("Archive".equals(nextText)) {
                                                                    bucketLifecycleRule.setArchiveDays(str3);
                                                                    bucketLifecycleRule.setArchiveExpireDate(str3);
                                                                }
                                                            }
                                                            str = nextText;
                                                            break;
                                                        }
                                                    } else {
                                                        String nextText2 = newPullParser.nextText();
                                                        if (bucketLifecycleRule != null) {
                                                            if (z) {
                                                                bucketLifecycleRule.setExpireDate(nextText2);
                                                            } else if (z2) {
                                                                bucketLifecycleRule.setMultipartExpireDate(nextText2);
                                                            } else if (z3 && str != null) {
                                                                if ("IA".equals(str)) {
                                                                    bucketLifecycleRule.setIAExpireDate(nextText2);
                                                                } else if ("Archive".equals(str)) {
                                                                    bucketLifecycleRule.setArchiveExpireDate(nextText2);
                                                                }
                                                            }
                                                        }
                                                        str3 = nextText2;
                                                        break;
                                                    }
                                                } else {
                                                    String nextText3 = newPullParser.nextText();
                                                    if (bucketLifecycleRule != null) {
                                                        if (z) {
                                                            bucketLifecycleRule.setDays(nextText3);
                                                        } else if (z2) {
                                                            bucketLifecycleRule.setMultipartDays(nextText3);
                                                        } else if (z3 && str != null) {
                                                            if ("IA".equals(str)) {
                                                                bucketLifecycleRule.setIADays(nextText3);
                                                            } else if ("Archive".equals(str)) {
                                                                bucketLifecycleRule.setArchiveDays(nextText3);
                                                            }
                                                        }
                                                    }
                                                    str2 = nextText3;
                                                    break;
                                                }
                                            } else {
                                                z3 = true;
                                                break;
                                            }
                                        } else {
                                            z2 = true;
                                            break;
                                        }
                                    } else {
                                        z = true;
                                        break;
                                    }
                                } else if (!"Enabled".equals(newPullParser.nextText())) {
                                    bucketLifecycleRule.setStatus(false);
                                    break;
                                } else {
                                    bucketLifecycleRule.setStatus(true);
                                    break;
                                }
                            } else {
                                bucketLifecycleRule.setPrefix(newPullParser.nextText());
                                break;
                            }
                        } else {
                            bucketLifecycleRule.setIdentifier(newPullParser.nextText());
                            break;
                        }
                    } else {
                        bucketLifecycleRule = new BucketLifecycleRule();
                        break;
                    }
                    break;
                case 3:
                    String name2 = newPullParser.getName();
                    if (!"Rule".equals(name2)) {
                        if (!"Expiration".equals(name2)) {
                            if (!"AbortMultipartUpload".equals(name2)) {
                                if ("Transition".equals(name2)) {
                                    z3 = false;
                                    str = null;
                                    str2 = null;
                                    str3 = null;
                                    break;
                                }
                            } else {
                                z2 = false;
                                break;
                            }
                        } else {
                            z = false;
                            break;
                        }
                    } else {
                        getBucketLifecycleResult.addLifecycleRule(bucketLifecycleRule);
                        break;
                    }
                    break;
            }
            eventType = newPullParser.next();
            if (eventType == 4) {
                eventType = newPullParser.next();
            }
        }
        return getBucketLifecycleResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DeleteMultipleObjectResult parseDeleteMultipleObjectResponse(InputStream inputStream, DeleteMultipleObjectResult deleteMultipleObjectResult) throws Exception {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "utf-8");
        int eventType = newPullParser.getEventType();
        while (eventType != 1) {
            if (eventType == 2 && "Key".equals(newPullParser.getName())) {
                deleteMultipleObjectResult.addDeletedObject(newPullParser.nextText());
            }
            eventType = newPullParser.next();
            if (eventType == 4) {
                eventType = newPullParser.next();
            }
        }
        return deleteMultipleObjectResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ListBucketsResult parseBucketListResponse(InputStream inputStream, ListBucketsResult listBucketsResult) throws Exception {
        listBucketsResult.clearBucketList();
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "utf-8");
        int eventType = newPullParser.getEventType();
        OSSBucketSummary oSSBucketSummary = null;
        while (eventType != 1) {
            switch (eventType) {
                case 2:
                    String name = newPullParser.getName();
                    if (name != null) {
                        if ("Prefix".equals(name)) {
                            listBucketsResult.setPrefix(newPullParser.nextText());
                            break;
                        } else if ("Marker".equals(name)) {
                            listBucketsResult.setMarker(newPullParser.nextText());
                            break;
                        } else if (!"MaxKeys".equals(name)) {
                            if (!"IsTruncated".equals(name)) {
                                if ("NextMarker".equals(name)) {
                                    listBucketsResult.setNextMarker(newPullParser.nextText());
                                    break;
                                } else if (ClientConstants.TRANSACT.ID.equals(name)) {
                                    listBucketsResult.setOwnerId(newPullParser.nextText());
                                    break;
                                } else if ("DisplayName".equals(name)) {
                                    listBucketsResult.setOwnerDisplayName(newPullParser.nextText());
                                    break;
                                } else if ("Bucket".equals(name)) {
                                    oSSBucketSummary = new OSSBucketSummary();
                                    break;
                                } else if ("CreationDate".equals(name)) {
                                    if (oSSBucketSummary != null) {
                                        oSSBucketSummary.createDate = DateUtil.parseIso8601Date(newPullParser.nextText());
                                        break;
                                    }
                                } else if ("ExtranetEndpoint".equals(name)) {
                                    if (oSSBucketSummary != null) {
                                        oSSBucketSummary.extranetEndpoint = newPullParser.nextText();
                                        break;
                                    }
                                } else if ("IntranetEndpoint".equals(name)) {
                                    if (oSSBucketSummary != null) {
                                        oSSBucketSummary.intranetEndpoint = newPullParser.nextText();
                                        break;
                                    }
                                } else if (HttpHeaders.LOCATION.equals(name)) {
                                    if (oSSBucketSummary != null) {
                                        oSSBucketSummary.location = newPullParser.nextText();
                                        break;
                                    }
                                } else if ("Name".equals(name)) {
                                    if (oSSBucketSummary != null) {
                                        oSSBucketSummary.name = newPullParser.nextText();
                                        break;
                                    }
                                } else if (CreateBucketRequest.TAB_STORAGECLASS.equals(name) && oSSBucketSummary != null) {
                                    oSSBucketSummary.storageClass = newPullParser.nextText();
                                    break;
                                }
                            } else {
                                String nextText = newPullParser.nextText();
                                if (nextText != null) {
                                    listBucketsResult.setTruncated(Boolean.valueOf(nextText).booleanValue());
                                    break;
                                }
                            }
                        } else {
                            String nextText2 = newPullParser.nextText();
                            if (nextText2 != null) {
                                listBucketsResult.setMaxKeys(Integer.valueOf(nextText2).intValue());
                                break;
                            }
                        }
                    }
                    break;
                case 3:
                    if ("Bucket".equals(newPullParser.getName()) && oSSBucketSummary != null) {
                        listBucketsResult.addBucket(oSSBucketSummary);
                        break;
                    }
                    break;
            }
            eventType = newPullParser.next();
            if (eventType == 4) {
                eventType = newPullParser.next();
            }
        }
        return listBucketsResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ListObjectsResult parseObjectListResponse(InputStream inputStream, ListObjectsResult listObjectsResult) throws Exception {
        listObjectsResult.clearCommonPrefixes();
        listObjectsResult.clearObjectSummaries();
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setInput(inputStream, "utf-8");
        int eventType = newPullParser.getEventType();
        Owner owner = null;
        boolean z = false;
        OSSObjectSummary oSSObjectSummary = null;
        while (eventType != 1) {
            switch (eventType) {
                case 2:
                    String name = newPullParser.getName();
                    if (!"Name".equals(name)) {
                        if (!"Prefix".equals(name)) {
                            if (!"Marker".equals(name)) {
                                if (!"Delimiter".equals(name)) {
                                    if (!"EncodingType".equals(name)) {
                                        if (!"MaxKeys".equals(name)) {
                                            if (!"NextMarker".equals(name)) {
                                                if (!"IsTruncated".equals(name)) {
                                                    if (!"Contents".equals(name)) {
                                                        if (!"Key".equals(name)) {
                                                            if (!"LastModified".equals(name)) {
                                                                if (!"Size".equals(name)) {
                                                                    if (!HttpHeaders.ETAG.equals(name)) {
                                                                        if (!"Type".equals(name)) {
                                                                            if (!CreateBucketRequest.TAB_STORAGECLASS.equals(name)) {
                                                                                if (!"Owner".equals(name)) {
                                                                                    if (!ClientConstants.TRANSACT.ID.equals(name)) {
                                                                                        if (!"DisplayName".equals(name)) {
                                                                                            if ("CommonPrefixes".equals(name)) {
                                                                                                z = true;
                                                                                                break;
                                                                                            }
                                                                                        } else {
                                                                                            owner.setDisplayName(newPullParser.nextText());
                                                                                            break;
                                                                                        }
                                                                                    } else {
                                                                                        owner.setId(newPullParser.nextText());
                                                                                        break;
                                                                                    }
                                                                                } else {
                                                                                    owner = new Owner();
                                                                                    break;
                                                                                }
                                                                            } else {
                                                                                oSSObjectSummary.setStorageClass(newPullParser.nextText());
                                                                                break;
                                                                            }
                                                                        } else {
                                                                            oSSObjectSummary.setType(newPullParser.nextText());
                                                                            break;
                                                                        }
                                                                    } else {
                                                                        oSSObjectSummary.setETag(newPullParser.nextText());
                                                                        break;
                                                                    }
                                                                } else {
                                                                    String nextText = newPullParser.nextText();
                                                                    if (!OSSUtils.isEmptyString(nextText)) {
                                                                        oSSObjectSummary.setSize(Long.valueOf(nextText).longValue());
                                                                        break;
                                                                    }
                                                                }
                                                            } else {
                                                                oSSObjectSummary.setLastModified(DateUtil.parseIso8601Date(newPullParser.nextText()));
                                                                break;
                                                            }
                                                        } else {
                                                            oSSObjectSummary.setKey(newPullParser.nextText());
                                                            break;
                                                        }
                                                    } else {
                                                        oSSObjectSummary = new OSSObjectSummary();
                                                        break;
                                                    }
                                                } else {
                                                    String nextText2 = newPullParser.nextText();
                                                    if (!OSSUtils.isEmptyString(nextText2)) {
                                                        listObjectsResult.setTruncated(Boolean.valueOf(nextText2).booleanValue());
                                                        break;
                                                    }
                                                }
                                            } else {
                                                listObjectsResult.setNextMarker(newPullParser.nextText());
                                                break;
                                            }
                                        } else {
                                            String nextText3 = newPullParser.nextText();
                                            if (!OSSUtils.isEmptyString(nextText3)) {
                                                listObjectsResult.setMaxKeys(Integer.valueOf(nextText3).intValue());
                                                break;
                                            }
                                        }
                                    } else {
                                        listObjectsResult.setEncodingType(newPullParser.nextText());
                                        break;
                                    }
                                } else {
                                    listObjectsResult.setDelimiter(newPullParser.nextText());
                                    break;
                                }
                            } else {
                                listObjectsResult.setMarker(newPullParser.nextText());
                                break;
                            }
                        } else if (!z) {
                            listObjectsResult.setPrefix(newPullParser.nextText());
                            break;
                        } else {
                            String nextText4 = newPullParser.nextText();
                            if (!OSSUtils.isEmptyString(nextText4)) {
                                listObjectsResult.addCommonPrefix(nextText4);
                                break;
                            }
                        }
                    } else {
                        listObjectsResult.setBucketName(newPullParser.nextText());
                        break;
                    }
                    break;
                case 3:
                    String name2 = newPullParser.getName();
                    if (!"Owner".equals(newPullParser.getName())) {
                        if (!"Contents".equals(name2)) {
                            if ("CommonPrefixes".equals(name2)) {
                                z = false;
                                break;
                            }
                        } else if (oSSObjectSummary != null) {
                            oSSObjectSummary.setBucketName(listObjectsResult.getBucketName());
                            listObjectsResult.addObjectSummary(oSSObjectSummary);
                            break;
                        }
                    } else if (owner != null) {
                        oSSObjectSummary.setOwner(owner);
                        break;
                    }
                    break;
            }
            eventType = newPullParser.next();
            if (eventType == 4) {
                eventType = newPullParser.next();
            }
        }
        return listObjectsResult;
    }

    public static String trimQuotes(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.startsWith("\"")) {
            trim = trim.substring(1);
        }
        return trim.endsWith("\"") ? trim.substring(0, trim.length() - 1) : trim;
    }

    public static ObjectMetadata parseObjectMetadata(Map<String, String> map) throws Exception {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            for (String str : map.keySet()) {
                if (str.indexOf(OSSHeaders.OSS_USER_METADATA_PREFIX) >= 0) {
                    objectMetadata.addUserMetadata(str, map.get(str));
                } else {
                    if (!str.equalsIgnoreCase(HttpHeaders.LAST_MODIFIED) && !str.equalsIgnoreCase(HttpHeaders.DATE)) {
                        if (str.equalsIgnoreCase(HttpHeaders.CONTENT_LENGTH)) {
                            objectMetadata.setHeader(str, Long.valueOf(map.get(str)));
                        } else if (str.equalsIgnoreCase(HttpHeaders.ETAG)) {
                            objectMetadata.setHeader(str, trimQuotes(map.get(str)));
                        } else {
                            objectMetadata.setHeader(str, map.get(str));
                        }
                    }
                    try {
                        objectMetadata.setHeader(str, DateUtil.parseRfc822Date(map.get(str)));
                    } catch (ParseException e) {
                        throw new IOException(e.getMessage(), e);
                    }
                }
            }
            return objectMetadata;
        } catch (Exception e2) {
            throw new IOException(e2.getMessage(), e2);
        }
    }

    public static ServiceException parseResponseErrorXML(ResponseMessage responseMessage, boolean z) throws ClientException {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        int statusCode = responseMessage.getStatusCode();
        String header = responseMessage.getResponse().header(OSSHeaders.OSS_HEADER_REQUEST_ID);
        String str7 = null;
        if (z) {
            str4 = header;
            str6 = null;
            str3 = null;
            str = null;
            str5 = null;
            str2 = null;
        } else {
            try {
                String string = responseMessage.getResponse().body().string();
                OSSLog.logDebug("errorMessage  ：  \n " + string);
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(string.getBytes());
                XmlPullParser newPullParser = Xml.newPullParser();
                newPullParser.setInput(byteArrayInputStream, "utf-8");
                int eventType = newPullParser.getEventType();
                String str8 = null;
                str = null;
                String str9 = null;
                str2 = null;
                while (eventType != 1) {
                    if (eventType == 2) {
                        if ("Code".equals(newPullParser.getName())) {
                            str7 = newPullParser.nextText();
                        } else if ("Message".equals(newPullParser.getName())) {
                            str8 = newPullParser.nextText();
                        } else if ("RequestId".equals(newPullParser.getName())) {
                            header = newPullParser.nextText();
                        } else if ("HostId".equals(newPullParser.getName())) {
                            str = newPullParser.nextText();
                        } else if ("PartNumber".equals(newPullParser.getName())) {
                            str9 = newPullParser.nextText();
                        } else if ("PartEtag".equals(newPullParser.getName())) {
                            str2 = newPullParser.nextText();
                        }
                    }
                    eventType = newPullParser.next();
                    if (eventType == 4) {
                        eventType = newPullParser.next();
                    }
                }
                str3 = str7;
                str7 = str8;
                str4 = header;
                String str10 = str9;
                str5 = string;
                str6 = str10;
            } catch (IOException e) {
                throw new ClientException(e);
            } catch (XmlPullParserException e2) {
                throw new ClientException(e2);
            }
        }
        ServiceException serviceException = new ServiceException(statusCode, str7, str3, str4, str, str5);
        if (!TextUtils.isEmpty(str2)) {
            serviceException.setPartEtag(str2);
        }
        if (!TextUtils.isEmpty(str6)) {
            serviceException.setPartNumber(str6);
        }
        return serviceException;
    }

    /* loaded from: classes11.dex */
    public static final class PutObjectResponseParser extends AbstractResponseParser<PutObjectResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public PutObjectResult parseData(ResponseMessage responseMessage, PutObjectResult putObjectResult) throws IOException {
            putObjectResult.setETag(ResponseParsers.trimQuotes((String) responseMessage.getHeaders().get(HttpHeaders.ETAG)));
            String string = responseMessage.getResponse().body().string();
            if (!TextUtils.isEmpty(string)) {
                putObjectResult.setServerCallbackReturnBody(string);
            }
            return putObjectResult;
        }
    }

    /* loaded from: classes11.dex */
    public static final class AppendObjectResponseParser extends AbstractResponseParser<AppendObjectResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public AppendObjectResult parseData(ResponseMessage responseMessage, AppendObjectResult appendObjectResult) throws IOException {
            String str = (String) responseMessage.getHeaders().get(OSSHeaders.OSS_NEXT_APPEND_POSITION);
            if (str != null) {
                appendObjectResult.setNextPosition(Long.valueOf(str));
            }
            appendObjectResult.setObjectCRC64((String) responseMessage.getHeaders().get(OSSHeaders.OSS_HASH_CRC64_ECMA));
            return appendObjectResult;
        }
    }

    /* loaded from: classes11.dex */
    public static final class HeadObjectResponseParser extends AbstractResponseParser<HeadObjectResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public HeadObjectResult parseData(ResponseMessage responseMessage, HeadObjectResult headObjectResult) throws Exception {
            headObjectResult.setMetadata(ResponseParsers.parseObjectMetadata(headObjectResult.getResponseHeader()));
            return headObjectResult;
        }
    }

    /* loaded from: classes11.dex */
    public static final class GetObjectResponseParser extends AbstractResponseParser<GetObjectResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public GetObjectResult parseData(ResponseMessage responseMessage, GetObjectResult getObjectResult) throws Exception {
            getObjectResult.setMetadata(ResponseParsers.parseObjectMetadata(getObjectResult.getResponseHeader()));
            getObjectResult.setContentLength(responseMessage.getContentLength());
            if (responseMessage.getRequest().isCheckCRC64()) {
                getObjectResult.setObjectContent(new CheckCRC64DownloadInputStream(responseMessage.getContent(), new CRC64(), responseMessage.getContentLength(), getObjectResult.getServerCRC().longValue(), getObjectResult.getRequestId()));
            } else {
                getObjectResult.setObjectContent(responseMessage.getContent());
            }
            return getObjectResult;
        }

        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public boolean needCloseResponse() {
            return false;
        }
    }

    /* loaded from: classes11.dex */
    public static final class GetObjectACLResponseParser extends AbstractResponseParser<GetObjectACLResult> {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public GetObjectACLResult parseData(ResponseMessage responseMessage, GetObjectACLResult getObjectACLResult) throws Exception {
            return ResponseParsers.parseGetObjectACLResponse(responseMessage.getContent(), getObjectACLResult);
        }
    }

    /* loaded from: classes11.dex */
    public static final class CopyObjectResponseParser extends AbstractResponseParser<CopyObjectResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public CopyObjectResult parseData(ResponseMessage responseMessage, CopyObjectResult copyObjectResult) throws Exception {
            return ResponseParsers.parseCopyObjectResponseXML(responseMessage.getContent(), copyObjectResult);
        }
    }

    /* loaded from: classes11.dex */
    public static final class CreateBucketResponseParser extends AbstractResponseParser<CreateBucketResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public CreateBucketResult parseData(ResponseMessage responseMessage, CreateBucketResult createBucketResult) throws Exception {
            if (createBucketResult.getResponseHeader().containsKey(HttpHeaders.LOCATION)) {
                createBucketResult.bucketLocation = createBucketResult.getResponseHeader().get(HttpHeaders.LOCATION);
            }
            return createBucketResult;
        }
    }

    /* loaded from: classes11.dex */
    public static final class DeleteBucketResponseParser extends AbstractResponseParser<DeleteBucketResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public DeleteBucketResult parseData(ResponseMessage responseMessage, DeleteBucketResult deleteBucketResult) throws Exception {
            return deleteBucketResult;
        }
    }

    /* loaded from: classes11.dex */
    public static final class GetBucketInfoResponseParser extends AbstractResponseParser<GetBucketInfoResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public GetBucketInfoResult parseData(ResponseMessage responseMessage, GetBucketInfoResult getBucketInfoResult) throws Exception {
            return ResponseParsers.parseGetBucketInfoResponse(responseMessage.getContent(), getBucketInfoResult);
        }
    }

    /* loaded from: classes11.dex */
    public static final class GetBucketACLResponseParser extends AbstractResponseParser<GetBucketACLResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public GetBucketACLResult parseData(ResponseMessage responseMessage, GetBucketACLResult getBucketACLResult) throws Exception {
            return ResponseParsers.parseGetBucketACLResponse(responseMessage.getContent(), getBucketACLResult);
        }
    }

    /* loaded from: classes11.dex */
    public static final class PutBucketRefererResponseParser extends AbstractResponseParser<PutBucketRefererResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public PutBucketRefererResult parseData(ResponseMessage responseMessage, PutBucketRefererResult putBucketRefererResult) throws Exception {
            return putBucketRefererResult;
        }
    }

    /* loaded from: classes11.dex */
    public static final class GetBucketRefererResponseParser extends AbstractResponseParser<GetBucketRefererResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public GetBucketRefererResult parseData(ResponseMessage responseMessage, GetBucketRefererResult getBucketRefererResult) throws Exception {
            return ResponseParsers.parseGetBucketRefererResponse(responseMessage.getContent(), getBucketRefererResult);
        }
    }

    /* loaded from: classes11.dex */
    public static final class PutBucketLoggingResponseParser extends AbstractResponseParser<PutBucketLoggingResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public PutBucketLoggingResult parseData(ResponseMessage responseMessage, PutBucketLoggingResult putBucketLoggingResult) throws Exception {
            return putBucketLoggingResult;
        }
    }

    /* loaded from: classes11.dex */
    public static final class GetBucketLoggingResponseParser extends AbstractResponseParser<GetBucketLoggingResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public GetBucketLoggingResult parseData(ResponseMessage responseMessage, GetBucketLoggingResult getBucketLoggingResult) throws Exception {
            return ResponseParsers.parseGetBucketLoggingResponse(responseMessage.getContent(), getBucketLoggingResult);
        }
    }

    /* loaded from: classes11.dex */
    public static final class DeleteBucketLoggingResponseParser extends AbstractResponseParser<DeleteBucketLoggingResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public DeleteBucketLoggingResult parseData(ResponseMessage responseMessage, DeleteBucketLoggingResult deleteBucketLoggingResult) throws Exception {
            return deleteBucketLoggingResult;
        }
    }

    /* loaded from: classes11.dex */
    public static final class PutBucketLifecycleResponseParser extends AbstractResponseParser<PutBucketLifecycleResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public PutBucketLifecycleResult parseData(ResponseMessage responseMessage, PutBucketLifecycleResult putBucketLifecycleResult) throws Exception {
            return putBucketLifecycleResult;
        }
    }

    /* loaded from: classes11.dex */
    public static final class GetBucketLifecycleResponseParser extends AbstractResponseParser<GetBucketLifecycleResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public GetBucketLifecycleResult parseData(ResponseMessage responseMessage, GetBucketLifecycleResult getBucketLifecycleResult) throws Exception {
            return ResponseParsers.parseGetBucketLifecycleResponse(responseMessage.getContent(), getBucketLifecycleResult);
        }
    }

    /* loaded from: classes11.dex */
    public static final class DeleteBucketLifecycleResponseParser extends AbstractResponseParser<DeleteBucketLifecycleResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public DeleteBucketLifecycleResult parseData(ResponseMessage responseMessage, DeleteBucketLifecycleResult deleteBucketLifecycleResult) throws Exception {
            return deleteBucketLifecycleResult;
        }
    }

    /* loaded from: classes11.dex */
    public static final class DeleteObjectResponseParser extends AbstractResponseParser<DeleteObjectResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public DeleteObjectResult parseData(ResponseMessage responseMessage, DeleteObjectResult deleteObjectResult) throws Exception {
            return deleteObjectResult;
        }
    }

    /* loaded from: classes11.dex */
    public static final class DeleteMultipleObjectResponseParser extends AbstractResponseParser<DeleteMultipleObjectResult> {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public DeleteMultipleObjectResult parseData(ResponseMessage responseMessage, DeleteMultipleObjectResult deleteMultipleObjectResult) throws Exception {
            return ResponseParsers.parseDeleteMultipleObjectResponse(responseMessage.getContent(), deleteMultipleObjectResult);
        }
    }

    /* loaded from: classes11.dex */
    public static final class ListObjectsResponseParser extends AbstractResponseParser<ListObjectsResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public ListObjectsResult parseData(ResponseMessage responseMessage, ListObjectsResult listObjectsResult) throws Exception {
            return ResponseParsers.parseObjectListResponse(responseMessage.getContent(), listObjectsResult);
        }
    }

    /* loaded from: classes11.dex */
    public static final class ListBucketResponseParser extends AbstractResponseParser<ListBucketsResult> {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public ListBucketsResult parseData(ResponseMessage responseMessage, ListBucketsResult listBucketsResult) throws Exception {
            return ResponseParsers.parseBucketListResponse(responseMessage.getContent(), listBucketsResult);
        }
    }

    /* loaded from: classes11.dex */
    public static final class InitMultipartResponseParser extends AbstractResponseParser<InitiateMultipartUploadResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public InitiateMultipartUploadResult parseData(ResponseMessage responseMessage, InitiateMultipartUploadResult initiateMultipartUploadResult) throws Exception {
            return ResponseParsers.parseInitMultipartResponseXML(responseMessage.getContent(), initiateMultipartUploadResult);
        }
    }

    /* loaded from: classes11.dex */
    public static final class UploadPartResponseParser extends AbstractResponseParser<UploadPartResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public UploadPartResult parseData(ResponseMessage responseMessage, UploadPartResult uploadPartResult) throws Exception {
            uploadPartResult.setETag(ResponseParsers.trimQuotes((String) responseMessage.getHeaders().get(HttpHeaders.ETAG)));
            return uploadPartResult;
        }
    }

    /* loaded from: classes11.dex */
    public static final class AbortMultipartUploadResponseParser extends AbstractResponseParser<AbortMultipartUploadResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public AbortMultipartUploadResult parseData(ResponseMessage responseMessage, AbortMultipartUploadResult abortMultipartUploadResult) throws Exception {
            return abortMultipartUploadResult;
        }
    }

    /* loaded from: classes11.dex */
    public static final class CompleteMultipartUploadResponseParser extends AbstractResponseParser<CompleteMultipartUploadResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public CompleteMultipartUploadResult parseData(ResponseMessage responseMessage, CompleteMultipartUploadResult completeMultipartUploadResult) throws Exception {
            if (((String) responseMessage.getHeaders().get("Content-Type")).equals("application/xml")) {
                return ResponseParsers.parseCompleteMultipartUploadResponseXML(responseMessage.getContent(), completeMultipartUploadResult);
            }
            String string = responseMessage.getResponse().body().string();
            if (!TextUtils.isEmpty(string)) {
                completeMultipartUploadResult.setServerCallbackReturnBody(string);
                return completeMultipartUploadResult;
            }
            return completeMultipartUploadResult;
        }
    }

    /* loaded from: classes11.dex */
    public static final class ListPartsResponseParser extends AbstractResponseParser<ListPartsResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public ListPartsResult parseData(ResponseMessage responseMessage, ListPartsResult listPartsResult) throws Exception {
            return ResponseParsers.parseListPartsResponseXML(responseMessage.getContent(), listPartsResult);
        }
    }

    /* loaded from: classes11.dex */
    public static final class ListMultipartUploadsResponseParser extends AbstractResponseParser<ListMultipartUploadsResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public ListMultipartUploadsResult parseData(ResponseMessage responseMessage, ListMultipartUploadsResult listMultipartUploadsResult) throws Exception {
            return listMultipartUploadsResult.parseData(responseMessage);
        }
    }

    /* loaded from: classes11.dex */
    public static final class TriggerCallbackResponseParser extends AbstractResponseParser<TriggerCallbackResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public TriggerCallbackResult parseData(ResponseMessage responseMessage, TriggerCallbackResult triggerCallbackResult) throws Exception {
            String string = responseMessage.getResponse().body().string();
            if (!TextUtils.isEmpty(string)) {
                triggerCallbackResult.setServerCallbackReturnBody(string);
            }
            return triggerCallbackResult;
        }
    }

    /* loaded from: classes11.dex */
    public static final class ImagePersistResponseParser extends AbstractResponseParser<ImagePersistResult> {
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public ImagePersistResult parseData(ResponseMessage responseMessage, ImagePersistResult imagePersistResult) throws Exception {
            return imagePersistResult;
        }
    }

    /* loaded from: classes11.dex */
    public static final class PutSymlinkResponseParser extends AbstractResponseParser<PutSymlinkResult> {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public PutSymlinkResult parseData(ResponseMessage responseMessage, PutSymlinkResult putSymlinkResult) throws Exception {
            return putSymlinkResult;
        }
    }

    /* loaded from: classes11.dex */
    public static final class GetSymlinkResponseParser extends AbstractResponseParser<GetSymlinkResult> {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public GetSymlinkResult parseData(ResponseMessage responseMessage, GetSymlinkResult getSymlinkResult) throws Exception {
            getSymlinkResult.setTargetObjectName((String) responseMessage.getHeaders().get(OSSHeaders.OSS_HEADER_SYMLINK_TARGET));
            return getSymlinkResult;
        }
    }

    /* loaded from: classes11.dex */
    public static final class RestoreObjectResponseParser extends AbstractResponseParser<RestoreObjectResult> {
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.alibaba.sdk.android.oss.internal.AbstractResponseParser
        public RestoreObjectResult parseData(ResponseMessage responseMessage, RestoreObjectResult restoreObjectResult) throws Exception {
            return restoreObjectResult;
        }
    }
}
