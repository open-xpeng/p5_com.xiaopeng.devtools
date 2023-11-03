package com.amap.api.services.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: DBOperation.java */
/* loaded from: classes11.dex */
public class x {
    private static Map<Class<? extends w>, w> d = new HashMap();
    private ac eS;
    private SQLiteDatabase eT;
    private w eU;

    public static synchronized w b(Class<? extends w> cls) throws IllegalAccessException, InstantiationException {
        w wVar;
        synchronized (x.class) {
            if (d.get(cls) == null) {
                d.put(cls, cls.newInstance());
            }
            wVar = d.get(cls);
        }
        return wVar;
    }

    public x(Context context, w wVar) {
        try {
            this.eS = new ac(context.getApplicationContext(), wVar.a(), null, wVar.b(), wVar);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.eU = wVar;
    }

    public static String a(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : map.keySet()) {
            if (z) {
                sb.append(str);
                sb.append(" = '");
                sb.append(map.get(str));
                sb.append("'");
                z = false;
            } else {
                sb.append(" and ");
                sb.append(str);
                sb.append(" = '");
                sb.append(map.get(str));
                sb.append("'");
            }
        }
        return sb.toString();
    }

    public <T> void a(String str, Class<T> cls) {
        synchronized (this.eU) {
            String a = a(c(cls));
            if (TextUtils.isEmpty(a)) {
                return;
            }
            this.eT = j(false);
            if (this.eT == null) {
                return;
            }
            this.eT.delete(a, str, null);
            if (this.eT != null) {
                this.eT.close();
                this.eT = null;
            }
        }
    }

