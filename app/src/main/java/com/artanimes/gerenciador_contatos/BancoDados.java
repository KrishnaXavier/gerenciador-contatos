package com.artanimes.gerenciador_contatos;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Krishna Pessoal on 04/12/2017.
 */

public class BancoDados extends SQLiteOpenHelper {

    private static  final int VERSAO_BANCO = 1;
    private static final String BANCO_CLIENTE = "bd_clientes";

    public BancoDados(Context context) {
        super(context, BANCO_CLIENTE, null, VERSAO_BANCO);
        
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
