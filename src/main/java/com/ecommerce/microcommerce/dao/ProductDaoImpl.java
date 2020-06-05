package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductDaoImpl implements ProductDao {
    public static List<Product>products=new ArrayList<>();
    static {
        products.add(new Product(1, new String("Ordinateur portable"), 350, 250));
        products.add(new Product(2, new String("Aspirateur Robot"), 500, 400));
        products.add(new  Product(3, new String("Table de Ping Pong"), 750, 500));
    }
    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(Integer id) {
//        for (Product product : products) {
//            if(product.getId() ==id){
//                return product;
//            }
//        }

        List<Product>  rp =    products.stream()
                    .filter(p -> {return p.getId() == id;})
                    .collect(Collectors.toList());
        if (rp.size() > 0) {
            return rp.get(0);
        }else{
            return new Product(0,"Produit inconnu", 0, 0);
        }


//        List<Product>  rp =    products.stream()
//                    .filter(p ->  Optional.ofNullable(p)
//                                .map(Product::getId)
//                                .map(prod -> prod.getId() == id)
//                                .orElse( new Product(0,"Produit inconnu", 0);)
//                    )
//                    .collect(Collectors.toList());

    }

    @Override
    public Product save(Product product) {
        products.add(product);
        return product;
    }
}
