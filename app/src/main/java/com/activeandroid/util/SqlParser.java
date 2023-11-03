package com.activeandroid.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes11.dex */
public class SqlParser {
    public static final int STATE_COMMENT = 2;
    public static final int STATE_COMMENT_BLOCK = 3;
    public static final int STATE_NONE = 0;
    public static final int STATE_STRING = 1;

    public static List<String> parse(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        ArrayList arrayList = new ArrayList();
        StringBuffer stringBuffer = new StringBuffer();
        try {
            Tokenizer tokenizer = new Tokenizer(bufferedInputStream);
            loop0: while (true) {
                boolean z = false;
                while (tokenizer.hasNext()) {
                    char next = (char) tokenizer.next();
                    if (z) {
                        if (tokenizer.skip("*/")) {
                            break;
                        }
                    } else if (z) {
                        if (isNewLine(next)) {
                            break;
                        }
                    } else if (!z && tokenizer.skip("/*")) {
                        z = true;
                    } else if (!z && tokenizer.skip("--")) {
                        z = true;
                    } else if (!z && next == ';') {
                        arrayList.add(stringBuffer.toString().trim());
                        stringBuffer.setLength(0);
                    } else {
                        if (z || next != '\'') {
                            if (z && next == '\'') {
                                z = false;
                            }
                        } else {
                            z = true;
                        }
                        if (!z || z) {
                            if (!z && isWhitespace(next)) {
                                if (stringBuffer.length() > 0 && stringBuffer.charAt(stringBuffer.length() - 1) != ' ') {
                                    stringBuffer.append(' ');
                                }
                            } else {
                                stringBuffer.append(next);
                            }
                        }
                    }
                }
            }
            IOUtils.closeQuietly(bufferedInputStream);
            if (stringBuffer.length() > 0) {
                arrayList.add(stringBuffer.toString().trim());
            }
            return arrayList;
        } catch (Throwable th) {
            IOUtils.closeQuietly(bufferedInputStream);
            throw th;
        }
    }

    private static boolean isNewLine(char c) {
        return c == '\r' || c == '\n';
    }

    private static boolean isWhitespace(char c) {
        return c == '\r' || c == '\n' || c == '\t' || c == ' ';
    }
}
