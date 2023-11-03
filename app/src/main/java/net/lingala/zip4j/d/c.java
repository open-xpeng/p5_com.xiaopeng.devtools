package net.lingala.zip4j.d;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.DosFileAttributes;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.progress.ProgressMonitor;

/* compiled from: FileUtils.java */
/* loaded from: classes13.dex */
public class c {
    public static final byte[] apM = {0, 0, -92, -127};
    public static final byte[] apN = {0, 0, -19, 65};

    public static byte[] k(File file) {
        if (file != null) {
            try {
                if (Files.isSymbolicLink(file.toPath()) || file.exists()) {
                    Path path = file.toPath();
                    if (ui()) {
                        return a(path);
                    }
                    if (!uj() && !uk()) {
                        return new byte[4];
                    }
                    return b(path);
                }
            } catch (NoSuchMethodError e) {
                return new byte[4];
            }
        }
        return new byte[4];
    }

    public static List<File> a(File file, boolean z, boolean z2, net.lingala.zip4j.model.g gVar) throws ZipException {
        if (file == null) {
            throw new ZipException("input path is null, cannot read files in the directory");
        }
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (!file.isDirectory() || !file.canRead() || listFiles == null) {
            return arrayList;
        }
        for (File file2 : listFiles) {
            if (gVar == null || !gVar.i(file2)) {
                if (file2.isHidden()) {
                    if (file2.isDirectory()) {
                        if (!z2) {
                        }
                    } else if (!z) {
                    }
                }
                arrayList.add(file2);
                if (file2.isDirectory()) {
                    arrayList.addAll(a(file2, z, z2, gVar));
                }
            }
        }
        return arrayList;
    }

