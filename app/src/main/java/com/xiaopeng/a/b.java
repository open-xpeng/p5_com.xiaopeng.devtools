package com.xiaopeng.a;

import android.content.Context;
import android.os.SystemProperties;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/* compiled from: XMLDataStorage.java */
/* loaded from: classes11.dex */
public class b {
    private static b Zz = null;
    private DocumentBuilder ZA;
    private Document ZB;
    private XPath ZC;
    private boolean ZD = false;
    private ConcurrentHashMap<String, String> ZE = new ConcurrentHashMap<>();
    private Context mContext;

    public static synchronized b qi() {
        b bVar;
        synchronized (b.class) {
            if (Zz == null) {
                Zz = new b();
            }
            bVar = Zz;
        }
        return bVar;
    }

    private b() {
        try {
            DocumentBuilderFactory newInstance = DocumentBuilderFactory.newInstance();
            newInstance.setIgnoringComments(true);
            newInstance.setIgnoringElementContentWhitespace(true);
            this.ZA = newInstance.newDocumentBuilder();
            this.ZC = XPathFactory.newInstance().newXPath();
        } catch (ParserConfigurationException e) {
            c.d(e);
        }
    }

    public boolean aw(Context context) {
        this.mContext = context;
        String packageName = context.getPackageName();
        if (packageName.equals("com.xiaopeng.factory")) {
            c.TAG = "FactoryTest";
        } else if (packageName.equals("com.xiaopeng.devtools")) {
            c.TAG = "DevTools";
        } else if (packageName.equals("com.xiaopeng.cardiagnosis")) {
            c.TAG = "CarDiagnosis";
        } else {
            c.v("XMLDataStorage", "parsingXML", "UnKnown PackageName");
            return false;
        }
        synchronized (b.class) {
            if (!qi().qj()) {
                String str = SystemProperties.get("ro.xpeng.xml.model", "unknown");
                String str2 = str.trim().replace(" ", "").toLowerCase(Locale.ENGLISH) + "_xiaopeng_xmlconfig.xml";
                c.t("XMLDataStorage", "parseXML", "modelXML=" + str2);
                if (!qi().l(context, str2)) {
                    c.u("XMLDataStorage", "parseXML", "model file not found : " + str2);
                    qi().l(context, "base_xiaopeng_xmlconfig.xml");
                    c.u("XMLDataStorage", "parseXML", "Default file loaded => base_xiaopeng_xmlconfig.xml");
                }
                c.v("XMLDataStorage", "parsingXML", "data parsing completed.");
            } else {
                c.v("XMLDataStorage", "parsingXML", "data parsing was completed.");
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00ba A[Catch: IOException -> 0x00b6, TRY_LEAVE, TryCatch #3 {IOException -> 0x00b6, blocks: (B:37:0x00b2, B:41:0x00ba), top: B:45:0x00b2 }] */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b2 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean l(android.content.Context r6, java.lang.String r7) {
        /*
            r5 = this;
            java.lang.String r6 = "/system/etc/"
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8f
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8f
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8f
            r3.<init>()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8f
            r3.append(r6)     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8f
            r3.append(r7)     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8f
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8f
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8f
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L8b java.lang.Exception -> L8f
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            r4.<init>()     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            r4.append(r6)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            java.lang.String r6 = "base_xiaopeng_xmlconfig.xml"
            r4.append(r6)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            r3.<init>(r6)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L84 java.lang.Exception -> L87
            java.lang.String r6 = "XMLDataStorage"
            java.lang.String r0 = "parseXmlFile"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            r3.<init>()     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            java.lang.String r4 = "parseXmlFile Is Started xml :"
            r3.append(r4)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            r3.append(r7)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            java.lang.String r7 = r3.toString()     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            com.xiaopeng.a.c.t(r6, r0, r7)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            javax.xml.parsers.DocumentBuilder r6 = r5.ZA     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            org.w3c.dom.Document r6 = r6.parse(r1)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            java.lang.String r7 = "XMLDataStorage"
            java.lang.String r0 = "parseXmlFile"
            java.lang.String r3 = "Decode base file: base_xiaopeng_xmlconfig.xml"
            com.xiaopeng.a.c.t(r7, r0, r3)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            javax.xml.parsers.DocumentBuilder r7 = r5.ZA     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            org.w3c.dom.Document r7 = r7.parse(r2)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            org.w3c.dom.Document r6 = r5.a(r7, r6)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            r5.ZB = r6     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            java.lang.String r6 = "XMLDataStorage"
            java.lang.String r7 = "parseXmlFile"
            java.lang.String r0 = "parseXmlFile Is Completed"
            com.xiaopeng.a.c.t(r6, r7, r0)     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            r6 = 1
            r5.ZD = r6     // Catch: java.lang.Throwable -> L80 java.lang.Exception -> L82
            r1.close()     // Catch: java.io.IOException -> L9d
            r2.close()     // Catch: java.io.IOException -> L9d
            goto Laa
        L80:
            r6 = move-exception
            goto Lb0
        L82:
            r6 = move-exception
            goto L89
        L84:
            r6 = move-exception
            r2 = r0
            goto Lb0
        L87:
            r6 = move-exception
            r2 = r0
        L89:
            r0 = r1
            goto L91
        L8b:
            r6 = move-exception
            r1 = r0
            r2 = r1
            goto Lb0
        L8f:
            r6 = move-exception
            r2 = r0
        L91:
            r7 = 0
            r5.ZD = r7     // Catch: java.lang.Throwable -> Lae
            com.xiaopeng.a.c.d(r6)     // Catch: java.lang.Throwable -> Lae
            if (r0 == 0) goto L9f
            r0.close()     // Catch: java.io.IOException -> L9d
            goto L9f
        L9d:
            r6 = move-exception
            goto La5
        L9f:
            if (r2 == 0) goto Laa
            r2.close()     // Catch: java.io.IOException -> L9d
            goto Laa
        La5:
            com.xiaopeng.a.c.d(r6)
            goto Lab
        Laa:
        Lab:
            boolean r6 = r5.ZD
            return r6
        Lae:
            r6 = move-exception
            r1 = r0
        Lb0:
            if (r1 == 0) goto Lb8
            r1.close()     // Catch: java.io.IOException -> Lb6
            goto Lb8
        Lb6:
            r7 = move-exception
            goto Lbe
        Lb8:
            if (r2 == 0) goto Lc3
            r2.close()     // Catch: java.io.IOException -> Lb6
            goto Lc3
        Lbe:
            com.xiaopeng.a.c.d(r7)
        Lc3:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaopeng.a.b.l(android.content.Context, java.lang.String):boolean");
    }

    private Document a(Document document, Document document2) {
        b(document, document2);
        return document;
    }

    public boolean qj() {
        return this.ZD;
    }

    public String ah(String str, String str2) {
        String str3 = str + "$" + str2;
        String str4 = this.ZE.get(str3);
        if (str4 == null) {
            String attribute = this.ZB.getElementById(str).getAttribute(str2);
            this.ZE.putIfAbsent(str3, attribute);
            return attribute;
        }
        return str4;
    }

    public String[] a(Element element) {
        if (element.hasAttributes()) {
            NamedNodeMap attributes = element.getAttributes();
            String[] strArr = new String[attributes.getLength()];
            for (int i = 0; i < attributes.getLength(); i++) {
                strArr[i] = attributes.item(i).getNodeName();
            }
            return strArr;
        }
        return null;
    }

    private void b(Document document, Document document2) {
        a(document, document2.getDocumentElement());
    }

    private void a(Document document, Element element) {
        String attribute;
        if (element.hasAttributes() && (attribute = element.getAttribute("id")) != null && !attribute.isEmpty()) {
            c.t("XMLDataStorage", "redefinitionById", "id=" + attribute);
            String[] a = a(element);
            if (a != null) {
                for (String str : a) {
                    if (!str.equals("id")) {
                        Element elementById = document.getElementById(attribute);
                        if (elementById != null) {
                            elementById.setAttribute(str, element.getAttribute(str));
                        } else {
                            c.t("XMLDataStorage", "redefinitionById", "Element \"" + attribute + "\" does not exist in base document.");
                        }
                    }
                }
            }
        }
        if (element.hasChildNodes()) {
            NodeList childNodes = element.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                if (childNodes.item(i).getNodeType() == 1) {
                    a(document, (Element) childNodes.item(i));
                }
            }
        }
    }
}
