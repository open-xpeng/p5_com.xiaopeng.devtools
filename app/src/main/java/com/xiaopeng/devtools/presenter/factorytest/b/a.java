package com.xiaopeng.devtools.presenter.factorytest.b;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.xiaopeng.a.a;
import com.xiaopeng.aftersales.AfterSalesManager;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.devdebug.drivemode.DriveModeActivity;
import com.xiaopeng.devtools.devdebug.hwcheck.HwCheckActivity;
import com.xiaopeng.devtools.model.c.b;
import com.xiaopeng.devtools.system.nativeImp.Security;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.devtools.view.MapDataCopyActivity;
import com.xiaopeng.devtools.view.aftersales.AfterSalesActivity;
import com.xiaopeng.devtools.view.aftersales.OneClickDiagnosisActivity;
import com.xiaopeng.devtools.view.aftersales.WlanPcToolsActivity;
import com.xiaopeng.devtools.view.aftersales.cdu.DiagnosisRecordActivity;
import com.xiaopeng.devtools.view.can.CanDataCollectActivity;
import com.xiaopeng.devtools.view.clean.ClearDataActivity;
import com.xiaopeng.devtools.view.copy.VideoCopyActivity;
import com.xiaopeng.devtools.view.customerservice.CsDebugToolActivity;
import com.xiaopeng.devtools.view.debug.GpsDebugActivity;
import com.xiaopeng.devtools.view.demo.AIMediaCopyActivity;
import com.xiaopeng.devtools.view.ecucheck.als.AlsStudyActivity;
import com.xiaopeng.devtools.view.factorytest.FactoryModeActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.DesignVerifyActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.HWAndSWInfoActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.camera.CsTopCameraActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.xpu.XpuActivateActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.xpu.XpuImageTestActivity;
import com.xiaopeng.devtools.view.factorytest.hardwaretest.xpu.XpuTestActivity;
import com.xiaopeng.devtools.view.factorytest.vehicle.VehicleFactoryTestActivity;
import com.xiaopeng.devtools.view.log.GrabLogActivity;
import com.xiaopeng.devtools.view.modemui.ModemUiActivity;
import com.xiaopeng.devtools.view.nationalstandard.NationalStandardActivity;
import com.xiaopeng.devtools.view.pre_env.PreEnvActivity;
import com.xiaopeng.devtools.view.smartdrive.DriveAllTestActivity;
import com.xiaopeng.devtools.view.systeminfo.AppListActivity;
import com.xiaopeng.devtools.view.systeminfo.EcuVersionActivity;
import com.xiaopeng.devtools.view.systeminfo.SystemVersionActivity;
import com.xiaopeng.devtools.view.update.TestUpdateActivity;
import com.xiaopeng.devtools.view.usersettings.CfcActivity;
import com.xiaopeng.devtools.view.usersettings.LteApnActivity;
import com.xiaopeng.devtools.view.usersettings.NedcModeActivity;
import com.xiaopeng.devtools.view.usersettings.RepairModeActivity;
import com.xiaopeng.devtools.view.usersettings.UserSettingsActivity;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;
import com.xiaopeng.xui.app.g;
import java.util.Map;

/* compiled from: SecurityCheckPresenter.java */
/* loaded from: classes12.dex */
public class a {
    private static final boolean ye = r.lj();
    private static final int yf = r.lD();
    private AfterSalesManager vl;
    private Map<String, com.xiaopeng.devtools.model.c.a> yg;
    private Security yj;
    private String sa = "";
    private boolean yh = false;
    private String yi = "";

    public a() {
        boolean z = false;
        L((r.lv() || r.lz()) ? true : true);
        jF();
        cb(r.getHardwareId());
        this.vl = (AfterSalesManager) MyApplication.getContext().getSystemService("xiaopeng_aftersales");
        this.yj = new Security();
    }

    public void L(boolean z) {
        this.yh = z;
    }

    public void jF() {
        this.yg = com.xiaopeng.devtools.model.c.a.fE();
    }

    public void cb(String str) {
        this.yi = str;
    }

    public boolean cc(String str) {
        com.xiaopeng.devtools.model.c.a aVar = this.yg.get(str);
        if (aVar != null) {
            return aVar.fC();
        }
        return false;
    }

    public void cd(String str) {
        c.g("SecurityCheckPresenter", "unlockCateIdState id = " + str);
        com.xiaopeng.devtools.model.c.a aVar = this.yg.get(str);
        if (aVar != null) {
            aVar.E(true);
        }
    }

    public void ce(String str) {
        c.g("SecurityCheckPresenter", "lockCateIdState id = " + str);
        com.xiaopeng.devtools.model.c.a aVar = this.yg.get(str);
        if (aVar != null) {
            aVar.E(false);
        }
    }

