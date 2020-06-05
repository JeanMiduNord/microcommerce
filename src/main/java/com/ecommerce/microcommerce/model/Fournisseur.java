package com.ecommerce.microcommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Fournisseur {
    @Id
    @GeneratedValue
    private Integer id;
    private String nomFournisseur;
    private Integer idVille;

    public Fournisseur(Integer id, String nomFournisseur, Integer idVille) {
        this.id = id;
        this.nomFournisseur = nomFournisseur;
        this.idVille = idVille;
    }
}
