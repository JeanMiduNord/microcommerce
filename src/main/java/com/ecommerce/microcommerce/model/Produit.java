package com.ecommerce.microcommerce.model;

import lombok.*;

import javax.persistence.*;

//Annotation JPA
@Entity
//@Table(name="produit")
//Annotation Lombok
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of= {"id","nom","prix","prixAchat"})
@Builder(toBuilder = true)
public class Produit {
    @Id
    @GeneratedValue
    private Integer id;
    private String nom;
    private Integer prix;
    private Integer prixAchat;
//    private Integer idFournisseur;

    @ManyToOne(fetch=FetchType.EAGER)
//    indique à JPA de ne pas appeler la colonne FOURNISSEUR_ID mais ID_FOURNISSEUR
    @JoinColumn (name="idFournisseur")
    private Fournisseur fournisseur;
}
