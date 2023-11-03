package com.xiaopeng.devtools.system.service;

import android.app.IntentService;
import android.car.hardware.CarPropertyValue;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import com.xiaopeng.a.a;
import com.xiaopeng.commonfunc.utils.o;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.presenter.b.b;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.lib.apirouter.ClientConstants;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import com.xiaopeng.xui.app.g;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

/* loaded from: classes12.dex */
public class CopyLogService extends IntentService {
    private static final boolean Ba = r.ln();
    private static final boolean Bb = a.C0041a.dX("ICM_SUPPORT_SSH");
    private b Bc;
    private CountDownLatch Bd;
    private List<String> Be;
    private volatile boolean Bf;
    private volatile boolean Bg;
    private boolean Bh;
    private final com.xiaopeng.commonfunc.b.a.a Bi;
    private final Handler mHandler;

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(CarPropertyValue carPropertyValue) {
        int propertyId = carPropertyValue.getPropertyId();
        Object value = carPropertyValue.getValue();
        c.f("CopyLogService", carPropertyValue.toString());
        if (propertyId != 554702438) {
            if (propertyId != 557846615) {
                return;
            }
            if (!this.mHandler.hasMessages(this.Bh ? 1006 : 1003)) {
                c.g("CopyLogService", "no exist modem log or tbox log messages!");
                return;
            } else if (!this.Bg) {
                this.Bg = true;
                this.mHandler.removeMessages(this.Bh ? 1006 : 1003);
                if (((Integer) value).intValue() == 200) {
                    j.execute(new Runnable() { // from class: com.xiaopeng.devtools.system.service.-$$Lambda$CopyLogService$E6KylWv97AeF8wiTZrvQsyKxmak
                        @Override // java.lang.Runnable
                        public final void run() {
                            CopyLogService.this.kS();
                        }
                    });
                    return;
                }
                c.i("CopyLogService", "tbox log response fail");
                this.Be.add(this.Bh ? "/cache/modemlog/" : "/cache/tboxlog/");
                this.Bd.countDown();
                return;
            } else {
                c.i("CopyLogService", "wait tbox response timeout");
                return;
            }
        }
        try {
            if (!this.mHandler.hasMessages(1007)) {
                c.g("CopyLogService", "no exist icm log messages!");
            } else if (!this.Bf) {
                this.Bf = true;
                this.mHandler.removeMessages(1007);
                String str = (String) value;
                c.g("CopyLogService", "ID_ICM_LOG_COMPRESS resStr:" + str);
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.getInt("cmd") == 200) {
                    final String string = jSONObject.getString(ClientConstants.ALIAS.PATH);
                    final String substring = string.substring(string.lastIndexOf(File.separator) + 1);
                    c.f("CopyLogService", "icm log file name : " + substring);
                    j.execute(new Runnable() { // from class: com.xiaopeng.devtools.system.service.-$$Lambda$CopyLogService$pQJJxrpx8Dxm5K7r3NX0Ksqf-zM
                        @Override // java.lang.Runnable
                        public final void run() {
                            CopyLogService.this.C(string, substring);
                        }
                    });
                } else {
                    c.i("CopyLogService", "icm log response fail");
                    this.Bd.countDown();
                }
            } else {
                c.i("CopyLogService", "wait icm log response timeout");
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.Bd.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void C(String str, String str2) {
        this.Bc.b(str, Ba, str2);
        this.Be.add("/cache/icmlog/");
        this.Bd.countDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void kS() {
        o.j("/mnt/sdcard/tftpboot/dlt.tar.gz", this.Bh ? "/cache/modemlog/dlt.tar.gz" : "/cache/tboxlog/dlt.tar.gz", o.ps);
        this.Be.add(this.Bh ? "/cache/modemlog/" : "/cache/tboxlog/");
        this.Bd.countDown();
    }

    public CopyLogService() {
        this("CopyLogService");
    }

    public CopyLogService(String str) {
        super(str);
        this.Be = Collections.synchronizedList(new ArrayList());
        this.Bf = false;
        this.Bg = false;
        this.Bh = false;
        this.mHandler = new Handler() { // from class: com.xiaopeng.devtools.system.service.CopyLogService.1
            @Override // android.os.Handler
            public void handleMessage(@NonNull Message message) {
                super.handleMessage(message);
                int i = message.what;
                if (i != 1003) {
                    switch (i) {
                        case 1006:
                            c.f("CopyLogService", "modem log response = " + CopyLogService.this.Bg);
                            if (!CopyLogService.this.Bg) {
                                CopyLogService.this.Bg = true;
                                CopyLogService.this.Be.add("/cache/modemlog/");
                                CopyLogService.this.Bd.countDown();
                                return;
                            }
                            return;
                        case 1007:
                            c.f("CopyLogService", "isIcmLogResponse = " + CopyLogService.this.Bf);
                            if (!CopyLogService.this.Bf) {
                                CopyLogService.this.Bf = true;
                                CopyLogService.this.Be.add("/cache/icmlog/");
                                CopyLogService.this.Bd.countDown();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
                c.f("CopyLogService", "tbox log response = " + CopyLogService.this.Bg);
                if (!CopyLogService.this.Bg) {
                    CopyLogService.this.Bg = true;
                    CopyLogService.this.Be.add("/cache/tboxlog/");
                    CopyLogService.this.Bd.countDown();
                }
            }
        };
        this.Bi = new com.xiaopeng.commonfunc.b.a.a() { // from class: com.xiaopeng.devtools.system.service.-$$Lambda$CopyLogService$0s8ElonlqwADkAObXBQuDf96D4Y
            @Override // com.xiaopeng.commonfunc.b.a.a
            public final void onChangeEvent(CarPropertyValue carPropertyValue) {
                CopyLogService.this.a(carPropertyValue);
            }
        };
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.Bc = new com.xiaopeng.devtools.presenter.b.a();
        this.Bc.a(this.Bi);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x02fa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // android.app.IntentService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onHandleIntent(android.content.Intent r14) {
        /*
            Method dump skipped, instructions count: 816
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.devtools.system.service.CopyLogService.onHandleIntent(android.content.Intent):void");
    }

    private void d(final int i, boolean z) {
        if (z) {
            j.a(new Runnable() { // from class: com.xiaopeng.devtools.system.service.-$$Lambda$CopyLogService$5-is_FlxMnfjeOYpKiuSLFDaPGg
                @Override // java.lang.Runnable
                public final void run() {
                    CopyLogService.cA(i);
                }
            }, 10000L);
        } else {
            j.c(new Runnable() { // from class: com.xiaopeng.devtools.system.service.-$$Lambda$CopyLogService$lQtXM0PbwcZT5wlthnm02u8aTRY
                @Override // java.lang.Runnable
                public final void run() {
                    CopyLogService.cz(i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void cA(int i) {
        EventBus.getDefault().post(Integer.valueOf(i == 10001 ? 7 : 1));
        g.show(R.string.copy_data_success);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void cz(int i) {
        EventBus.getDefault().post(Integer.valueOf(i == 10001 ? 8 : 2));
        g.show(R.string.copy_data_fail);
    }

    @Override // android.app.IntentService, android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.Bc.onDestroy();
    }
}
