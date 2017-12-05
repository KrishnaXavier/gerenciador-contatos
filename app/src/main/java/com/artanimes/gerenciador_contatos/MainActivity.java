package com.artanimes.gerenciador_contatos;

import android.app.Service;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

    InputMethodManager inputMethodManager;

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

        inputMethodManager =(InputMethodManager) this.getSystemService(Service.INPUT_METHOD_SERVICE);

        listViewClientes = (ListView) findViewById(R.id.listViewClientes);

        listarClientes();

        btnLimpar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                limpaCampos();
            }
        });

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

        btnSalvar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String codigo = editCodigo.getText().toString();
                String nome = editNome.getText().toString();
                String telefone = editTelefone.getText().toString();
                String email = editEmail.getText().toString();

                if(nome.isEmpty()){
                    editNome.setError("Este campo é obrigatório");
                }else if(codigo.isEmpty()){
                    /* insert */
                    db.addCliente(new Cliente(nome, telefone, email));
                    Toast.makeText(MainActivity.this, "Cliente Adicionado com sucesso", Toast.LENGTH_LONG).show();

                    limpaCampos();
                    listarClientes();
                    escodeTeclado();

                }else{
                    /* update */
                    db.atualizaCliente(new Cliente(Integer.parseInt(codigo), nome, telefone, email));
                    Toast.makeText(MainActivity.this, "Cliente Atualizado com sucesso", Toast.LENGTH_LONG).show();
                    limpaCampos();
                    listarClientes();
                    escodeTeclado();
                }

            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String codigo = editCodigo.getText().toString();

                if(codigo.isEmpty()){
                    Toast.makeText(MainActivity.this, "Nenhum cliente está selecionado", Toast.LENGTH_LONG).show();
                }else{
                    Cliente cliente = new Cliente();
                    cliente.setCodigo(Integer.parseInt(codigo));
                    db.apagarCliente(cliente);

                    Toast.makeText(MainActivity.this, "Cliente excluido com sucesso", Toast.LENGTH_LONG).show();

                    limpaCampos();
                    listarClientes();
                }
            }
        });

        inserteExemplo();
    }

    void escodeTeclado(){
        inputMethodManager.hideSoftInputFromWindow(editNome.getWindowToken(), 0);
    }

    void limpaCampos(){
        editCodigo.setText("");
        editNome.setText("");
        editTelefone.setText("");
        editEmail.setText("");

        editNome.requestFocus();
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

    void inserteExemplo(){
        db.addCliente(new Cliente("Luiz Pereira Ricarod", "(53) 987653527", "ririca12@gmail.com"));
        db.addCliente(new Cliente("Felipe Souza", "(53) 994633527", "gonsalves.souza@email.com"));
        db.addCliente(new Cliente("Fernado Krauz", "(53) 984533512", "lele1990@gmail.com"));
        db.addCliente(new Cliente("Leonel Fonseca", "(53) 981325632", "fonseca.9012@email.com"));;

    }

}
