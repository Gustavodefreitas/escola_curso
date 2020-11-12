package com.example.ac.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.ac.dto.CursoDTO;
import com.example.ac.model.curso;
import com.example.ac.model.escola;
import com.example.ac.repository.repositoriocurso;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CursoService {

    @Autowired
    private repositoriocurso repositorio;

    @Autowired 
    private EscolaService escolaService;

    public List<curso> getAllCursos(){
        return repositorio.getAllCursos();
    }
    public List<curso> getAllCursosByCodigo(int codigo){

        List<curso> Cursos = repositorio.getAllCursos();
        List<curso> Cursospararetorna= new ArrayList<curso>();
        for(curso curso : Cursos)
        {
            if(curso.getEscola().getCodigo()==codigo)
            {
                Cursospararetorna.add(curso);
            }
        }
        return Cursospararetorna;
    }


    
    public curso getCursoByCodigo(long numero) {
        Optional <curso> op = repositorio.getCursoByCodigo(numero);
		return op.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "curso nao cadastrado"));
    }

    public curso salvar(curso curso, int codigoEscola){

        //Verificar se existe o codigoEscola, se nao existir 404 NotFound
        //Se lancar 404, nao continua a execucao do salvar
        escola escola = escolaService.getescolaByCodigo(codigoEscola);    
        
        //Associar curso ao Cliente e Cliente ao curso
        curso.setEscola(escola);
        escola.addCurso(curso);

        return repositorio.save(curso);
        
    }

    public curso fromDTO(CursoDTO dto){
        curso curso = new curso();
        curso.setNome(dto.getNome());
        curso.setSemestres(dto.getSemestres());
        curso.setTurma(dto.getTurma());
        curso.setTurno(dto.getTurno());
        return curso;
    }
    public curso update(curso curso) {
        getCursoByCodigo(curso.getCodigodocurso());
        return repositorio.update(curso);
	}


    public void remove(int codigo) {
        curso curso = getCursoByCodigo(codigo);
        repositorio.remove(curso);

        List<escola> escolas = escolaService.getAll();
        for(escola escola : escolas){
            escola.getCursos().remove(curso);
        }


   }

   


    
}
