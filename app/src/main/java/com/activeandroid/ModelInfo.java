package com.activeandroid;

import android.content.Context;
import com.activeandroid.serializer.CalendarSerializer;
import com.activeandroid.serializer.FileSerializer;
import com.activeandroid.serializer.SqlDateSerializer;
import com.activeandroid.serializer.TypeSerializer;
import com.activeandroid.serializer.UtilDateSerializer;
import com.activeandroid.util.Log;
import com.activeandroid.util.ReflectionUtils;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public final class ModelInfo {
    private Map<Class<? extends Model>, TableInfo> mTableInfos = new HashMap();
    private Map<Class<?>, TypeSerializer> mTypeSerializers = new HashMap<Class<?>, TypeSerializer>() { // from class: com.activeandroid.ModelInfo.1
        {
            put(Calendar.class, new CalendarSerializer());
            put(Date.class, new SqlDateSerializer());
            put(java.util.Date.class, new UtilDateSerializer());
            put(File.class, new FileSerializer());
        }
    };

    public ModelInfo(Configuration configuration) {
        if (!loadModelFromMetaData(configuration)) {
            try {
                scanForModel(configuration.getContext());
            } catch (IOException e) {
                Log.e("Couldn't open source path.", e);
            }
        }
        Log.i("ModelInfo loaded.");
    }

    public Collection<TableInfo> getTableInfos() {
        return this.mTableInfos.values();
    }

    public TableInfo getTableInfo(Class<? extends Model> cls) {
        return this.mTableInfos.get(cls);
    }

    public TypeSerializer getTypeSerializer(Class<?> cls) {
        return this.mTypeSerializers.get(cls);
    }

    private boolean loadModelFromMetaData(Configuration configuration) {
        if (!configuration.isValid()) {
            return false;
        }
        List<Class<? extends Model>> modelClasses = configuration.getModelClasses();
        if (modelClasses != null) {
            for (Class<? extends Model> cls : modelClasses) {
                this.mTableInfos.put(cls, new TableInfo(cls));
            }
        }
        List<Class<? extends TypeSerializer>> typeSerializers = configuration.getTypeSerializers();
        if (typeSerializers != null) {
            for (Class<? extends TypeSerializer> cls2 : typeSerializers) {
                try {
                    TypeSerializer newInstance = cls2.newInstance();
                    this.mTypeSerializers.put(newInstance.getDeserializedType(), newInstance);
                } catch (IllegalAccessException e) {
                    Log.e("IllegalAccessException", e);
                } catch (InstantiationException e2) {
                    Log.e("Couldn't instantiate TypeSerializer.", e2);
                }
            }
            return true;
        }
        return true;
    }

    private void scanForModel(Context context) throws IOException {
        String packageName = context.getPackageName();
        String str = context.getApplicationInfo().sourceDir;
        ArrayList<String> arrayList = new ArrayList();
        if (str != null && !new File(str).isDirectory()) {
            Enumeration<String> entries = new DexFile(str).entries();
            while (entries.hasMoreElements()) {
                arrayList.add(entries.nextElement());
            }
        } else {
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources("");
            while (resources.hasMoreElements()) {
                String file = resources.nextElement().getFile();
                if (file.contains("bin") || file.contains("classes")) {
                    arrayList.add(file);
                }
            }
        }
        for (String str2 : arrayList) {
            scanForModelClasses(new File(str2), packageName, context.getClassLoader());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0080 -> B:31:0x0094). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0087 -> B:31:0x0094). Please submit an issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x008e -> B:31:0x0094). Please submit an issue!!! */
    private void scanForModelClasses(File file, String str, ClassLoader classLoader) {
        String replace;
        int lastIndexOf;
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                scanForModelClasses(file2, str, classLoader);
            }
            return;
        }
        String name = file.getName();
        if (!file.getPath().equals(name)) {
            String path = file.getPath();
            if (!path.endsWith(".class") || (lastIndexOf = (replace = path.substring(0, path.length() - 6).replace(System.getProperty("file.separator"), ".")).lastIndexOf(str)) < 0) {
                return;
            }
            name = replace.substring(lastIndexOf);
        }
        try {
            Class<?> cls = Class.forName(name, false, classLoader);
            if (ReflectionUtils.isModel(cls)) {
                this.mTableInfos.put(cls, new TableInfo(cls));
            } else if (ReflectionUtils.isTypeSerializer(cls)) {
                TypeSerializer typeSerializer = (TypeSerializer) cls.newInstance();
                this.mTypeSerializers.put(typeSerializer.getDeserializedType(), typeSerializer);
            }
        } catch (ClassNotFoundException e) {
            Log.e("Couldn't create class.", e);
        } catch (IllegalAccessException e2) {
            Log.e("IllegalAccessException", e2);
        } catch (InstantiationException e3) {
            Log.e("Couldn't instantiate TypeSerializer.", e3);
        }
    }
}
