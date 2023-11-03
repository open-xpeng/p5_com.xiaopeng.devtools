package android.support.v4.media.subtitle;

import android.graphics.Color;
import android.pso.PsoCrypto;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@RequiresApi(28)
/* loaded from: classes7.dex */
class Cea708CCParser {
    public static final int CAPTION_EMIT_TYPE_BUFFER = 1;
    public static final int CAPTION_EMIT_TYPE_COMMAND_CLW = 4;
    public static final int CAPTION_EMIT_TYPE_COMMAND_CWX = 3;
    public static final int CAPTION_EMIT_TYPE_COMMAND_DFX = 16;
    public static final int CAPTION_EMIT_TYPE_COMMAND_DLC = 10;
    public static final int CAPTION_EMIT_TYPE_COMMAND_DLW = 8;
    public static final int CAPTION_EMIT_TYPE_COMMAND_DLY = 9;
    public static final int CAPTION_EMIT_TYPE_COMMAND_DSW = 5;
    public static final int CAPTION_EMIT_TYPE_COMMAND_HDW = 6;
    public static final int CAPTION_EMIT_TYPE_COMMAND_RST = 11;
    public static final int CAPTION_EMIT_TYPE_COMMAND_SPA = 12;
    public static final int CAPTION_EMIT_TYPE_COMMAND_SPC = 13;
    public static final int CAPTION_EMIT_TYPE_COMMAND_SPL = 14;
    public static final int CAPTION_EMIT_TYPE_COMMAND_SWA = 15;
    public static final int CAPTION_EMIT_TYPE_COMMAND_TGW = 7;
    public static final int CAPTION_EMIT_TYPE_CONTROL = 2;
    private static final boolean DEBUG = false;
    private static final String MUSIC_NOTE_CHAR = new String("♫".getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    private static final String TAG = "Cea708CCParser";
    private final StringBuilder mBuilder = new StringBuilder();
    private int mCommand = 0;
    private DisplayListener mListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public interface DisplayListener {
        void emitEvent(CaptionEvent captionEvent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Cea708CCParser(DisplayListener displayListener) {
        this.mListener = new DisplayListener() { // from class: android.support.v4.media.subtitle.Cea708CCParser.1
            @Override // android.support.v4.media.subtitle.Cea708CCParser.DisplayListener
            public void emitEvent(CaptionEvent captionEvent) {
            }
        };
        if (displayListener != null) {
            this.mListener = displayListener;
        }
    }

    private void emitCaptionEvent(CaptionEvent captionEvent) {
        emitCaptionBuffer();
        this.mListener.emitEvent(captionEvent);
    }

    private void emitCaptionBuffer() {
        if (this.mBuilder.length() > 0) {
            this.mListener.emitEvent(new CaptionEvent(1, this.mBuilder.toString()));
            this.mBuilder.setLength(0);
        }
    }

    public void parse(byte[] bArr) {
        int i = 0;
        while (i < bArr.length) {
            i = parseServiceBlockData(bArr, i);
        }
        emitCaptionBuffer();
    }

    private int parseServiceBlockData(byte[] bArr, int i) {
        this.mCommand = bArr[i] & 255;
        int i2 = i + 1;
        if (this.mCommand == 16) {
            return parseExt1(bArr, i2);
        }
        if (this.mCommand >= 0 && this.mCommand <= 31) {
            return parseC0(bArr, i2);
        }
        if (this.mCommand >= 128 && this.mCommand <= 159) {
            return parseC1(bArr, i2);
        }
        if (this.mCommand >= 32 && this.mCommand <= 127) {
            return parseG0(bArr, i2);
        }
        if (this.mCommand >= 160 && this.mCommand <= 255) {
            return parseG1(bArr, i2);
        }
        return i2;
    }

    private int parseC0(byte[] bArr, int i) {
        if (this.mCommand >= 24 && this.mCommand <= 31) {
            if (this.mCommand == 24) {
                try {
                    if (bArr[i] == 0) {
                        this.mBuilder.append((char) bArr[i + 1]);
                    } else {
                        this.mBuilder.append(new String(Arrays.copyOfRange(bArr, i, i + 2), "EUC-KR"));
                    }
                } catch (UnsupportedEncodingException e) {
                    Log.e(TAG, "P16 Code - Could not find supported encoding", e);
                }
            }
            return i + 2;
        } else if (this.mCommand >= 16 && this.mCommand <= 23) {
            return i + 1;
        } else {
            int i2 = this.mCommand;
            if (i2 != 0) {
                if (i2 == 3) {
                    emitCaptionEvent(new CaptionEvent(2, Character.valueOf((char) this.mCommand)));
                    return i;
                } else if (i2 == 8) {
                    emitCaptionEvent(new CaptionEvent(2, Character.valueOf((char) this.mCommand)));
                    return i;
                } else {
                    switch (i2) {
                        case 12:
                            emitCaptionEvent(new CaptionEvent(2, Character.valueOf((char) this.mCommand)));
                            return i;
                        case 13:
                            this.mBuilder.append('\n');
                            return i;
                        case 14:
                            emitCaptionEvent(new CaptionEvent(2, Character.valueOf((char) this.mCommand)));
                            return i;
                        default:
                            return i;
                    }
                }
            }
            return i;
        }
    }

    private int parseC1(byte[] bArr, int i) {
        int i2;
        int i3 = this.mCommand;
        switch (i3) {
            case 128:
            case Const.CODE_C1_CW1 /* 129 */:
            case Const.CODE_C1_CW2 /* 130 */:
            case Const.CODE_C1_CW3 /* 131 */:
            case Const.CODE_C1_CW4 /* 132 */:
            case Const.CODE_C1_CW5 /* 133 */:
            case Const.CODE_C1_CW6 /* 134 */:
            case 135:
                emitCaptionEvent(new CaptionEvent(3, Integer.valueOf(this.mCommand + PsoCrypto.Desc.CertKeyVerifyFailed)));
                return i;
            case 136:
                int i4 = i + 1;
                emitCaptionEvent(new CaptionEvent(4, Integer.valueOf(bArr[i] & 255)));
                return i4;
            case Const.CODE_C1_DSW /* 137 */:
                int i5 = i + 1;
                emitCaptionEvent(new CaptionEvent(5, Integer.valueOf(bArr[i] & 255)));
                return i5;
            case Const.CODE_C1_HDW /* 138 */:
                int i6 = i + 1;
                emitCaptionEvent(new CaptionEvent(6, Integer.valueOf(bArr[i] & 255)));
                return i6;
            case Const.CODE_C1_TGW /* 139 */:
                int i7 = i + 1;
                emitCaptionEvent(new CaptionEvent(7, Integer.valueOf(bArr[i] & 255)));
                return i7;
            case Const.CODE_C1_DLW /* 140 */:
                int i8 = i + 1;
                emitCaptionEvent(new CaptionEvent(8, Integer.valueOf(bArr[i] & 255)));
                return i8;
            case Const.CODE_C1_DLY /* 141 */:
                int i9 = i + 1;
                emitCaptionEvent(new CaptionEvent(9, Integer.valueOf(bArr[i] & 255)));
                return i9;
            case Const.CODE_C1_DLC /* 142 */:
                emitCaptionEvent(new CaptionEvent(10, null));
                return i;
            case 143:
                emitCaptionEvent(new CaptionEvent(11, null));
                return i;
            case Const.CODE_C1_SPA /* 144 */:
                int i10 = (bArr[i] & 240) >> 4;
                int i11 = bArr[i] & 3;
                int i12 = (bArr[i] & 12) >> 2;
                int i13 = i + 1;
                boolean z = (bArr[i13] & 128) != 0;
                i2 = i + 2;
                emitCaptionEvent(new CaptionEvent(12, new CaptionPenAttr(i11, i12, i10, bArr[i13] & 7, (bArr[i13] & 56) >> 3, (bArr[i13] & 64) != 0, z)));
                return i2;
            case Const.CODE_C1_SPC /* 145 */:
                CaptionColor captionColor = new CaptionColor((bArr[i] & 192) >> 6, (bArr[i] & 48) >> 4, (bArr[i] & 12) >> 2, bArr[i] & 3);
                int i14 = i + 1;
                CaptionColor captionColor2 = new CaptionColor((bArr[i14] & 192) >> 6, (bArr[i14] & 48) >> 4, (bArr[i14] & 12) >> 2, bArr[i14] & 3);
                int i15 = i14 + 1;
                i2 = i15 + 1;
                emitCaptionEvent(new CaptionEvent(13, new CaptionPenColor(captionColor, captionColor2, new CaptionColor(0, (bArr[i15] & 48) >> 4, (bArr[i15] & 12) >> 2, bArr[i15] & 3))));
                return i2;
            case Const.CODE_C1_SPL /* 146 */:
                int i16 = i + 2;
                emitCaptionEvent(new CaptionEvent(14, new CaptionPenLocation(bArr[i] & 15, bArr[i + 1] & 63)));
                return i16;
            default:
                switch (i3) {
                    case Const.CODE_C1_SWA /* 151 */:
                        CaptionColor captionColor3 = new CaptionColor((bArr[i] & 192) >> 6, (bArr[i] & 48) >> 4, (bArr[i] & 12) >> 2, bArr[i] & 3);
                        int i17 = i + 1;
                        int i18 = i + 2;
                        int i19 = ((bArr[i17] & 192) >> 6) | ((bArr[i18] & 128) >> 5);
                        CaptionColor captionColor4 = new CaptionColor(0, (bArr[i17] & 48) >> 4, (bArr[i17] & 12) >> 2, bArr[i17] & 3);
                        int i20 = i + 3;
                        i2 = i + 4;
                        emitCaptionEvent(new CaptionEvent(15, new CaptionWindowAttr(captionColor3, captionColor4, i19, (bArr[i18] & 64) != 0, (bArr[i18] & 48) >> 4, (bArr[i18] & 12) >> 2, bArr[i18] & 3, (bArr[i20] & 12) >> 2, (bArr[i20] & 240) >> 4, bArr[i20] & 3)));
                        return i2;
                    case 152:
                    case Const.CODE_C1_DF1 /* 153 */:
                    case Const.CODE_C1_DF2 /* 154 */:
                    case Const.CODE_C1_DF3 /* 155 */:
                    case Const.CODE_C1_DF4 /* 156 */:
                    case Const.CODE_C1_DF5 /* 157 */:
                    case Const.CODE_C1_DF6 /* 158 */:
                    case 159:
                        int i21 = i + 1;
                        int i22 = i + 3;
                        int i23 = i + 5;
                        i2 = i + 6;
                        emitCaptionEvent(new CaptionEvent(16, new CaptionWindow(this.mCommand - 152, (bArr[i] & 32) != 0, (bArr[i] & 16) != 0, (bArr[i] & 8) != 0, bArr[i] & 7, (bArr[i21] & 128) != 0, bArr[i21] & Byte.MAX_VALUE, bArr[i + 2] & 255, (bArr[i22] & 240) >> 4, bArr[i22] & 15, bArr[i + 4] & 63, bArr[i23] & 7, (bArr[i23] & 56) >> 3)));
                        return i2;
                    default:
                        return i;
                }
        }
    }

    private int parseG0(byte[] bArr, int i) {
        if (this.mCommand == 127) {
            this.mBuilder.append(MUSIC_NOTE_CHAR);
        } else {
            this.mBuilder.append((char) this.mCommand);
        }
        return i;
    }

    private int parseG1(byte[] bArr, int i) {
        this.mBuilder.append((char) this.mCommand);
        return i;
    }

    private int parseExt1(byte[] bArr, int i) {
        this.mCommand = bArr[i] & 255;
        int i2 = i + 1;
        if (this.mCommand >= 0 && this.mCommand <= 31) {
            return parseC2(bArr, i2);
        }
        if (this.mCommand >= 128 && this.mCommand <= 159) {
            return parseC3(bArr, i2);
        }
        if (this.mCommand >= 32 && this.mCommand <= 127) {
            return parseG2(bArr, i2);
        }
        if (this.mCommand >= 160 && this.mCommand <= 255) {
            return parseG3(bArr, i2);
        }
        return i2;
    }

    private int parseC2(byte[] bArr, int i) {
        if (this.mCommand < 0 || this.mCommand > 7) {
            if (this.mCommand >= 8 && this.mCommand <= 15) {
                return i + 1;
            }
            if (this.mCommand >= 16 && this.mCommand <= 23) {
                return i + 2;
            }
            if (this.mCommand >= 24 && this.mCommand <= 31) {
                return i + 3;
            }
            return i;
        }
        return i;
    }

    private int parseC3(byte[] bArr, int i) {
        if (this.mCommand >= 128 && this.mCommand <= 135) {
            return i + 4;
        }
        if (this.mCommand >= 136 && this.mCommand <= 143) {
            return i + 5;
        }
        return i;
    }

    private int parseG2(byte[] bArr, int i) {
        int i2 = this.mCommand;
        if (i2 != 48) {
            switch (i2) {
            }
        }
        return i;
    }

    private int parseG3(byte[] bArr, int i) {
        int i2 = this.mCommand;
        return i;
    }

    /* loaded from: classes7.dex */
    private static class Const {
        public static final int CODE_C0_BS = 8;
        public static final int CODE_C0_CR = 13;
        public static final int CODE_C0_ETX = 3;
        public static final int CODE_C0_EXT1 = 16;
        public static final int CODE_C0_FF = 12;
        public static final int CODE_C0_HCR = 14;
        public static final int CODE_C0_NUL = 0;
        public static final int CODE_C0_P16 = 24;
        public static final int CODE_C0_RANGE_END = 31;
        public static final int CODE_C0_RANGE_START = 0;
        public static final int CODE_C0_SKIP1_RANGE_END = 23;
        public static final int CODE_C0_SKIP1_RANGE_START = 16;
        public static final int CODE_C0_SKIP2_RANGE_END = 31;
        public static final int CODE_C0_SKIP2_RANGE_START = 24;
        public static final int CODE_C1_CLW = 136;
        public static final int CODE_C1_CW0 = 128;
        public static final int CODE_C1_CW1 = 129;
        public static final int CODE_C1_CW2 = 130;
        public static final int CODE_C1_CW3 = 131;
        public static final int CODE_C1_CW4 = 132;
        public static final int CODE_C1_CW5 = 133;
        public static final int CODE_C1_CW6 = 134;
        public static final int CODE_C1_CW7 = 135;
        public static final int CODE_C1_DF0 = 152;
        public static final int CODE_C1_DF1 = 153;
        public static final int CODE_C1_DF2 = 154;
        public static final int CODE_C1_DF3 = 155;
        public static final int CODE_C1_DF4 = 156;
        public static final int CODE_C1_DF5 = 157;
        public static final int CODE_C1_DF6 = 158;
        public static final int CODE_C1_DF7 = 159;
        public static final int CODE_C1_DLC = 142;
        public static final int CODE_C1_DLW = 140;
        public static final int CODE_C1_DLY = 141;
        public static final int CODE_C1_DSW = 137;
        public static final int CODE_C1_HDW = 138;
        public static final int CODE_C1_RANGE_END = 159;
        public static final int CODE_C1_RANGE_START = 128;
        public static final int CODE_C1_RST = 143;
        public static final int CODE_C1_SPA = 144;
        public static final int CODE_C1_SPC = 145;
        public static final int CODE_C1_SPL = 146;
        public static final int CODE_C1_SWA = 151;
        public static final int CODE_C1_TGW = 139;
        public static final int CODE_C2_RANGE_END = 31;
        public static final int CODE_C2_RANGE_START = 0;
        public static final int CODE_C2_SKIP0_RANGE_END = 7;
        public static final int CODE_C2_SKIP0_RANGE_START = 0;
        public static final int CODE_C2_SKIP1_RANGE_END = 15;
        public static final int CODE_C2_SKIP1_RANGE_START = 8;
        public static final int CODE_C2_SKIP2_RANGE_END = 23;
        public static final int CODE_C2_SKIP2_RANGE_START = 16;
        public static final int CODE_C2_SKIP3_RANGE_END = 31;
        public static final int CODE_C2_SKIP3_RANGE_START = 24;
        public static final int CODE_C3_RANGE_END = 159;
        public static final int CODE_C3_RANGE_START = 128;
        public static final int CODE_C3_SKIP4_RANGE_END = 135;
        public static final int CODE_C3_SKIP4_RANGE_START = 128;
        public static final int CODE_C3_SKIP5_RANGE_END = 143;
        public static final int CODE_C3_SKIP5_RANGE_START = 136;
        public static final int CODE_G0_MUSICNOTE = 127;
        public static final int CODE_G0_RANGE_END = 127;
        public static final int CODE_G0_RANGE_START = 32;
        public static final int CODE_G1_RANGE_END = 255;
        public static final int CODE_G1_RANGE_START = 160;
        public static final int CODE_G2_BLK = 48;
        public static final int CODE_G2_NBTSP = 33;
        public static final int CODE_G2_RANGE_END = 127;
        public static final int CODE_G2_RANGE_START = 32;
        public static final int CODE_G2_TSP = 32;
        public static final int CODE_G3_CC = 160;
        public static final int CODE_G3_RANGE_END = 255;
        public static final int CODE_G3_RANGE_START = 160;

        private Const() {
        }
    }

    /* loaded from: classes7.dex */
    public static class CaptionColor {
        public static final int OPACITY_FLASH = 1;
        public static final int OPACITY_SOLID = 0;
        public static final int OPACITY_TRANSLUCENT = 2;
        public static final int OPACITY_TRANSPARENT = 3;
        public final int blue;
        public final int green;
        public final int opacity;
        public final int red;
        private static final int[] COLOR_MAP = {0, 15, 240, 255};
        private static final int[] OPACITY_MAP = {255, 254, 128, 0};

        CaptionColor(int i, int i2, int i3, int i4) {
            this.opacity = i;
            this.red = i2;
            this.green = i3;
            this.blue = i4;
        }

        public int getArgbValue() {
            return Color.argb(OPACITY_MAP[this.opacity], COLOR_MAP[this.red], COLOR_MAP[this.green], COLOR_MAP[this.blue]);
        }
    }

    /* loaded from: classes7.dex */
    public static class CaptionEvent {
        public final Object obj;
        public final int type;

        CaptionEvent(int i, Object obj) {
            this.type = i;
            this.obj = obj;
        }
    }

    /* loaded from: classes7.dex */
    public static class CaptionPenAttr {
        public static final int OFFSET_NORMAL = 1;
        public static final int OFFSET_SUBSCRIPT = 0;
        public static final int OFFSET_SUPERSCRIPT = 2;
        public static final int PEN_SIZE_LARGE = 2;
        public static final int PEN_SIZE_SMALL = 0;
        public static final int PEN_SIZE_STANDARD = 1;
        public final int edgeType;
        public final int fontTag;
        public final boolean italic;
        public final int penOffset;
        public final int penSize;
        public final int textTag;
        public final boolean underline;

        CaptionPenAttr(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2) {
            this.penSize = i;
            this.penOffset = i2;
            this.textTag = i3;
            this.fontTag = i4;
            this.edgeType = i5;
            this.underline = z;
            this.italic = z2;
        }
    }

    /* loaded from: classes7.dex */
    public static class CaptionPenColor {
        public final CaptionColor backgroundColor;
        public final CaptionColor edgeColor;
        public final CaptionColor foregroundColor;

        CaptionPenColor(CaptionColor captionColor, CaptionColor captionColor2, CaptionColor captionColor3) {
            this.foregroundColor = captionColor;
            this.backgroundColor = captionColor2;
            this.edgeColor = captionColor3;
        }
    }

    /* loaded from: classes7.dex */
    public static class CaptionPenLocation {
        public final int column;
        public final int row;

        CaptionPenLocation(int i, int i2) {
            this.row = i;
            this.column = i2;
        }
    }

    /* loaded from: classes7.dex */
    public static class CaptionWindowAttr {
        public final CaptionColor borderColor;
        public final int borderType;
        public final int displayEffect;
        public final int effectDirection;
        public final int effectSpeed;
        public final CaptionColor fillColor;
        public final int justify;
        public final int printDirection;
        public final int scrollDirection;
        public final boolean wordWrap;

        CaptionWindowAttr(CaptionColor captionColor, CaptionColor captionColor2, int i, boolean z, int i2, int i3, int i4, int i5, int i6, int i7) {
            this.fillColor = captionColor;
            this.borderColor = captionColor2;
            this.borderType = i;
            this.wordWrap = z;
            this.printDirection = i2;
            this.scrollDirection = i3;
            this.justify = i4;
            this.effectDirection = i5;
            this.effectSpeed = i6;
            this.displayEffect = i7;
        }
    }

    /* loaded from: classes7.dex */
    public static class CaptionWindow {
        public final int anchorHorizontal;
        public final int anchorId;
        public final int anchorVertical;
        public final int columnCount;
        public final boolean columnLock;
        public final int id;
        public final int penStyle;
        public final int priority;
        public final boolean relativePositioning;
        public final int rowCount;
        public final boolean rowLock;
        public final boolean visible;
        public final int windowStyle;

        CaptionWindow(int i, boolean z, boolean z2, boolean z3, int i2, boolean z4, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            this.id = i;
            this.visible = z;
            this.rowLock = z2;
            this.columnLock = z3;
            this.priority = i2;
            this.relativePositioning = z4;
            this.anchorVertical = i3;
            this.anchorHorizontal = i4;
            this.anchorId = i5;
            this.rowCount = i6;
            this.columnCount = i7;
            this.penStyle = i8;
            this.windowStyle = i9;
        }
    }
}
