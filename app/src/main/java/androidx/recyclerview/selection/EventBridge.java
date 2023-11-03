package androidx.recyclerview.selection;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Preconditions;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
@VisibleForTesting(otherwise = 3)
/* loaded from: classes10.dex */
public class EventBridge {
    private static final String TAG = "EventsRelays";

    public static <K> void install(@NonNull RecyclerView.Adapter<?> adapter, @NonNull SelectionTracker<K> selectionTracker, @NonNull ItemKeyProvider<K> itemKeyProvider) {
        new TrackerToAdapterBridge(selectionTracker, itemKeyProvider, adapter);
        adapter.registerAdapterDataObserver(selectionTracker.getAdapterDataObserver());
    }

    /* loaded from: classes10.dex */
    private static final class TrackerToAdapterBridge<K> extends SelectionTracker.SelectionObserver<K> {
        private final RecyclerView.Adapter<?> mAdapter;
        private final ItemKeyProvider<K> mKeyProvider;

        TrackerToAdapterBridge(@NonNull SelectionTracker<K> selectionTracker, @NonNull ItemKeyProvider<K> itemKeyProvider, @NonNull RecyclerView.Adapter<?> adapter) {
            selectionTracker.addObserver(this);
            Preconditions.checkArgument(itemKeyProvider != null);
            Preconditions.checkArgument(adapter != null);
            this.mKeyProvider = itemKeyProvider;
            this.mAdapter = adapter;
        }

        @Override // androidx.recyclerview.selection.SelectionTracker.SelectionObserver
        public void onItemStateChanged(@NonNull K k, boolean z) {
            int position = this.mKeyProvider.getPosition(k);
            if (position < 0) {
                Log.w(EventBridge.TAG, "Item change notification received for unknown item: " + k);
                return;
            }
            this.mAdapter.notifyItemChanged(position, SelectionTracker.SELECTION_CHANGED_MARKER);
        }
    }

    private EventBridge() {
    }
}
