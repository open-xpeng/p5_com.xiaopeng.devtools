package com.xiaopeng.logictree;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.xiaopeng.a.a;
import com.xiaopeng.aftersales.AfterSalesManager;
import com.xiaopeng.aftersales.LogicTreeUpgrader;
import com.xiaopeng.commonfunc.bean.event.CommonEvent;
import com.xiaopeng.commonfunc.utils.i;
import com.xiaopeng.commonfunc.utils.n;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.Callback;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.IRemoteStorage;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.StorageException;
import com.xiaopeng.lib.framework.netchannelmodule.NetworkChannelsEntry;
import com.xiaopeng.lib.utils.j;
import com.xiaopeng.logictree.bean.LogicTreeVersion;
import com.xiaopeng.logictree.handler.h;
import com.xiaopeng.logictree.handler.k;
import com.xiaopeng.logictree.handler.l;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import org.greenrobot.eventbus.EventBus;

/* compiled from: LogicTreeParser.java */
/* loaded from: classes12.dex */
public class g {
    private static final String XZ = com.xiaopeng.lib.utils.a.a.WY + "/flow/cv2/vehicle/cdu/cdu_diagnosis_logic_tree";
    private String XW;
    private String Yc;
    private Context mContext;
    private String mDownloadPath;
    private AfterSalesManager vl;
    private volatile f Ek = null;
    private LogicTreeUpgrader Yd = new LogicTreeUpgrader() { // from class: com.xiaopeng.logictree.-$$Lambda$g$fmW1WynHrWjdwPxR6qZqhHD1riY
        public final void onUpgradeStatus(boolean z) {
            g.aT(z);
        }
    };
    private final HashMap<String, h> Ya = new HashMap<>();
    private final a Yb = a.pC();

