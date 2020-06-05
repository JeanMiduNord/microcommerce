package com.ecommerce.microcommerce.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
//Annotation Lomb
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(of= {"id","nom","prix","prixAchat"})
@Builder
public class Produit {
    @Id
    @GeneratedValue
    private Integer id;
    private String nom;
    private Integer prix;
    private Integer prixAchat;
}
