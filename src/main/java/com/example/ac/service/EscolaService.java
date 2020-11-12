package com.example.ac.service;

import java.util.List;
import java.util.Optional;

import com.example.ac.dto.EscolaDTO;
import com.example.ac.model.escola;
import com.example.ac.repository.repositorioescola;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EscolaService {

    @Autowired
    private repositorioescola repositorio;
    
    public escola fromDTO(EscolaDTO dto){
        escola escola = new escola();
        escola.setEndereco(dto.getEndereco());
        escola.setEscola(dto.getEscola());
        escola.setDiretor(dto.getDiretor());
        escola.setTelefone(dto.getTelefone());
        return escola;
    }
    public escola save(escola escola) {
		return repositorio.save(escola);
	}

	public List<escola> getAll() {
		return repositorio.getAll();
	}

	public void removeByCodigo(int codigo) {
        if(getescolaByCodigo(codigo).getCursos().size()<=0)
        {
            repositorio.remove(getescolaByCodigo(codigo));
        }
        else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"A escola não pode ser deletada, há cursos vinculados");
        }
        
        
        
	}

	public escola update(escola escola) {
        getescolaByCodigo(escola.getCodigo());
        return repositorio.update(escola);
	}

	public escola getescolaByCodigo(int codigo) {
        Optional<escola> op = repositorio.getEscolaByCodigo(codigo);
         return op.orElseThrow( () -> 
                   new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"escola nao cadastrada"
                   )
                );
	}


}
