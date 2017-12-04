package com.artanimes.gerenciador_contatos;

/**
 * Created by Krishna Pessoal on 04/12/2017.
 */
public class Cliente {

    int codigo;
    String nome, telefone, email;

    /* instanciar */
    public Cliente(){

    }

    /* update */
    public Cliente(int _codigo, String _nome, String _telefone, String _email){
        this.codigo     = _codigo;
        this.nome       = _nome;
        this.telefone   = _telefone;
        this.email      = _email;
    }

    /* insert */
    public Cliente(String _nome, String _telefone, String _email){
        this.nome       = _nome;
        this.telefone   = _telefone;
        this.email      = _email;
    }

    /* ######################## getes and setters ########################*/
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
