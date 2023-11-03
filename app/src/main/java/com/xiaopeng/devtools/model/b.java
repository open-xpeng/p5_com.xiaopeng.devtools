package com.xiaopeng.devtools.model;

import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.xiaopeng.devtools.bean.factorytest.ItemResult;
import com.xiaopeng.devtools.bean.factorytest.TestResult;
import com.xiaopeng.lib.utils.c;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ItemResultLab.java */
/* loaded from: classes12.dex */
public class b {
    private static b rn;
    private List<ItemResult> ro = new ArrayList();

    private b() {
    }

    public static b fl() {
        if (rn == null) {
            rn = new b();
        }
        return rn;
    }

    public void a(ItemResult itemResult) {
        for (int i = 0; i < this.ro.size(); i++) {
            if (this.ro.get(i).getItem().equals(itemResult.getItem())) {
                this.ro.get(i).setSucceed(itemResult.isSucceed());
                this.ro.get(i).setLog(itemResult.getLog());
                return;
            }
        }
        this.ro.add(itemResult);
    }

    public List<ItemResult> fm() {
        return this.ro;
    }

    public void fn() {
        new Delete().from(TestResult.class).execute();
    }

    public void deleteAll() {
        this.ro.clear();
    }

    public void a(String str, boolean z, String str2) {
        a(new ItemResult(str, z, str2));
    }

    public void c(String str, boolean z) {
        StringBuilder sb = new StringBuilder();
        List<ItemResult> fm = fm();
        for (int i = 0; i < fm.size(); i++) {
            sb.append(fm.get(i).getItem().trim());
            sb.append(fm.get(i).isSucceed() + "(");
            sb.append(fm.get(i).getLog() + ")");
            if (i < fm.size() - 1) {
                sb.append("#");
            }
        }
        TestResult testResult = (TestResult) new Select().from(TestResult.class).where("target = ?", str).executeSingle();
        if (testResult == null) {
            testResult = new TestResult();
            testResult.setTarget(str);
        }
        testResult.setSuccess(z);
        testResult.setItemsResult(sb.toString());
        testResult.save();
    }

    public void bo(String str) {
        RandomAccessFile randomAccessFile;
        List<TestResult> execute = new Select().from(TestResult.class).execute();
        if (execute != null && execute.size() > 0) {
            File X = com.xiaopeng.lib.utils.b.X(com.xiaopeng.lib.utils.b.oL(), str + com.xiaopeng.lib.utils.a.m(System.currentTimeMillis()));
            try {
                if (!X.exists()) {
                    X.getParentFile().mkdirs();
                    X.createNewFile();
                }
                randomAccessFile = new RandomAccessFile(X, "rwd");
                try {
                    randomAccessFile.seek(X.length());
                } catch (IOException e) {
                }
            } catch (IOException e2) {
                randomAccessFile = null;
            }
            for (TestResult testResult : execute) {
                c.f("TestResult", testResult.toString());
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.write((testResult.toString() + "\r\n\r\n").getBytes());
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            }
            try {
                randomAccessFile.close();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
    }
}
