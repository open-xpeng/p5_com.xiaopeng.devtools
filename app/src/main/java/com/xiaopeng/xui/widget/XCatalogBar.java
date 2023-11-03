package com.xiaopeng.xui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RadioButton;
import com.xiaopeng.xpui.R;

/* loaded from: classes13.dex */
public class XCatalogBar extends XRadioGroup {
    private int[] acL;
    private String[] mNames;
    private int mOrientation;

    public XCatalogBar(Context context) {
        this(context, null);
    }

    public XCatalogBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.XCatalogBar);
        int integer = obtainStyledAttributes.getInteger(R.styleable.XCatalogBar_catalog_check_position, 0);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.XCatalogBar_catalog_text_array, 0);
        if (resourceId != 0) {
            this.mNames = getResources().getStringArray(resourceId);
        }
        int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.XCatalogBar_catalog_icon_array, 0);
        if (resourceId2 != 0) {
            String[] stringArray = getResources().getStringArray(resourceId2);
            if (stringArray.length > 0) {
                this.acL = new int[stringArray.length];
                for (int i = 0; i < stringArray.length; i++) {
                    this.acL[i] = getResources().getIdentifier(stringArray[i], "drawable", context.getPackageName());
                }
            }
        }
        obtainStyledAttributes.recycle();
        this.mOrientation = getResources().getConfiguration().orientation;
        init(integer);
    }

    private void init(int i) {
        LayoutInflater from = LayoutInflater.from(getContext());
        for (int i2 = 0; i2 < this.mNames.length; i2++) {
            RadioButton radioButton = (RadioButton) from.inflate(R.layout.x_catalogbar_item, (ViewGroup) this, false);
            radioButton.setText(this.mNames[i2]);
            if (i2 < this.acL.length) {
                if (this.mOrientation == 1) {
                    radioButton.setCompoundDrawablesWithIntrinsicBounds(0, this.acL[i2], 0, 0);
                } else {
                    radioButton.setCompoundDrawablesWithIntrinsicBounds(this.acL[i2], 0, 0, 0);
                }
            }
            radioButton.setId(i2);
            addView(radioButton);
            if (i == i2) {
                radioButton.setChecked(true);
            }
        }
    }
}
