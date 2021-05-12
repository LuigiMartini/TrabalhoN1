package com.luigi.trabalhon1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CachorroDAO {

    public static void inserir(Cachorro cachorro, Context context) {
        ContentValues valores = new ContentValues();
        valores.put("nome", cachorro.nome);
        valores.put("raca", cachorro.raca);
        valores.put("idade", cachorro.idade);

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("cachorro", null, valores);
    }

    public static List<Cachorro> getCachorros(Context context) {
        List<Cachorro> lista= new ArrayList<>();

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM cachorro ORDER BY id", null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Cachorro cachorro = new Cachorro();
                cachorro.id = cursor.getInt(0);
                cachorro.nome = cursor.getString(1);
                cachorro.raca = cursor.getString(2);
                cachorro.idade = cursor.getInt(3);

                lista.add(cachorro);

            } while ( cursor.moveToNext());
        }

        return lista;
    }

}
