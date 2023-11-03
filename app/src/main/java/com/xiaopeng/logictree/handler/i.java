package com.xiaopeng.logictree.handler;

import android.app.Application;
import android.net.InterfaceConfiguration;
import android.net.LinkAddress;
import android.os.INetworkManagementService;
import android.os.RemoteException;
import android.os.ServiceManager;
import java.net.InetAddress;

/* compiled from: NetworkInfo.java */
/* loaded from: classes12.dex */
public class i extends h {
    private final INetworkManagementService YB;

    public i(Application application) {
        super(application);
        this.CLASS_NAME = "NetworkInfo";
        this.YB = INetworkManagementService.Stub.asInterface(ServiceManager.getService("network_management"));
    }

    @Override // com.xiaopeng.logictree.handler.h
    public synchronized String a(com.xiaopeng.logictree.a aVar) {
        String str;
        super.a(aVar);
        if (a(this.YA, new String[]{"1"})) {
            com.xiaopeng.lib.utils.c.g(this.CLASS_NAME, "check whether iface exist ? ");
            boolean z = false;
            try {
                String[] listInterfaces = this.YB.listInterfaces();
                int length = listInterfaces.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    } else if (!listInterfaces[i].equalsIgnoreCase(this.YA[1])) {
                        i++;
                    } else {
                        z = true;
                        break;
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            if (z) {
                com.xiaopeng.logictree.d.pP();
            } else {
                com.xiaopeng.logictree.d.pO();
            }
        } else if (a(this.YA, new String[]{"2"})) {
            com.xiaopeng.lib.utils.c.g(this.CLASS_NAME, "check whether iface up ? ");
            InterfaceConfiguration dT = dT(this.YA[1]);
            if (dT != null && dT.isUp()) {
                com.xiaopeng.logictree.d.pP();
            } else {
                com.xiaopeng.logictree.d.pO();
            }
        } else if (a(this.YA, new String[]{"3"})) {
            com.xiaopeng.lib.utils.c.g(this.CLASS_NAME, "check whether ip address exist ? ");
            InterfaceConfiguration dT2 = dT(this.YA[1]);
            if (dT2 != null) {
                LinkAddress linkAddress = dT2.getLinkAddress();
                com.xiaopeng.lib.utils.c.g("getIpAddress", "linkAddr" + linkAddress.toString());
                InetAddress address = linkAddress.getAddress();
                com.xiaopeng.lib.utils.c.g("getIpAddress", "inetaddr" + address.toString());
                str = address.getHostAddress();
                if (str != null) {
                    com.xiaopeng.lib.utils.c.g("getIpAddress", "address " + str);
                }
            } else {
                str = null;
            }
            if (str != null) {
                com.xiaopeng.logictree.d.pP();
            } else {
                com.xiaopeng.logictree.d.pO();
            }
        } else if (a(this.YA, new String[]{"4"})) {
            com.xiaopeng.lib.utils.c.g(this.CLASS_NAME, "check whether phy link up ? ");
            if ("UP".equalsIgnoreCase(com.xiaopeng.commonfunc.utils.l.eF())) {
                com.xiaopeng.logictree.d.pP();
            } else {
                com.xiaopeng.commonfunc.utils.l.eG();
                com.xiaopeng.logictree.d.pO();
            }
        }
        return null;
    }

    private InterfaceConfiguration dT(String str) {
        try {
            return this.YB.getInterfaceConfig(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.xiaopeng.logictree.handler.h
    public void destroy() {
        super.destroy();
    }
}
