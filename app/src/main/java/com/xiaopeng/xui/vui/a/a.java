package com.xiaopeng.xui.vui.a;

import androidx.annotation.RestrictTo;
import com.xiaopeng.vui.commons.VuiElementType;
import com.xiaopeng.vui.commons.VuiFeedbackType;
import com.xiaopeng.vui.commons.VuiMode;
import com.xiaopeng.vui.commons.VuiPriority;

/* compiled from: VuiCommonUtils.java */
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes13.dex */
public class a {
    public static VuiPriority ey(int i) {
        switch (i) {
            case 0:
                return VuiPriority.LEVEL1;
            case 1:
                return VuiPriority.LEVEL2;
            case 2:
                return VuiPriority.LEVEL3;
            case 3:
                return VuiPriority.LEVEL4;
            default:
                return VuiPriority.LEVEL3;
        }
    }

    public static VuiMode ez(int i) {
        switch (i) {
            case 1:
                return VuiMode.DISABLED;
            case 2:
                return VuiMode.SILENT;
            case 3:
                return VuiMode.UNDERSTOOD;
            default:
                return VuiMode.NORMAL;
        }
    }

    public static VuiFeedbackType eA(int i) {
        switch (i) {
            case 1:
                return VuiFeedbackType.SOUND;
            case 2:
                return VuiFeedbackType.TTS;
            default:
                return VuiFeedbackType.SOUND;
        }
    }

    public static VuiElementType eB(int i) {
        switch (i) {
            case 1:
                return VuiElementType.BUTTON;
            case 2:
                return VuiElementType.LISTVIEW;
            case 3:
                return VuiElementType.CHECKBOX;
            case 4:
                return VuiElementType.RADIOBUTTON;
            case 5:
                return VuiElementType.RADIOGROUP;
            case 6:
                return VuiElementType.GROUP;
            case 7:
                return VuiElementType.EDITTEXT;
            case 8:
                return VuiElementType.PROGRESSBAR;
            case 9:
                return VuiElementType.SEEKBAR;
            case 10:
                return VuiElementType.TABHOST;
            case 11:
                return VuiElementType.SEARCHVIEW;
            case 12:
                return VuiElementType.RATINGBAR;
            case 13:
                return VuiElementType.IMAGEBUTTON;
            case 14:
                return VuiElementType.IMAGEVIEW;
            case 15:
                return VuiElementType.SCROLLVIEW;
            case 16:
                return VuiElementType.TEXTVIEW;
            case 17:
                return VuiElementType.RECYCLEVIEW;
            case 18:
                return VuiElementType.SWITCH;
            case 19:
                return VuiElementType.CUSTOM;
            case 20:
                return VuiElementType.XSLIDER;
            case 21:
                return VuiElementType.XTABLAYOUT;
            case 22:
                return VuiElementType.XGROUPHEADER;
            default:
                return VuiElementType.UNKNOWN;
        }
    }
}
