package br.edu.ifpb.acomidadobebeservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import br.edu.ifpb.acomidadobebeservice.model.Usuario;
import br.edu.ifpb.acomidadobebeservice.repository.UsuarioRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor

public class UsuarioController {
  UsuarioRepository repository;

  @GetMapping("/usuario")
  public List<Usuario> getAllUsuarios(){
      return repository.findAll();
  }

  @GetMapping("/usuario/{id}")
  public Usuario getUsuarioById(@PathVariable Integer id){
      return repository.findById(id).get();
  }

  @PostMapping("/usuario")
  public Usuario saveUsuario(@RequestBody Usuario usuario){
      return repository.save(usuario);
  }

  @DeleteMapping("/usuario/{id}")
  public void deleteUsuario(@PathVariable Integer id){
      repository.deleteById(id);
  }

  // atualizar pelo id
  @PutMapping("/usuario/{id}")
  public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Usuario usuario) {
    return repository.findById(id)
      .map(record -> {
        record.setNome(usuario.getNome());
        record.setSobrenome(usuario.getSobrenome());
        record.setNascimento(usuario.getNascimento());
        record.setEmail(usuario.getEmail());
        record.setSenha(usuario.getSenha());
        Usuario updated = repository.save(record);
        return ResponseEntity.ok().body(updated);
    }).orElse(ResponseEntity.notFound().build());
  }

}