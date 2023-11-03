package android.support.design.card;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RestrictTo;
import android.support.v4.view.ViewCompat;

/* JADX INFO: Access modifiers changed from: package-private */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes3.dex */
public class MaterialCardViewHelper {
    private static final int DEFAULT_STROKE_VALUE = -1;
    private float cornerRadius;
    private final MaterialCardView materialCardView;
    private int strokeColor;
    private int strokeWidth;

    public MaterialCardViewHelper(MaterialCardView materialCardView) {
        this.materialCardView = materialCardView;
    }

    public void loadFromAttributes(TypedArray typedArray) {
        this.cornerRadius = typedArray.getDimensionPixelSize(R.styleable.CardView_cardCornerRadius, 0);
        this.strokeColor = typedArray.getColor(R.styleable.MaterialCardView_strokeColor, -1);
        this.strokeWidth = typedArray.getDimensionPixelSize(R.styleable.MaterialCardView_strokeWidth, 0);
        ViewCompat.setBackground(this.materialCardView, createBackgroundDrawable());
        adjustContentPadding(this.strokeWidth);
    }

    private Drawable createBackgroundDrawable() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(this.cornerRadius);
        if (this.strokeColor != -1) {
            gradientDrawable.setStroke(this.strokeWidth, this.strokeColor);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            gradientDrawable.setColor(this.materialCardView.getCardBackgroundColor());
        } else {
            gradientDrawable.setColor(this.materialCardView.getCardBackgroundColor().getDefaultColor());
        }
        return gradientDrawable;
    }

    private void adjustContentPadding(int i) {
        this.materialCardView.setContentPadding(this.materialCardView.getContentPaddingLeft() + i, this.materialCardView.getContentPaddingTop() + i, this.materialCardView.getContentPaddingRight() + i, this.materialCardView.getContentPaddingBottom() + i);
    }
}
