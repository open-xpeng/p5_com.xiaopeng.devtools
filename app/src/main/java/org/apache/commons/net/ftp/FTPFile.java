package org.apache.commons.net.ftp;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Calendar;

/* loaded from: classes13.dex */
public class FTPFile implements Serializable {
    private static final long serialVersionUID = 9010790363003271996L;
    private Calendar _date;
    private String _group;
    private int _hardLinkCount;
    private String _link;
    private String _name;
    private final boolean[][] _permissions;
    private String _rawListing;
    private long _size;
    private int _type;
    private String _user;

    public FTPFile() {
        this._permissions = (boolean[][]) Array.newInstance(boolean.class, 3, 3);
        this._type = 3;
        this._hardLinkCount = 0;
        this._size = -1L;
        this._user = "";
        this._group = "";
        this._date = null;
        this._name = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FTPFile(String str) {
        this._permissions = null;
        this._rawListing = str;
        this._type = 3;
        this._hardLinkCount = 0;
        this._size = -1L;
        this._user = "";
        this._group = "";
        this._date = null;
        this._name = null;
    }

    public void fT(String str) {
        this._rawListing = str;
    }

    public String va() {
        return this._rawListing;
    }

    public boolean isDirectory() {
        return this._type == 1;
    }

    public void setType(int i) {
        this._type = i;
    }

    public void setName(String str) {
        this._name = str;
    }

    public String getName() {
        return this._name;
    }

    public void setSize(long j) {
        this._size = j;
    }

    public void fz(int i) {
        this._hardLinkCount = i;
    }

    public void setGroup(String str) {
        this._group = str;
    }

    public void setUser(String str) {
        this._user = str;
    }

    public void fU(String str) {
        this._link = str;
    }

    public void a(Calendar calendar) {
        this._date = calendar;
    }

    public void b(int i, int i2, boolean z) {
        this._permissions[i][i2] = z;
    }

    public String toString() {
        return va();
    }
}
