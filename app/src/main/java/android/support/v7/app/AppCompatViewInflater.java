package android.support.v7.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatMultiAutoCompleteTextView;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.TintContextWrapper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/* loaded from: classes7.dex */
public class AppCompatViewInflater {
    private static final String LOG_TAG = "AppCompatViewInflater";
    private final Object[] mConstructorArgs = new Object[2];
    private static final Class<?>[] sConstructorSignature = {Context.class, AttributeSet.class};
    private static final int[] sOnClickAttrs = {16843375};
    private static final String[] sClassPrefixList = {"android.widget.", "android.view.", "android.webkit."};
    private static final Map<String, Constructor<? extends View>> sConstructorMap = new ArrayMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final View createView(View view, String str, @NonNull Context context, @NonNull AttributeSet attributeSet, boolean z, boolean z2, boolean z3, boolean z4) {
        Context context2;
        View createTextView;
        if (z && view != null) {
            context2 = view.getContext();
        } else {
            context2 = context;
        }
        if (z2 || z3) {
            context2 = themifyContext(context2, attributeSet, z2, z3);
        }
        if (z4) {
            context2 = TintContextWrapper.wrap(context2);
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1946472170:
                if (str.equals("RatingBar")) {
                    c = 11;
                    break;
                }
                break;
            case -1455429095:
                if (str.equals("CheckedTextView")) {
                    c = '\b';
                    break;
                }
                break;
            case -1346021293:
                if (str.equals("MultiAutoCompleteTextView")) {
                    c = '\n';
                    break;
                }
                break;
            case -938935918:
                if (str.equals("TextView")) {
                    c = 0;
                    break;
                }
                break;
            case -937446323:
                if (str.equals("ImageButton")) {
                    c = 5;
                    break;
                }
                break;
            case -658531749:
                if (str.equals("SeekBar")) {
                    c = '\f';
                    break;
                }
                break;
            case -339785223:
                if (str.equals("Spinner")) {
                    c = 4;
                    break;
                }
                break;
            case 776382189:
                if (str.equals("RadioButton")) {
                    c = 7;
                    break;
                }
                break;
            case 1125864064:
                if (str.equals("ImageView")) {
                    c = 1;
                    break;
                }
                break;
            case 1413872058:
                if (str.equals("AutoCompleteTextView")) {
                    c = '\t';
                    break;
                }
                break;
            case 1601505219:
                if (str.equals("CheckBox")) {
                    c = 6;
                    break;
                }
                break;
            case 1666676343:
                if (str.equals("EditText")) {
                    c = 3;
                    break;
                }
                break;
            case 2001146706:
                if (str.equals("Button")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                createTextView = createTextView(context2, attributeSet);
                verifyNotNull(createTextView, str);
                break;
            case 1:
                createTextView = createImageView(context2, attributeSet);
                verifyNotNull(createTextView, str);
                break;
            case 2:
                createTextView = createButton(context2, attributeSet);
                verifyNotNull(createTextView, str);
                break;
            case 3:
                createTextView = createEditText(context2, attributeSet);
                verifyNotNull(createTextView, str);
                break;
            case 4:
                createTextView = createSpinner(context2, attributeSet);
                verifyNotNull(createTextView, str);
                break;
            case 5:
                createTextView = createImageButton(context2, attributeSet);
                verifyNotNull(createTextView, str);
                break;
            case 6:
                createTextView = createCheckBox(context2, attributeSet);
                verifyNotNull(createTextView, str);
                break;
            case 7:
                createTextView = createRadioButton(context2, attributeSet);
                verifyNotNull(createTextView, str);
                break;
            case '\b':
                createTextView = createCheckedTextView(context2, attributeSet);
                verifyNotNull(createTextView, str);
                break;
            case '\t':
                createTextView = createAutoCompleteTextView(context2, attributeSet);
                verifyNotNull(createTextView, str);
                break;
            case '\n':
                createTextView = createMultiAutoCompleteTextView(context2, attributeSet);
                verifyNotNull(createTextView, str);
                break;
            case 11:
                createTextView = createRatingBar(context2, attributeSet);
                verifyNotNull(createTextView, str);
                break;
            case '\f':
                createTextView = createSeekBar(context2, attributeSet);
                verifyNotNull(createTextView, str);
                break;
            default:
                createTextView = createView(context2, str, attributeSet);
                break;
        }
        if (createTextView == null && context != context2) {
            createTextView = createViewFromTag(context2, str, attributeSet);
        }
        if (createTextView != null) {
            checkOnClickListener(createTextView, attributeSet);
        }
        return createTextView;
    }

    @NonNull
    protected AppCompatTextView createTextView(Context context, AttributeSet attributeSet) {
        return new AppCompatTextView(context, attributeSet);
    }

    @NonNull
    protected AppCompatImageView createImageView(Context context, AttributeSet attributeSet) {
        return new AppCompatImageView(context, attributeSet);
    }

    @NonNull
    protected AppCompatButton createButton(Context context, AttributeSet attributeSet) {
        return new AppCompatButton(context, attributeSet);
    }

    @NonNull
    protected AppCompatEditText createEditText(Context context, AttributeSet attributeSet) {
        return new AppCompatEditText(context, attributeSet);
    }

    @NonNull
    protected AppCompatSpinner createSpinner(Context context, AttributeSet attributeSet) {
        return new AppCompatSpinner(context, attributeSet);
    }

    @NonNull
    protected AppCompatImageButton createImageButton(Context context, AttributeSet attributeSet) {
        return new AppCompatImageButton(context, attributeSet);
    }

    @NonNull
    protected AppCompatCheckBox createCheckBox(Context context, AttributeSet attributeSet) {
        return new AppCompatCheckBox(context, attributeSet);
    }

    @NonNull
    protected AppCompatRadioButton createRadioButton(Context context, AttributeSet attributeSet) {
        return new AppCompatRadioButton(context, attributeSet);
    }

    @NonNull
    protected AppCompatCheckedTextView createCheckedTextView(Context context, AttributeSet attributeSet) {
        return new AppCompatCheckedTextView(context, attributeSet);
    }

    @NonNull
    protected AppCompatAutoCompleteTextView createAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        return new AppCompatAutoCompleteTextView(context, attributeSet);
    }

