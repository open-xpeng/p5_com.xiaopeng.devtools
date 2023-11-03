package com.alibaba.mtl.appmonitor;

import android.os.Parcel;
import android.os.Parcelable;
import com.alibaba.mtl.appmonitor.model.DimensionValueSet;
import java.util.Map;

/* loaded from: classes11.dex */
public class Transaction implements Parcelable {
    public static final Parcelable.Creator<Transaction> CREATOR = new Parcelable.Creator<Transaction>() { // from class: com.alibaba.mtl.appmonitor.Transaction.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public Transaction createFromParcel(Parcel parcel) {
            return Transaction.a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: s */
        public Transaction[] newArray(int i) {
            return new Transaction[i];
        }
    };
    protected Integer a;
    protected DimensionValueSet az;
    protected Map<String, String> e;
    protected String o;
    protected String p;
    protected String r;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.az, i);
        parcel.writeInt(this.a.intValue());
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeString(this.r);
        parcel.writeMap(this.e);
    }

    static Transaction a(Parcel parcel) {
        Transaction transaction = new Transaction();
        try {
            transaction.az = (DimensionValueSet) parcel.readParcelable(Transaction.class.getClassLoader());
            transaction.a = Integer.valueOf(parcel.readInt());
            transaction.o = parcel.readString();
            transaction.p = parcel.readString();
            transaction.r = parcel.readString();
            transaction.e = parcel.readHashMap(Transaction.class.getClassLoader());
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return transaction;
    }
}
