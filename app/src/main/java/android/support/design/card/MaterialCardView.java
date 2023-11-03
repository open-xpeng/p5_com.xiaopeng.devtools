package android.support.design.card;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.internal.ThemeEnforcement;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

/* loaded from: classes3.dex */
public class MaterialCardView extends CardView {
    public MaterialCardView(Context context) {
        this(context, null);
    }

    public MaterialCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialCardViewStyle);
    }

    public MaterialCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, R.styleable.MaterialCardView, i, R.style.Widget_MaterialComponents_CardView);
        new MaterialCardViewHelper(this).loadFromAttributes(obtainStyledAttributes);
        obtainStyledAttributes.recycle();
    }
}
