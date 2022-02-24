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


import br.edu.ifpb.acomidadobebeservice.repository.AlimentoRepository;
import br.edu.ifpb.acomidadobebeservice.model.Alimento;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/alimento")
public class AlimentoController {

  AlimentoRepository repository;
  
  // listar todos
  @GetMapping("/alimento")
  public List<Alimento> getAllAlimento(){
      return repository.findAll();

  }

  // listar pelo id
  @GetMapping("/alimento/{id}")
  public Alimento getUsuarioById(@PathVariable Integer id){
      return repository.findById(id).get();
  }

  // apagar pelo id
  @DeleteMapping("/alimento/{id}")
  public void deletarAlimento(@PathVariable Integer id){
      repository.deleteById(id);
  }

  // criar
  @PostMapping("/alimento")
  public Alimento saveAlimento(@RequestBody Alimento alimento){
      return repository.save(alimento);
  }

  // atualizar pelo id
  @PutMapping("/alimento/{id}")
  public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Alimento alimento) {
    return repository.findById(id)
      .map(record -> {
        record.setNome(alimento.getNome());
        record.setGrupoAlimentar(alimento.getGrupoAlimentar());
        Alimento updated = repository.save(record);
        return ResponseEntity.ok().body(updated);
    }).orElse(ResponseEntity.notFound().build());
  }
}