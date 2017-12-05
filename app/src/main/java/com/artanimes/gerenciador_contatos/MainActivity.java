package com.artanimes.gerenciador_contatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editCodigo,editNome, editTelefone, editEmail;
    Button btnLimpar, btnSalvar, btnExcluir;
    ListView listViewClientes;

    BancoDados db = new BancoDados(this);

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

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

        listarClientes();

        listViewClientes.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String conteudo = (String) listViewClientes.getItemAtPosition(position);

                //Toast.makeText(MainActivity.this, "Selecinado: "+conteudo, Toast.LENGTH_LONG).show();

                String codigo = conteudo.substring(0, conteudo.indexOf("-"));

                Cliente cliente = db.selecionarCliente(Integer.parseInt(codigo));
                editCodigo.setText(String.valueOf(cliente.getCodigo()));
                editNome.setText(cliente.getNome());
                editTelefone.setText(cliente.getTelefone());
                editEmail.setText(cliente.getEmail());

            }
        });

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

        /* update ok
        Cliente cliente = new Cliente();
        cliente.setCodigo(1);
        cliente.setNome("Nome editado 1");
        cliente.setTelefone("55 55555555");
        cliente.setEmail("super-user1@email.com");
        db.atualizaCliente(cliente);
        Toast.makeText(MainActivity.this, "Atualizado com sucesso", Toast.LENGTH_LONG).show();
        */
    }

    public void listarClientes(){
        List<Cliente> clientes = db.listaTodosClientes();

        arrayList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);

        listViewClientes.setAdapter(adapter);

        for(Cliente c : clientes){
            Log.d("Lista", "\n ID: "+c.getCodigo() + "\nNome: "+c.getNome());
            arrayList.add(c.getCodigo() + "-" + c.getNome());
            adapter.notifyDataSetChanged();
        }
    }
}
