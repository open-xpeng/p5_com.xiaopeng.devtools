package com.xiaopeng.libbluetooth;

/* compiled from: AbsControlBox.java */
/* loaded from: classes12.dex */
public abstract class a {
    protected d Xg = new d() { // from class: com.xiaopeng.libbluetooth.a.1
        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.xiaopeng.libbluetooth.d
        public void onDisconnected() {
            a.this.pr();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.xiaopeng.libbluetooth.d
        public void b(com.xiaopeng.b.a aVar) {
            a.this.a(aVar);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.xiaopeng.libbluetooth.d
        public void pt() {
            super.pt();
            a.this.ps();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.xiaopeng.libbluetooth.d
        public void pu() {
            a.this.release();
        }
    };

    protected abstract void a(com.xiaopeng.b.a aVar);

    protected abstract void pr();

    protected abstract void ps();

    protected abstract void release();
}
