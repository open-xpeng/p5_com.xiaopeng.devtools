package com.xiaopeng.devtools.presenter.oled;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes12.dex */
public class CANMsg387 extends a implements Parcelable {
    public static final Parcelable.Creator<CANMsg387> CREATOR = new Parcelable.Creator<CANMsg387>() { // from class: com.xiaopeng.devtools.presenter.oled.CANMsg387.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: aB */
        public CANMsg387 createFromParcel(Parcel parcel) {
            return new CANMsg387(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: cu */
        public CANMsg387[] newArray(int i) {
            return new CANMsg387[i];
        }
    };
    public int yF;
    public int yG;
    public int yH;
    public int yI;
    public int yJ;
    public int yK;
    public int yL;
    public int yM;
    public int yN;
    public int yO;
    public int yP;
    public int yQ;
    public int yR;
    public int yS;
    public int yT;
    public int yU;
    public int yV;
    public int yW;
    public int yX;
    public int yY;
    public int yZ;
    public int za;
    public int zb;
    public int zc;
    public int zd;
    public int ze;
    public int zf;
    public int zg;
    public int zh;

    public CANMsg387() {
        this.id = 28;
        this.yF = -1;
        this.yG = -1;
        this.yH = -1;
        this.yI = -1;
        this.yJ = -1;
        this.yK = -1;
        this.yL = -1;
        this.yM = -1;
        this.yN = -1;
        this.yO = -1;
        this.yP = -1;
        this.yQ = -1;
        this.yR = -1;
        this.yS = -1;
        this.yT = -1;
        this.yU = -1;
        this.yV = -1;
        this.yW = -1;
        this.yX = -1;
        this.yY = -1;
        this.yZ = -1;
        this.za = -1;
        this.zb = -1;
        this.zc = -1;
        this.zd = -1;
        this.ze = -1;
        this.zf = -1;
        this.zg = -1;
        this.zh = 1;
    }

    public CANMsg387(Parcel parcel) {
        this.id = parcel.readInt();
        this.yF = parcel.readInt();
        this.yG = parcel.readInt();
        this.yH = parcel.readInt();
        this.yI = parcel.readInt();
        this.yJ = parcel.readInt();
        this.yK = parcel.readInt();
        this.yL = parcel.readInt();
        this.yM = parcel.readInt();
        this.yN = parcel.readInt();
        this.yO = parcel.readInt();
        this.yP = parcel.readInt();
        this.yQ = parcel.readInt();
        this.yR = parcel.readInt();
        this.yS = parcel.readInt();
        this.yT = parcel.readInt();
        this.yU = parcel.readInt();
        this.yV = parcel.readInt();
        this.yW = parcel.readInt();
        this.yX = parcel.readInt();
        this.yY = parcel.readInt();
        this.yZ = parcel.readInt();
        this.za = parcel.readInt();
        this.zb = parcel.readInt();
        this.zc = parcel.readInt();
        this.zd = parcel.readInt();
        this.ze = parcel.readInt();
        this.zf = parcel.readInt();
        this.zg = parcel.readInt();
        this.zh = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeInt(this.yF);
        parcel.writeInt(this.yG);
        parcel.writeInt(this.yH);
        parcel.writeInt(this.yI);
        parcel.writeInt(this.yJ);
        parcel.writeInt(this.yK);
        parcel.writeInt(this.yL);
        parcel.writeInt(this.yM);
        parcel.writeInt(this.yN);
        parcel.writeInt(this.yO);
        parcel.writeInt(this.yP);
        parcel.writeInt(this.yQ);
        parcel.writeInt(this.yR);
        parcel.writeInt(this.yS);
        parcel.writeInt(this.yT);
        parcel.writeInt(this.yU);
        parcel.writeInt(this.yV);
        parcel.writeInt(this.yW);
        parcel.writeInt(this.yX);
        parcel.writeInt(this.yY);
        parcel.writeInt(this.yZ);
        parcel.writeInt(this.za);
        parcel.writeInt(this.zb);
        parcel.writeInt(this.zc);
        parcel.writeInt(this.zd);
        parcel.writeInt(this.ze);
        parcel.writeInt(this.zf);
        parcel.writeInt(this.zg);
        parcel.writeInt(this.zh);
    }
}
