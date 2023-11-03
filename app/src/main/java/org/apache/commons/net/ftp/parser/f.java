package org.apache.commons.net.ftp.parser;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/* compiled from: FTPTimestampParserImpl.java */
/* loaded from: classes13.dex */
public class f implements org.apache.commons.net.ftp.a, e {
    private static final int[] atb = {14, 13, 12, 11, 5, 2, 1};
    private boolean ark = false;
    private SimpleDateFormat asX;
    private int asY;
    private SimpleDateFormat asZ;
    private int ata;

    private static int a(SimpleDateFormat simpleDateFormat) {
        char[] charArray;
        if (simpleDateFormat == null) {
            return 0;
        }
        String pattern = simpleDateFormat.toPattern();
        for (char c : "SsmHdM".toCharArray()) {
            if (pattern.indexOf(c) != -1) {
                if (c == 'H') {
                    return indexOf(11);
                }
                if (c == 'M') {
                    return indexOf(2);
                }
                if (c == 'S') {
                    return indexOf(14);
                }
                if (c == 'd') {
                    return indexOf(5);
                }
                if (c == 'm') {
                    return indexOf(12);
                }
                if (c == 's') {
                    return indexOf(13);
                }
            }
        }
        return 0;
    }

    private static int indexOf(int i) {
        for (int i2 = 0; i2 < atb.length; i2++) {
            if (i == atb[i2]) {
                return i2;
            }
        }
        return 0;
    }

    private static void a(int i, Calendar calendar) {
        if (i <= 0) {
            return;
        }
        int i2 = atb[i - 1];
        if (calendar.get(i2) == 0) {
            calendar.clear(i2);
        }
    }

    public f() {
        a("MMM d yyyy", (DateFormatSymbols) null);
        b("MMM d HH:mm", null);
    }

    @Override // org.apache.commons.net.ftp.parser.e
    public Calendar fW(String str) throws ParseException {
        return a(str, Calendar.getInstance());
    }

    public Calendar a(String str, Calendar calendar) throws ParseException {
        Calendar calendar2 = (Calendar) calendar.clone();
        calendar2.setTimeZone(ve());
        if (this.asZ != null) {
            Calendar calendar3 = (Calendar) calendar.clone();
            calendar3.setTimeZone(ve());
            if (this.ark) {
                calendar3.add(5, 1);
            }
            String str2 = str + " " + Integer.toString(calendar3.get(1));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.asZ.toPattern() + " yyyy", this.asZ.getDateFormatSymbols());
            simpleDateFormat.setLenient(false);
            simpleDateFormat.setTimeZone(this.asZ.getTimeZone());
            ParsePosition parsePosition = new ParsePosition(0);
            Date parse = simpleDateFormat.parse(str2, parsePosition);
            if (parse != null && parsePosition.getIndex() == str2.length()) {
                calendar2.setTime(parse);
                if (calendar2.after(calendar3)) {
                    calendar2.add(1, -1);
                }
                a(this.ata, calendar2);
                return calendar2;
            }
        }
        ParsePosition parsePosition2 = new ParsePosition(0);
        Date parse2 = this.asX.parse(str, parsePosition2);
        if (parse2 != null && parsePosition2.getIndex() == str.length()) {
            calendar2.setTime(parse2);
            a(this.asY, calendar2);
            return calendar2;
        }
        throw new ParseException("Timestamp '" + str + "' could not be parsed using a server time of " + calendar.getTime().toString(), parsePosition2.getErrorIndex());
    }

    private void a(String str, DateFormatSymbols dateFormatSymbols) {
        if (str != null) {
            if (dateFormatSymbols != null) {
                this.asX = new SimpleDateFormat(str, dateFormatSymbols);
            } else {
                this.asX = new SimpleDateFormat(str);
            }
            this.asX.setLenient(false);
        } else {
            this.asX = null;
        }
        this.asY = a(this.asX);
    }

    private void b(String str, DateFormatSymbols dateFormatSymbols) {
        if (str != null) {
            if (dateFormatSymbols != null) {
                this.asZ = new SimpleDateFormat(str, dateFormatSymbols);
            } else {
                this.asZ = new SimpleDateFormat(str);
            }
            this.asZ.setLenient(false);
        } else {
            this.asZ = null;
        }
        this.ata = a(this.asZ);
    }

    public TimeZone ve() {
        return this.asX.getTimeZone();
    }

    private void fY(String str) {
        TimeZone timeZone = TimeZone.getDefault();
        if (str != null) {
            timeZone = TimeZone.getTimeZone(str);
        }
        this.asX.setTimeZone(timeZone);
        if (this.asZ != null) {
            this.asZ.setTimeZone(timeZone);
        }
    }

    @Override // org.apache.commons.net.ftp.a
    public void a(org.apache.commons.net.ftp.d dVar) {
        DateFormatSymbols fQ;
        String uX = dVar.uX();
        String uW = dVar.uW();
        if (uW != null) {
            fQ = org.apache.commons.net.ftp.d.fR(uW);
        } else if (uX != null) {
            fQ = org.apache.commons.net.ftp.d.fQ(uX);
        } else {
            fQ = org.apache.commons.net.ftp.d.fQ("en");
        }
        b(dVar.uU(), fQ);
        String uT = dVar.uT();
        if (uT == null) {
            throw new IllegalArgumentException("defaultFormatString cannot be null");
        }
        a(uT, fQ);
        fY(dVar.uV());
        this.ark = dVar.uY();
    }
}