    @NonNull
    protected AppCompatMultiAutoCompleteTextView createMultiAutoCompleteTextView(Context context, AttributeSet attributeSet) {
        return new AppCompatMultiAutoCompleteTextView(context, attributeSet);
    }

    @NonNull
    protected AppCompatRatingBar createRatingBar(Context context, AttributeSet attributeSet) {
        return new AppCompatRatingBar(context, attributeSet);
    }

    @NonNull
    protected AppCompatSeekBar createSeekBar(Context context, AttributeSet attributeSet) {
        return new AppCompatSeekBar(context, attributeSet);
    }

    private void verifyNotNull(View view, String str) {
        if (view == null) {
            throw new IllegalStateException(getClass().getName() + " asked to inflate view for <" + str + ">, but returned null");
        }
    }

    @Nullable
    protected View createView(Context context, String str, AttributeSet attributeSet) {
        return null;
    }

    private View createViewFromTag(Context context, String str, AttributeSet attributeSet) {
        if (str.equals("view")) {
            str = attributeSet.getAttributeValue(null, "class");
        }
        try {
            this.mConstructorArgs[0] = context;
            this.mConstructorArgs[1] = attributeSet;
            if (-1 == str.indexOf(46)) {
                for (int i = 0; i < sClassPrefixList.length; i++) {
                    View createViewByPrefix = createViewByPrefix(context, str, sClassPrefixList[i]);
                    if (createViewByPrefix != null) {
                        return createViewByPrefix;
                    }
                }
                return null;
            }
            return createViewByPrefix(context, str, null);
        } catch (Exception e) {
            return null;
        } finally {
            this.mConstructorArgs[0] = null;
            this.mConstructorArgs[1] = null;
        }
    }

