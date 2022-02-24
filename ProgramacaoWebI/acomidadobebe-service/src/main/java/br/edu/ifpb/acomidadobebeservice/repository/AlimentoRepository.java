package br.edu.ifpb.acomidadobebeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpb.acomidadobebeservice.model.Alimento;

public interface AlimentoRepository extends JpaRepository<Alimento, Integer> {
    
}
