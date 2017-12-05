package com.artanimes.gerenciador_contatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        /* insert ok
        db.addCliente(new Cliente("Nome 1", "55 5555-5551", "user1@email.com"));
        db.addCliente(new Cliente("Nome 2", "55 5555-5551", "user2@email.com"));
        db.addCliente(new Cliente("Nome 3", "55 5555-5551", "user3@email.com"));
        db.addCliente(new Cliente("Nome 4", "55 5555-5551", "user4@email.com"));
        db.addCliente(new Cliente("Nome 5", "55 5555-5551", "user5@email.com"));
        Toast.makeText(MainActivity.this, "Salvo com sucesso", Toast.LENGTH_LONG).show();
        */

        /* apagar ok
        Cliente cliente = new Cliente();
        cliente.setCodigo(3);
        db.apagarCliente(cliente);
        */
        //Toast.makeText(MainActivity.this, "Apagado com sucesso", Toast.LENGTH_LONG).show();

        /* select ok
        Cliente cliente = db.selecionarCliente(2);
        Log.d("Cliente selecionado", "Codigo: "+cliente.getCodigo() + ", Nome: "+cliente.getNome() + ", Telefone: "+cliente.getTelefone() + ", Email: "+cliente.getEmail());
        */

        Cliente cliente = new Cliente();
        cliente.setCodigo(1);
        cliente.setNome("Nome editado 1");
        cliente.setTelefone("55 55555555");
        cliente.setEmail("super-user1@email.com");
        db.atualizaCliente(cliente);
        Toast.makeText(MainActivity.this, "Atualizado com sucesso", Toast.LENGTH_LONG).show();
    }
}
