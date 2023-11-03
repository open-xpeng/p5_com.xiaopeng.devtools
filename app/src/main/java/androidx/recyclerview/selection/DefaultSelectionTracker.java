package androidx.recyclerview.selection;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Preconditions;
import androidx.recyclerview.selection.Range;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public class DefaultSelectionTracker<K> extends SelectionTracker<K> {
    private static final String EXTRA_SELECTION_PREFIX = "androidx.recyclerview.selection";
    private static final String TAG = "DefaultSelectionTracker";
    private final AdapterObserver mAdapterObserver;
    private final ItemKeyProvider<K> mKeyProvider;
    @Nullable
    private Range mRange;
    private final DefaultSelectionTracker<K>.RangeCallbacks mRangeCallbacks;
    private final String mSelectionId;
    private final SelectionTracker.SelectionPredicate<K> mSelectionPredicate;
    private final boolean mSingleSelect;
    private final StorageStrategy<K> mStorage;
    private final Selection<K> mSelection = new Selection<>();
    private final List<SelectionTracker.SelectionObserver> mObservers = new ArrayList(1);

    public DefaultSelectionTracker(@NonNull String str, @NonNull ItemKeyProvider itemKeyProvider, @NonNull SelectionTracker.SelectionPredicate selectionPredicate, @NonNull StorageStrategy<K> storageStrategy) {
        Preconditions.checkArgument(str != null);
        Preconditions.checkArgument(!str.trim().isEmpty());
        Preconditions.checkArgument(itemKeyProvider != null);
        Preconditions.checkArgument(selectionPredicate != null);
        Preconditions.checkArgument(storageStrategy != null);
        this.mSelectionId = str;
        this.mKeyProvider = itemKeyProvider;
        this.mSelectionPredicate = selectionPredicate;
        this.mStorage = storageStrategy;
        this.mRangeCallbacks = new RangeCallbacks();
        this.mSingleSelect = !selectionPredicate.canSelectMultiple();
        this.mAdapterObserver = new AdapterObserver(this);
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public void addObserver(@NonNull SelectionTracker.SelectionObserver selectionObserver) {
        Preconditions.checkArgument(selectionObserver != null);
        this.mObservers.add(selectionObserver);
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public boolean hasSelection() {
        return !this.mSelection.isEmpty();
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public Selection getSelection() {
        return this.mSelection;
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public void copySelection(@NonNull MutableSelection mutableSelection) {
        mutableSelection.copyFrom(this.mSelection);
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public boolean isSelected(@Nullable K k) {
        return this.mSelection.contains(k);
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    protected void restoreSelection(@NonNull Selection selection) {
        Preconditions.checkArgument(selection != null);
        setItemsSelectedQuietly(selection.mSelection, true);
        notifySelectionRestored();
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public boolean setItemsSelected(@NonNull Iterable<K> iterable, boolean z) {
        boolean itemsSelectedQuietly = setItemsSelectedQuietly(iterable, z);
        notifySelectionChanged();
        return itemsSelectedQuietly;
    }

    private boolean setItemsSelectedQuietly(@NonNull Iterable<K> iterable, boolean z) {
        boolean z2 = false;
        for (K k : iterable) {
            boolean z3 = true;
            if (!z ? !canSetState(k, false) || !this.mSelection.remove(k) : !canSetState(k, true) || !this.mSelection.add(k)) {
                z3 = false;
            }
            if (z3) {
                notifyItemStateChanged(k, z);
            }
            z2 |= z3;
        }
        return z2;
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public boolean clearSelection() {
        if (!hasSelection()) {
            return false;
        }
        clearProvisionalSelection();
        clearPrimarySelection();
        return true;
    }

    private void clearPrimarySelection() {
        if (!hasSelection()) {
            return;
        }
        notifySelectionCleared(clearSelectionQuietly());
        notifySelectionChanged();
    }

    private Selection clearSelectionQuietly() {
        this.mRange = null;
        MutableSelection mutableSelection = new MutableSelection();
        if (hasSelection()) {
            copySelection(mutableSelection);
            this.mSelection.clear();
        }
        return mutableSelection;
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public boolean select(@NonNull K k) {
        Preconditions.checkArgument(k != null);
        if (this.mSelection.contains(k) || !canSetState(k, true)) {
            return false;
        }
        if (this.mSingleSelect && hasSelection()) {
            notifySelectionCleared(clearSelectionQuietly());
        }
        this.mSelection.add(k);
        notifyItemStateChanged(k, true);
        notifySelectionChanged();
        return true;
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public boolean deselect(@NonNull K k) {
        Preconditions.checkArgument(k != null);
        if (this.mSelection.contains(k) && canSetState(k, false)) {
            this.mSelection.remove(k);
            notifyItemStateChanged(k, false);
            notifySelectionChanged();
            if (this.mSelection.isEmpty() && isRangeActive()) {
                endRange();
            }
            return true;
        }
        return false;
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public void startRange(int i) {
        if (this.mSelection.contains(this.mKeyProvider.getKey(i)) || select(this.mKeyProvider.getKey(i))) {
            anchorRange(i);
        }
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public void extendRange(int i) {
        extendRange(i, 0);
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public void endRange() {
        this.mRange = null;
        clearProvisionalSelection();
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public void anchorRange(int i) {
        Preconditions.checkArgument(i != -1);
        Preconditions.checkArgument(this.mSelection.contains(this.mKeyProvider.getKey(i)));
        this.mRange = new Range(i, this.mRangeCallbacks);
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public void extendProvisionalRange(int i) {
        if (this.mSingleSelect) {
            return;
        }
        Preconditions.checkState(isRangeActive(), "Range start point not set.");
        extendRange(i, 1);
    }

    private void extendRange(int i, int i2) {
        Preconditions.checkState(isRangeActive(), "Range start point not set.");
        this.mRange.extendRange(i, i2);
        notifySelectionChanged();
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public void setProvisionalSelection(@NonNull Set<K> set) {
        if (this.mSingleSelect) {
            return;
        }
        for (Map.Entry<K, Boolean> entry : this.mSelection.setProvisionalSelection(set).entrySet()) {
            notifyItemStateChanged(entry.getKey(), entry.getValue().booleanValue());
        }
        notifySelectionChanged();
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public void mergeProvisionalSelection() {
        this.mSelection.mergeProvisionalSelection();
        notifySelectionChanged();
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public void clearProvisionalSelection() {
        for (K k : this.mSelection.mProvisionalSelection) {
            notifyItemStateChanged(k, false);
        }
        this.mSelection.clearProvisionalSelection();
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public boolean isRangeActive() {
        return this.mRange != null;
    }

    private boolean canSetState(@NonNull K k, boolean z) {
        return this.mSelectionPredicate.canSetStateForKey(k, z);
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    RecyclerView.AdapterDataObserver getAdapterDataObserver() {
        return this.mAdapterObserver;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDataSetChanged() {
        this.mSelection.clearProvisionalSelection();
        notifySelectionRefresh();
        Iterator<K> it = this.mSelection.iterator();
        while (it.hasNext()) {
            K next = it.next();
            if (canSetState(next, true)) {
                for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                    this.mObservers.get(size).onItemStateChanged(next, true);
                }
            } else {
                deselect(next);
            }
        }
        notifySelectionChanged();
    }

    private void notifyItemStateChanged(@NonNull K k, boolean z) {
        Preconditions.checkArgument(k != null);
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            this.mObservers.get(size).onItemStateChanged(k, z);
        }
    }

    private void notifySelectionCleared(@NonNull Selection<K> selection) {
        for (K k : selection.mSelection) {
            notifyItemStateChanged(k, false);
        }
        for (K k2 : selection.mProvisionalSelection) {
            notifyItemStateChanged(k2, false);
        }
    }

    private void notifySelectionChanged() {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            this.mObservers.get(size).onSelectionChanged();
        }
    }

    private void notifySelectionRestored() {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            this.mObservers.get(size).onSelectionRestored();
        }
    }

    private void notifySelectionRefresh() {
        for (int size = this.mObservers.size() - 1; size >= 0; size--) {
            this.mObservers.get(size).onSelectionRefresh();
        }
    }

    private void updateForRange(int i, int i2, boolean z, int i3) {
        switch (i3) {
            case 0:
                updateForRegularRange(i, i2, z);
                return;
            case 1:
                updateForProvisionalRange(i, i2, z);
                return;
            default:
                throw new IllegalArgumentException("Invalid range type: " + i3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateForRegularRange(int i, int i2, boolean z) {
        Preconditions.checkArgument(i2 >= i);
        while (i <= i2) {
            K key = this.mKeyProvider.getKey(i);
            if (key != null) {
                if (z) {
                    select(key);
                } else {
                    deselect(key);
                }
            }
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateForProvisionalRange(int i, int i2, boolean z) {
        boolean z2;
        Preconditions.checkArgument(i2 >= i);
        while (i <= i2) {
            K key = this.mKeyProvider.getKey(i);
            if (key != null) {
                if (z) {
                    if (!canSetState(key, true) || this.mSelection.mSelection.contains(key)) {
                        z2 = false;
                    } else {
                        this.mSelection.mProvisionalSelection.add(key);
                        z2 = true;
                    }
                } else {
                    this.mSelection.mProvisionalSelection.remove(key);
                    z2 = true;
                }
                if (z2) {
                    notifyItemStateChanged(key, z);
                }
            }
            i++;
        }
        notifySelectionChanged();
    }

    @VisibleForTesting
    String getInstanceStateKey() {
        return "androidx.recyclerview.selection:" + this.mSelectionId;
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public final void onSaveInstanceState(@NonNull Bundle bundle) {
        if (this.mSelection.isEmpty()) {
            return;
        }
        bundle.putBundle(getInstanceStateKey(), this.mStorage.asBundle(this.mSelection));
    }

    @Override // androidx.recyclerview.selection.SelectionTracker
    public final void onRestoreInstanceState(@Nullable Bundle bundle) {
        Bundle bundle2;
        Selection<K> asSelection;
        if (bundle != null && (bundle2 = bundle.getBundle(getInstanceStateKey())) != null && (asSelection = this.mStorage.asSelection(bundle2)) != null && !asSelection.isEmpty()) {
            restoreSelection(asSelection);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public final class RangeCallbacks extends Range.Callbacks {
        private RangeCallbacks() {
        }

        @Override // androidx.recyclerview.selection.Range.Callbacks
        void updateForRange(int i, int i2, boolean z, int i3) {
            switch (i3) {
                case 0:
                    DefaultSelectionTracker.this.updateForRegularRange(i, i2, z);
                    return;
                case 1:
                    DefaultSelectionTracker.this.updateForProvisionalRange(i, i2, z);
                    return;
                default:
                    throw new IllegalArgumentException("Invalid range type: " + i3);
            }
        }
    }

    /* loaded from: classes10.dex */
    private static final class AdapterObserver extends RecyclerView.AdapterDataObserver {
        private final DefaultSelectionTracker<?> mSelectionTracker;

        AdapterObserver(@NonNull DefaultSelectionTracker<?> defaultSelectionTracker) {
            Preconditions.checkArgument(defaultSelectionTracker != null);
            this.mSelectionTracker = defaultSelectionTracker;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            this.mSelectionTracker.onDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int i, int i2, @Nullable Object obj) {
            if (!SelectionTracker.SELECTION_CHANGED_MARKER.equals(obj)) {
                this.mSelectionTracker.onDataSetChanged();
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int i, int i2) {
            this.mSelectionTracker.endRange();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int i, int i2) {
            this.mSelectionTracker.endRange();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int i, int i2, int i3) {
            this.mSelectionTracker.endRange();
        }
    }
}
