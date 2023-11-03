package android.pso;

import android.content.Context;
import android.pso.PsoCrypto;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProtection;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class PsoAKS {
    public static final int DecBlockSize = 10240;
    private static KeyStore b;
    Context a = null;

    public PsoAKS() {
        a();
    }

    protected String a() {
        String exc;
        try {
            b = KeyStore.getInstance("XpengAndroidKeyStore");
            b.load(null);
            exc = null;
        } catch (IOException e) {
            exc = e.toString();
        } catch (KeyStoreException e2) {
            exc = e2.toString();
        } catch (NoSuchAlgorithmException e3) {
            exc = e3.toString();
        } catch (CertificateException e4) {
            exc = e4.toString();
        } catch (Exception e5) {
            exc = e5.toString();
        }
        if (exc != null) {
            b = null;
            Log.e("PsoAKS", "Keystore initial failed...\r\n(" + exc + ")");
        }
        return exc;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String a(String str, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(str.getBytes(StandardCharsets.UTF_8));
        return Base64Util.encodeToString(signature.sign());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PrivateKey a(byte[] bArr) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(bArr));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PublicKey a(byte[] bArr, String str) throws XpPsoException {
        String invalidKeySpecException;
        if (bArr != null) {
            PublicKey publicKey = null;
            try {
                publicKey = KeyFactory.getInstance(str).generatePublic(new X509EncodedKeySpec(bArr));
                invalidKeySpecException = null;
            } catch (NoSuchAlgorithmException e) {
                invalidKeySpecException = e.toString();
            } catch (InvalidKeySpecException e2) {
                invalidKeySpecException = e2.toString();
            }
            if (publicKey != null) {
                return publicKey;
            }
            throw new XpPsoException(this.a, "der to  PublicKey Failed\r\n(" + invalidKeySpecException + ")", PsoCrypto.Desc.der2PublicKeyFailed);
        }
        throw new XpPsoException(this.a, "PublicKey der input is null", -14);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Signature a(PublicKey publicKey) throws XpPsoException {
        String noSuchAlgorithmException;
        try {
            Signature signature = Signature.getInstance("SHA512withECDSA");
            signature.initVerify(publicKey);
            return signature;
        } catch (InvalidKeyException e) {
            noSuchAlgorithmException = e.toString();
            Context context = this.a;
            throw new XpPsoException(context, "get Verify Signature Failed...\r\n(" + noSuchAlgorithmException + ")", -41);
        } catch (NoSuchAlgorithmException e2) {
            noSuchAlgorithmException = e2.toString();
            Context context2 = this.a;
            throw new XpPsoException(context2, "get Verify Signature Failed...\r\n(" + noSuchAlgorithmException + ")", -41);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(String str) {
        try {
            return (b != null) && b.containsAlias(str);
        } catch (KeyStoreException e) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(String str, String str2) throws XpPsoException {
        boolean z = false;
        String str3 = null;
        try {
            z = a(str, "CBC", "NoPadding");
        } catch (UnsupportedEncodingException e) {
        } catch (InvalidAlgorithmParameterException e2) {
            str3 = e2.toString();
        } catch (InvalidKeyException e3) {
            str3 = e3.toString();
        } catch (KeyStoreException e4) {
            str3 = e4.toString();
        } catch (NoSuchAlgorithmException e5) {
            str3 = e5.toString();
        } catch (UnrecoverableKeyException e6) {
            str3 = e6.toString();
        } catch (BadPaddingException e7) {
            str3 = e7.toString();
        } catch (IllegalBlockSizeException e8) {
            str3 = e8.toString();
        } catch (NoSuchPaddingException e9) {
            str3 = e9.toString();
        } catch (Exception e10) {
            str3 = e10.toString();
        }
        if (z) {
            return z;
        }
        Context context = this.a;
        throw new XpPsoException(context, "Aes Cbc Key " + str2 + "Test Failed\r\n(" + str3 + ")", PsoCrypto.Desc.TestAesCbcKeyFailed);
    }

    protected boolean a(String str, String str2, String str3) throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        SecretKey secretKey = (SecretKey) b.getKey(str, null);
        IvParameterSpec ivParameterSpec = new IvParameterSpec("1234567890123456".getBytes());
        Cipher cipher = Cipher.getInstance("AES/" + str2 + "/" + str3);
        cipher.init(1, secretKey, ivParameterSpec);
        byte[] doFinal = cipher.doFinal("1234567890123456".getBytes("UTF-8"));
        String str4 = "";
        for (int i = 0; i < doFinal.length; i++) {
            str4 = str4 + String.format("0x%02x,", Byte.valueOf(doFinal[i]));
        }
        Base64Util.encodeToString(doFinal);
        cipher.init(2, secretKey, ivParameterSpec);
        return "1234567890123456".equals(new String(cipher.doFinal(doFinal)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(String str, String str2, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);
        signature.update(str.getBytes(StandardCharsets.UTF_8));
        return signature.verify(Base64Util.decode(str2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(String str, byte[] bArr) throws XpPsoException {
        String exc;
        try {
            return a(str, bArr, "CBC", "NoPadding");
        } catch (IOException e) {
            exc = e.toString();
            Context context = this.a;
            throw new XpPsoException(context, "Save Aes Cbc Key Failed\r\n(" + exc + ")", PsoCrypto.Desc.SaveAesCbcKeyFailed);
        } catch (KeyStoreException e2) {
            exc = e2.toString();
            Context context2 = this.a;
            throw new XpPsoException(context2, "Save Aes Cbc Key Failed\r\n(" + exc + ")", PsoCrypto.Desc.SaveAesCbcKeyFailed);
        } catch (NoSuchAlgorithmException e3) {
            exc = e3.toString();
            Context context22 = this.a;
            throw new XpPsoException(context22, "Save Aes Cbc Key Failed\r\n(" + exc + ")", PsoCrypto.Desc.SaveAesCbcKeyFailed);
        } catch (CertificateException e4) {
            exc = e4.toString();
            Context context222 = this.a;
            throw new XpPsoException(context222, "Save Aes Cbc Key Failed\r\n(" + exc + ")", PsoCrypto.Desc.SaveAesCbcKeyFailed);
        } catch (Exception e5) {
            exc = e5.toString();
            Context context2222 = this.a;
            throw new XpPsoException(context2222, "Save Aes Cbc Key Failed\r\n(" + exc + ")", PsoCrypto.Desc.SaveAesCbcKeyFailed);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(String str, byte[] bArr, String str2) throws XpPsoException {
        String exc;
        try {
            return a(str, bArr, "CTR", "NoPadding");
        } catch (IOException e) {
            exc = e.toString();
            Context context = this.a;
            throw new XpPsoException(context, "Save Aes Ctr Key " + str2 + " Failed\r\n(" + exc + ")", PsoCrypto.Desc.SaveAesCbcKeyFailed);
        } catch (KeyStoreException e2) {
            exc = e2.toString();
            Context context2 = this.a;
            throw new XpPsoException(context2, "Save Aes Ctr Key " + str2 + " Failed\r\n(" + exc + ")", PsoCrypto.Desc.SaveAesCbcKeyFailed);
        } catch (NoSuchAlgorithmException e3) {
            exc = e3.toString();
            Context context22 = this.a;
            throw new XpPsoException(context22, "Save Aes Ctr Key " + str2 + " Failed\r\n(" + exc + ")", PsoCrypto.Desc.SaveAesCbcKeyFailed);
        } catch (CertificateException e4) {
            exc = e4.toString();
            Context context222 = this.a;
            throw new XpPsoException(context222, "Save Aes Ctr Key " + str2 + " Failed\r\n(" + exc + ")", PsoCrypto.Desc.SaveAesCbcKeyFailed);
        } catch (Exception e5) {
            exc = e5.toString();
            Context context2222 = this.a;
            throw new XpPsoException(context2222, "Save Aes Ctr Key " + str2 + " Failed\r\n(" + exc + ")", PsoCrypto.Desc.SaveAesCbcKeyFailed);
        }
    }

    protected boolean a(String str, byte[] bArr, String str2, String str3) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException {
        b.setEntry(str, new KeyStore.SecretKeyEntry(new SecretKeySpec(bArr, "AES")), new KeyProtection.Builder(3).setBlockModes(str2).setEncryptionPaddings(str3).setRandomizedEncryptionRequired(false).build());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean a(byte[] bArr, byte[] bArr2, PublicKey publicKey) throws XpPsoException {
        String signatureException;
        boolean z = false;
        try {
            Signature signature = Signature.getInstance("SHA512withECDSA");
            signature.initVerify(publicKey);
            signature.update(bArr);
            boolean verify = signature.verify(bArr2);
            signatureException = null;
            z = verify;
        } catch (InvalidKeyException e) {
            signatureException = e.toString();
        } catch (NoSuchAlgorithmException e2) {
            signatureException = e2.toString();
        } catch (SignatureException e3) {
            signatureException = e3.toString();
        }
        if (z) {
            return z;
        }
        Context context = this.a;
        throw new XpPsoException(context, "bin Signed Data Verify Fail...\r\n(" + signatureException + ")", -4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] a(String str, byte[] bArr, byte[] bArr2) throws XpPsoException {
        String exc;
        byte[] bArr3 = null;
        try {
            bArr3 = a(str, bArr, bArr2, "CBC", "NoPadding");
            exc = null;
        } catch (InvalidAlgorithmParameterException e) {
            exc = e.toString();
        } catch (InvalidKeyException e2) {
            exc = e2.toString();
        } catch (KeyStoreException e3) {
            exc = e3.toString();
        } catch (NoSuchAlgorithmException e4) {
            exc = e4.toString();
        } catch (UnrecoverableKeyException e5) {
            exc = e5.toString();
        } catch (NoSuchPaddingException e6) {
            exc = e6.toString();
        } catch (ShortBufferException e7) {
            exc = e7.toString();
        } catch (Exception e8) {
            exc = e8.toString();
        }
        if (bArr3 != null) {
            return bArr3;
        }
        throw new XpPsoException(this.a, "Aes Cbc Encrypt Fail\r\n(" + exc + ")", -31);
    }

    protected byte[] a(String str, byte[] bArr, byte[] bArr2, String str2, String str3) throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, ShortBufferException, XpPsoException, BadPaddingException, IllegalBlockSizeException {
        byte[] bArr3 = new byte[bArr.length];
        byte[] bArr4 = new byte[bArr2.length];
        System.arraycopy(bArr2, 0, bArr4, 0, bArr4.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr4);
        Cipher cipher = Cipher.getInstance("AES/" + str2 + "/" + str3);
        cipher.init(1, (SecretKey) b.getKey(str, null), ivParameterSpec);
        return b(cipher, bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] a(Cipher cipher, byte[] bArr) throws BadPaddingException, NoSuchAlgorithmException, UnrecoverableKeyException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchPaddingException, ShortBufferException, KeyStoreException, IllegalBlockSizeException {
        return b(cipher, bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] a(byte[] bArr, SecretKey secretKey, byte[] bArr2) throws XpPsoException {
        String exc;
        byte[] bArr3 = null;
        try {
            bArr3 = a(bArr, secretKey, bArr2, "CTR", "NoPadding");
            exc = null;
        } catch (InvalidAlgorithmParameterException e) {
            exc = e.toString();
        } catch (InvalidKeyException e2) {
            exc = e2.toString();
        } catch (NoSuchAlgorithmException e3) {
            exc = e3.toString();
        } catch (BadPaddingException e4) {
            exc = e4.toString();
        } catch (IllegalBlockSizeException e5) {
            exc = e5.toString();
        } catch (NoSuchPaddingException e6) {
            exc = e6.toString();
        } catch (ShortBufferException e7) {
            exc = e7.toString();
        } catch (Exception e8) {
            exc = e8.toString();
        }
        if (bArr3 != null) {
            return bArr3;
        }
        throw new XpPsoException(this.a, "Aes Ctr Decrypt Failed...\r\n(" + exc + ")", -3);
    }

    protected byte[] a(byte[] bArr, SecretKey secretKey, byte[] bArr2, String str, String str2) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, ShortBufferException, BadPaddingException, IllegalBlockSizeException {
        byte[] bArr3 = new byte[bArr.length];
        System.arraycopy(bArr2, 0, bArr2, 0, bArr2.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr2);
        Cipher cipher = Cipher.getInstance("AES/" + str + "/" + str2);
        cipher.init(2, secretKey, ivParameterSpec);
        return b(cipher, bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Signature b(PublicKey publicKey) throws XpPsoException {
        String noSuchAlgorithmException;
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(publicKey);
            return signature;
        } catch (InvalidKeyException e) {
            noSuchAlgorithmException = e.toString();
            Context context = this.a;
            throw new XpPsoException(context, "get Verify Signature Failed...\r\n(" + noSuchAlgorithmException + ")", -41);
        } catch (NoSuchAlgorithmException e2) {
            noSuchAlgorithmException = e2.toString();
            Context context2 = this.a;
            throw new XpPsoException(context2, "get Verify Signature Failed...\r\n(" + noSuchAlgorithmException + ")", -41);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Certificate b(byte[] bArr) throws CertificateException {
        return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Cipher b(String str, byte[] bArr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException, UnrecoverableKeyException, KeyStoreException {
        return c(str, bArr, "CBC", "NoPadding");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b() throws XpPsoException {
        String a = b == null ? a() : null;
        if (b != null) {
            return;
        }
        Context context = this.a;
        throw new XpPsoException(context, "Keystore initial failed...\r\n(" + a + ")", -99);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean b(String str) throws XpPsoException {
        String exc;
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", "XpengAndroidKeyStore");
            keyGenerator.init(new KeyGenParameterSpec.Builder(str, 3).setBlockModes("CBC").setEncryptionPaddings("NoPadding").setRandomizedEncryptionRequired(false).build());
            keyGenerator.generateKey();
            return true;
        } catch (InvalidAlgorithmParameterException e) {
            exc = e.toString();
            Context context = this.a;
            throw new XpPsoException(context, "create Ramdon Aes Cbc Key failed...\r\n(" + exc + ")", -72);
        } catch (NoSuchAlgorithmException e2) {
            exc = e2.toString();
            Context context2 = this.a;
            throw new XpPsoException(context2, "create Ramdon Aes Cbc Key failed...\r\n(" + exc + ")", -72);
        } catch (NoSuchProviderException e3) {
            exc = e3.toString();
            Context context22 = this.a;
            throw new XpPsoException(context22, "create Ramdon Aes Cbc Key failed...\r\n(" + exc + ")", -72);
        } catch (Exception e4) {
            exc = e4.toString();
            Context context222 = this.a;
            throw new XpPsoException(context222, "create Ramdon Aes Cbc Key failed...\r\n(" + exc + ")", -72);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean b(String str, String str2) throws XpPsoException {
        String exc;
        boolean z = false;
        try {
            byte[] bArr = new byte[16];
            Arrays.fill(bArr, (byte) 0);
            boolean b2 = b(str, bArr, "CTR", "NoPadding");
            exc = null;
            z = b2;
        } catch (UnsupportedEncodingException e) {
            exc = e.toString();
        } catch (InvalidAlgorithmParameterException e2) {
            exc = e2.toString();
        } catch (InvalidKeyException e3) {
            exc = e3.toString();
        } catch (KeyStoreException e4) {
            exc = e4.toString();
        } catch (NoSuchAlgorithmException e5) {
            exc = e5.toString();
        } catch (UnrecoverableKeyException e6) {
            exc = e6.toString();
        } catch (BadPaddingException e7) {
            exc = e7.toString();
        } catch (IllegalBlockSizeException e8) {
            exc = e8.toString();
        } catch (NoSuchPaddingException e9) {
            exc = e9.toString();
        } catch (Exception e10) {
            exc = e10.toString();
        }
        if (z) {
            return z;
        }
        Context context = this.a;
        throw new XpPsoException(context, "Aes Ctr Key Test " + str2 + "Failed\r\n(" + exc + ")", PsoCrypto.Desc.TestAesCrtKeyFailed);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean b(String str, byte[] bArr, String str2) throws XpPsoException {
        String exc;
        try {
            return a(str, bArr, "CBC", "NoPadding");
        } catch (IOException e) {
            exc = e.toString();
            Context context = this.a;
            throw new XpPsoException(context, "Save Aes Cbc Key " + str2 + "Failed\r\n(" + exc + ")", PsoCrypto.Desc.SaveAesCbcKeyFailed);
        } catch (KeyStoreException e2) {
            exc = e2.toString();
            Context context2 = this.a;
            throw new XpPsoException(context2, "Save Aes Cbc Key " + str2 + "Failed\r\n(" + exc + ")", PsoCrypto.Desc.SaveAesCbcKeyFailed);
        } catch (NoSuchAlgorithmException e3) {
            exc = e3.toString();
            Context context22 = this.a;
            throw new XpPsoException(context22, "Save Aes Cbc Key " + str2 + "Failed\r\n(" + exc + ")", PsoCrypto.Desc.SaveAesCbcKeyFailed);
        } catch (CertificateException e4) {
            exc = e4.toString();
            Context context222 = this.a;
            throw new XpPsoException(context222, "Save Aes Cbc Key " + str2 + "Failed\r\n(" + exc + ")", PsoCrypto.Desc.SaveAesCbcKeyFailed);
        } catch (Exception e5) {
            exc = e5.toString();
            Context context2222 = this.a;
            throw new XpPsoException(context2222, "Save Aes Cbc Key " + str2 + "Failed\r\n(" + exc + ")", PsoCrypto.Desc.SaveAesCbcKeyFailed);
        }
    }

    protected boolean b(String str, byte[] bArr, String str2, String str3) throws KeyStoreException, UnrecoverableKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        if (!a(str)) {
            Log.e("PsoAKS", str + " is invalid .... ");
        }
        SecretKey secretKey = (SecretKey) b.getKey(str, null);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr);
        Cipher cipher = Cipher.getInstance("AES/" + str2 + "/" + str3);
        cipher.init(1, secretKey, ivParameterSpec);
        byte[] doFinal = cipher.doFinal("1234567890123456".getBytes("UTF-8"));
        String str4 = "";
        for (int i = 0; i < doFinal.length; i++) {
            str4 = str4 + String.format("0x%02x,", Byte.valueOf(doFinal[i]));
        }
        Base64Util.encodeToString(doFinal);
        cipher.init(2, secretKey, ivParameterSpec);
        return "1234567890123456".equals(new String(cipher.doFinal(doFinal)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] b(String str, byte[] bArr, byte[] bArr2) throws NoSuchPaddingException, ShortBufferException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, InvalidKeyException, XpPsoException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
        return a(str, bArr, bArr2, "CBC", "NoPadding");
    }

    protected byte[] b(Cipher cipher, byte[] bArr) throws ShortBufferException, BadPaddingException, IllegalBlockSizeException {
        byte[] bArr2 = new byte[bArr.length];
        int length = 10240 > bArr.length ? bArr.length : 10240;
        int length2 = (bArr.length / length) + (bArr.length % length == 0 ? 0 : 1);
        if (length2 > 1) {
            int i = 0;
            int i2 = 0;
            for (int i3 = 0; i3 < length2; i3++) {
                int i4 = i + length;
                if (i4 <= bArr.length) {
                    int update = cipher.update(bArr, i, length, bArr2, i2);
                    if (update > 0) {
                        i2 += update;
                    }
                    i = i4;
                } else {
                    int length3 = bArr.length - i;
                    byte[] doFinal = cipher.doFinal(bArr, i, length3);
                    if (doFinal != null) {
                        System.arraycopy(doFinal, 0, bArr2, i2, doFinal.length);
                    }
                    i += length3;
                }
            }
        } else {
            byte[] doFinal2 = cipher.doFinal(bArr);
            System.arraycopy(doFinal2, 0, bArr2, 0, doFinal2.length);
        }
        return bArr2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte[] b(byte[] bArr, SecretKey secretKey, byte[] bArr2) throws XpPsoException {
        String exc;
        byte[] bArr3 = null;
        try {
            bArr3 = b(bArr, secretKey, bArr2, "CTR", "NoPadding");
            exc = null;
        } catch (InvalidAlgorithmParameterException e) {
            exc = e.toString();
        } catch (InvalidKeyException e2) {
            exc = e2.toString();
        } catch (NoSuchAlgorithmException e3) {
            exc = e3.toString();
        } catch (NoSuchPaddingException e4) {
            exc = e4.toString();
        } catch (ShortBufferException e5) {
            exc = e5.toString();
        } catch (Exception e6) {
            exc = e6.toString();
        }
        if (bArr3 != null) {
            return bArr3;
        }
        throw new XpPsoException(this.a, "Aes Ctr Encrypt Fail\r\n(" + exc + ")", -31);
    }

    protected byte[] b(byte[] bArr, SecretKey secretKey, byte[] bArr2, String str, String str2) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, ShortBufferException, BadPaddingException, IllegalBlockSizeException {
        byte[] bArr3 = new byte[bArr.length];
        System.arraycopy(bArr2, 0, bArr2, 0, bArr2.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr2);
        Cipher cipher = Cipher.getInstance("AES/" + str + "/" + str2);
        cipher.init(1, secretKey, ivParameterSpec);
        return b(cipher, bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Cipher c(String str, byte[] bArr, String str2, String str3) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, UnrecoverableKeyException, KeyStoreException {
        byte[] bArr2 = new byte[bArr.length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr2);
        Cipher cipher = Cipher.getInstance("AES/" + str2 + "/" + str3);
        cipher.init(2, (SecretKey) b.getKey(str, null), ivParameterSpec);
        return cipher;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean c(String str, byte[] bArr, String str2) throws XpPsoException {
        String exc;
        boolean z = false;
        try {
            boolean b2 = b(str, bArr, "CBC", "NoPadding");
            exc = null;
            z = b2;
        } catch (UnsupportedEncodingException e) {
            exc = e.toString();
        } catch (InvalidAlgorithmParameterException e2) {
            exc = e2.toString();
        } catch (InvalidKeyException e3) {
            exc = e3.toString();
        } catch (KeyStoreException e4) {
            exc = e4.toString();
        } catch (NoSuchAlgorithmException e5) {
            exc = e5.toString();
        } catch (UnrecoverableKeyException e6) {
            exc = e6.toString();
        } catch (BadPaddingException e7) {
            exc = e7.toString();
        } catch (IllegalBlockSizeException e8) {
            exc = e8.toString();
        } catch (NoSuchPaddingException e9) {
            exc = e9.toString();
        } catch (Exception e10) {
            exc = e10.toString();
        }
        if (z) {
            return z;
        }
        Context context = this.a;
        throw new XpPsoException(context, "Aes Cbc Key Test " + str2 + "Failed\r\n(" + exc + ")", PsoCrypto.Desc.TestAesCbcKeyFailed);
    }
}
