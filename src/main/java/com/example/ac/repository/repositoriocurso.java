package com.example.ac.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.ac.model.curso;

import org.springframework.stereotype.Component;

@Component
public class repositoriocurso {
    
    private List<curso>cursos;
    private int nextcode;
    @PostConstruct

    public void cadastrodecurso(){
        nextcode=1;
        cursos = new ArrayList<curso>();  
    }

    public curso save(curso curso) {
        curso.setCodigodocurso(nextcode++);
        cursos.add(curso);
        return curso;
    }
    
    public void remove(curso curso) {
        cursos.remove(curso);
    }
    
    public List<curso>getAllCursos(){
        return cursos;
    }

    public Optional<curso> getCursoByCodigo(int codigo){
        for (curso curso : cursos) {
            if (curso.getCodigodocurso() == codigo) {
                return Optional.of(curso);
            }
        }    
        return Optional.empty(); //return Escolas.stream().filter(e->e.getCodigo()==codigo).findFirst();  
    }

    public curso update(curso curso)
    {

        curso aux = getCursoByCodigo(curso.getCodigodocurso()).get(); 

        if(aux != null){
            aux.setNome(curso.getNome());  
        }
        return aux;
    }

}
