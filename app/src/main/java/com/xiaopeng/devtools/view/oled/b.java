package com.xiaopeng.devtools.view.oled;

import android.support.v4.util.ArrayMap;
import com.xiaopeng.devtools.bean.oled.CustomOledMusicBook;
import com.xiaopeng.devtools.bean.oled.OledData;
import com.xiaopeng.devtools.presenter.oled.CANMsg387;
import java.util.List;

/* compiled from: IOledView.java */
/* loaded from: classes12.dex */
public interface b {
    void A(List<CustomOledMusicBook> list);

    void a(ArrayMap<String, List<CANMsg387>> arrayMap);

    void z(List<OledData> list);
}
