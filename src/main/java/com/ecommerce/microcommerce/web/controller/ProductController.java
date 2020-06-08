package com.ecommerce.microcommerce.web.controller;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.dao.ProduitDao;
import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.model.Produit;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProduitDao produitDao;

    @RequestMapping(value="/Products", method = RequestMethod.GET)
    public List<Product> listeProduits(){
        return productDao.findAll();
    }

    //Récupérer un produit par son Id
    @GetMapping(value="/Products/{id}")
    public Product afficherUnProduit(@PathVariable Integer id) {
        return productDao.findById(id);
//        return null;
    }

    //ajouter un produit
    @PostMapping(value = "/Products")
    public void ajouterProduit(@RequestBody Product product) {
        productDao.save(product);
    }

    //ajouter un produit avec gestion des codes retours
    @PostMapping(value = "/Products2")
    public ResponseEntity<Void> ajouterProduit2(@RequestBody Product product) {

        Product productAdded =  productDao.save(product);

        if (productAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

//    JPA

    //Récupérer la liste des produits
    @RequestMapping(value = "/ProduitsJpa", method = RequestMethod.GET)
    public MappingJacksonValue listeProduitsJpa() {
        Iterable<Produit> produits = produitDao.findAll();

        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat");

        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("monFiltreDynamique", monFiltre);

        MappingJacksonValue produitsFiltres = new MappingJacksonValue(produits);

        produitsFiltres.setFilters(listDeNosFiltres);

        return produitsFiltres;
    }

    //Récupérer un produit par son Id
    @GetMapping(value = "/Produits/{id}")
    public Optional<Produit> afficherUnProduit(@PathVariable int id) {
        return produitDao.findById(id);
    }

    //Récupérer un produit par son prix et son nom
    @GetMapping(value = "/Produits/{prix}/{nom}")
    public  List<Produit> afficherPlusieursCritere(@PathVariable Integer prix, @PathVariable String nom) {
        List<Produit> p = produitDao.recherchePrixLimiteAndNom(prix,nom + "%");
//        System.out.println(p.get(0).toString());
        return p;
//        produitDao.recherchePrixLimiteAndNom(prix,nom + "%");
    }

    //Récupérer un produit par son prix et son nom
    @GetMapping(value = "/ProduitsFournisseur/{nomFournisseur}")
    public  List<Produit> afficherProduitParFournisseur(@PathVariable String nomFournisseur) {
        List<Produit> p = produitDao.rechercheProduitParFournisseur(nomFournisseur);
//        System.out.println(p.get(0).toString());
        return p;
    }
}