    public void e(String str, boolean z) {
        com.xiaopeng.devtools.model.c.c.a("factory.verify.times", str, z);
    }

    public boolean cf(String str) {
        if (this.yh) {
            c.g("SecurityCheckPresenter", MyApplication.getContext().getString(R.string.text_factory_check_build_pass));
            return true;
        } else if (com.xiaopeng.devtools.model.a.c.fx().isFactoryMode()) {
            c.g("SecurityCheckPresenter", "under factory mode, can use factory code");
            return true;
        } else if (com.xiaopeng.devtools.model.a.c.fx().fy()) {
            c.g("SecurityCheckPresenter", "factory code is temperarily unlocked by mcu");
            return true;
        } else {
            this.sa = b.bq(str);
            c.g("SecurityCheckPresenter", " preVerifyFactoryCode() mCateId:" + this.sa);
            b a = b.a(this.yg, str);
            if (a == null) {
                return false;
            }
            if (!a.fG()) {
                c.g("SecurityCheckPresenter", MyApplication.getContext().getString(R.string.text_factory_check_build_deny));
                return false;
            } else if (!a.fI()) {
                c.g("SecurityCheckPresenter", MyApplication.getContext().getString(R.string.text_factory_check_remote_deny));
                return false;
            } else if (a.fH()) {
                c.g("SecurityCheckPresenter", MyApplication.getContext().getString(R.string.text_factory_check_unique_pass));
                return true;
            } else if (!cc(this.sa)) {
                c.g("SecurityCheckPresenter", MyApplication.getContext().getString(R.string.text_factory_check_secret_deny));
                return false;
            } else if (yf <= 1 || this.yj.checkUnlockTimeValid(this.yg.get(this.sa).fD())) {
                return true;
            } else {
                c.g("SecurityCheckPresenter", MyApplication.getContext().getString(R.string.text_factory_check_unlock_time_deny));
                ce(this.sa);
                return false;
            }
        }
    }

    public boolean e(String str, Context context) {
        c.g("SecurityCheckPresenter", " handleSecretKey() keyInput:" + str);
        if (this.yh) {
            c.g("SecurityCheckPresenter", MyApplication.getContext().getString(R.string.text_factory_check_build_pass));
            return true;
        }
        this.sa = b.br(str);
        c.g("SecurityCheckPresenter", " handleSecretKey() mCateId:" + this.sa);
        if (cc(this.sa)) {
            c.g("SecurityCheckPresenter", MyApplication.getContext().getString(R.string.text_secret_check_passed));
            return true;
        }
        boolean z = false;
        if (yf == 1) {
            z = this.yj.isUnlockKeyValid(str);
        } else if (yf == 2) {
            z = this.yj.isUnlockKeyValidV2(str);
        }
        e(this.sa, z);
        if (z) {
            c.g("SecurityCheckPresenter", MyApplication.getContext().getString(R.string.text_secret_check_pass));
            cd(this.sa);
        } else {
            c.g("SecurityCheckPresenter", MyApplication.getContext().getString(R.string.text_secret_check_deny));
        }
        return cc(this.sa);
    }

