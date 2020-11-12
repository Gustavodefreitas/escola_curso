package com.example.ac.controller;

import java.util.List;

import com.example.ac.dto.CursoDTO;
import com.example.ac.model.curso;
import com.example.ac.service.CursoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired 
    private CursoService cursoservice;
    


    @GetMapping
    public List<curso> getAllCursos(){
        return cursoservice.getAllCursos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<curso> getCursoByCodigo(@PathVariable int codigo) {
        curso curso = cursoservice.getCursoByCodigo(codigo);
        return ResponseEntity.ok(curso);	
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<curso> atualizar(@RequestBody CursoDTO CursoDto, @PathVariable int codigo) {
        curso curso = cursoservice.fromDTO(CursoDto);
       curso.setCodigodocurso(codigo);
        curso = cursoservice.update(curso);
        return ResponseEntity.ok(curso);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> remover(@PathVariable int codigo) {
        cursoservice.remove(codigo);
        
        return ResponseEntity.noContent().build();
    }
    

    
}
