package com.xiaopeng.ipc;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes12.dex */
public class IpcMessage implements Parcelable {
    public static final Parcelable.Creator<IpcMessage> CREATOR = new Parcelable.Creator<IpcMessage>() { // from class: com.xiaopeng.ipc.IpcMessage.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: aC */
        public IpcMessage createFromParcel(Parcel parcel) {
            return new IpcMessage(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: dP */
        public IpcMessage[] newArray(int i) {
            return new IpcMessage[i];
        }
    };
    int UI;
    Bundle UJ;
    String packageName;

    public IpcMessage() {
    }

    protected IpcMessage(Parcel parcel) {
        this.packageName = parcel.readString();
        this.UI = parcel.readInt();
        this.UJ = parcel.readBundle();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.packageName);
        parcel.writeInt(this.UI);
        parcel.writeBundle(this.UJ);
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public int getMsgId() {
        return this.UI;
    }

    public void dO(int i) {
        this.UI = i;
    }

    public Bundle getPayloadData() {
        return this.UJ;
    }

    public void setPayloadData(Bundle bundle) {
        this.UJ = bundle;
    }
}
