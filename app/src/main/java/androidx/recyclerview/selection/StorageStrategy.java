package androidx.recyclerview.selection;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes10.dex */
public abstract class StorageStrategy<K> {
    @VisibleForTesting
    static final String SELECTION_ENTRIES = "androidx.recyclerview.selection.entries";
    @VisibleForTesting
    static final String SELECTION_KEY_TYPE = "androidx.recyclerview.selection.type";
    private final Class<K> mType;

    @NonNull
    public abstract Bundle asBundle(@NonNull Selection<K> selection);

    @Nullable
    public abstract Selection<K> asSelection(@NonNull Bundle bundle);

    public StorageStrategy(@NonNull Class<K> cls) {
        Preconditions.checkArgument(cls != null);
        this.mType = cls;
    }

    String getKeyTypeName() {
        return this.mType.getCanonicalName();
    }

    public static <K extends Parcelable> StorageStrategy<K> createParcelableStorage(Class<K> cls) {
        return new ParcelableStorageStrategy(cls);
    }

    public static StorageStrategy<String> createStringStorage() {
        return new StringStorageStrategy();
    }

    public static StorageStrategy<Long> createLongStorage() {
        return new LongStorageStrategy();
    }

    /* loaded from: classes10.dex */
    private static class StringStorageStrategy extends StorageStrategy<String> {
        StringStorageStrategy() {
            super(String.class);
        }

        @Override // androidx.recyclerview.selection.StorageStrategy
        @Nullable
        public Selection<String> asSelection(@NonNull Bundle bundle) {
            ArrayList<String> stringArrayList;
            String string = bundle.getString(StorageStrategy.SELECTION_KEY_TYPE, null);
            if (string == null || !string.equals(getKeyTypeName()) || (stringArrayList = bundle.getStringArrayList(StorageStrategy.SELECTION_ENTRIES)) == null) {
                return null;
            }
            Selection<String> selection = new Selection<>();
            selection.mSelection.addAll(stringArrayList);
            return selection;
        }

        @Override // androidx.recyclerview.selection.StorageStrategy
        @NonNull
        public Bundle asBundle(@NonNull Selection<String> selection) {
            Bundle bundle = new Bundle();
            bundle.putString(StorageStrategy.SELECTION_KEY_TYPE, getKeyTypeName());
            ArrayList<String> arrayList = new ArrayList<>(selection.size());
            arrayList.addAll(selection.mSelection);
            bundle.putStringArrayList(StorageStrategy.SELECTION_ENTRIES, arrayList);
            return bundle;
        }
    }

    /* loaded from: classes10.dex */
    private static class LongStorageStrategy extends StorageStrategy<Long> {
        LongStorageStrategy() {
            super(Long.class);
        }

        @Override // androidx.recyclerview.selection.StorageStrategy
        @Nullable
        public Selection<Long> asSelection(@NonNull Bundle bundle) {
            long[] longArray;
            String string = bundle.getString(StorageStrategy.SELECTION_KEY_TYPE, null);
            if (string == null || !string.equals(getKeyTypeName()) || (longArray = bundle.getLongArray(StorageStrategy.SELECTION_ENTRIES)) == null) {
                return null;
            }
            Selection<Long> selection = new Selection<>();
            for (long j : longArray) {
                selection.mSelection.add(Long.valueOf(j));
            }
            return selection;
        }

        @Override // androidx.recyclerview.selection.StorageStrategy
        @NonNull
        public Bundle asBundle(@NonNull Selection<Long> selection) {
            Bundle bundle = new Bundle();
            bundle.putString(StorageStrategy.SELECTION_KEY_TYPE, getKeyTypeName());
            long[] jArr = new long[selection.size()];
            Iterator<Long> it = selection.iterator();
            int i = 0;
            while (it.hasNext()) {
                jArr[i] = it.next().longValue();
                i++;
            }
            bundle.putLongArray(StorageStrategy.SELECTION_ENTRIES, jArr);
            return bundle;
        }
    }

    /* loaded from: classes10.dex */
    private static class ParcelableStorageStrategy<K extends Parcelable> extends StorageStrategy<K> {
        ParcelableStorageStrategy(Class<K> cls) {
            super(cls);
            Preconditions.checkArgument(Parcelable.class.isAssignableFrom(cls));
        }

        @Override // androidx.recyclerview.selection.StorageStrategy
        @Nullable
        public Selection<K> asSelection(@NonNull Bundle bundle) {
            ArrayList parcelableArrayList;
            String string = bundle.getString(StorageStrategy.SELECTION_KEY_TYPE, null);
            if (string == null || !string.equals(getKeyTypeName()) || (parcelableArrayList = bundle.getParcelableArrayList(StorageStrategy.SELECTION_ENTRIES)) == null) {
                return null;
            }
            Selection<K> selection = new Selection<>();
            selection.mSelection.addAll(parcelableArrayList);
            return selection;
        }

        @Override // androidx.recyclerview.selection.StorageStrategy
        @NonNull
        public Bundle asBundle(@NonNull Selection<K> selection) {
            Bundle bundle = new Bundle();
            bundle.putString(StorageStrategy.SELECTION_KEY_TYPE, getKeyTypeName());
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>(selection.size());
            arrayList.addAll(selection.mSelection);
            bundle.putParcelableArrayList(StorageStrategy.SELECTION_ENTRIES, arrayList);
            return bundle;
        }
    }
}
