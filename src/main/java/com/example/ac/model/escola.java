package com.example.ac.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class escola {

    private String escola;
    private String endereco;
    private String telefone; 
    private String diretor; 
    private int codigo;
   
    @JsonIgnore
    private ArrayList<curso> cursos = new ArrayList<curso>();

    public escola(){
        
    }

    public boolean addCurso(curso curso)
    {
        return cursos.add(curso);
    }

    public boolean removeCurso(curso curso)
    {
        return cursos.remove(curso);
    }

	public static curso getCursoByCodigo(int idCliente) {
		return null;
	}

    public String getEscola() {
        return escola;
    }

    public void setEscola(String escola) {
        this.escola = escola;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ArrayList<curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<curso> cursos) {
        this.cursos = cursos;
    }

    @Override
    public String toString() {
        return "escola [codigo=" + codigo + ", cursos=" + cursos + ", diretor=" + diretor + ", endereco=" + endereco
                + ", escola=" + escola + ", telefone=" + telefone + "]";
    }
    



}