    public <T> void a(String str, Object obj, boolean z) {
        synchronized (this.eU) {
            try {
                if (obj == null) {
                    return;
                }
                y c = c(obj.getClass());
                String a = a(c);
                if (TextUtils.isEmpty(a)) {
                    return;
                }
                ContentValues a2 = a(obj, c);
                if (a2 == null) {
                    return;
                }
                this.eT = j(z);
                if (this.eT == null) {
                    return;
                }
                this.eT.update(a, a2, str, null);
                if (this.eT != null) {
                    this.eT.close();
                    this.eT = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public <T> void a(String str, Object obj) {
        a(str, obj, false);
    }

    public void c(Object obj, String str) {
        synchronized (this.eU) {
            List b = b(str, obj.getClass());
            if (b != null && b.size() != 0) {
                a(str, obj);
            }
            a((x) obj);
        }
    }

    public <T> void a(T t) {
        a((x) t, false);
    }

    public <T> void a(T t, boolean z) {
        synchronized (this.eU) {
            this.eT = j(z);
            if (this.eT == null) {
                return;
            }
            a(this.eT, (SQLiteDatabase) t);
            if (this.eT != null) {
                this.eT.close();
                this.eT = null;
            }
        }
    }

    private <T> void a(SQLiteDatabase sQLiteDatabase, T t) {
        ContentValues a;
        y c = c(t.getClass());
        String a2 = a(c);
        if (TextUtils.isEmpty(a2) || t == null || sQLiteDatabase == null || (a = a(t, c)) == null) {
            return;
        }
        sQLiteDatabase.insert(a2, null, a);
    }

    public <T> List<T> a(String str, Class<T> cls, boolean z) {
        Cursor cursor;
        synchronized (this.eU) {
            ArrayList arrayList = new ArrayList();
            y c = c(cls);
            String a = a(c);
            if (this.eT == null) {
                this.eT = i(z);
            }
            if (this.eT == null || TextUtils.isEmpty(a) || str == null) {
                return arrayList;
            }
            try {
                cursor = this.eT.query(a, null, str, null, null, null, null);
            } catch (Throwable th) {
                th = th;
                cursor = null;
            }
            try {
            } catch (Throwable th2) {
                th = th2;
                if (!z) {
                    o.a(th, "DataBase", "searchListData");
                }
                if (cursor != null) {
                    cursor.close();
                }
                if (this.eT != null) {
                    this.eT.close();
                    this.eT = null;
                }
                return arrayList;
            }
            if (cursor == null) {
                this.eT.close();
                this.eT = null;
                if (cursor != null) {
                    cursor.close();
                }
                if (this.eT != null) {
                    this.eT.close();
                    this.eT = null;
                }
                return arrayList;
            }
            while (cursor.moveToNext()) {
                arrayList.add(a(cursor, cls, c));
            }
            if (cursor != null) {
                cursor.close();
            }
            if (this.eT != null) {
                this.eT.close();
                this.eT = null;
            }
            return arrayList;
        }
    }

    public <T> List<T> b(String str, Class<T> cls) {
        return a(str, (Class) cls, false);
    }

    private <T> T a(Cursor cursor, Class<T> cls, y yVar) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Field[] a = a((Class<?>) cls, yVar.b());
        Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
        declaredConstructor.setAccessible(true);
        T newInstance = declaredConstructor.newInstance(new Object[0]);
        for (Field field : a) {
            field.setAccessible(true);
            Annotation annotation = field.getAnnotation(z.class);
            if (annotation != null) {
                z zVar = (z) annotation;
                int b = zVar.b();
                int columnIndex = cursor.getColumnIndex(zVar.a());
                switch (b) {
                    case 1:
                        field.set(newInstance, Short.valueOf(cursor.getShort(columnIndex)));
                        continue;
                    case 2:
                        field.set(newInstance, Integer.valueOf(cursor.getInt(columnIndex)));
                        continue;
                    case 3:
                        field.set(newInstance, Float.valueOf(cursor.getFloat(columnIndex)));
                        continue;
                    case 4:
                        field.set(newInstance, Double.valueOf(cursor.getDouble(columnIndex)));
                        continue;
                    case 5:
                        field.set(newInstance, Long.valueOf(cursor.getLong(columnIndex)));
                        continue;
                    case 6:
                        field.set(newInstance, cursor.getString(columnIndex));
                        continue;
                    case 7:
                        field.set(newInstance, cursor.getBlob(columnIndex));
                        continue;
                }
            }
        }
        return newInstance;
    }

    private void a(Object obj, Field field, ContentValues contentValues) {
        Annotation annotation = field.getAnnotation(z.class);
        if (annotation == null) {
            return;
        }
        z zVar = (z) annotation;
        try {
            switch (zVar.b()) {
                case 1:
                    contentValues.put(zVar.a(), Short.valueOf(field.getShort(obj)));
                    break;
                case 2:
                    contentValues.put(zVar.a(), Integer.valueOf(field.getInt(obj)));
                    break;
                case 3:
                    contentValues.put(zVar.a(), Float.valueOf(field.getFloat(obj)));
                    break;
                case 4:
                    contentValues.put(zVar.a(), Double.valueOf(field.getDouble(obj)));
                    break;
                case 5:
                    contentValues.put(zVar.a(), Long.valueOf(field.getLong(obj)));
                    break;
                case 6:
                    contentValues.put(zVar.a(), (String) field.get(obj));
                    break;
                case 7:
                    contentValues.put(zVar.a(), (byte[]) field.get(obj));
                    break;
                default:
                    return;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private ContentValues a(Object obj, y yVar) {
        Field[] a;
        ContentValues contentValues = new ContentValues();
        for (Field field : a(obj.getClass(), yVar.b())) {
            field.setAccessible(true);
            a(obj, field, contentValues);
        }
        return contentValues;
    }

    private Field[] a(Class<?> cls, boolean z) {
        if (cls == null) {
            return null;
        }
        if (z) {
            return cls.getSuperclass().getDeclaredFields();
        }
        return cls.getDeclaredFields();
    }

    private SQLiteDatabase i(boolean z) {
        try {
            if (this.eT == null) {
                this.eT = this.eS.getReadableDatabase();
            }
        } catch (Throwable th) {
            if (!z) {
                o.a(th, "DBOperation", "getReadAbleDataBase");
            } else {
                th.printStackTrace();
            }
        }
        return this.eT;
    }

    private SQLiteDatabase j(boolean z) {
        try {
            if (this.eT == null || this.eT.isReadOnly()) {
                if (this.eT != null) {
                    this.eT.close();
                }
                this.eT = this.eS.getWritableDatabase();
            }
        } catch (Throwable th) {
            o.a(th, "DBOperation", "getWriteDatabase");
        }
        return this.eT;
    }

    private boolean a(Annotation annotation) {
        return annotation != null;
    }

    private <T> String a(y yVar) {
        if (yVar == null) {
            return null;
        }
        return yVar.a();
    }

    private <T> y c(Class<T> cls) {
        Annotation annotation = cls.getAnnotation(y.class);
        if (!a(annotation)) {
            return null;
        }
        return (y) annotation;
    }
}
