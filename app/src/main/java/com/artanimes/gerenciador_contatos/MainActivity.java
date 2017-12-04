package com.artanimes.gerenciador_contatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editCodigo,editNome, editTelefone, editEmail;
    Button btnLimpar, btnSalvar, btnExcluir;
    ListView listViewClientes;

    BancoDados db = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editCodigo = (EditText)findViewById(R.id.editCodigo);
        editNome = (EditText)findViewById(R.id.editNome);
        editTelefone = (EditText)findViewById(R.id.editTelefone);
        editEmail = (EditText)findViewById(R.id.editEmail);

        btnSalvar = (Button)findViewById(R.id.btnSalvar);
        btnExcluir = (Button)findViewById(R.id.btnExcluir);
        btnLimpar = (Button)findViewById(R.id.btnLimpar);

        listViewClientes = (ListView) findViewById(R.id.listViewClientes);

        /* TESTES DO CRUD */
        db.addCliente(new Cliente("Nome 1", "55 5555-5551", "user1@email.com"));

        Toast.makeText(MainActivity.this, "Salvo com sucesso", Toast.LENGTH_LONG).show();
    }
}
