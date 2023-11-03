package com.xiaopeng.devtools.system.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import com.xiaopeng.aftersales.AfterSalesManager;
import com.xiaopeng.devtools.devdebug.drivemode.a;
import com.xiaopeng.devtools.model.i.b;
import com.xiaopeng.devtools.system.service.SecurityCheckService;
import com.xiaopeng.devtools.view.aftersales.AfterSalesActivity;
import com.xiaopeng.lib.utils.c;

/* loaded from: classes12.dex */
public class AppBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        c.f("AppBroadcastReceiver", "Action = " + action);
        if (action == null) {
            return;
        }
        char c = 65535;
        switch (action.hashCode()) {
            case -1805061056:
                if (action.equals("com.xiaopeng.action.SET_DRIVE_MODEFROM_FACTORYTEST")) {
                    c = 1;
                    break;
                }
                break;
            case -895268132:
                if (action.equals("com.xiaopeng.action.HIDDEN_MENU_CODE")) {
                    c = 3;
                    break;
                }
                break;
            case 71883562:
                if (action.equals("com.xiaopeng.action.IVI_DIAGNOSIS")) {
                    c = 4;
                    break;
                }
                break;
            case 1469671768:
                if (action.equals("com.xiaopeng.action.SET_DRIVE_MODE_FROM_CARCONTROL")) {
                    c = 0;
                    break;
                }
                break;
            case 2123077582:
                if (action.equals("com.xiaopeng.action.OPERATE_LOG_SWITCH")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
                a.bR(intent.getIntExtra("extra_boolean_remote_set_drive_mode_which", 1));
                return;
            case 2:
                b.hN().G(intent.getIntExtra("switchStatus", 0) == 1);
                return;
            case 3:
                String stringExtra = intent.getStringExtra("code");
                Intent intent2 = new Intent(context, SecurityCheckService.class);
                intent2.putExtra("code", stringExtra);
                context.startServiceAsUser(intent2, UserHandle.CURRENT);
                return;
            case 4:
                if (ab(context)) {
                    com.xiaopeng.devtools.utils.a.a(context, AfterSalesActivity.class);
                    return;
                }
                return;
            default:
                return;
        }
    }

    private boolean ab(Context context) {
        return ((AfterSalesManager) context.getSystemService("xiaopeng_aftersales")).getRepairMode();
    }
}
