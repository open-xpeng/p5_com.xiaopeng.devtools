package androidx.recyclerview.selection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes10.dex */
public class Selection<K> implements Iterable<K> {
    final Set<K> mProvisionalSelection;
    final Set<K> mSelection;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Selection() {
        this.mSelection = new HashSet();
        this.mProvisionalSelection = new HashSet();
    }

    Selection(@NonNull Set<K> set) {
        this.mSelection = set;
        this.mProvisionalSelection = new HashSet();
    }

    public boolean contains(@Nullable K k) {
        return this.mSelection.contains(k) || this.mProvisionalSelection.contains(k);
    }

    @Override // java.lang.Iterable
    public Iterator<K> iterator() {
        return this.mSelection.iterator();
    }

    public int size() {
        return this.mSelection.size() + this.mProvisionalSelection.size();
    }

    public boolean isEmpty() {
        return this.mSelection.isEmpty() && this.mProvisionalSelection.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public Map<K, Boolean> setProvisionalSelection(@NonNull Set<K> set) {
        HashMap hashMap = new HashMap();
        for (K k : this.mProvisionalSelection) {
            if (!set.contains(k) && !this.mSelection.contains(k)) {
                hashMap.put(k, false);
            }
        }
        for (K k2 : this.mSelection) {
            if (!set.contains(k2)) {
                hashMap.put(k2, false);
            }
        }
        for (K k3 : set) {
            if (!this.mSelection.contains(k3) && !this.mProvisionalSelection.contains(k3)) {
                hashMap.put(k3, true);
            }
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            Object key = entry.getKey();
            if (((Boolean) entry.getValue()).booleanValue()) {
                this.mProvisionalSelection.add(key);
            } else {
                this.mProvisionalSelection.remove(key);
            }
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void mergeProvisionalSelection() {
        this.mSelection.addAll(this.mProvisionalSelection);
        this.mProvisionalSelection.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clearProvisionalSelection() {
        this.mProvisionalSelection.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean add(@NonNull K k) {
        return this.mSelection.add(k);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean remove(@NonNull K k) {
        return this.mSelection.remove(k);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clear() {
        this.mSelection.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void copyFrom(@NonNull Selection<K> selection) {
        this.mSelection.clear();
        this.mSelection.addAll(selection.mSelection);
        this.mProvisionalSelection.clear();
        this.mProvisionalSelection.addAll(selection.mProvisionalSelection);
    }

    public String toString() {
        if (size() <= 0) {
            return "size=0, items=[]";
        }
        StringBuilder sb = new StringBuilder(size() * 28);
        sb.append("Selection{");
        sb.append("primary{size=" + this.mSelection.size());
        sb.append(", entries=" + this.mSelection);
        sb.append("}, provisional{size=" + this.mProvisionalSelection.size());
        sb.append(", entries=" + this.mProvisionalSelection);
        sb.append("}}");
        return sb.toString();
    }

    public int hashCode() {
        return this.mSelection.hashCode() ^ this.mProvisionalSelection.hashCode();
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof Selection) && isEqualTo((Selection) obj));
    }

    private boolean isEqualTo(Selection selection) {
        return this.mSelection.equals(selection.mSelection) && this.mProvisionalSelection.equals(selection.mProvisionalSelection);
    }
}
