package se.systementor.supershoppen1.shop.model;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {


    //ta bort crud
}
