package com.xiaopeng.devtools.view.factorytest.hardwaretest.radio;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import java.lang.ref.WeakReference;

/* loaded from: classes12.dex */
public class LongClickButton extends Button {
    private a KR;
    private long KS;
    private c KT;

    /* loaded from: classes12.dex */
    public interface a {
        void repeatAction(View view);
    }

    public LongClickButton(Context context) {
        super(context);
        init();
    }

    public LongClickButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public LongClickButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    private void init() {
        this.KT = new c(this);
        setOnLongClickListener(new View.OnLongClickListener() { // from class: com.xiaopeng.devtools.view.factorytest.hardwaretest.radio.LongClickButton.1
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view) {
                new Thread(new b(view)).start();
                return true;
            }
        });
    }

    /* loaded from: classes12.dex */
    private class b implements Runnable {
        private int KV;
        private View mView;

        public b(View view) {
            this.mView = null;
            this.mView = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            while (LongClickButton.this.isPressed()) {
                this.KV++;
                if (this.KV % 5 == 0) {
                    LongClickButton.this.KT.obtainMessage(1, this.mView).sendToTarget();
                }
                SystemClock.sleep(LongClickButton.this.KS / 5);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class c extends Handler {
        private WeakReference<LongClickButton> KW;

        c(LongClickButton longClickButton) {
            this.KW = new WeakReference<>(longClickButton);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            LongClickButton longClickButton = this.KW.get();
            if (longClickButton != null && longClickButton.KR != null) {
                longClickButton.KR.repeatAction((View) message.obj);
            }
        }
    }

    public void a(a aVar, long j) {
        this.KR = aVar;
        this.KS = j;
    }

    public void setLongClickRepeatListener(a aVar) {
        a(aVar, 200L);
        this.KS = 200L;
    }
}
