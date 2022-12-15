package com.codegym.service;

import com.codegym.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService{

private  static final Map<Integer, Product> products;

    static {
        products = new HashMap<>();
        products.put(1, new Product(1,"Iphone", "Apple Inc", 3000));
        products.put(2, new Product(2,"Samsung", "Samsung Group", 2000));
        products.put(3, new Product(3,"Nokia", "Nokia Group", 1500));
        products.put(4, new Product(4,"Xiaomi", "Xiaomi Group", 1000));
    }
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id, product);

    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public void remove(int id) {
        products.remove(id);
    }
    public List<Product> findByName(String name){
        List<Product> list = new ArrayList<>();
        for(Product p : products.values()){
            if (name.equals(p.getName())){
                list.add(p);
            }
        }
        return list;
    }
}
