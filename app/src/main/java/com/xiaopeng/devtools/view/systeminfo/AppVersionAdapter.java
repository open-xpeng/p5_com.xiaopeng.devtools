package com.xiaopeng.devtools.view.systeminfo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes12.dex */
public class AppVersionAdapter extends BaseAdapter {
    private Context mContext = MyApplication.getContext();
    private PackageManager TF = this.mContext.getPackageManager();
    private List<PackageInfo> TD = new ArrayList();

    public void setPackageInfos(List<PackageInfo> list) {
        this.TD = list;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.TD.size();
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
            View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.app_version_item, (ViewGroup) null);
            aVar.TG = (TextView) inflate.findViewById(R.id.tv_app_version);
            inflate.setTag(aVar);
            view = inflate;
        }
        PackageInfo packageInfo = this.TD.get(i);
        ((a) view.getTag()).TG.setText(this.mContext.getString(R.string.text_package_version_name, this.TF.getApplicationLabel(packageInfo.applicationInfo), packageInfo.versionName));
        return view;
    }

    /* loaded from: classes12.dex */
    static class a {
        TextView TG;

        a() {
        }
    }
}
