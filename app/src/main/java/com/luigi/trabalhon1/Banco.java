package com.luigi.trabalhon1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Banco extends SQLiteOpenHelper {

    private static final int VERSAO = 2;
    private static final String NOME = "HQs";

    public Banco(Context context) {
        super(context, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS hq (" +
                "    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ," +
                "    nome TEXT NOT NULL ," +
                "    autor TEXT NOT NULL ," +
                "    ano INTEGER NOT NULL ," +
                "    foto TEXT )");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}