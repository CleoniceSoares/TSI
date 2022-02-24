package br.edu.ifpb.acomidadobebeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.acomidadobebeservice.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
}
