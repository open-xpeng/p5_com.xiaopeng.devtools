package androidx.recyclerview.selection;

import androidx.annotation.NonNull;
import androidx.recyclerview.selection.SelectionTracker;

/* loaded from: classes10.dex */
public final class SelectionPredicates {
    private SelectionPredicates() {
    }

    public static <K> SelectionTracker.SelectionPredicate<K> createSelectAnything() {
        return new SelectionTracker.SelectionPredicate<K>() { // from class: androidx.recyclerview.selection.SelectionPredicates.1
            @Override // androidx.recyclerview.selection.SelectionTracker.SelectionPredicate
            public boolean canSetStateForKey(@NonNull K k, boolean z) {
                return true;
            }

            @Override // androidx.recyclerview.selection.SelectionTracker.SelectionPredicate
            public boolean canSetStateAtPosition(int i, boolean z) {
                return true;
            }

            @Override // androidx.recyclerview.selection.SelectionTracker.SelectionPredicate
            public boolean canSelectMultiple() {
                return true;
            }
        };
    }

    public static <K> SelectionTracker.SelectionPredicate<K> createSelectSingleAnything() {
        return new SelectionTracker.SelectionPredicate<K>() { // from class: androidx.recyclerview.selection.SelectionPredicates.2
            @Override // androidx.recyclerview.selection.SelectionTracker.SelectionPredicate
            public boolean canSetStateForKey(@NonNull K k, boolean z) {
                return true;
            }

            @Override // androidx.recyclerview.selection.SelectionTracker.SelectionPredicate
            public boolean canSetStateAtPosition(int i, boolean z) {
                return true;
            }

            @Override // androidx.recyclerview.selection.SelectionTracker.SelectionPredicate
            public boolean canSelectMultiple() {
                return false;
            }
        };
    }
}
