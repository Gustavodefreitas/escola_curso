package com.example.ac.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.ac.model.curso;
import com.example.ac.model.escola;

import org.springframework.stereotype.Component;

@Component
public class repositorioescola {


    private List<escola>Escolas;
    private int nextCode;


    @PostConstruct
    public void cadastrodeEscola(){
        nextCode=1;
        Escolas = new ArrayList<escola>();

    }

    public escola save(escola escola) {
        escola.setCodigo(nextCode++);
        Escolas.add(escola);
        return escola;
    }

	public void remove(escola escola) {
        Escolas.remove(escola);
    }
    public List<escola>getAll(){
        return Escolas;
    }
    public Optional<escola> getEscolaByCodigo(int codigo){
        for (escola escola : Escolas) {
            if (escola.getCodigo() == codigo) {
                return Optional.of(escola);
            }
        }    
        return Optional.empty(); //return Escolas.stream().filter(e->e.getCodigo()==codigo).findFirst();  
    }

    public escola update(escola escola)
    {

        escola aux = getEscolaByCodigo(escola.getCodigo()).get(); 

        if(aux != null){
            aux.setEndereco(escola.getEndereco());
            aux.setQuantcurso(escola.getQuantcurso());
        }
        return aux;
    }

    public Optional<List<curso>> getAllbyCodigoEscola(int codigo)
    {
        escola aux =  getEscolaByCodigo(codigo).get();
        if(aux != null)
        {
            return Optional.of(aux.getCursos());

        }
        return Optional.empty();
    }
}
