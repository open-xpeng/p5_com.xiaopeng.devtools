package com.xiaopeng.devtools.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaopeng.aftersales.AfterSalesManager;
import com.xiaopeng.devtools.MyApplication;
import com.xiaopeng.devtools.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: UIUtil.java */
/* loaded from: classes12.dex */
public class u {
    public static SparseArray<Button> a(ViewGroup viewGroup, Context context, View.OnClickListener onClickListener, HashMap<Integer, String> hashMap, int i) {
        int i2;
        SparseArray<Button> sparseArray = new SparseArray<>();
        viewGroup.removeAllViews();
        LinearLayout linearLayout = new LinearLayout(context);
        float f = 1.0f;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2, 1.0f);
        int i3 = 5;
        int i4 = 0;
        layoutParams.setMargins(0, 5, 0, 5);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(0);
        float f2 = i * 1.0f;
        linearLayout.setWeightSum(f2);
        linearLayout.setBaselineAligned(false);
        Iterator<Map.Entry<Integer, String>> it = hashMap.entrySet().iterator();
        LinearLayout linearLayout2 = linearLayout;
        int i5 = 0;
        while (it.hasNext()) {
            Map.Entry<Integer, String> next = it.next();
            i5++;
            Button button = new Button(context);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i4, 100, f);
            layoutParams2.setMargins(i3, i3, i3, i3);
            button.setLayoutParams(layoutParams2);
            button.setId(next.getKey().intValue());
            button.setTextColor(-16777216);
            button.setTextSize(30.0f);
            button.setText(next.getValue());
            button.setBackgroundResource(R.drawable.btn_background);
            button.setOnClickListener(onClickListener);
            StringBuilder sb = new StringBuilder();
            Iterator<Map.Entry<Integer, String>> it2 = it;
            sb.append("Create Button. id=");
            sb.append(next.getKey());
            sb.append(", name=");
            sb.append(next.getValue());
            com.xiaopeng.lib.utils.c.d("UIUtil", "createUI", sb.toString());
            linearLayout2.addView(button);
            sparseArray.put(next.getKey().intValue(), button);
            if (i5 % i == 0) {
                viewGroup.addView(linearLayout2);
                LinearLayout linearLayout3 = new LinearLayout(context);
                linearLayout3.setLayoutParams(layoutParams);
                i2 = 0;
                linearLayout3.setOrientation(0);
                linearLayout3.setWeightSum(f2);
                linearLayout3.setBaselineAligned(false);
                linearLayout2 = linearLayout3;
            } else {
                i2 = 0;
            }
            i4 = i2;
            it = it2;
            f = 1.0f;
            i3 = 5;
        }
        if (i5 % i != 0) {
            viewGroup.addView(linearLayout2);
        }
        return sparseArray;
    }

    public static void a(ViewGroup viewGroup, Context context, ArrayList<String> arrayList, int i) {
        viewGroup.removeAllViews();
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2, 1.0f);
        layoutParams.setMargins(0, 5, 0, 5);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setOrientation(0);
        float f = i * 1.0f;
        linearLayout.setWeightSum(f);
        Iterator<String> it = arrayList.iterator();
        LinearLayout linearLayout2 = linearLayout;
        int i2 = 0;
        while (it.hasNext()) {
            i2++;
            TextView textView = new TextView(context);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -2, 1.0f);
            layoutParams2.setMargins(5, 5, 5, 5);
            textView.setLayoutParams(layoutParams2);
            textView.setTextColor(-1);
            textView.setTextSize(32.0f);
            textView.setText(it.next());
            linearLayout2.addView(textView);
            if (i2 % i == 0) {
                viewGroup.addView(linearLayout2);
                linearLayout2 = new LinearLayout(context);
                linearLayout2.setLayoutParams(layoutParams);
                linearLayout2.setOrientation(0);
                linearLayout2.setWeightSum(f);
            }
        }
        if (i2 % i != 0) {
            viewGroup.addView(linearLayout2);
        }
    }

    public static void cr(final int i) {
        com.xiaopeng.lib.utils.j.c(new Runnable() { // from class: com.xiaopeng.devtools.utils.u.1
            @Override // java.lang.Runnable
            public void run() {
                com.xiaopeng.xui.app.g.i(MyApplication.getContext().getString(i));
            }
        });
    }

    public static void bd(final String str) {
        com.xiaopeng.lib.utils.j.c(new Runnable() { // from class: com.xiaopeng.devtools.utils.u.2
            @Override // java.lang.Runnable
            public void run() {
                if (!TextUtils.isEmpty(str)) {
                    com.xiaopeng.xui.app.g.i(str);
                }
            }
        });
    }

    public static boolean lF() {
        if (!((AfterSalesManager) MyApplication.getContext().getSystemService("xiaopeng_aftersales")).getRepairMode() && r.hL()) {
            cr(R.string.tip_switch_udisk_mountmode);
            return false;
        }
        return true;
    }
}
