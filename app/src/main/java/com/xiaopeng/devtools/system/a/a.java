package com.xiaopeng.devtools.system.a;

import android.hardware.input.InputManager;
import android.view.KeyEvent;
import com.xiaopeng.IXPKeyListener;
import com.xiaopeng.lib.utils.c;
import com.xiaopeng.lib.utils.j;

/* compiled from: KeyEventListener.java */
/* loaded from: classes12.dex */
public class a extends IXPKeyListener.Stub {
    private InputManager An = InputManager.getInstance();
    private InterfaceC0069a Ao;

    /* compiled from: KeyEventListener.java */
    /* renamed from: com.xiaopeng.devtools.system.a.a$a  reason: collision with other inner class name */
    /* loaded from: classes12.dex */
    public interface InterfaceC0069a {
        void b(KeyEvent keyEvent);
    }

    public a(InterfaceC0069a interfaceC0069a) {
        this.Ao = interfaceC0069a;
    }

    public void register() {
        if (this.An != null) {
            this.An.registerListener(this, "FactoryKeyEventListener", true);
        }
    }

    public int notify(final KeyEvent keyEvent, String str) {
        int keyCode = keyEvent.getKeyCode();
        int action = keyEvent.getAction();
        c.f("FactoryKeyEventListener", "notify keycode=" + keyCode + " action=" + action);
        if (action == 1 && this.Ao != null) {
            j.c(new Runnable() { // from class: com.xiaopeng.devtools.system.a.-$$Lambda$a$SgUAGuTa5NvZDe0J4dseMxg7-qo
                @Override // java.lang.Runnable
                public final void run() {
                    a.this.a(keyEvent);
                }
            });
            return 0;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(KeyEvent keyEvent) {
        this.Ao.b(keyEvent);
    }
}