    public boolean f(String str, Context context) {
        c.g("SecurityCheckPresenter", " handleFactoryCode() str:" + str);
        if (cf(str)) {
            try {
                g(str, context);
                return true;
            } catch (Exception e) {
                c.i("SecurityCheckPresenter", e.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean cg(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.matches("\\u002A#[0-9]{2}\\u002A[0-9]{8}#\\u002A$");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void g(String str, Context context) {
        char c;
        c.g("SecurityCheckPresenter", " handleActivity() code:" + str);
        switch (str.hashCode()) {
            case -1910862486:
                if (str.equals("*#9925*111#*")) {
                    c = 31;
                    break;
                }
                c = 65535;
                break;
            case -1910832695:
                if (str.equals("*#9925*121#*")) {
                    c = ' ';
                    break;
                }
                c = 65535;
                break;
            case -1910802904:
                if (str.equals("*#9925*131#*")) {
                    c = '!';
                    break;
                }
                c = 65535;
                break;
            case -1909938965:
                if (str.equals("*#9925*211#*")) {
                    c = '\"';
                    break;
                }
                c = 65535;
                break;
            case -1692589534:
                if (str.equals("*#0101#*")) {
                    c = '.';
                    break;
                }
                c = 65535;
                break;
            case -1662974397:
                if (str.equals("*#1224#*")) {
                    c = ',';
                    break;
                }
                c = 65535;
                break;
            case -1662973436:
                if (str.equals("*#1225#*")) {
                    c = '-';
                    break;
                }
                c = 65535;
                break;
            case -328030462:
                if (str.equals("*#400#*")) {
                    c = '+';
                    break;
                }
                c = 65535;
                break;
            case -309102816:
                if (str.equals("*#9387*111#*")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -309073025:
                if (str.equals("*#9387*121#*")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -309072064:
                if (str.equals("*#9387*122#*")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -309071103:
                if (str.equals("*#9387*123#*")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -309043234:
                if (str.equals("*#9387*131#*")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -309042273:
                if (str.equals("*#9387*132#*")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -309041312:
                if (str.equals("*#9387*133#*")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case -309040351:
                if (str.equals("*#9387*134#*")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case -309039390:
                if (str.equals("*#9387*135#*")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case -309013443:
                if (str.equals("*#9387*141#*")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case -309012482:
                if (str.equals("*#9387*142#*")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case -309011521:
                if (str.equals("*#9387*143#*")) {
                    c = '\r';
                    break;
                }
                c = 65535;
                break;
            case -308983652:
                if (str.equals("*#9387*151#*")) {
                    c = 14;
                    break;
                }
                c = 65535;
                break;
            case -308953861:
                if (str.equals("*#9387*161#*")) {
                    c = 15;
                    break;
                }
                c = 65535;
                break;
            case -308924070:
                if (str.equals("*#9387*171#*")) {
                    c = 16;
                    break;
                }
                c = 65535;
                break;
            case -308179295:
                if (str.equals("*#9387*211#*")) {
                    c = 17;
                    break;
                }
                c = 65535;
                break;
            case -308178334:
                if (str.equals("*#9387*212#*")) {
                    c = 18;
                    break;
                }
                c = 65535;
                break;
            case -308177373:
                if (str.equals("*#9387*213#*")) {
                    c = 19;
                    break;
                }
                c = 65535;
                break;
            case -308176412:
                if (str.equals("*#9387*214#*")) {
                    c = 20;
                    break;
                }
                c = 65535;
                break;
            case -308175451:
                if (str.equals("*#9387*215#*")) {
                    c = 21;
                    break;
                }
                c = 65535;
                break;
            case -307255774:
                if (str.equals("*#9387*311#*")) {
                    c = 25;
                    break;
                }
                c = 65535;
                break;
            case -307254813:
                if (str.equals("*#9387*312#*")) {
                    c = 26;
                    break;
                }
                c = 65535;
                break;
            case -307253852:
                if (str.equals("*#9387*313#*")) {
                    c = 27;
                    break;
                }
                c = 65535;
                break;
            case -307252891:
                if (str.equals("*#9387*314#*")) {
                    c = 28;
                    break;
                }
                c = 65535;
                break;
            case -307251930:
                if (str.equals("*#9387*315#*")) {
                    c = 23;
                    break;
                }
                c = 65535;
                break;
            case -307225983:
                if (str.equals("*#9387*321#*")) {
                    c = 24;
                    break;
                }
                c = 65535;
                break;
            case -306332253:
                if (str.equals("*#9387*411#*")) {
                    c = 29;
                    break;
                }
                c = 65535;
                break;
            case -305408732:
                if (str.equals("*#9387*511#*")) {
                    c = 30;
                    break;
                }
                c = 65535;
                break;
            case -70960922:
                if (str.equals("*#9723*111#*")) {
                    c = '#';
                    break;
                }
                c = 65535;
                break;
            case -70931131:
                if (str.equals("*#9723*121#*")) {
                    c = '$';
                    break;
                }
                c = 65535;
                break;
            case -70930170:
                if (str.equals("*#9723*122#*")) {
                    c = '%';
                    break;
                }
                c = 65535;
                break;
            case -70901340:
                if (str.equals("*#9723*131#*")) {
                    c = '&';
                    break;
                }
                c = 65535;
                break;
            case -70037401:
                if (str.equals("*#9723*211#*")) {
                    c = '\'';
                    break;
                }
                c = 65535;
                break;
            case 39885510:
                if (str.equals("*#8#*")) {
                    c = '*';
                    break;
                }
                c = 65535;
                break;
            case 545890219:
                if (str.equals("*#4227*2#*")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 613991754:
                if (str.equals("*#4227*111#*")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1651736319:
                if (str.equals("*#7494*111#*")) {
                    c = '(';
                    break;
                }
                c = 65535;
                break;
            case 1651766110:
                if (str.equals("*#7494*121#*")) {
                    c = ')';
                    break;
                }
                c = 65535;
                break;
            case 1729285889:
                if (str.equals("*#9387*2#*")) {
                    c = 22;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case '#':
                return;
            case 1:
                a(context, VehicleFactoryTestActivity.class);
                return;
            case 2:
                aa(context);
                return;
            case 3:
                context.sendBroadcast(new Intent("com.xiaopeng.secret_code_action", Uri.parse("android_secret_code://0808")));
                return;
            case 4:
                e(context, "com.aispeech.aios", "com.aispeech.aios.SettingActivity");
                return;
            case 5:
                a(context, DiagnosisRecordActivity.class);
                return;
            case 6:
                a(context, GrabLogActivity.class);
                return;
            case 7:
                a(context, ClearDataActivity.class);
                return;
            case '\b':
                a(context, PreEnvActivity.class);
                return;
            case '\t':
                a(context, VideoCopyActivity.class);
                return;
            case '\n':
                a(context, CfcActivity.class);
                return;
            case 11:
                a(context, UserSettingsActivity.class);
                return;
            case '\f':
                a(context, GpsDebugActivity.class);
                return;
            case '\r':
                a(context, LteApnActivity.class);
                return;
            case 14:
                a(context, TestUpdateActivity.class);
                return;
            case 15:
                a(context, WlanPcToolsActivity.class);
                return;
            case 16:
                a(context, OneClickDiagnosisActivity.class);
                return;
            case 17:
                a(context, DriveAllTestActivity.class);
                return;
            case 18:
                e(context, "com.xpeng.ai", "com.xpeng.ai.HeartBeatActivity");
                return;
            case 19:
                a(context, NedcModeActivity.class);
                return;
            case 20:
                a(context, XpuActivateActivity.class);
                return;
            case 21:
                a(context, CsTopCameraActivity.class);
                return;
            case 22:
                if (a.b.getBoolean("XPU_LVDS")) {
                    a(context, XpuTestActivity.class);
                    return;
                } else {
                    a(context, XpuImageTestActivity.class);
                    return;
                }
            case 23:
                a(context, AlsStudyActivity.class);
                return;
            case 24:
                a(context, FactoryModeActivity.class);
                return;
            case 25:
                a(context, HwCheckActivity.class);
                return;
            case 26:
                a(context, ModemUiActivity.class);
                return;
            case 27:
                a(context, DesignVerifyActivity.class);
                return;
            case 28:
                a(context, CanDataCollectActivity.class);
                return;
            case 29:
                a(context, DriveModeActivity.class);
                return;
            case 30:
                a(context, MapDataCopyActivity.class);
                return;
            case 31:
                a(context, SystemVersionActivity.class);
                return;
            case ' ':
                a(context, AppListActivity.class);
                return;
            case '!':
                a(context, HWAndSWInfoActivity.class);
                return;
            case '\"':
                a(context, EcuVersionActivity.class);
                return;
            case '$':
                Intent intent = new Intent("com.xiaopeng.factorytest.ACTION_FACTORY_CAR_SHOW");
                intent.setPackage("com.xiaopeng.autoshow");
                context.sendBroadcast(intent);
                return;
            case '%':
                Intent intent2 = new Intent("com.xiaopeng.factorytest.ACTION_FACTORY_CAR_TESTDRIVE");
                intent2.setPackage("com.xiaopeng.autoshow");
                context.sendBroadcast(intent2);
                return;
            case '&':
                a(context, AIMediaCopyActivity.class);
                return;
            case '\'':
                a(context, NationalStandardActivity.class);
                return;
            case '(':
                a(context, CsDebugToolActivity.class);
                return;
            case ')':
                a(context, RepairModeActivity.class);
                return;
            case '*':
                if (this.vl.getRepairMode()) {
                    a(context, AfterSalesActivity.class);
                    return;
                }
                return;
            case '+':
                if (!ye) {
                    c.f("SecurityCheckPresenter", "just chn region support");
                    return;
                } else if (this.vl.getAuthMode()) {
                    cr(R.string.under_auth_mode);
                    return;
                } else {
                    new com.xiaopeng.devtools.presenter.aftersales.a(context).showDialog();
                    return;
                }
            case ',':
                B("christmaseve", "com.xiaopeng.musicradio");
                return;
            case '-':
                B("christmas", "com.xiaopeng.musicradio");
                return;
            case '.':
                B("newyear", "com.xiaopeng.musicradio");
                return;
            default:
                c.g("SecurityCheckPresenter", String.format("cannot handleActivity code :", str));
                return;
        }
    }

    private void B(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("string_msg", str);
        MyApplication.fd().sendData(60001, bundle, str2);
    }

    private void a(Context context, Class cls) {
        Intent intent = new Intent(context, cls);
        intent.setFlags(268468224);
        context.startActivity(intent);
    }

    private void e(Context context, String str, String str2) {
        Intent intent = new Intent();
        intent.setClassName(str, str2);
        intent.setFlags(268468224);
        context.startActivity(intent);
    }

    private void aa(Context context) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    private void cr(final int i) {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.presenter.factorytest.b.-$$Lambda$a$cRpb3-sTr-oE6_XBt0brFG0KGsg
            @Override // java.lang.Runnable
            public final void run() {
                g.ee(i);
            }
        });
    }
}
