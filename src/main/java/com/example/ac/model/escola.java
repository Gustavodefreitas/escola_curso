package com.example.ac.model;

import java.util.ArrayList;

public class escola {

    private String escola;
    private String endereco;
    private int codigo;
    private int quantcurso;
    private ArrayList<curso> cursos = new ArrayList<curso>();

    public escola(){
        
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

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQuantcurso() {
        return quantcurso;
    }

    public void setQuantcurso(int quantcurso) {
        this.quantcurso = quantcurso;
    }

    public ArrayList<curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<curso> cursos) {
        this.cursos = cursos;
    }

    public boolean addCurso(curso curso)
    {
        return this.cursos.add(curso);
    }

    public boolean removeCurso(curso curso)
    {
        return this.cursos.remove(curso);
    }

    @Override
    public String toString() {
        return "escola [codigo=" + codigo + ", cursos=" + cursos + ", endereco=" + endereco + ", escola=" + escola
                + ", quantcurso=" + quantcurso + "]";
    }

    


}
