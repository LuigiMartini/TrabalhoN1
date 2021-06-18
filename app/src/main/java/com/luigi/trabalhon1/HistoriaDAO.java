package com.luigi.trabalhon1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class HistoriaDAO {

    public static void inserir(Historia historia, Context context) {
        ContentValues valores = new ContentValues();
        valores.put("nome", historia.nome);
        valores.put("autor", historia.autor);
        valores.put("ano", historia.ano);

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("hq", null, valores);
    }

    public static List<Historia> getHistorias(Context context) {
        List<Historia> lista= new ArrayList<>();

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM hq ORDER BY id", null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            do {
                Historia historia = new Historia();
                historia.id = cursor.getInt(0);
                historia.nome = cursor.getString(1);
                historia.autor = cursor.getString(2);
                historia.ano = cursor.getInt(3);

                lista.add(historia);

            } while ( cursor.moveToNext());
        }

        return lista;
    }

    public static void editar(Historia historia, Context context) {
        ContentValues valores = new ContentValues();
        valores.put("nome", historia.nome);
        valores.put("ano", historia.ano);
        valores.put("autor", historia.autor);

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.update("hq", valores, " id = " + historia.id, null);
    }

    public static void excluir(int id, Context context) {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("hq", " id = " + id, null);
    }

    public static Historia getHistoriaById(Context context, int id) {
        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT id, nome, autor, ano FROM hq WHERE id = " + id, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            Historia historia = new Historia();
                historia.id = cursor.getInt(0);
                historia.nome = cursor.getString(1);
                historia.autor = cursor.getString(2);
                historia.ano = cursor.getInt(3);

                return historia;
        } else {
            return null;
        }
    }
}
