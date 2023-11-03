package org.greenrobot.eventbus.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import org.greenrobot.eventbus.EventBus;

/* loaded from: classes13.dex */
public class ErrorDialogManager {
    public static final String KEY_EVENT_TYPE_ON_CLOSE = "de.greenrobot.eventbus.errordialog.event_type_on_close";
    public static final String KEY_FINISH_AFTER_DIALOG = "de.greenrobot.eventbus.errordialog.finish_after_dialog";
    public static final String KEY_ICON_ID = "de.greenrobot.eventbus.errordialog.icon_id";
    public static final String KEY_MESSAGE = "de.greenrobot.eventbus.errordialog.message";
    public static final String KEY_TITLE = "de.greenrobot.eventbus.errordialog.title";
    protected static final String TAG_ERROR_DIALOG = "de.greenrobot.eventbus.error_dialog";
    protected static final String TAG_ERROR_DIALOG_MANAGER = "de.greenrobot.eventbus.error_dialog_manager";
    public static ErrorDialogFragmentFactory<?> factory;

    /* loaded from: classes13.dex */
    public static class SupportManagerFragment extends Fragment {
        protected Bundle argumentsForErrorDialog;
        private EventBus eventBus;
        private Object executionScope;
        protected boolean finishAfterDialog;
        private boolean skipRegisterOnNextResume;

        @Override // android.support.v4.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            this.eventBus = ErrorDialogManager.factory.config.getEventBus();
            this.eventBus.register(this);
            this.skipRegisterOnNextResume = true;
        }

        @Override // android.support.v4.app.Fragment
        public void onResume() {
            super.onResume();
            if (this.skipRegisterOnNextResume) {
                this.skipRegisterOnNextResume = false;
                return;
            }
            this.eventBus = ErrorDialogManager.factory.config.getEventBus();
            this.eventBus.register(this);
        }

        @Override // android.support.v4.app.Fragment
        public void onPause() {
            this.eventBus.unregister(this);
            super.onPause();
        }

        public void onEventMainThread(ThrowableFailureEvent throwableFailureEvent) {
            if (!ErrorDialogManager.isInExecutionScope(this.executionScope, throwableFailureEvent)) {
                return;
            }
            ErrorDialogManager.checkLogException(throwableFailureEvent);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.executePendingTransactions();
            DialogFragment dialogFragment = (DialogFragment) fragmentManager.findFragmentByTag(ErrorDialogManager.TAG_ERROR_DIALOG);
            if (dialogFragment != null) {
                dialogFragment.dismiss();
            }
            DialogFragment dialogFragment2 = (DialogFragment) ErrorDialogManager.factory.prepareErrorFragment(throwableFailureEvent, this.finishAfterDialog, this.argumentsForErrorDialog);
            if (dialogFragment2 != null) {
                dialogFragment2.show(fragmentManager, ErrorDialogManager.TAG_ERROR_DIALOG);
            }
        }

