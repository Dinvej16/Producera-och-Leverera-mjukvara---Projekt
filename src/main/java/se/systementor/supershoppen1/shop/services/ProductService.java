package se.systementor.supershoppen1.shop.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.model.ProductRepository;

import static java.util.Comparator.comparing;

@Service
public class ProductService {

    private final ProductRepository repository;

    ProductService(ProductRepository rep) {
        super();
        this.repository = rep;
    }

    public List<Product> getAll(){
        var l = new ArrayList<Product>();
        for(Product r : repository.findAll())
        {
            l.add(r);
        }
        return l;
    }

    public List<Product> getTenLatestProducts(){
        List<Product> l = getAll()
                .stream()
                .sorted(comparing(Product::getId, comparing(Math::abs)).reversed())
                .limit(10)
                .collect(Collectors.toList());
        return l;
    }

    public Product get(Integer id){
        return repository.findById(id).get();
    }

    public void save(Product product1) {
        repository.save(product1);
    }
}

