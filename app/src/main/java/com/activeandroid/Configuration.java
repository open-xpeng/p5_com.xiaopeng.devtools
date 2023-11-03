package com.activeandroid;

import android.content.Context;
import com.activeandroid.serializer.TypeSerializer;
import com.activeandroid.util.Log;
import com.activeandroid.util.ReflectionUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes11.dex */
public class Configuration {
    public static final String SQL_PARSER_DELIMITED = "delimited";
    public static final String SQL_PARSER_LEGACY = "legacy";
    private int mCacheSize;
    private Context mContext;
    private String mDatabaseName;
    private int mDatabaseVersion;
    private List<Class<? extends Model>> mModelClasses;
    private String mSqlParser;
    private List<Class<? extends TypeSerializer>> mTypeSerializers;

    private Configuration(Context context) {
        this.mContext = context;
    }

    public Context getContext() {
        return this.mContext;
    }

    public String getDatabaseName() {
        return this.mDatabaseName;
    }

    public int getDatabaseVersion() {
        return this.mDatabaseVersion;
    }

    public String getSqlParser() {
        return this.mSqlParser;
    }

    public List<Class<? extends Model>> getModelClasses() {
        return this.mModelClasses;
    }

    public List<Class<? extends TypeSerializer>> getTypeSerializers() {
        return this.mTypeSerializers;
    }

    public int getCacheSize() {
        return this.mCacheSize;
    }

    public boolean isValid() {
        return this.mModelClasses != null && this.mModelClasses.size() > 0;
    }

    /* loaded from: classes11.dex */
    public static class Builder {
        private static final String AA_DB_NAME = "AA_DB_NAME";
        private static final String AA_DB_VERSION = "AA_DB_VERSION";
        private static final String AA_MODELS = "AA_MODELS";
        private static final String AA_SERIALIZERS = "AA_SERIALIZERS";
        private static final String AA_SQL_PARSER = "AA_SQL_PARSER";
        private static final int DEFAULT_CACHE_SIZE = 1024;
        private static final String DEFAULT_DB_NAME = "Application.db";
        private static final String DEFAULT_SQL_PARSER = "legacy";
        private Integer mCacheSize = 1024;
        private Context mContext;
        private String mDatabaseName;
        private Integer mDatabaseVersion;
        private List<Class<? extends Model>> mModelClasses;
        private String mSqlParser;
        private List<Class<? extends TypeSerializer>> mTypeSerializers;

        public Builder(Context context) {
            this.mContext = context.getApplicationContext();
        }

        public Builder setCacheSize(int i) {
            this.mCacheSize = Integer.valueOf(i);
            return this;
        }

        public Builder setDatabaseName(String str) {
            this.mDatabaseName = str;
            return this;
        }

        public Builder setDatabaseVersion(int i) {
            this.mDatabaseVersion = Integer.valueOf(i);
            return this;
        }

        public Builder setSqlParser(String str) {
            this.mSqlParser = str;
            return this;
        }

        public Builder addModelClass(Class<? extends Model> cls) {
            if (this.mModelClasses == null) {
                this.mModelClasses = new ArrayList();
            }
            this.mModelClasses.add(cls);
            return this;
        }

        public Builder addModelClasses(Class<? extends Model>... clsArr) {
            if (this.mModelClasses == null) {
                this.mModelClasses = new ArrayList();
            }
            this.mModelClasses.addAll(Arrays.asList(clsArr));
            return this;
        }

        public Builder setModelClasses(Class<? extends Model>... clsArr) {
            this.mModelClasses = Arrays.asList(clsArr);
            return this;
        }

        public Builder addTypeSerializer(Class<? extends TypeSerializer> cls) {
            if (this.mTypeSerializers == null) {
                this.mTypeSerializers = new ArrayList();
            }
            this.mTypeSerializers.add(cls);
            return this;
        }

        public Builder addTypeSerializers(Class<? extends TypeSerializer>... clsArr) {
            if (this.mTypeSerializers == null) {
                this.mTypeSerializers = new ArrayList();
            }
            this.mTypeSerializers.addAll(Arrays.asList(clsArr));
            return this;
        }

        public Builder setTypeSerializers(Class<? extends TypeSerializer>... clsArr) {
            this.mTypeSerializers = Arrays.asList(clsArr);
            return this;
        }

        public Configuration create() {
            Configuration configuration = new Configuration(this.mContext);
            configuration.mCacheSize = this.mCacheSize.intValue();
            if (this.mDatabaseName != null) {
                configuration.mDatabaseName = this.mDatabaseName;
            } else {
                configuration.mDatabaseName = getMetaDataDatabaseNameOrDefault();
            }
            if (this.mDatabaseVersion != null) {
                configuration.mDatabaseVersion = this.mDatabaseVersion.intValue();
            } else {
                configuration.mDatabaseVersion = getMetaDataDatabaseVersionOrDefault();
            }
            if (this.mSqlParser != null) {
                configuration.mSqlParser = this.mSqlParser;
            } else {
                configuration.mSqlParser = getMetaDataSqlParserOrDefault();
            }
            if (this.mModelClasses != null) {
                configuration.mModelClasses = this.mModelClasses;
            } else {
                String str = (String) ReflectionUtils.getMetaData(this.mContext, AA_MODELS);
                if (str != null) {
                    configuration.mModelClasses = loadModelList(str.split(","));
                }
            }
            if (this.mTypeSerializers != null) {
                configuration.mTypeSerializers = this.mTypeSerializers;
            } else {
                String str2 = (String) ReflectionUtils.getMetaData(this.mContext, AA_SERIALIZERS);
                if (str2 != null) {
                    configuration.mTypeSerializers = loadSerializerList(str2.split(","));
                }
            }
            return configuration;
        }

        private String getMetaDataDatabaseNameOrDefault() {
            String str = (String) ReflectionUtils.getMetaData(this.mContext, AA_DB_NAME);
            if (str == null) {
                return DEFAULT_DB_NAME;
            }
            return str;
        }

        private int getMetaDataDatabaseVersionOrDefault() {
            Integer num = (Integer) ReflectionUtils.getMetaData(this.mContext, AA_DB_VERSION);
            return ((num == null || num.intValue() == 0) ? 1 : 1).intValue();
        }

        private String getMetaDataSqlParserOrDefault() {
            String str = (String) ReflectionUtils.getMetaData(this.mContext, AA_SQL_PARSER);
            if (str == null) {
                return "legacy";
            }
            return str;
        }

        private List<Class<? extends Model>> loadModelList(String[] strArr) {
            ArrayList arrayList = new ArrayList();
            ClassLoader classLoader = this.mContext.getClass().getClassLoader();
            for (String str : strArr) {
                try {
                    Class<?> cls = Class.forName(str.trim(), false, classLoader);
                    if (ReflectionUtils.isModel(cls)) {
                        arrayList.add(cls);
                    }
                } catch (ClassNotFoundException e) {
                    Log.e("Couldn't create class.", e);
                }
            }
            return arrayList;
        }

        private List<Class<? extends TypeSerializer>> loadSerializerList(String[] strArr) {
            ArrayList arrayList = new ArrayList();
            ClassLoader classLoader = this.mContext.getClass().getClassLoader();
            for (String str : strArr) {
                try {
                    Class<?> cls = Class.forName(str.trim(), false, classLoader);
                    if (ReflectionUtils.isTypeSerializer(cls)) {
                        arrayList.add(cls);
                    }
                } catch (ClassNotFoundException e) {
                    Log.e("Couldn't create class.", e);
                }
            }
            return arrayList;
        }
    }
}
