package br.edu.ifpb.acomidadobebeservice.model;

import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_responsavel")
public class Responsavel extends Usuario {
    @Id
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "membros")
    // nome e parentesco com a criança
    public Map<String, String> membros = new HashMap<String, String>();
    
    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL)
    private Set<Crianca> crianca = new HashSet<>();

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
