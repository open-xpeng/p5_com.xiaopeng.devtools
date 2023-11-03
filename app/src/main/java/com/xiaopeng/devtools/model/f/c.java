package com.xiaopeng.devtools.model.f;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.pso.PsoCrypto;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.event.signal.NeighboringEvent;
import com.xiaopeng.devtools.bean.event.signal.NetServiceEvent;
import com.xiaopeng.devtools.bean.event.signal.NetStrengthEvent;
import com.xiaopeng.devtools.utils.r;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

/* compiled from: NetworkModel.java */
/* loaded from: classes12.dex */
public class c {
    private static volatile c uo;
    private PhoneStateListener uk;
    private String um;
    private String un;
    private StringBuilder ul = new StringBuilder();
    private Handler mHandler = new Handler() { // from class: com.xiaopeng.devtools.model.f.c.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 50) {
                List<NeighboringCellInfo> neighboringCellInfo = c.this.mTelephonyManager.getNeighboringCellInfo();
                c.this.ul.setLength(0);
                if (neighboringCellInfo == null || neighboringCellInfo.isEmpty()) {
                    c.this.ul.append("N/A");
                } else {
                    for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
                        int cid = neighboringCellInfo2.getCid();
                        int lac = neighboringCellInfo2.getLac();
                        int rssi = (-131) + (2 * neighboringCellInfo2.getRssi());
                        StringBuilder sb = c.this.ul;
                        sb.append("\n");
                        sb.append("cell_id_0:");
                        sb.append(cid);
                        sb.append(", 区域码_0:");
                        sb.append(lac);
                        sb.append(", 信号强度_0:");
                        sb.append(rssi);
                    }
                }
                EventBus.getDefault().post(new NeighboringEvent(c.this.ul.toString()));
            }
        }
    };
    private TelephonyManager mTelephonyManager = (TelephonyManager) MyApplication.getContext().getSystemService("phone");
    private Context mContext = MyApplication.getContext();

    private c() {
        this.ul.append("N/A");
        this.un = "N/A";
        this.um = "N/A";
        hn();
    }

    private void hn() {
        if (this.uk == null) {
            this.uk = new PhoneStateListener() { // from class: com.xiaopeng.devtools.model.f.c.2
                @Override // android.telephony.PhoneStateListener
                public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                    super.onSignalStrengthsChanged(signalStrength);
                    String[] split = signalStrength.toString().split(" ");
                    if (c.this.mTelephonyManager.getNetworkType() != 13) {
                        if (c.this.mTelephonyManager.getNetworkType() == 8 || c.this.mTelephonyManager.getNetworkType() == 10 || c.this.mTelephonyManager.getNetworkType() == 9 || c.this.mTelephonyManager.getNetworkType() == 3) {
                            String hp = c.this.hp();
                            if (hp == c.this.mContext.getString(R.string.china_mobile)) {
                                c.this.um = "0";
                            } else if (hp != c.this.mContext.getString(R.string.china_unicom)) {
                                if (hp == c.this.mContext.getString(R.string.china_telecom)) {
                                    int evdoDbm = signalStrength.getEvdoDbm();
                                    c cVar = c.this;
                                    cVar.um = evdoDbm + "";
                                }
                            } else {
                                int cdmaDbm = signalStrength.getCdmaDbm();
                                c cVar2 = c.this;
                                cVar2.um = cdmaDbm + "";
                            }
                        } else {
                            int gsmSignalStrength = PsoCrypto.Desc.SaveAesCbcKeyFailed + (2 * signalStrength.getGsmSignalStrength());
                            c cVar3 = c.this;
                            cVar3.um = gsmSignalStrength + "";
                        }
                    } else {
                        int parseInt = Integer.parseInt(split[9]);
                        c cVar4 = c.this;
                        cVar4.um = parseInt + "";
                    }
                    EventBus.getDefault().post(new NetStrengthEvent(c.this.um));
                }

                @Override // android.telephony.PhoneStateListener
                public void onServiceStateChanged(ServiceState serviceState) {
                    super.onServiceStateChanged(serviceState);
                    String str = "N/A";
                    switch (serviceState.getState()) {
                        case 0:
                            str = MyApplication.getContext().getString(R.string.radioInfo_service_in);
                            break;
                        case 1:
                        case 2:
                            str = MyApplication.getContext().getString(R.string.radioInfo_service_out);
                            break;
                        case 3:
                            str = MyApplication.getContext().getString(R.string.radioInfo_service_off);
                            break;
                    }
                    c.this.un = str;
                    EventBus.getDefault().post(new NetServiceEvent(c.this.un));
                }

                @Override // android.telephony.PhoneStateListener
                public void onCellLocationChanged(CellLocation cellLocation) {
                    super.onCellLocationChanged(cellLocation);
                    c.this.mHandler.sendEmptyMessage(50);
                }
            };
            this.mTelephonyManager.listen(this.uk, 256);
            this.mTelephonyManager.listen(this.uk, 1);
            this.mTelephonyManager.listen(this.uk, 16);
        }
    }

    public static c ho() {
        if (uo == null) {
            synchronized (c.class) {
                if (uo == null) {
                    uo = new c();
                }
            }
        }
        return uo;
    }

    public String getIccid() {
        return r.getIccid();
    }

    public String getImei() {
        return this.mTelephonyManager.getDeviceId();
    }

    public String hp() {
        String string;
        try {
            String networkOperatorName = this.mTelephonyManager.getNetworkOperatorName();
            if (networkOperatorName == null) {
                return "N/A";
            }
            if (!networkOperatorName.toUpperCase().contains("MOBILE") && !networkOperatorName.equals(this.mContext.getString(R.string.china_mobile))) {
                if (!networkOperatorName.toUpperCase().contains("UNICOM") && !networkOperatorName.equals(this.mContext.getString(R.string.china_unicom))) {
                    if (!networkOperatorName.toUpperCase().contains("TELECOM") && !networkOperatorName.equals(this.mContext.getString(R.string.china_telecom))) {
                        return "N/A";
                    }
                    string = this.mContext.getString(R.string.china_telecom);
                    return string;
                }
                string = this.mContext.getString(R.string.china_unicom);
                return string;
            }
            string = this.mContext.getString(R.string.china_mobile);
            return string;
        } catch (Exception e) {
            e.printStackTrace();
            return "N/A";
        }
    }

    public String hq() {
        return System.getProperty("ril.cellinfo.band", "N/A");
    }

    public String hr() {
        return System.getProperty("ril.cellinfo.channel", "N/A");
    }

    public String hs() {
        return this.ul.toString();
    }

    public String ht() {
        return this.um;
    }

    public String hu() {
        return this.un;
    }
}
