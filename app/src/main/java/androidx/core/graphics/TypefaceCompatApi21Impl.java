package androidx.core.graphics;

import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.io.File;

@RequiresApi(21)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes8.dex */
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
    private static final String TAG = "TypefaceCompatApi21Impl";

    private File getFile(ParcelFileDescriptor parcelFileDescriptor) {
        try {
            String readlink = Os.readlink("/proc/self/fd/" + parcelFileDescriptor.getFd());
            if (!OsConstants.S_ISREG(Os.stat(readlink).st_mode)) {
                return null;
            }
            return new File(readlink);
        } catch (ErrnoException e) {
            return null;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public android.graphics.Typeface createFromFontInfo(android.content.Context r5, android.os.CancellationSignal r6, @androidx.annotation.NonNull androidx.core.provider.FontsContractCompat.FontInfo[] r7, int r8) {
        /*
            r4 = this;
            int r0 = r7.length
            r1 = 0
            r2 = 1
            if (r0 >= r2) goto L6
            return r1
        L6:
            androidx.core.provider.FontsContractCompat$FontInfo r7 = r4.findBestInfo(r7, r8)
            android.content.ContentResolver r8 = r5.getContentResolver()
            android.net.Uri r7 = r7.getUri()     // Catch: java.io.IOException -> L79
            java.lang.String r0 = "r"
            android.os.ParcelFileDescriptor r6 = r8.openFileDescriptor(r7, r0, r6)     // Catch: java.io.IOException -> L79
            java.io.File r7 = r4.getFile(r6)     // Catch: java.lang.Throwable -> L62
            if (r7 == 0) goto L31
            boolean r8 = r7.canRead()     // Catch: java.lang.Throwable -> L62
            if (r8 != 0) goto L27
            goto L31
        L27:
            android.graphics.Typeface r5 = android.graphics.Typeface.createFromFile(r7)     // Catch: java.lang.Throwable -> L62
            if (r6 == 0) goto L30
            r6.close()     // Catch: java.io.IOException -> L79
        L30:
            return r5
        L31:
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L62
            java.io.FileDescriptor r8 = r6.getFileDescriptor()     // Catch: java.lang.Throwable -> L62
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L62
            android.graphics.Typeface r5 = super.createFromInputStream(r5, r7)     // Catch: java.lang.Throwable -> L4a
            r7.close()     // Catch: java.lang.Throwable -> L62
            if (r6 == 0) goto L46
            r6.close()     // Catch: java.io.IOException -> L79
        L46:
            return r5
        L47:
            r5 = move-exception
            r8 = r1
            goto L50
        L4a:
            r5 = move-exception
            throw r5     // Catch: java.lang.Throwable -> L4c
        L4c:
            r8 = move-exception
            r3 = r8
            r8 = r5
            r5 = r3
        L50:
            if (r8 == 0) goto L5b
            r7.close()     // Catch: java.lang.Throwable -> L56
            goto L5e
        L56:
            r7 = move-exception
            r8.addSuppressed(r7)     // Catch: java.lang.Throwable -> L62
            goto L5e
        L5b:
            r7.close()     // Catch: java.lang.Throwable -> L62
        L5e:
            throw r5     // Catch: java.lang.Throwable -> L62
        L5f:
            r5 = move-exception
            r7 = r1
            goto L68
        L62:
            r5 = move-exception
            throw r5     // Catch: java.lang.Throwable -> L64
        L64:
            r7 = move-exception
            r3 = r7
            r7 = r5
            r5 = r3
        L68:
            if (r6 == 0) goto L78
            if (r7 == 0) goto L75
            r6.close()     // Catch: java.lang.Throwable -> L70
            goto L78
        L70:
            r6 = move-exception
            r7.addSuppressed(r6)     // Catch: java.io.IOException -> L79
            goto L78
        L75:
            r6.close()     // Catch: java.io.IOException -> L79
        L78:
            throw r5     // Catch: java.io.IOException -> L79
        L79:
            r5 = move-exception
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.TypefaceCompatApi21Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, androidx.core.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }
}
