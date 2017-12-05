package com.artanimes.gerenciador_contatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Krishna Pessoal on 04/12/2017.
 */

public class BancoDados extends SQLiteOpenHelper {

    private static  final int VERSAO_BANCO      = 1;
    private static final String BANCO_CLIENTE   = "bd_clientes";
    private static final String TABELA_CLIENTE  = "tb_clientes";

    private static final String COLUNA_CODIGO   = "codigo";
    private static final String COLUNA_NOME     = "nome";
    private static final String COLUNA_TELEFONE = "telefone";
    private static final String COLUNA_EMAIL    = "email";

    public BancoDados(Context context) {
        super(context, BANCO_CLIENTE, null, VERSAO_BANCO);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String QUERY_COLUNA = "CREATE TABLE " + TABELA_CLIENTE +
                "("+
                    COLUNA_CODIGO + " INTEGER PRIMARY KEY, "+
                    COLUNA_NOME + " TEXT, "+
                    COLUNA_TELEFONE + " TEXT, "+
                    COLUNA_EMAIL + " TEXT"+
                ")";
        db.execSQL(QUERY_COLUNA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /* CRUD ABAIXO */

    void addCliente(Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_NOME, cliente.getNome());
        values.put(COLUNA_TELEFONE, cliente.getTelefone());
        values.put(COLUNA_EMAIL, cliente.getEmail());

        db.insert(TABELA_CLIENTE, null, values);

        db.close();
    }

    void apagarCliente(Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_CLIENTE, COLUNA_CODIGO + " = ?", new String[] {String.valueOf(cliente.getCodigo())});

        db.close();
    }

    Cliente selecionarCliente(int codigo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABELA_CLIENTE,
                new String[]{COLUNA_CODIGO, COLUNA_NOME, COLUNA_TELEFONE, COLUNA_EMAIL },
                COLUNA_CODIGO + " = ? ",
                new String[] {String.valueOf(codigo)},
                null,
                null,
                null,
                null
        );

        if(cursor != null){
            cursor.moveToFirst();
        }

        Cliente cliente1 = new Cliente(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
        );

        return  cliente1;
    }

    void atualizaCliente(Cliente cliente){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUNA_NOME, cliente.getNome());
        values.put(COLUNA_TELEFONE, cliente.getTelefone());
        values.put(COLUNA_EMAIL, cliente.getEmail());

        db.update(
                TABELA_CLIENTE,
                values,
                COLUNA_CODIGO + " = ?",
                new String[] {String.valueOf(cliente.getCodigo())}
                );

    }
}
