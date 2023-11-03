package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.mediacompat.Rating2;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.MediaBrowserServiceCompat;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaItem2;
import android.support.v4.media.MediaMetadata2;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.MediaSession2;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public class MediaUtils2 {
    static final String TAG = "MediaUtils2";
    static final MediaBrowserServiceCompat.BrowserRoot sDefaultBrowserRoot = new MediaBrowserServiceCompat.BrowserRoot(MediaLibraryService2.SERVICE_INTERFACE, null);

    private MediaUtils2() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MediaBrowserCompat.MediaItem convertToMediaItem(MediaItem2 mediaItem2) {
        MediaDescriptionCompat build;
        if (mediaItem2 == null) {
            return null;
        }
        MediaMetadata2 metadata = mediaItem2.getMetadata();
        if (metadata == null) {
            build = new MediaDescriptionCompat.Builder().setMediaId(mediaItem2.getMediaId()).build();
        } else {
            MediaDescriptionCompat.Builder extras = new MediaDescriptionCompat.Builder().setMediaId(mediaItem2.getMediaId()).setSubtitle(metadata.getText("android.media.metadata.DISPLAY_SUBTITLE")).setDescription(metadata.getText("android.media.metadata.DISPLAY_DESCRIPTION")).setIconBitmap(metadata.getBitmap("android.media.metadata.DISPLAY_ICON")).setExtras(metadata.getExtras());
            String string = metadata.getString("android.media.metadata.TITLE");
            if (string != null) {
                extras.setTitle(string);
            } else {
                extras.setTitle(metadata.getString("android.media.metadata.DISPLAY_TITLE"));
            }
            String string2 = metadata.getString("android.media.metadata.DISPLAY_ICON_URI");
            if (string2 != null) {
                extras.setIconUri(Uri.parse(string2));
            }
            String string3 = metadata.getString("android.media.metadata.MEDIA_URI");
            if (string3 != null) {
                extras.setMediaUri(Uri.parse(string3));
            }
            build = extras.build();
        }
        return new MediaBrowserCompat.MediaItem(build, mediaItem2.getFlags());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<MediaBrowserCompat.MediaItem> convertToMediaItemList(List<MediaItem2> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(convertToMediaItem(list.get(i)));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MediaItem2 convertToMediaItem2(MediaBrowserCompat.MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.getMediaId() == null) {
            return null;
        }
        return new MediaItem2.Builder(mediaItem.getFlags()).setMediaId(mediaItem.getMediaId()).setMetadata(convertToMediaMetadata2(mediaItem.getDescription())).build();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<MediaItem2> convertToMediaItem2List(Parcelable[] parcelableArr) {
        MediaItem2 fromBundle;
        ArrayList arrayList = new ArrayList();
        if (parcelableArr != null) {
            for (int i = 0; i < parcelableArr.length; i++) {
                if ((parcelableArr[i] instanceof Bundle) && (fromBundle = MediaItem2.fromBundle((Bundle) parcelableArr[i])) != null) {
                    arrayList.add(fromBundle);
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<MediaItem2> convertMediaItemListToMediaItem2List(List<MediaBrowserCompat.MediaItem> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(convertToMediaItem2(list.get(i)));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<MediaItem2> convertBundleListToMediaItem2List(List<Bundle> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Bundle bundle = list.get(i);
            if (bundle != null) {
                arrayList.add(MediaItem2.fromBundle(bundle));
            }
        }
        return arrayList;
    }

    static MediaMetadata2 convertToMediaMetadata2(MediaDescriptionCompat mediaDescriptionCompat) {
        if (mediaDescriptionCompat == null) {
            return null;
        }
        MediaMetadata2.Builder builder = new MediaMetadata2.Builder();
        builder.putString("android.media.metadata.MEDIA_ID", mediaDescriptionCompat.getMediaId());
        CharSequence title = mediaDescriptionCompat.getTitle();
        if (title != null) {
            builder.putText("android.media.metadata.DISPLAY_TITLE", title);
        }
        if (mediaDescriptionCompat.getDescription() != null) {
            builder.putText("android.media.metadata.DISPLAY_DESCRIPTION", mediaDescriptionCompat.getDescription());
        }
        CharSequence subtitle = mediaDescriptionCompat.getSubtitle();
        if (subtitle != null) {
            builder.putText("android.media.metadata.DISPLAY_SUBTITLE", subtitle);
        }
        Bitmap iconBitmap = mediaDescriptionCompat.getIconBitmap();
        if (iconBitmap != null) {
            builder.putBitmap("android.media.metadata.DISPLAY_ICON", iconBitmap);
        }
        Uri iconUri = mediaDescriptionCompat.getIconUri();
        if (iconUri != null) {
            builder.putText("android.media.metadata.DISPLAY_ICON_URI", iconUri.toString());
        }
        if (mediaDescriptionCompat.getExtras() != null) {
            builder.setExtras(mediaDescriptionCompat.getExtras());
        }
        Uri mediaUri = mediaDescriptionCompat.getMediaUri();
        if (mediaUri != null) {
            builder.putText("android.media.metadata.MEDIA_URI", mediaUri.toString());
        }
        return builder.build();
    }

    static MediaMetadata2 convertToMediaMetadata2(MediaMetadataCompat mediaMetadataCompat) {
        if (mediaMetadataCompat == null) {
            return null;
        }
        return new MediaMetadata2(mediaMetadataCompat.getBundle());
    }

    static MediaMetadataCompat convertToMediaMetadataCompat(MediaMetadata2 mediaMetadata2) {
        if (mediaMetadata2 == null) {
            return null;
        }
        MediaMetadataCompat.Builder builder = new MediaMetadataCompat.Builder();
        ArrayList<String> arrayList = new ArrayList();
        Bundle bundle = mediaMetadata2.toBundle();
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj instanceof CharSequence) {
                builder.putText(str, (CharSequence) obj);
            } else if (obj instanceof Rating2) {
                builder.putRating(str, convertToRatingCompat((Rating2) obj));
            } else if (obj instanceof Bitmap) {
                builder.putBitmap(str, (Bitmap) obj);
            } else if (obj instanceof Long) {
                builder.putLong(str, ((Long) obj).longValue());
            } else {
                arrayList.add(str);
            }
        }
        MediaMetadataCompat build = builder.build();
        for (String str2 : arrayList) {
            Object obj2 = bundle.get(str2);
            if (obj2 instanceof Float) {
                build.getBundle().putFloat(str2, ((Float) obj2).floatValue());
            } else if (MediaMetadata2.METADATA_KEY_EXTRAS.equals(obj2)) {
                build.getBundle().putBundle(str2, (Bundle) obj2);
            }
        }
        return build;
    }

    static Rating2 convertToRating2(RatingCompat ratingCompat) {
        if (ratingCompat == null) {
            return null;
        }
        if (!ratingCompat.isRated()) {
            return Rating2.newUnratedRating(ratingCompat.getRatingStyle());
        }
        switch (ratingCompat.getRatingStyle()) {
            case 1:
                return Rating2.newHeartRating(ratingCompat.hasHeart());
            case 2:
                return Rating2.newThumbRating(ratingCompat.isThumbUp());
            case 3:
            case 4:
            case 5:
                return Rating2.newStarRating(ratingCompat.getRatingStyle(), ratingCompat.getStarRating());
            case 6:
                return Rating2.newPercentageRating(ratingCompat.getPercentRating());
            default:
                return null;
        }
    }

    static RatingCompat convertToRatingCompat(Rating2 rating2) {
        if (rating2 == null) {
            return null;
        }
        if (!rating2.isRated()) {
            return RatingCompat.newUnratedRating(rating2.getRatingStyle());
        }
        switch (rating2.getRatingStyle()) {
            case 1:
                return RatingCompat.newHeartRating(rating2.hasHeart());
            case 2:
                return RatingCompat.newThumbRating(rating2.isThumbUp());
            case 3:
            case 4:
            case 5:
                return RatingCompat.newStarRating(rating2.getRatingStyle(), rating2.getStarRating());
            case 6:
                return RatingCompat.newPercentageRating(rating2.getPercentRating());
            default:
                return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<Bundle> convertToBundleList(Parcelable[] parcelableArr) {
        if (parcelableArr == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Parcelable parcelable : parcelableArr) {
            arrayList.add((Bundle) parcelable);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<Bundle> convertMediaItem2ListToBundleList(List<MediaItem2> list) {
        Bundle bundle;
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            MediaItem2 mediaItem2 = list.get(i);
            if (mediaItem2 != null && (bundle = mediaItem2.toBundle()) != null) {
                arrayList.add(bundle);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<Bundle> convertCommandButtonListToBundleList(List<MediaSession2.CommandButton> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Bundle bundle = list.get(i).toBundle();
            if (bundle != null) {
                arrayList.add(bundle);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Parcelable[] convertMediaItem2ListToParcelableArray(List<MediaItem2> list) {
        Bundle bundle;
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            MediaItem2 mediaItem2 = list.get(i);
            if (mediaItem2 != null && (bundle = mediaItem2.toBundle()) != null) {
                arrayList.add(bundle);
            }
        }
        return (Parcelable[]) arrayList.toArray(new Parcelable[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Parcelable[] convertCommandButtonListToParcelableArray(List<MediaSession2.CommandButton> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Bundle bundle = list.get(i).toBundle();
            if (bundle != null) {
                arrayList.add(bundle);
            }
        }
        return (Parcelable[]) arrayList.toArray(new Parcelable[0]);
    }

    static List<MediaSession2.CommandButton> convertToCommandButtonList(List<Bundle> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            Bundle bundle = list.get(i);
            if (bundle != null) {
                arrayList.add(MediaSession2.CommandButton.fromBundle(bundle));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static List<MediaSession2.CommandButton> convertToCommandButtonList(Parcelable[] parcelableArr) {
        MediaSession2.CommandButton fromBundle;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < parcelableArr.length; i++) {
            if ((parcelableArr[i] instanceof Bundle) && (fromBundle = MediaSession2.CommandButton.fromBundle((Bundle) parcelableArr[i])) != null) {
                arrayList.add(fromBundle);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int convertToPlaybackStateCompatState(int i, int i2) {
        switch (i) {
            case 0:
                return 0;
            case 1:
                return 2;
            case 2:
                if (i2 == 2) {
                    return 6;
                }
                return 3;
            case 3:
                return 7;
            default:
                return 7;
        }
    }

    static int convertToPlayerState(int i) {
        switch (i) {
            case 0:
                return 0;
            case 1:
            case 2:
            case 6:
                return 1;
            case 3:
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
            case 11:
                return 2;
            case 7:
                return 3;
            default:
                return 3;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isDefaultLibraryRootHint(Bundle bundle) {
        return bundle != null && bundle.getBoolean("android.support.v4.media.root_default_root", false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Bundle createBundle(Bundle bundle) {
        return bundle == null ? new Bundle() : new Bundle(bundle);
    }
}
