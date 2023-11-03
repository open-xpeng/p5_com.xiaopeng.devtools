package androidx.recyclerview.selection;

import androidx.annotation.NonNull;

/* loaded from: classes10.dex */
public final class MutableSelection<K> extends Selection<K> {
    @Override // androidx.recyclerview.selection.Selection
    public boolean add(@NonNull K k) {
        return super.add(k);
    }

    @Override // androidx.recyclerview.selection.Selection
    public boolean remove(@NonNull K k) {
        return super.remove(k);
    }

    @Override // androidx.recyclerview.selection.Selection
    public void copyFrom(@NonNull Selection<K> selection) {
        super.copyFrom(selection);
    }

    @Override // androidx.recyclerview.selection.Selection
    public void clear() {
        super.clear();
    }
}
