package com.ut.mini;

import android.app.Activity;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.mtl.log.d.i;
import com.ut.mini.base.UTMIVariables;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/* loaded from: classes11.dex */
public class UTPageHitHelper {
    private static UTPageHitHelper a = new UTPageHitHelper();
    private boolean O = false;
    private Map<String, String> z = new HashMap();
    private Map<String, UTPageEventObject> A = new HashMap();
    private String al = null;
    private Map<String, String> B = new HashMap();
    private String am = null;

    /* renamed from: a  reason: collision with other field name */
    private Queue<UTPageEventObject> f141a = new LinkedList();
    private Map<Object, String> C = new HashMap();

    /* loaded from: classes11.dex */
    public static class UTPageEventObject {
        private Map<String, String> z = new HashMap();
        private long A = 0;
        private Uri a = null;
        private String an = null;
        private String ao = null;

        /* renamed from: a  reason: collision with other field name */
        private UTPageStatus f142a = null;
        private boolean P = false;
        private boolean Q = false;
        private boolean R = false;
        private String ap = null;

        public void setCacheKey(String str) {
            this.ap = str;
        }

        public String getCacheKey() {
            return this.ap;
        }

        public void resetPropertiesWithoutSkipFlagAndH5Flag() {
            this.z = new HashMap();
            this.A = 0L;
            this.a = null;
            this.an = null;
            this.ao = null;
            if (this.f142a == null || this.f142a != UTPageStatus.UT_H5_IN_WebView) {
                this.f142a = null;
            }
            this.P = false;
            this.R = false;
        }

        public boolean isH5Called() {
            return this.R;
        }

        public void setH5Called() {
            this.R = true;
        }

        public void setToSkipPage() {
            this.Q = true;
        }

        public boolean isSkipPage() {
            return this.Q;
        }

        public void setPageAppearCalled() {
            this.P = true;
        }

        public boolean isPageAppearCalled() {
            return this.P;
        }

        public void setPageStatus(UTPageStatus uTPageStatus) {
            this.f142a = uTPageStatus;
        }

        public UTPageStatus getPageStatus() {
            return this.f142a;
        }

        public Map<String, String> getPageProperties() {
            return this.z;
        }

        public void setPageProperties(Map<String, String> map) {
            this.z = map;
        }

        public long getPageStayTimstamp() {
            return this.A;
        }

        public void setPageStayTimstamp(long j) {
            this.A = j;
        }

        public Uri getPageUrl() {
            return this.a;
        }

        public void setPageUrl(Uri uri) {
            this.a = uri;
        }

        public void setPageName(String str) {
            this.an = str;
        }

        public String getPageName() {
            return this.an;
        }

        public void setRefPage(String str) {
            this.ao = str;
        }

        public String getRefPage() {
            return this.ao;
        }
    }

