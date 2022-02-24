package br.edu.ifpb.acomidadobebeservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.edu.ifpb.acomidadobebeservice.repository.NutricionistaRepository;
import br.edu.ifpb.acomidadobebeservice.model.Nutricionista;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/nutricionista")
public class NutricionistaController {

    NutricionistaRepository repository;
    
    // listar todos
    @GetMapping("/nutricionista")
    public List<Nutricionista> getAllNutricionista(){
        return repository.findAll();

    }

    // listar pelo id
    @GetMapping("/nutricionista/{id}")
    public Nutricionista getUsuarioById(@PathVariable Integer id){
        return repository.findById(id).get();
    }

    // apagar pelo id
    @DeleteMapping("/nutricionista/{id}")
    public void deletarNutricionista(@PathVariable Integer id){
        repository.deleteById(id);
    }

    // criar
    @PostMapping("/nutricionista")
    public Nutricionista saveNutricionista(@RequestBody Nutricionista nutricionista){
        return repository.save(nutricionista);
    }

    // atualizar pelo id
    @PutMapping("/nutricionista/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Nutricionista nutricionista) {
        return repository.findById(id).map(record -> {
            record.setCrn(nutricionista.getCrn());
            record.setUsuario(nutricionista.getUsuario());
            Nutricionista updated = repository.save(record);
            return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
    }

}