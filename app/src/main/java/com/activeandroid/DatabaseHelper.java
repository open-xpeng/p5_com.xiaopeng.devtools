package com.activeandroid;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import com.activeandroid.util.IOUtils;
import com.activeandroid.util.Log;
import com.activeandroid.util.NaturalOrderComparator;
import com.activeandroid.util.SQLiteUtils;
import com.activeandroid.util.SqlParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes11.dex */
public final class DatabaseHelper extends SQLiteOpenHelper {
    public static final String MIGRATION_PATH = "migrations";
    private final String mSqlParser;

    public DatabaseHelper(Configuration configuration) {
        super(configuration.getContext(), configuration.getDatabaseName(), (SQLiteDatabase.CursorFactory) null, configuration.getDatabaseVersion());
        copyAttachedDatabase(configuration.getContext(), configuration.getDatabaseName());
        this.mSqlParser = configuration.getSqlParser();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        executePragmas(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        executePragmas(sQLiteDatabase);
        executeCreate(sQLiteDatabase);
        executeMigrations(sQLiteDatabase, -1, sQLiteDatabase.getVersion());
        executeCreateIndex(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        executePragmas(sQLiteDatabase);
        executeCreate(sQLiteDatabase);
        executeMigrations(sQLiteDatabase, i, i2);
    }

    public void copyAttachedDatabase(Context context, String str) {
        File databasePath = context.getDatabasePath(str);
        if (databasePath.exists()) {
            return;
        }
        databasePath.getParentFile().mkdirs();
        try {
            InputStream open = context.getAssets().open(str);
            FileOutputStream fileOutputStream = new FileOutputStream(databasePath);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = open.read(bArr, 0, 8192);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    open.close();
                    return;
                }
            }
        } catch (IOException e) {
            Log.e("Failed to open file", e);
        }
    }

    private void executePragmas(SQLiteDatabase sQLiteDatabase) {
        if (SQLiteUtils.FOREIGN_KEYS_SUPPORTED) {
            sQLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
            Log.i("Foreign Keys supported. Enabling foreign key features.");
        }
    }

    private void executeCreateIndex(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        try {
            for (TableInfo tableInfo : Cache.getTableInfos()) {
                for (String str : SQLiteUtils.createIndexDefinition(tableInfo)) {
                    sQLiteDatabase.execSQL(str);
                }
            }
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private void executeCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.beginTransaction();
        try {
            for (TableInfo tableInfo : Cache.getTableInfos()) {
                sQLiteDatabase.execSQL(SQLiteUtils.createTableDefinition(tableInfo));
            }
            sQLiteDatabase.setTransactionSuccessful();
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private boolean executeMigrations(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        boolean z = false;
        try {
            List<String> asList = Arrays.asList(Cache.getContext().getAssets().list(MIGRATION_PATH));
            Collections.sort(asList, new NaturalOrderComparator());
            sQLiteDatabase.beginTransaction();
            for (String str : asList) {
                try {
                    int intValue = Integer.valueOf(str.replace(".sql", "")).intValue();
                    if (intValue > i && intValue <= i2) {
                        executeSqlScript(sQLiteDatabase, str);
                        z = true;
                        Log.i(str + " executed succesfully.");
                    }
                } catch (NumberFormatException e) {
                    Log.w("Skipping invalidly named file: " + str, e);
                }
            }
            sQLiteDatabase.setTransactionSuccessful();
            sQLiteDatabase.endTransaction();
        } catch (IOException e2) {
            Log.e("Failed to execute migrations.", e2);
        }
        return z;
    }

    private void executeSqlScript(SQLiteDatabase sQLiteDatabase, String str) {
        InputStream inputStream = null;
        try {
            try {
                AssetManager assets = Cache.getContext().getAssets();
                InputStream open = assets.open("migrations/" + str);
                try {
                    if (Configuration.SQL_PARSER_DELIMITED.equalsIgnoreCase(this.mSqlParser)) {
                        executeDelimitedSqlScript(sQLiteDatabase, open);
                    } else {
                        executeLegacySqlScript(sQLiteDatabase, open);
                    }
                    IOUtils.closeQuietly(open);
                } catch (IOException e) {
                    e = e;
                    inputStream = open;
                    Log.e("Failed to execute " + str, e);
                    IOUtils.closeQuietly(inputStream);
                } catch (Throwable th) {
                    th = th;
                    inputStream = open;
                    IOUtils.closeQuietly(inputStream);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void executeDelimitedSqlScript(SQLiteDatabase sQLiteDatabase, InputStream inputStream) throws IOException {
        for (String str : SqlParser.parse(inputStream)) {
            sQLiteDatabase.execSQL(str);
        }
    }

    private void executeLegacySqlScript(SQLiteDatabase sQLiteDatabase, InputStream inputStream) throws IOException {
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(inputStream);
            try {
                bufferedReader = new BufferedReader(inputStreamReader);
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            String trim = readLine.replace(";", "").trim();
                            if (!TextUtils.isEmpty(trim)) {
                                sQLiteDatabase.execSQL(trim);
                            }
                        } else {
                            IOUtils.closeQuietly(bufferedReader);
                            IOUtils.closeQuietly(inputStreamReader);
                            return;
                        }
                    } catch (Throwable th) {
                        th = th;
                        IOUtils.closeQuietly(bufferedReader);
                        IOUtils.closeQuietly(inputStreamReader);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
            inputStreamReader = null;
        }
    }
}
