package com.xiaopeng.xui.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import com.xiaopeng.xpui.R;
import com.xiaopeng.xui.app.d;
import com.xiaopeng.xui.widget.timepicker.XTimePicker;

/* compiled from: XTimePickerDialog.java */
/* loaded from: classes13.dex */
public class f extends c implements d.a, XTimePicker.a {
    private final XTimePicker aaq;
    private final a aar;

    /* compiled from: XTimePickerDialog.java */
    /* loaded from: classes13.dex */
    public interface a {
        void onTimeSet(XTimePicker xTimePicker, int i, int i2);
    }

    public f(@NonNull Context context, @Nullable a aVar, int i, int i2) {
        this(context, 0, aVar, i, i2);
    }

    public f(@NonNull Context context, @StyleRes int i, @Nullable a aVar, int i2, int i3) {
        super(context, resolveDialogTheme(context, i));
        this.aar = aVar;
        View inflate = LayoutInflater.from(context).inflate(R.layout.x_time_picker, qu(), false);
        d(inflate, false);
        a(" ", this);
        b(" ", this);
        this.aaq = (XTimePicker) inflate.findViewById(R.id.x_time_picker);
        this.aaq.setCurrentHour(Integer.valueOf(i2));
        this.aaq.setCurrentMinute(Integer.valueOf(i3));
        this.aaq.setOnTimeChangedListener(this);
    }

    static int resolveDialogTheme(Context context, int i) {
        if (i == 0) {
            return R.style.XDialogView_Large;
        }
        return i;
    }

    @Override // com.xiaopeng.xui.app.c
    @Deprecated
    public c a(@Nullable CharSequence charSequence, d.a aVar) {
        return super.a(charSequence, this);
    }

    @Override // com.xiaopeng.xui.app.c
    @Deprecated
    public c b(@Nullable CharSequence charSequence, d.a aVar) {
        return super.b(charSequence, this);
    }

    @Override // com.xiaopeng.xui.widget.timepicker.XTimePicker.a
    public void a(XTimePicker xTimePicker, int i, int i2) {
    }

    @Override // com.xiaopeng.xui.app.d.a
    public void onClick(c cVar, int i) {
        switch (i) {
            case -2:
                super.getDialog().cancel();
                return;
            case -1:
                if (this.aar != null) {
                    this.aar.onTimeSet(this.aaq, this.aaq.getCurrentHour().intValue(), this.aaq.getCurrentMinute().intValue());
                    return;
                }
                return;
            default:
                return;
        }
    }
}
