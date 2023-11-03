package android.pso;

/* loaded from: classes.dex */
public interface PsoCrypto {
    public static final int D21 = 21;
    public static final int D22 = 22;
    public static final int E28 = 28;
    public static final int OTA_DEC_MOD2 = 2;

    /* loaded from: classes.dex */
    public static class Desc {
        public static final int CertKeyVerifyFailed = -128;
        public static final int CheckSumError = -5;
        public static final int DecryptExit = -33;
        public static final int DecryptFail = -3;
        public static final int DecryptKeybinFailed = -111;
        public static final int EncryptFail = -31;
        public static final int FileNotExists = -6;
        public static final int InvalidFile = -1;
        public static final int KeystoreInitialFailed = -99;
        public static final int NoCert = -2;
        public static final int NoCertKeyEncData = -12;
        public static final int NoPrivateKey = -23;
        public static final int NoPublicKey = -14;
        public static final int NoRootKey = -98;
        public static final int NoSignedData = -13;
        public static final int ParseKeybinFailed = -11;
        public static final int ReadFileFailed = -22;
        public static final int SaveAesCbcKeyFailed = -113;
        public static final int SaveAesCrtKeyFailed = -114;
        public static final int SaveCustomKeyFailed = -115;
        public static final int SaveECcertKeyFailed = -112;
        public static final int Success = 0;
        public static final int TestAesCbcKeyFailed = -123;
        public static final int TestAesCrtKeyFailed = -124;
        public static final int TestCertFailed = -122;
        public static final int TestCustomKeyFailed = -125;
        public static final int binSignVerifyFail = -4;
        public static final int createNewFileFailed = -43;
        public static final int createRamdonAesCbcKeyFailed = -72;
        public static final int der2PublicKeyFailed = -141;
        public static final int getAesKeyCipherFailed = -15;
        public static final int getAliasFailed = -7;
        public static final int getOtaSecurityAccessKeyFailed = -73;
        public static final int getPsuidFailed = -71;
        public static final int getVerifySignatureFailed = -41;
        public static final int ota_package_decryptverify_failed = -42;
        public static final int writeFileFailed = -44;
    }

    boolean CertKeyPreset(String str) throws XpPsoException;

    boolean CertKeyVerify() throws XpPsoException;

    boolean CertKeyVerify(String str) throws XpPsoException;

    String GetPSUID(String str) throws XpPsoException;

    boolean McuKeyPreset(String str) throws XpPsoException;

    byte[] ble_package_decryptverify_mtom(byte[] bArr, int i) throws XpPsoException;

    boolean checkIfPreseted() throws XpPsoException;

    byte[] e28ota_package_decryptverify_tofile(String str, String str2) throws XpPsoException;

    int get_carType();

    long get_dec_mtom_processed_len();

    long get_dec_tofile_processed_len();

    long get_dec_tomem_processed_len();

    void ota_dec_stop();

    byte[] ota_gen_mcu_key() throws XpPsoException;

    byte[] ota_mcu_decrypt(byte[] bArr) throws XpPsoException;

    byte[] ota_mcu_encrypt(byte[] bArr) throws XpPsoException;

    byte[] ota_package_decryptverify_mtom(byte[] bArr) throws XpPsoException;

    boolean ota_package_decryptverify_tofile(String str, String str2) throws XpPsoException;

    byte[] ota_package_decryptverify_tomem(String str) throws XpPsoException;

    int ota_security_access_key(int i, int i2, int i3) throws XpPsoException;

    void set_carType(int i);
}
