package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.TintableBackgroundView;
import android.support.v4.widget.TintableImageSourceView;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: classes8.dex */
public class AppCompatImageView extends ImageView implements TintableBackgroundView, TintableImageSourceView {
    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final AppCompatImageHelper mImageHelper;

    public AppCompatImageView(Context context) {
        this(context, null);
    }

    public AppCompatImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AppCompatImageView(Context context, AttributeSet attributeSet, int i) {
        super(TintContextWrapper.wrap(context), attributeSet, i);
        this.mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
        this.mBackgroundTintHelper.loadFromAttributes(attributeSet, i);
        this.mImageHelper = new AppCompatImageHelper(this);
        this.mImageHelper.loadFromAttributes(attributeSet, i);
    }

    @Override // android.widget.ImageView
    public void setImageResource(@DrawableRes int i) {
        if (this.mImageHelper != null) {
            this.mImageHelper.setImageResource(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(@Nullable Drawable drawable) {
        super.setImageDrawable(drawable);
        if (this.mImageHelper != null) {
            this.mImageHelper.applySupportImageTint();
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        if (this.mImageHelper != null) {
            this.mImageHelper.applySupportImageTint();
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(@Nullable Uri uri) {
        super.setImageURI(uri);
        if (this.mImageHelper != null) {
            this.mImageHelper.applySupportImageTint();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.onSetBackgroundResource(i);
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.onSetBackgroundDrawable(drawable);
        }
    }

    @Override // android.support.v4.view.TintableBackgroundView
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.setSupportBackgroundTintList(colorStateList);
        }
    }

    @Override // android.support.v4.view.TintableBackgroundView
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ColorStateList getSupportBackgroundTintList() {
        if (this.mBackgroundTintHelper != null) {
            return this.mBackgroundTintHelper.getSupportBackgroundTintList();
        }
        return null;
    }

    @Override // android.support.v4.view.TintableBackgroundView
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.setSupportBackgroundTintMode(mode);
        }
    }

    @Override // android.support.v4.view.TintableBackgroundView
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        if (this.mBackgroundTintHelper != null) {
            return this.mBackgroundTintHelper.getSupportBackgroundTintMode();
        }
        return null;
    }

    @Override // android.support.v4.widget.TintableImageSourceView
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setSupportImageTintList(@Nullable ColorStateList colorStateList) {
        if (this.mImageHelper != null) {
            this.mImageHelper.setSupportImageTintList(colorStateList);
        }
    }

    @Override // android.support.v4.widget.TintableImageSourceView
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public ColorStateList getSupportImageTintList() {
        if (this.mImageHelper != null) {
            return this.mImageHelper.getSupportImageTintList();
        }
        return null;
    }

    @Override // android.support.v4.widget.TintableImageSourceView
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setSupportImageTintMode(@Nullable PorterDuff.Mode mode) {
        if (this.mImageHelper != null) {
            this.mImageHelper.setSupportImageTintMode(mode);
        }
    }

    @Override // android.support.v4.widget.TintableImageSourceView
    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public PorterDuff.Mode getSupportImageTintMode() {
        if (this.mImageHelper != null) {
            return this.mImageHelper.getSupportImageTintMode();
        }
        return null;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.mBackgroundTintHelper != null) {
            this.mBackgroundTintHelper.applySupportBackgroundTint();
        }
        if (this.mImageHelper != null) {
            this.mImageHelper.applySupportImageTint();
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return this.mImageHelper.hasOverlappingRendering() && super.hasOverlappingRendering();
    }
}
