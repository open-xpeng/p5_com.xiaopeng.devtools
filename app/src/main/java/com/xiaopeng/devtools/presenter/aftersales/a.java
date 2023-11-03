package com.xiaopeng.devtools.presenter.aftersales;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.google.gson.Gson;
import com.xiaopeng.a.a;
import com.xiaopeng.aftersales.AfterSalesManager;
import com.xiaopeng.devtools.R;
import com.xiaopeng.devtools.utils.r;
import com.xiaopeng.lib.framework.module.Module;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IHttp;
import com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.IResponse;
import com.xiaopeng.lib.framework.netchannelmodule.NetworkChannelsEntry;
import com.xiaopeng.lib.utils.j;
import com.xiaopeng.xui.app.d;
import com.xiaopeng.xui.widget.XCompoundButton;
import java.util.HashMap;

/* compiled from: AuthModePresenter.java */
/* loaded from: classes12.dex */
public class a {
    private static final String vi = com.xiaopeng.lib.utils.a.a.WY + a.f.ed("REQUEST_TO_ENTER_AUTH_MODE");
    private Context mContext;
    private com.xiaopeng.xui.app.e pe;
    private com.xiaopeng.xui.app.c vj;
    private XCompoundButton vk;
    private AfterSalesManager vl;

    public a(Context context) {
        this.mContext = context;
        this.vl = (AfterSalesManager) context.getSystemService("xiaopeng_aftersales");
    }

    public void showDialog() {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.presenter.aftersales.-$$Lambda$a$MXn0Nd2449-4Nqwg8TLSp7j-NX0
            @Override // java.lang.Runnable
            public final void run() {
                a.this.hZ();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void hZ() {
        this.vj = new com.xiaopeng.xui.app.c(this.mContext, 2131821106);
        this.pe = new com.xiaopeng.xui.app.e(this.mContext, 2131821043);
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.authmode_protocol_read, this.vj.qu(), false);
        final TextView textView = (TextView) inflate.findViewById(R.id.protocol_text);
        textView.setText(R.string.agreement_authmode_protocol_tips);
        this.vk = (XCompoundButton) inflate.findViewById(R.id.checkboxbtn);
        final TextView textView2 = (TextView) inflate.findViewById(R.id.agreement_privacy);
        textView2.setTextColor(this.mContext.getResources().getColorStateList(R.color.agreement_selector_color, null));
        textView2.setSelected(false);
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.xiaopeng.devtools.presenter.aftersales.-$$Lambda$a$2a37I8Ana55PrteU7uak-6VBqGM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                a.this.a(textView2, textView, view);
            }
        });
        this.vk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.xiaopeng.devtools.presenter.aftersales.-$$Lambda$a$7OOI8ghjFgQtClyY_3XwLuNLjXc
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                a.this.a(compoundButton, z);
            }
        });
        this.vj.dV(R.string.agreement_authmode_title).d(inflate, false).a(this.mContext.getString(R.string.agree_to_enter_authmode), new d.a() { // from class: com.xiaopeng.devtools.presenter.aftersales.-$$Lambda$a$iESobsKfLRDpUafypdb27ZA1cl8
            @Override // com.xiaopeng.xui.app.d.a
            public final void onClick(com.xiaopeng.xui.app.c cVar, int i) {
                a.this.a(cVar, i);
            }
        }).b(this.mContext.getString(R.string.not_agree_to_enter_authmode), (d.a) null);
        this.vj.aX(false);
        this.vk.setChecked(false);
        this.vj.dX(2008);
        this.vj.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(TextView textView, TextView textView2, View view) {
        textView.setSelected(true);
        textView2.setText(R.string.agreement_privacy_detail);
        this.vj.dV(R.string.agreement_privacy_title);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.vj.aX(true);
        } else {
            this.vj.aX(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(com.xiaopeng.xui.app.c cVar, int i) {
        if (this.vk.isChecked()) {
            this.pe.getWindow().setType(2047);
            this.pe.create();
            this.pe.setMessage(this.mContext.getString(R.string.switching_authmode));
            this.pe.show();
            hV();
        }
    }

    private void hV() {
        j.execute(new Runnable() { // from class: com.xiaopeng.devtools.presenter.aftersales.-$$Lambda$a$QZNde5Y5O87OKZZCOAflUA-5s5Y
            @Override // java.lang.Runnable
            public final void run() {
                a.this.hY();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void hY() {
        String eH = r.eH();
        String hardwareId = r.getHardwareId();
        String iccid = r.getIccid();
        if (eH.length() > 0 && hardwareId.length() > 0 && iccid.length() > 0) {
            final String bI = com.xiaopeng.devtools.utils.d.bI(16);
            HashMap hashMap = new HashMap();
            hashMap.put("vin", eH);
            hashMap.put("cduId", hardwareId);
            hashMap.put("iccid", iccid);
            hashMap.put("password", bI);
            hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
            IHttp iHttp = (IHttp) Module.get(NetworkChannelsEntry.class).get(IHttp.class);
            iHttp.cancelTag(vi);
            iHttp.bizHelper().post(vi, new Gson().toJson(hashMap)).needAuthorizationInfo().enableSecurityEncoding().build().tag(vi).execute(new Callback() { // from class: com.xiaopeng.devtools.presenter.aftersales.a.1
                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onSuccess(IResponse iResponse) {
                    com.xiaopeng.lib.http.a.a a = com.xiaopeng.devtools.utils.d.a(iResponse);
                    if (a == null || a.getCode() != 200) {
                        a.this.hW();
                        return;
                    }
                    try {
                        a.this.vl.enableAuthMode(bI, Long.valueOf(a.getData()).longValue());
                        if (a.this.pe != null) {
                            a.this.pe.dismiss();
                        }
                    } catch (NumberFormatException e) {
                        com.xiaopeng.lib.utils.c.i("AuthModePresenter", "e:" + e.toString());
                        a.this.hW();
                    }
                }

                @Override // com.xiaopeng.lib.framework.moduleinterface.netchannelmodule.http.Callback
                public void onFailure(IResponse iResponse) {
                    a.this.hW();
                }
            });
            return;
        }
        com.xiaopeng.lib.utils.c.i("AuthModePresenter", "fail to request auth mode VIN = " + eH + ", cduid = " + hardwareId + ", iccid = " + iccid);
        hW();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hW() {
        j.c(new Runnable() { // from class: com.xiaopeng.devtools.presenter.aftersales.-$$Lambda$a$CeYT8gK6XfIg1zORaCz_7Epvpl4
            @Override // java.lang.Runnable
            public final void run() {
                a.this.hX();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void hX() {
        if (this.pe != null) {
            this.pe.dismiss();
        }
        com.xiaopeng.xui.app.g.k(this.mContext.getString(R.string.fail_request_authmode));
    }
}
