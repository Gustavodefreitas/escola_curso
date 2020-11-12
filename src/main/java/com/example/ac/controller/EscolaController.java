package com.example.ac.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.ac.dto.CursoDTO;
import com.example.ac.dto.EscolaDTO;
import com.example.ac.model.curso;
import com.example.ac.model.escola;
import com.example.ac.service.CursoService;
import com.example.ac.service.EscolaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;



@RestController
@RequestMapping("/escolas")
public class EscolaController {

    @Autowired
    private EscolaService escolaservice;
    @Autowired
    private CursoService cursoservice;
    
    @GetMapping()
    public List<escola> getEscolas() {
        return escolaservice.getAll();
    }
    @GetMapping("/{codigo}/cursos")
    public List<curso> getAllCursosByCodigo(@PathVariable int codigo){
        return cursoservice.getAllCursosByCodigo(codigo);
    }


    @GetMapping("/{codigo}")
    public ResponseEntity<escola> getEscolaByCodigo(@PathVariable int codigo) {
        escola escola = escolaservice.getescolaByCodigo(codigo);
        return ResponseEntity.ok(escola);
    }

    @PostMapping()
    public ResponseEntity<escola> salvar(@RequestBody EscolaDTO escolaDTO, HttpServletRequest request,
            UriComponentsBuilder builder

    ) {
        escola escola = escolaservice.fromDTO(escolaDTO);
        escola novaescola = escolaservice.save(escola);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + novaescola.getCodigo()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }


    @PostMapping("/{idCliente}/cursos")
    public ResponseEntity<curso> salvar(@PathVariable int idCliente,
                                          @RequestBody CursoDTO cursoDTO,
                                          HttpServletRequest request,
                                          UriComponentsBuilder builder

    ) {
        curso curso = cursoservice.fromDTO(cursoDTO);
        curso = cursoservice.salvar(curso, idCliente);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + curso.getCodigodocurso()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }
    

    @PutMapping("/{codigo}")
    public ResponseEntity<escola> atualizar(@RequestBody EscolaDTO escolaDto, @PathVariable int codigo) {
        escola escola = escolaservice.fromDTO(escolaDto);
        escola.setCodigo(codigo);
        escola = escolaservice.update(escola);
        return ResponseEntity.ok(escola);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable int codigo) {
        escolaservice.removeByCodigo(codigo);
        return ResponseEntity.noContent().build();
    }

    
}
