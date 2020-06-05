package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProduitDao extends JpaRepository<Produit,Integer> {
    public List<Produit> findAll();

    public Optional<Produit> findById(Integer id);

    public Produit save(Produit product);

    @Query("SELECT new com.ecommerce.microcommerce.model.Produit(id, nom, prix, prixAchat) FROM Produit p WHERE p.prix > :prixLimit and p.nom like :name")
    List<Produit>  recherchePrixLimiteAndNom(@Param("prixLimit") Integer prix, @Param("name") String nom);
}
