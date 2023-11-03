package com.xiaopeng.devtools.model.c.a;

import android.os.AsyncTask;
import android.text.Html;
import android.text.TextUtils;
import com.activeandroid.query.Select;
import com.xiaopeng.devtools.bean.factorytest.TestResult;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.regex.Pattern;

/* compiled from: ShowReportModel.java */
/* loaded from: classes12.dex */
public class h implements f {
    private com.xiaopeng.devtools.presenter.factorytest.hardwaretest.g sE;

    public h(com.xiaopeng.devtools.presenter.factorytest.hardwaretest.g gVar) {
        this.sE = gVar;
    }

    @Override // com.xiaopeng.devtools.model.c.a.f
    public void fW() {
        new a(this.sE).execute(new Void[0]);
    }

    /* compiled from: ShowReportModel.java */
    /* loaded from: classes12.dex */
    static class a extends AsyncTask<Void, Void, CharSequence> {
        private WeakReference<com.xiaopeng.devtools.presenter.factorytest.hardwaretest.g> sF;

        public a(com.xiaopeng.devtools.presenter.factorytest.hardwaretest.g gVar) {
            this.sF = new WeakReference<>(gVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: b */
        public CharSequence doInBackground(Void... voidArr) {
            String str;
            String str2;
            String[] split;
            String str3;
            String str4;
            List<TestResult> execute = new Select().from(TestResult.class).execute();
            StringBuilder sb = new StringBuilder();
            for (TestResult testResult : execute) {
                sb.append("<big>");
                sb.append(testResult.getTarget());
                sb.append("\t\t");
                if (testResult.isSuccess()) {
                    str = "成功";
                    str2 = "green";
                } else {
                    str = "失败";
                    str2 = "red";
                }
                sb.append(getString(str, str2));
                sb.append("</big><br>");
                if (!TextUtils.isEmpty(testResult.getItemsResult())) {
                    for (String str5 : Pattern.compile("#").split(testResult.getItemsResult())) {
                        sb.append("<br>");
                        String[] split2 = Pattern.compile("\\({1}+").split(str5);
                        if (split2.length > 0 && !TextUtils.isEmpty(split2[0])) {
                            String[] split3 = split2[0].split(":");
                            if (split3.length > 1) {
                                sb.append("\t\t\t");
                                sb.append(split3[0]);
                                sb.append("\t\t");
                                if (split3[1].equals("true")) {
                                    str3 = "成功";
                                    str4 = "green";
                                } else {
                                    str3 = "失败";
                                    str4 = "red";
                                }
                                sb.append(getString(str3, str4));
                            }
                            if (split2.length > 1 && !TextUtils.isEmpty(split2[1])) {
                                String substring = split2[1].substring(0, split2[1].length() - 1);
                                sb.append("\t\t");
                                sb.append(substring);
                            }
                        }
                    }
                    sb.append("<br><br>");
                    sb.append("------------------------------------------------------------<br>");
                    sb.append("<br>");
                } else {
                    sb.append("<br>");
                    sb.append("------------------------------------------------------------<br>");
                    sb.append("<br>");
                }
            }
            return Html.fromHtml(sb.toString());
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* renamed from: c */
        public void onPostExecute(CharSequence charSequence) {
            if (this.sF.get() != null) {
                this.sF.get().d(charSequence);
            }
        }

        private String getString(String str, String str2) {
            return "<font color=\"" + str2 + "\">" + str + "</font>";
        }
    }
}