    public boolean pU() {
        this.Yc = null;
        this.mDownloadPath = null;
        boolean z = true;
        HashMap hashMap = new HashMap(1);
        hashMap.put("vin", n.eH());
        try {
            com.xiaopeng.lib.http.a.a a = com.xiaopeng.commonfunc.utils.d.a(((IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class)).bizHelper().post(XZ, new Gson().toJson(hashMap)).needAuthorizationInfo().enableSecurityEncoding().build().execute());
            com.xiaopeng.lib.utils.c.f("LogicTreeParser", "serverBean code : " + a.getCode() + "  data : " + a.getData() + " msg : " + a.getMsg());
            if (a.getCode() != 200 && TextUtils.isEmpty(a.getData())) {
                this.Yc = null;
                this.mDownloadPath = null;
                z = false;
            } else {
                LogicTreeVersion logicTreeVersion = (LogicTreeVersion) new Gson().fromJson(a.getData(), (Class<Object>) LogicTreeVersion.class);
                this.Yc = logicTreeVersion.getVersion();
                this.mDownloadPath = logicTreeVersion.getUrl();
            }
            com.xiaopeng.lib.utils.c.g("LogicTreeParser", "getCloudVer version : " + this.Yc + " path : " + this.mDownloadPath);
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void pV() {
        this.XW = "/system/etc/aftersales";
        try {
            try {
                this.Ek = (f) new Gson().fromJson(new JsonReader(new InputStreamReader(new FileInputStream("/system/etc/aftersales" + File.separator + "logictree_list.json"))), f.class);
            } catch (Exception e) {
                e.printStackTrace();
                this.Ek = null;
            }
            String str = d.XV + File.separator + "logictree_list.json";
            if (i.aY(str)) {
                f fVar = (f) new Gson().fromJson(new JsonReader(new InputStreamReader(new FileInputStream(str))), f.class);
                if (this.Ek != null && (fVar == null || fVar.getVersion().compareToIgnoreCase(this.Ek.getVersion()) <= 0 || !a.b.getString("MODEL_NAME").equalsIgnoreCase(fVar.pS()))) {
                    if (fVar == null) {
                        com.xiaopeng.lib.utils.c.i("LogicTreeParser", "logicTreeInfoList is null");
                    } else {
                        com.xiaopeng.lib.utils.c.a("LogicTreeParser", "logicTreeInfoList version[%s] project[%s]", fVar.getVersion(), fVar.pS());
                    }
                }
                this.Ek = fVar;
                this.XW = d.XV;
            }
            com.xiaopeng.lib.utils.c.g("LogicTreeParser", this.Ek.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (this.Yb.pG() == 1) {
            EventBus.getDefault().post(new CommonEvent(CommonEvent.REMOTE_ONE_CLICK_DIAGNOSIS_INIT_FINISH, null));
        } else {
            EventBus.getDefault().post(new CommonEvent(CommonEvent.DIAGNOSIS_LOGICTREE_INIT_FINISH, null));
        }
    }

    public void d(Application application) {
        if (TextUtils.isEmpty(this.mDownloadPath)) {
            com.xiaopeng.lib.utils.c.i("LogicTreeParser", "upgradeLogicTreeViaCloud with empty downloadPath");
            EventBus.getDefault().post(new CommonEvent(CommonEvent.UPGRADE_LOGICTREE_FAIL, null));
            return;
        }
        IRemoteStorage iRemoteStorage = (IRemoteStorage) Module.get(NetworkChannelsEntry.class).get(IRemoteStorage.class);
        try {
            iRemoteStorage.initWithContext(application);
            iRemoteStorage.downloadWithPathAndCallback("xp-security", this.mDownloadPath, "/cache/aftersales/logictree/temp_logictree.zip", new Callback() { // from class: com.xiaopeng.logictree.g.1
                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.Callback
                public void onStart(String str, String str2) {
                    com.xiaopeng.lib.utils.c.f("LogicTreeParser", "downloadWithPathAndCallback onStart s:" + str + ", s1:" + str2);
                }

                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.Callback
                public void onSuccess(String str, String str2) {
                    com.xiaopeng.lib.utils.c.g("LogicTreeParser", "downloadWithPathAndCallback onSuccess s:" + str + ", s1:" + str2);
                    g.this.h("/cache/aftersales/logictree/temp_logictree.zip", false);
                }

                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.remotestorage.Callback
                public void onFailure(String str, String str2, StorageException storageException) {
                    com.xiaopeng.lib.utils.c.i("LogicTreeParser", "downloadWithPathAndCallback onFailure s:" + str + ", s1:" + str2 + ", e:" + storageException.toString());
                    EventBus.getDefault().post(new CommonEvent(CommonEvent.UPGRADE_LOGICTREE_FAIL, null));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            EventBus.getDefault().post(new CommonEvent(CommonEvent.UPGRADE_LOGICTREE_FAIL, null));
        }
    }

    public void h(final String str, final boolean z) {
        j.execute(new Runnable() { // from class: com.xiaopeng.logictree.-$$Lambda$g$XWI6atI8Q4M1K-fcz1bpVspQPGE
            @Override // java.lang.Runnable
            public final void run() {
                g.this.b(z, str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(boolean z, String str) {
        if (z) {
            str = str + File.separatorChar + i.h(str, ".zip", a.d.get("XML_MODEL"));
        }
        if (i.aY(str)) {
            i.x(str, "/cache/aftersales/logictree");
            if (!z) {
                i.deleteFile(str);
            }
            this.vl.requestUpgradeLogicTree("/cache/aftersales/logictree");
            return;
        }
        EventBus.getDefault().post(new CommonEvent(CommonEvent.UPGRADE_LOGICTREE_FAIL, null));
    }

    public synchronized void e(Application application) {
        com.xiaopeng.lib.utils.c.g("LogicTreeParser", "Register Logic Action handler");
        this.mContext = application.getApplicationContext();
        this.vl = (AfterSalesManager) this.mContext.getSystemService("xiaopeng_aftersales");
        this.Ya.put("interactive", new com.xiaopeng.logictree.handler.f(application));
        this.Ya.put("shellcmd", new k(application));
        this.Ya.put("diagnosiscode", new com.xiaopeng.logictree.handler.b(application));
        this.Ya.put("displayresult", new com.xiaopeng.logictree.handler.d(application));
        this.Ya.put("security", new com.xiaopeng.logictree.handler.j(application));
        this.Ya.put("networkinfo", new com.xiaopeng.logictree.handler.i(application));
        this.Ya.put("usbinfo", new l(application));
        this.Ya.put("didinfo", new com.xiaopeng.logictree.handler.c(application));
        this.Ya.put("bluetooth", new com.xiaopeng.logictree.handler.a(application));
        this.Ya.put("gpsinfo", new com.xiaopeng.logictree.handler.e(application));
        com.xiaopeng.commonfunc.utils.f.k(this);
        this.vl.addLogicTreeUpgrader(this.Yd);
    }

    public synchronized void pW() {
        if (!this.Ya.isEmpty()) {
            com.xiaopeng.lib.utils.c.g("LogicTreeParser", "unregister Logic Action handler");
            for (String str : this.Ya.keySet()) {
                h hVar = this.Ya.get(str);
                if (hVar != null) {
                    hVar.destroy();
                }
            }
            this.Ya.clear();
        }
        this.Ek = null;
        this.XW = null;
        this.mDownloadPath = null;
        this.Yc = null;
        this.vl.removeLogicTreeUpgrader(this.Yd);
        com.xiaopeng.commonfunc.utils.f.l(this);
    }

    public String pX() {
        return this.Yc;
    }

    public f pY() {
        return this.Ek;
    }

    public boolean pZ() {
        return this.Yb.pE() == null;
    }

    public boolean qa() {
        boolean z = this.Yc != null && this.Ek.getVersion().compareToIgnoreCase(this.Yc) < 0;
        com.xiaopeng.lib.utils.c.i("LogicTreeParser", "local version[%s], cloud version[%s] isLogicTreeNeedUpdate[%s]", this.Ek.getVersion(), this.Yc, Boolean.valueOf(z));
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void aT(boolean z) {
        EventBus.getDefault().post(new CommonEvent(z ? CommonEvent.UPGRADE_LOGICTREE_SUCCESS : CommonEvent.UPGRADE_LOGICTREE_FAIL, null));
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0075, code lost:
        if (r11.compareToIgnoreCase(r10.Yb.pE().pJ()[r0]) >= 0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x008a, code lost:
        if (r11.compareToIgnoreCase(r10.Yb.pE().pJ()[r0]) > 0) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x018d, code lost:
        if (r11.equalsIgnoreCase(r10.Yb.pE().pJ()[r0]) != false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x01a0, code lost:
        if (r11.matches(r10.Yb.pE().pJ()[r0]) != false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x01a3, code lost:
        r4 = r0;
     */
    @org.greenrobot.eventbus.Subscribe(threadMode = org.greenrobot.eventbus.ThreadMode.BACKGROUND)
    @android.support.annotation.RequiresApi(api = 24)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onEvent(com.xiaopeng.logictree.b r11) {
        /*
            Method dump skipped, instructions count: 2136
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.logictree.g.onEvent(com.xiaopeng.logictree.b):void");
    }

    public boolean a(e eVar, long j, long j2, int i) {
        com.xiaopeng.lib.utils.c.i("LogicTreeParser", "process logicTreePath[%s] startTime[%d] endTime[%d]", eVar.pR(), Long.valueOf(j), Long.valueOf(j2));
        if (this.Yb.pE() == null) {
            try {
                a aVar = this.Yb;
                Gson gson = new Gson();
                aVar.a((c) gson.fromJson(new JsonReader(new InputStreamReader(new FileInputStream(this.XW + File.separator + eVar.pR()))), c.class));
                this.Yb.dP(eVar.getName());
                this.Yb.setStartTime(j);
                this.Yb.r(j2);
                this.Yb.dQ(this.Ek.getVersion());
                this.Yb.dT(i);
                return qb();
            } catch (Exception e) {
                EventBus.getDefault().post(new CommonEvent(CommonEvent.DISPLAY_LOGICTREE_TOAST, this.mContext.getString(R.string.tips_logictree_parse_fail)));
                mc();
                e.printStackTrace();
            }
        } else {
            EventBus.getDefault().post(new CommonEvent(CommonEvent.DISPLAY_LOGICTREE_TOAST, this.mContext.getString(R.string.tips_for_wait_last_diagnosis_done)));
            mc();
            com.xiaopeng.lib.utils.c.i("LogicTreeParser", "it was processing other logic tree, plz trigger again later");
        }
        return false;
    }

    public boolean qb() {
        if (this.Yb.pE() != null) {
            String action = this.Yb.pE().getAction();
            final h hVar = this.Ya.get(action);
            if (hVar != null) {
                EventBus.getDefault().post(new CommonEvent(CommonEvent.DISPLAY_ONE_CLICK_DIAGNOSIS_RUNNING, null));
                j.execute(new Runnable() { // from class: com.xiaopeng.logictree.-$$Lambda$g$f6q2f6yp3OGvFmpsiDObd3M1qFw
                    @Override // java.lang.Runnable
                    public final void run() {
                        g.this.a(hVar);
                    }
                });
                return true;
            }
            mc();
            this.Yb.a(null);
            if (TextUtils.isEmpty(action)) {
                return false;
            }
            EventBus.getDefault().post(new CommonEvent(CommonEvent.DISPLAY_LOGICTREE_TOAST, this.mContext.getString(R.string.tips_logictree_parse_fail)));
            com.xiaopeng.lib.utils.c.i("LogicTreeParser", "dont support this action [%s]", action);
            return false;
        }
        mc();
        com.xiaopeng.lib.utils.c.g("LogicTreeParser", "end of issue diagnosis");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(h hVar) {
        hVar.a(this.Yb);
    }

    private void mc() {
        EventBus.getDefault().post(new CommonEvent(CommonEvent.DIAGNOSIS_DISMISS_LOADING_DIALOG, null));
    }
}