    public static UTPageHitHelper getInstance() {
        return a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized Map<String, String> c() {
        if (this.B != null && this.B.size() > 0) {
            HashMap hashMap = new HashMap();
            hashMap.putAll(this.B);
            this.B.clear();
            return hashMap;
        }
        return null;
    }

    synchronized void a(UTPageEventObject uTPageEventObject) {
        uTPageEventObject.resetPropertiesWithoutSkipFlagAndH5Flag();
        if (!this.f141a.contains(uTPageEventObject)) {
            this.f141a.add(uTPageEventObject);
        }
        if (this.f141a.size() > 200) {
            for (int i = 0; i < 100; i++) {
                UTPageEventObject poll = this.f141a.poll();
                if (poll != null && this.A.containsKey(poll.getCacheKey())) {
                    this.A.remove(poll.getCacheKey());
                }
            }
        }
    }

    @Deprecated
    public synchronized void turnOffAutoPageTrack() {
        this.O = true;
    }

    public String getCurrentPageName() {
        return this.am;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pageAppearByAuto(Activity activity) {
        if (this.O) {
            return;
        }
        pageAppear(activity);
    }

    /* renamed from: a  reason: collision with other method in class */
    private String m82a(Object obj) {
        String simpleName;
        if (obj instanceof String) {
            simpleName = (String) obj;
        } else {
            simpleName = obj.getClass().getSimpleName();
        }
        int hashCode = obj.hashCode();
        return simpleName + hashCode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public synchronized boolean m85a(Object obj) {
        if (obj != null) {
            UTPageEventObject a2 = a(obj);
            if (a2.getPageStatus() != null) {
                if (a2.getPageStatus() == UTPageStatus.UT_H5_IN_WebView) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a  reason: collision with other method in class */
    public synchronized void m84a(Object obj) {
        if (obj != null) {
            UTPageEventObject a2 = a(obj);
            if (a2.getPageStatus() != null) {
                a2.setH5Called();
            }
        }
    }

    private synchronized UTPageEventObject a(Object obj) {
        String m82a = m82a(obj);
        if (this.A.containsKey(m82a)) {
            return this.A.get(m82a);
        }
        UTPageEventObject uTPageEventObject = new UTPageEventObject();
        this.A.put(m82a, uTPageEventObject);
        uTPageEventObject.setCacheKey(m82a);
        return uTPageEventObject;
    }

    private synchronized void a(String str, UTPageEventObject uTPageEventObject) {
        this.A.put(str, uTPageEventObject);
    }

    private synchronized void b(UTPageEventObject uTPageEventObject) {
        if (this.A.containsKey(uTPageEventObject.getCacheKey())) {
            this.A.remove(uTPageEventObject.getCacheKey());
        }
    }

    /* renamed from: b  reason: collision with other method in class */
    private synchronized void m83b(Object obj) {
        String m82a = m82a(obj);
        if (this.A.containsKey(m82a)) {
            this.A.remove(m82a);
        }
    }

    @Deprecated
    public synchronized void pageAppear(Object obj) {
        a(obj, null, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void a(Object obj, String str, boolean z) {
        try {
            if (obj != null) {
                String m82a = m82a(obj);
                if (m82a != null && m82a.equals(this.al)) {
                    return;
                }
                if (this.al != null) {
                    i.a("lost 2001", "Last page requires leave(" + this.al + ").");
                }
                UTPageEventObject a2 = a(obj);
                if (!z && a2.isSkipPage()) {
                    i.a("skip page[pageAppear]", "page name:" + obj.getClass().getSimpleName());
                    return;
                }
                String h5Url = UTMIVariables.getInstance().getH5Url();
                if (h5Url != null) {
                    this.z.put("spm", Uri.parse(h5Url).getQueryParameter("spm"));
                    UTMIVariables.getInstance().setH5Url(null);
                }
                String b = b(obj);
                if (TextUtils.isEmpty(str)) {
                    str = b;
                }
                if (!TextUtils.isEmpty(a2.getPageName())) {
                    str = a2.getPageName();
                }
                this.am = str;
                a2.setPageName(str);
                a2.setPageStayTimstamp(SystemClock.elapsedRealtime());
                a2.setRefPage(UTMIVariables.getInstance().getRefPage());
                a2.setPageAppearCalled();
                if (this.B != null) {
                    Map<String, String> pageProperties = a2.getPageProperties();
                    if (pageProperties == null) {
                        a2.setPageProperties(this.B);
                    } else {
                        HashMap hashMap = new HashMap();
                        hashMap.putAll(pageProperties);
                        hashMap.putAll(this.B);
                        a2.setPageProperties(hashMap);
                    }
                }
                this.B = null;
                this.al = m82a(obj);
                b(a2);
                a(m82a(obj), a2);
            } else {
                i.a("pageAppear", "The page object should not be null");
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void pageAppear(Object obj, String str) {
        a(obj, str, false);
    }

    @Deprecated
    public synchronized void updatePageProperties(Map<String, String> map) {
        if (map != null) {
            this.z.putAll(map);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void updatePageProperties(Object obj, Map<String, String> map) {
        if (obj != null && map != null) {
            if (map.size() != 0) {
                HashMap hashMap = new HashMap();
                hashMap.putAll(map);
                UTPageEventObject a2 = a(obj);
                Map<String, String> pageProperties = a2.getPageProperties();
                if (pageProperties == null) {
                    a2.setPageProperties(hashMap);
                } else {
                    HashMap hashMap2 = new HashMap();
                    hashMap2.putAll(pageProperties);
                    hashMap2.putAll(hashMap);
                    a2.setPageProperties(hashMap2);
                }
                return;
            }
        }
        i.a("updatePageProperties", "failed to update project, parameters should not be null and the map should not be empty");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void updatePageName(Object obj, String str) {
        if (obj != null) {
            if (!TextUtils.isEmpty(str)) {
                a(obj).setPageName(str);
                this.am = str;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void updatePageUrl(Object obj, Uri uri) {
        if (obj == null || uri == null) {
            return;
        }
        Log.i("url", "url" + uri.toString());
        a(obj).setPageUrl(uri);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void updatePageStatus(Object obj, UTPageStatus uTPageStatus) {
        if (obj == null || uTPageStatus == null) {
            return;
        }
        a(obj).setPageStatus(uTPageStatus);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void updateNextPageProperties(Map<String, String> map) {
        if (map != null) {
            HashMap hashMap = new HashMap();
            hashMap.putAll(map);
            this.B = hashMap;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pageDisAppearByAuto(Activity activity) {
        if (this.O) {
            return;
        }
        pageDisAppear(activity);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void skipPage(Object obj) {
        if (obj == null) {
            return;
        }
        a(obj).setToSkipPage();
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x00f1 A[Catch: Throwable -> 0x013f, TryCatch #3 {all -> 0x01bf, blocks: (B:4:0x0003, B:9:0x000a, B:11:0x0014, B:13:0x001a, B:15:0x0022, B:17:0x0028, B:20:0x002d, B:22:0x003c, B:24:0x0040, B:26:0x0049, B:27:0x0057, B:29:0x0061, B:32:0x0069, B:34:0x006d, B:35:0x0072, B:37:0x0079, B:38:0x0080, B:40:0x0084, B:43:0x0093, B:45:0x0099, B:47:0x009f, B:48:0x00a6, B:51:0x00b2, B:55:0x00ca, B:56:0x00d8, B:83:0x0143, B:85:0x0164, B:89:0x0193, B:91:0x01a0, B:98:0x01b9, B:92:0x01a4, B:94:0x01aa, B:96:0x01b2, B:97:0x01b6, B:86:0x016c, B:88:0x0174, B:101:0x01c1, B:53:0x00b8, B:61:0x00e8, B:62:0x00eb, B:64:0x00f1, B:66:0x00fa, B:70:0x0109, B:71:0x0113, B:73:0x011f, B:74:0x0124, B:76:0x012e, B:77:0x0135, B:79:0x013b), top: B:110:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x011f A[Catch: Throwable -> 0x013f, TryCatch #3 {all -> 0x01bf, blocks: (B:4:0x0003, B:9:0x000a, B:11:0x0014, B:13:0x001a, B:15:0x0022, B:17:0x0028, B:20:0x002d, B:22:0x003c, B:24:0x0040, B:26:0x0049, B:27:0x0057, B:29:0x0061, B:32:0x0069, B:34:0x006d, B:35:0x0072, B:37:0x0079, B:38:0x0080, B:40:0x0084, B:43:0x0093, B:45:0x0099, B:47:0x009f, B:48:0x00a6, B:51:0x00b2, B:55:0x00ca, B:56:0x00d8, B:83:0x0143, B:85:0x0164, B:89:0x0193, B:91:0x01a0, B:98:0x01b9, B:92:0x01a4, B:94:0x01aa, B:96:0x01b2, B:97:0x01b6, B:86:0x016c, B:88:0x0174, B:101:0x01c1, B:53:0x00b8, B:61:0x00e8, B:62:0x00eb, B:64:0x00f1, B:66:0x00fa, B:70:0x0109, B:71:0x0113, B:73:0x011f, B:74:0x0124, B:76:0x012e, B:77:0x0135, B:79:0x013b), top: B:110:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x012e A[Catch: Throwable -> 0x013f, TryCatch #3 {all -> 0x01bf, blocks: (B:4:0x0003, B:9:0x000a, B:11:0x0014, B:13:0x001a, B:15:0x0022, B:17:0x0028, B:20:0x002d, B:22:0x003c, B:24:0x0040, B:26:0x0049, B:27:0x0057, B:29:0x0061, B:32:0x0069, B:34:0x006d, B:35:0x0072, B:37:0x0079, B:38:0x0080, B:40:0x0084, B:43:0x0093, B:45:0x0099, B:47:0x009f, B:48:0x00a6, B:51:0x00b2, B:55:0x00ca, B:56:0x00d8, B:83:0x0143, B:85:0x0164, B:89:0x0193, B:91:0x01a0, B:98:0x01b9, B:92:0x01a4, B:94:0x01aa, B:96:0x01b2, B:97:0x01b6, B:86:0x016c, B:88:0x0174, B:101:0x01c1, B:53:0x00b8, B:61:0x00e8, B:62:0x00eb, B:64:0x00f1, B:66:0x00fa, B:70:0x0109, B:71:0x0113, B:73:0x011f, B:74:0x0124, B:76:0x012e, B:77:0x0135, B:79:0x013b), top: B:110:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x013b A[Catch: Throwable -> 0x013f, TRY_LEAVE, TryCatch #3 {all -> 0x01bf, blocks: (B:4:0x0003, B:9:0x000a, B:11:0x0014, B:13:0x001a, B:15:0x0022, B:17:0x0028, B:20:0x002d, B:22:0x003c, B:24:0x0040, B:26:0x0049, B:27:0x0057, B:29:0x0061, B:32:0x0069, B:34:0x006d, B:35:0x0072, B:37:0x0079, B:38:0x0080, B:40:0x0084, B:43:0x0093, B:45:0x0099, B:47:0x009f, B:48:0x00a6, B:51:0x00b2, B:55:0x00ca, B:56:0x00d8, B:83:0x0143, B:85:0x0164, B:89:0x0193, B:91:0x01a0, B:98:0x01b9, B:92:0x01a4, B:94:0x01aa, B:96:0x01b2, B:97:0x01b6, B:86:0x016c, B:88:0x0174, B:101:0x01c1, B:53:0x00b8, B:61:0x00e8, B:62:0x00eb, B:64:0x00f1, B:66:0x00fa, B:70:0x0109, B:71:0x0113, B:73:0x011f, B:74:0x0124, B:76:0x012e, B:77:0x0135, B:79:0x013b), top: B:110:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0164 A[Catch: all -> 0x01bf, TryCatch #3 {all -> 0x01bf, blocks: (B:4:0x0003, B:9:0x000a, B:11:0x0014, B:13:0x001a, B:15:0x0022, B:17:0x0028, B:20:0x002d, B:22:0x003c, B:24:0x0040, B:26:0x0049, B:27:0x0057, B:29:0x0061, B:32:0x0069, B:34:0x006d, B:35:0x0072, B:37:0x0079, B:38:0x0080, B:40:0x0084, B:43:0x0093, B:45:0x0099, B:47:0x009f, B:48:0x00a6, B:51:0x00b2, B:55:0x00ca, B:56:0x00d8, B:83:0x0143, B:85:0x0164, B:89:0x0193, B:91:0x01a0, B:98:0x01b9, B:92:0x01a4, B:94:0x01aa, B:96:0x01b2, B:97:0x01b6, B:86:0x016c, B:88:0x0174, B:101:0x01c1, B:53:0x00b8, B:61:0x00e8, B:62:0x00eb, B:64:0x00f1, B:66:0x00fa, B:70:0x0109, B:71:0x0113, B:73:0x011f, B:74:0x0124, B:76:0x012e, B:77:0x0135, B:79:0x013b), top: B:110:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x016c A[Catch: all -> 0x01bf, TryCatch #3 {all -> 0x01bf, blocks: (B:4:0x0003, B:9:0x000a, B:11:0x0014, B:13:0x001a, B:15:0x0022, B:17:0x0028, B:20:0x002d, B:22:0x003c, B:24:0x0040, B:26:0x0049, B:27:0x0057, B:29:0x0061, B:32:0x0069, B:34:0x006d, B:35:0x0072, B:37:0x0079, B:38:0x0080, B:40:0x0084, B:43:0x0093, B:45:0x0099, B:47:0x009f, B:48:0x00a6, B:51:0x00b2, B:55:0x00ca, B:56:0x00d8, B:83:0x0143, B:85:0x0164, B:89:0x0193, B:91:0x01a0, B:98:0x01b9, B:92:0x01a4, B:94:0x01aa, B:96:0x01b2, B:97:0x01b6, B:86:0x016c, B:88:0x0174, B:101:0x01c1, B:53:0x00b8, B:61:0x00e8, B:62:0x00eb, B:64:0x00f1, B:66:0x00fa, B:70:0x0109, B:71:0x0113, B:73:0x011f, B:74:0x0124, B:76:0x012e, B:77:0x0135, B:79:0x013b), top: B:110:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01a0 A[Catch: all -> 0x01bf, TryCatch #3 {all -> 0x01bf, blocks: (B:4:0x0003, B:9:0x000a, B:11:0x0014, B:13:0x001a, B:15:0x0022, B:17:0x0028, B:20:0x002d, B:22:0x003c, B:24:0x0040, B:26:0x0049, B:27:0x0057, B:29:0x0061, B:32:0x0069, B:34:0x006d, B:35:0x0072, B:37:0x0079, B:38:0x0080, B:40:0x0084, B:43:0x0093, B:45:0x0099, B:47:0x009f, B:48:0x00a6, B:51:0x00b2, B:55:0x00ca, B:56:0x00d8, B:83:0x0143, B:85:0x0164, B:89:0x0193, B:91:0x01a0, B:98:0x01b9, B:92:0x01a4, B:94:0x01aa, B:96:0x01b2, B:97:0x01b6, B:86:0x016c, B:88:0x0174, B:101:0x01c1, B:53:0x00b8, B:61:0x00e8, B:62:0x00eb, B:64:0x00f1, B:66:0x00fa, B:70:0x0109, B:71:0x0113, B:73:0x011f, B:74:0x0124, B:76:0x012e, B:77:0x0135, B:79:0x013b), top: B:110:0x0001 }] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01a4 A[Catch: all -> 0x01bf, TryCatch #3 {all -> 0x01bf, blocks: (B:4:0x0003, B:9:0x000a, B:11:0x0014, B:13:0x001a, B:15:0x0022, B:17:0x0028, B:20:0x002d, B:22:0x003c, B:24:0x0040, B:26:0x0049, B:27:0x0057, B:29:0x0061, B:32:0x0069, B:34:0x006d, B:35:0x0072, B:37:0x0079, B:38:0x0080, B:40:0x0084, B:43:0x0093, B:45:0x0099, B:47:0x009f, B:48:0x00a6, B:51:0x00b2, B:55:0x00ca, B:56:0x00d8, B:83:0x0143, B:85:0x0164, B:89:0x0193, B:91:0x01a0, B:98:0x01b9, B:92:0x01a4, B:94:0x01aa, B:96:0x01b2, B:97:0x01b6, B:86:0x016c, B:88:0x0174, B:101:0x01c1, B:53:0x00b8, B:61:0x00e8, B:62:0x00eb, B:64:0x00f1, B:66:0x00fa, B:70:0x0109, B:71:0x0113, B:73:0x011f, B:74:0x0124, B:76:0x012e, B:77:0x0135, B:79:0x013b), top: B:110:0x0001 }] */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void pageDisAppear(java.lang.Object r13) {
        /*
            Method dump skipped, instructions count: 460
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ut.mini.UTPageHitHelper.pageDisAppear(java.lang.Object):void");
    }

    private static String a(Uri uri) {
        List<String> queryParameters;
        if (uri != null && (queryParameters = uri.getQueryParameters("ttid")) != null) {
            for (String str : queryParameters) {
                if (!str.contains("@") && !str.contains("%40")) {
                    return str;
                }
            }
            return null;
        }
        return null;
    }

    private static String b(Object obj) {
        String simpleName = obj.getClass().getSimpleName();
        if (simpleName != null && simpleName.toLowerCase().endsWith("activity")) {
            return simpleName.substring(0, simpleName.length() - 8);
        }
        return simpleName;
    }
}