        public static void attachTo(Activity activity, Object obj, boolean z, Bundle bundle) {
            FragmentManager supportFragmentManager = ((FragmentActivity) activity).getSupportFragmentManager();
            SupportManagerFragment supportManagerFragment = (SupportManagerFragment) supportFragmentManager.findFragmentByTag(ErrorDialogManager.TAG_ERROR_DIALOG_MANAGER);
            if (supportManagerFragment == null) {
                supportManagerFragment = new SupportManagerFragment();
                supportFragmentManager.beginTransaction().add(supportManagerFragment, ErrorDialogManager.TAG_ERROR_DIALOG_MANAGER).commit();
                supportFragmentManager.executePendingTransactions();
            }
            supportManagerFragment.finishAfterDialog = z;
            supportManagerFragment.argumentsForErrorDialog = bundle;
            supportManagerFragment.executionScope = obj;
        }
    }

    @TargetApi(11)
    /* loaded from: classes13.dex */
    public static class HoneycombManagerFragment extends android.app.Fragment {
        protected Bundle argumentsForErrorDialog;
        private EventBus eventBus;
        private Object executionScope;
        protected boolean finishAfterDialog;

        @Override // android.app.Fragment
        public void onResume() {
            super.onResume();
            this.eventBus = ErrorDialogManager.factory.config.getEventBus();
            this.eventBus.register(this);
        }

        @Override // android.app.Fragment
        public void onPause() {
            this.eventBus.unregister(this);
            super.onPause();
        }

        public void onEventMainThread(ThrowableFailureEvent throwableFailureEvent) {
            if (!ErrorDialogManager.isInExecutionScope(this.executionScope, throwableFailureEvent)) {
                return;
            }
            ErrorDialogManager.checkLogException(throwableFailureEvent);
            android.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.executePendingTransactions();
            android.app.DialogFragment dialogFragment = (android.app.DialogFragment) fragmentManager.findFragmentByTag(ErrorDialogManager.TAG_ERROR_DIALOG);
            if (dialogFragment != null) {
                dialogFragment.dismiss();
            }
            android.app.DialogFragment dialogFragment2 = (android.app.DialogFragment) ErrorDialogManager.factory.prepareErrorFragment(throwableFailureEvent, this.finishAfterDialog, this.argumentsForErrorDialog);
            if (dialogFragment2 != null) {
                dialogFragment2.show(fragmentManager, ErrorDialogManager.TAG_ERROR_DIALOG);
            }
        }

        public static void attachTo(Activity activity, Object obj, boolean z, Bundle bundle) {
            android.app.FragmentManager fragmentManager = activity.getFragmentManager();
            HoneycombManagerFragment honeycombManagerFragment = (HoneycombManagerFragment) fragmentManager.findFragmentByTag(ErrorDialogManager.TAG_ERROR_DIALOG_MANAGER);
            if (honeycombManagerFragment == null) {
                honeycombManagerFragment = new HoneycombManagerFragment();
                fragmentManager.beginTransaction().add(honeycombManagerFragment, ErrorDialogManager.TAG_ERROR_DIALOG_MANAGER).commit();
                fragmentManager.executePendingTransactions();
            }
            honeycombManagerFragment.finishAfterDialog = z;
            honeycombManagerFragment.argumentsForErrorDialog = bundle;
            honeycombManagerFragment.executionScope = obj;
        }
    }

    public static void attachTo(Activity activity) {
        attachTo(activity, false, null);
    }

    public static void attachTo(Activity activity, boolean z) {
        attachTo(activity, z, null);
    }

    public static void attachTo(Activity activity, boolean z, Bundle bundle) {
        attachTo(activity, activity.getClass(), z, bundle);
    }

    public static void attachTo(Activity activity, Object obj, boolean z, Bundle bundle) {
        if (factory == null) {
            throw new RuntimeException("You must set the static factory field to configure error dialogs for your app.");
        }
        if (isSupportActivity(activity)) {
            SupportManagerFragment.attachTo(activity, obj, z, bundle);
        } else {
            HoneycombManagerFragment.attachTo(activity, obj, z, bundle);
        }
    }

    private static boolean isSupportActivity(Activity activity) {
        String name;
        Class<?> cls = activity.getClass();
        do {
            cls = cls.getSuperclass();
            if (cls == null) {
                throw new RuntimeException("Illegal activity type: " + activity.getClass());
            }
            name = cls.getName();
            if (name.equals("android.support.v4.app.FragmentActivity")) {
                return true;
            }
            if (name.startsWith("com.actionbarsherlock.app") && (name.endsWith(".SherlockActivity") || name.endsWith(".SherlockListActivity") || name.endsWith(".SherlockPreferenceActivity"))) {
                throw new RuntimeException("Please use SherlockFragmentActivity. Illegal activity: " + name);
            }
        } while (!name.equals("android.app.Activity"));
        if (Build.VERSION.SDK_INT < 11) {
            throw new RuntimeException("Illegal activity without fragment support. Either use Android 3.0+ or android.support.v4.app.FragmentActivity.");
        }
        return false;
    }

    protected static void checkLogException(ThrowableFailureEvent throwableFailureEvent) {
        if (factory.config.logExceptions) {
            String str = factory.config.tagForLoggingExceptions;
            if (str == null) {
                str = EventBus.TAG;
            }
            Log.i(str, "Error dialog manager received exception", throwableFailureEvent.throwable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isInExecutionScope(Object obj, ThrowableFailureEvent throwableFailureEvent) {
        Object executionScope;
        if (throwableFailureEvent != null && (executionScope = throwableFailureEvent.getExecutionScope()) != null && !executionScope.equals(obj)) {
            return false;
        }
        return true;
    }
}