    private void checkOnClickListener(View view, AttributeSet attributeSet) {
        Context context = view.getContext();
        if (context instanceof ContextWrapper) {
            if (Build.VERSION.SDK_INT >= 15 && !ViewCompat.hasOnClickListeners(view)) {
                return;
            }
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, sOnClickAttrs);
            String string = obtainStyledAttributes.getString(0);
            if (string != null) {
                view.setOnClickListener(new DeclaredOnClickListener(view, string));
            }
            obtainStyledAttributes.recycle();
        }
    }

    private View createViewByPrefix(Context context, String str, String str2) throws ClassNotFoundException, InflateException {
        String str3;
        Constructor<? extends View> constructor = sConstructorMap.get(str);
        if (constructor == null) {
            try {
                ClassLoader classLoader = context.getClassLoader();
                if (str2 != null) {
                    str3 = str2 + str;
                } else {
                    str3 = str;
                }
                constructor = classLoader.loadClass(str3).asSubclass(View.class).getConstructor(sConstructorSignature);
                sConstructorMap.put(str, constructor);
            } catch (Exception e) {
                return null;
            }
        }
        constructor.setAccessible(true);
        return constructor.newInstance(this.mConstructorArgs);
    }

    private static Context themifyContext(Context context, AttributeSet attributeSet, boolean z, boolean z2) {
        int i;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.View, 0, 0);
        if (z) {
            i = obtainStyledAttributes.getResourceId(R.styleable.View_android_theme, 0);
        } else {
            i = 0;
        }
        if (z2 && i == 0 && (i = obtainStyledAttributes.getResourceId(R.styleable.View_theme, 0)) != 0) {
            Log.i(LOG_TAG, "app:theme is now deprecated. Please move to using android:theme instead.");
        }
        obtainStyledAttributes.recycle();
        return i != 0 ? ((context instanceof ContextThemeWrapper) && ((ContextThemeWrapper) context).getThemeResId() == i) ? context : new ContextThemeWrapper(context, i) : context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static class DeclaredOnClickListener implements View.OnClickListener {
        private final View mHostView;
        private final String mMethodName;
        private Context mResolvedContext;
        private Method mResolvedMethod;

        public DeclaredOnClickListener(@NonNull View view, @NonNull String str) {
            this.mHostView = view;
            this.mMethodName = str;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(@NonNull View view) {
            if (this.mResolvedMethod == null) {
                resolveMethod(this.mHostView.getContext(), this.mMethodName);
            }
            try {
                this.mResolvedMethod.invoke(this.mResolvedContext, view);
            } catch (IllegalAccessException e) {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", e);
            } catch (InvocationTargetException e2) {
                throw new IllegalStateException("Could not execute method for android:onClick", e2);
            }
        }

        @NonNull
        private void resolveMethod(@Nullable Context context, @NonNull String str) {
            int id;
            String str2;
            Method method;
            while (context != null) {
                try {
                    if (!context.isRestricted() && (method = context.getClass().getMethod(this.mMethodName, View.class)) != null) {
                        this.mResolvedMethod = method;
                        this.mResolvedContext = context;
                        return;
                    }
                } catch (NoSuchMethodException e) {
                }
                if (context instanceof ContextWrapper) {
                    context = ((ContextWrapper) context).getBaseContext();
                } else {
                    context = null;
                }
            }
            if (this.mHostView.getId() != -1) {
                str2 = " with id '" + this.mHostView.getContext().getResources().getResourceEntryName(id) + "'";
            } else {
                str2 = "";
            }
            throw new IllegalStateException("Could not find method " + this.mMethodName + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + this.mHostView.getClass() + str2);
        }
    }
}
