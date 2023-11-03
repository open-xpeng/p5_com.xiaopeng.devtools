package com.xiaopeng.commonfunc.utils;

import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

/* compiled from: ZipUtil.java */
/* loaded from: classes11.dex */
public class r {
    public static boolean a(String[] strArr, String str, String str2) {
        boolean z;
        net.lingala.zip4j.a aVar = !TextUtils.isEmpty(str2) ? new net.lingala.zip4j.a(str, str2.toCharArray()) : new net.lingala.zip4j.a(str);
        try {
            try {
                ZipParameters zipParameters = new ZipParameters();
                zipParameters.a(CompressionMethod.DEFLATE);
                zipParameters.a(CompressionLevel.NORMAL);
                if (!TextUtils.isEmpty(str2)) {
                    zipParameters.bA(true);
                    zipParameters.a(EncryptionMethod.AES);
                    zipParameters.a(AesKeyStrength.KEY_STRENGTH_256);
                }
                for (String str3 : strArr) {
                    File file = new File(str3);
                    boolean exists = file.exists();
                    com.xiaopeng.lib.utils.c.i("ZipUtil", "srcPath[%s] isFileExists[%s]", str3, Boolean.valueOf(exists));
                    if (exists) {
                        if (file.isDirectory()) {
                            aVar.b(file, zipParameters);
                        } else {
                            aVar.a(file, zipParameters);
                        }
                    }
                }
                try {
                    com.xiaopeng.commonfunc.system.a.a.sleep(5000L);
                    aVar.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                z = true;
            } catch (Throwable th) {
                try {
                    com.xiaopeng.commonfunc.system.a.a.sleep(5000L);
                    aVar.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                throw th;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            try {
                com.xiaopeng.commonfunc.system.a.a.sleep(5000L);
                aVar.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
            z = false;
        }
        com.xiaopeng.lib.utils.c.i("ZipUtil", "zipFiles res[%s] ", Boolean.valueOf(z));
        return z;
    }
}
