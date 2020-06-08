package com.ecommerce.microcommerce.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
//@Table(name="fournisseur")
//Annotation Lombok
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of= {"id","nomFournisseur","produit"})
@Builder(toBuilder = true)
public class Fournisseur {
    @Id
    @GeneratedValue
    private Integer id;
    private String nomFournisseur;
    @OneToMany(mappedBy = "fournisseur",cascade=CascadeType.ALL)
    private List<Produit> produit;

}