    public static String fv(String str) {
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return str;
        }
        return str.substring(0, lastIndexOf);
    }

    public static String fw(String str) throws ZipException {
        if (!f.fy(str)) {
            throw new ZipException("zip file name is empty or null, cannot determine zip file name");
        }
        if (str.contains(System.getProperty("file.separator"))) {
            str = str.substring(str.lastIndexOf(System.getProperty("file.separator")) + 1);
        }
        if (str.endsWith(".zip")) {
            return str.substring(0, str.lastIndexOf("."));
        }
        return str;
    }

    public static String c(File file, ZipParameters zipParameters) throws ZipException {
        String b;
        String substring;
        try {
            String canonicalPath = file.getCanonicalPath();
            if (f.fy(zipParameters.tP())) {
                String canonicalPath2 = new File(zipParameters.tP()).getCanonicalPath();
                if (!canonicalPath2.endsWith(d.apP)) {
                    canonicalPath2 = canonicalPath2 + d.apP;
                }
                if (o(file)) {
                    substring = new File(file.getParentFile().getCanonicalFile().getPath() + File.separator + file.getCanonicalFile().getName()).getPath().substring(canonicalPath2.length());
                } else {
                    substring = canonicalPath.substring(canonicalPath2.length());
                }
                if (substring.startsWith(System.getProperty("file.separator"))) {
                    substring = substring.substring(1);
                }
                File file2 = new File(canonicalPath);
                if (file2.isDirectory()) {
                    b = substring.replaceAll("\\\\", "/") + "/";
                } else {
                    b = substring.substring(0, substring.lastIndexOf(file2.getName())).replaceAll("\\\\", "/") + b(file2, zipParameters.tQ());
                }
            } else {
                File file3 = new File(canonicalPath);
                b = b(file3, zipParameters.tQ());
                if (file3.isDirectory()) {
                    b = b + "/";
                }
            }
            String tV = zipParameters.tV();
            if (f.fy(tV)) {
                if (!tV.endsWith("\\") && !tV.endsWith("/")) {
                    tV = tV + d.apP;
                }
                tV = tV.replaceAll("\\\\", "/");
                b = tV + b;
            }
            if (!f.fy(b)) {
                String str = "fileName to add to zip is empty or null. fileName: '" + b + "' DefaultFolderPath: '" + zipParameters.tP() + "' FileNameInZip: " + zipParameters.tQ();
                if (o(file)) {
                    str = str + "isSymlink: true ";
                }
                if (f.fy(tV)) {
                    str = "rootFolderNameInZip: '" + tV + "' ";
                }
                throw new ZipException(str);
            }
            return b;
        } catch (IOException e) {
            throw new ZipException(e);
        }
    }

    private static String b(File file, String str) throws IOException {
        if (f.fy(str)) {
            return str;
        }
        return o(file) ? file.toPath().toRealPath(LinkOption.NOFOLLOW_LINKS).getFileName().toString() : file.getName();
    }

    public static boolean fx(String str) {
        return str.endsWith("/") || str.endsWith("\\");
    }

    public static void b(RandomAccessFile randomAccessFile, OutputStream outputStream, long j, long j2, ProgressMonitor progressMonitor, int i) throws ZipException {
        int i2;
        byte[] bArr;
        long j3 = 0;
        if (j < 0 || j2 < 0 || j > j2) {
            throw new ZipException("invalid offsets");
        }
        if (i2 == 0) {
            return;
        }
        try {
            randomAccessFile.seek(j);
            long j4 = j2 - j;
            if (j4 < i) {
                bArr = new byte[(int) j4];
            } else {
                bArr = new byte[i];
            }
            while (true) {
                int read = randomAccessFile.read(bArr);
                if (read != -1) {
                    outputStream.write(bArr, 0, read);
                    long j5 = read;
                    progressMonitor.J(j5);
                    if (progressMonitor.ud()) {
                        progressMonitor.a(ProgressMonitor.Result.CANCELLED);
                        return;
                    }
                    j3 += j5;
                    if (j3 != j4) {
                        if (bArr.length + j3 > j4) {
                            bArr = new byte[(int) (j4 - j3)];
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            }
        } catch (IOException e) {
            throw new ZipException(e);
        }
    }

    public static void a(List<File> list, ZipParameters.SymbolicLinkAction symbolicLinkAction) throws ZipException {
        for (File file : list) {
            if (o(file)) {
                if (symbolicLinkAction.equals(ZipParameters.SymbolicLinkAction.INCLUDE_LINK_AND_LINKED_FILE) || symbolicLinkAction.equals(ZipParameters.SymbolicLinkAction.INCLUDE_LINKED_FILE_ONLY)) {
                    r(file);
                }
            } else {
                q(file);
            }
        }
    }

    public static boolean l(File file) {
        return file.getName().endsWith(".zip.001");
    }

    public static String m(File file) {
        String name = file.getName();
        if (!name.contains(".")) {
            return "";
        }
        return name.substring(name.lastIndexOf(".") + 1);
    }

    public static File[] n(File file) {
        final String fv = fv(file.getName());
        File[] listFiles = file.getParentFile().listFiles(new FilenameFilter() { // from class: net.lingala.zip4j.d.c.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str) {
                return str.startsWith(fv + ".");
            }
        });
        if (listFiles == null) {
            return new File[0];
        }
        Arrays.sort(listFiles);
        return listFiles;
    }

    public static boolean o(File file) {
        try {
            return Files.isSymbolicLink(file.toPath());
        } catch (Error | Exception e) {
            return false;
        }
    }

    public static String p(File file) {
        try {
            return Files.readSymbolicLink(file.toPath()).toString();
        } catch (Error | Exception e) {
            return "";
        }
    }

    public static byte[] bC(boolean z) {
        byte[] bArr = new byte[4];
        if (uk() || uj()) {
            if (z) {
                System.arraycopy(apN, 0, bArr, 0, bArr.length);
            } else {
                System.arraycopy(apM, 0, bArr, 0, bArr.length);
            }
        } else if (ui() && z) {
            bArr[0] = a.b(bArr[0], 4);
        }
        return bArr;
    }

    public static boolean ui() {
        return System.getProperty("os.name").toLowerCase().contains("win");
    }

    public static boolean uj() {
        return System.getProperty("os.name").toLowerCase().contains("mac");
    }

    public static boolean uk() {
        return System.getProperty("os.name").toLowerCase().contains("nux");
    }

    private static byte[] a(Path path) {
        byte[] bArr = new byte[4];
        try {
            DosFileAttributes readAttributes = ((DosFileAttributeView) Files.getFileAttributeView(path, DosFileAttributeView.class, LinkOption.NOFOLLOW_LINKS)).readAttributes();
            bArr[0] = a(readAttributes.isArchive(), a(readAttributes.isDirectory(), a(readAttributes.isSystem(), a(readAttributes.isHidden(), a(readAttributes.isReadOnly(), (byte) 0, 0), 1), 2), 4), 5);
        } catch (IOException e) {
        }
        return bArr;
    }

    private static void q(File file) throws ZipException {
        if (!file.exists()) {
            throw new ZipException("File does not exist: " + file);
        }
    }

    private static void r(File file) throws ZipException {
        if (!file.exists()) {
            throw new ZipException("Symlink target '" + p(file) + "' does not exist for link '" + file + "'");
        }
    }

    private static byte[] b(Path path) {
        byte[] bArr = new byte[4];
        try {
            Set<PosixFilePermission> permissions = ((PosixFileAttributeView) Files.getFileAttributeView(path, PosixFileAttributeView.class, LinkOption.NOFOLLOW_LINKS)).readAttributes().permissions();
            bArr[3] = a(Files.isRegularFile(path, new LinkOption[0]), bArr[3], 7);
            bArr[3] = a(Files.isDirectory(path, new LinkOption[0]), bArr[3], 6);
            bArr[3] = a(Files.isSymbolicLink(path), bArr[3], 5);
            bArr[3] = a(permissions.contains(PosixFilePermission.OWNER_READ), bArr[3], 0);
            bArr[2] = a(permissions.contains(PosixFilePermission.OWNER_WRITE), bArr[2], 7);
            bArr[2] = a(permissions.contains(PosixFilePermission.OWNER_EXECUTE), bArr[2], 6);
            bArr[2] = a(permissions.contains(PosixFilePermission.GROUP_READ), bArr[2], 5);
            bArr[2] = a(permissions.contains(PosixFilePermission.GROUP_WRITE), bArr[2], 4);
            bArr[2] = a(permissions.contains(PosixFilePermission.GROUP_EXECUTE), bArr[2], 3);
            bArr[2] = a(permissions.contains(PosixFilePermission.OTHERS_READ), bArr[2], 2);
            bArr[2] = a(permissions.contains(PosixFilePermission.OTHERS_WRITE), bArr[2], 1);
            bArr[2] = a(permissions.contains(PosixFilePermission.OTHERS_EXECUTE), bArr[2], 0);
        } catch (IOException e) {
        }
        return bArr;
    }

    private static byte a(boolean z, byte b, int i) {
        if (z) {
            return a.b(b, i);
        }
        return b;
    }
}
