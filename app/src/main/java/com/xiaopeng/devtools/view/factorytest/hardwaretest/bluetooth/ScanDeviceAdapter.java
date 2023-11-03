package com.xiaopeng.devtools.view.factorytest.hardwaretest.bluetooth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.bean.factorytest.ScanDevice;
import java.util.List;

/* loaded from: classes12.dex */
public class ScanDeviceAdapter extends BaseAdapter {
    private List<ScanDevice> Jf;
    private int Jg;
    private String mAddress;
    private Context mContext = MyApplication.getContext();

    public ScanDeviceAdapter(List<ScanDevice> list) {
        this.Jf = list;
    }

    public void setScanDevices(List<ScanDevice> list) {
        this.Jf = list;
    }

    public void setAddress(String str) {
        this.mAddress = str;
    }

    public void setHintId(int i) {
        this.Jg = i;
    }

    public String getAddress() {
        return this.mAddress;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.Jf.size();
    }

    public ScanDevice cV(int i) {
        return this.Jf.get(i);
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            a aVar = new a();
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.layout_scan_device_item, (ViewGroup) null);
            aVar.Jh = (TextView) inflate.findViewById(R.id.scan_device_name_tv);
            aVar.Ji = (TextView) inflate.findViewById(R.id.scan_device_address_tv);
            aVar.Jk = (TextView) inflate.findViewById(R.id.scan_device_pair_tv);
            aVar.Jj = (TextView) inflate.findViewById(R.id.scan_device_rssi_tv);
            inflate.setTag(aVar);
            view = inflate;
        }
        a aVar2 = (a) view.getTag();
        ScanDevice scanDevice = this.Jf.get(i);
        aVar2.Jh.setText(scanDevice.getName());
        aVar2.Ji.setText(this.mContext.getString(R.string.format_with_bracket, scanDevice.getAddress()));
        aVar2.Jj.setText(this.mContext.getString(R.string.rssi, Integer.valueOf(scanDevice.getRssi())));
        if (!this.Jf.get(i).getAddress().equals(this.mAddress)) {
            aVar2.Jk.setText((CharSequence) null);
            aVar2.Jk.setVisibility(8);
        } else {
            aVar2.Jk.setVisibility(0);
            aVar2.Jk.setText(this.Jg);
        }
        return view;
    }

    /* loaded from: classes12.dex */
    static class a {
        private TextView Jh;
        private TextView Ji;
        private TextView Jj;
        private TextView Jk;

        a() {
        }
    }
}
