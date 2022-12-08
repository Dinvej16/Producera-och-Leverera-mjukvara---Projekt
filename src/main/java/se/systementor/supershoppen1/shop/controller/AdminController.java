package se.systementor.supershoppen1.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.services.ProductService;

@Controller
public class AdminController {
    private  ProductService productService;
    @Autowired
    public AdminController(ProductService productService) {
        this.productService = productService;
    }    

    @GetMapping(path="/admin/products")
    String empty(Model model)
    {
        model.addAttribute("products", productService.getAll());
        return "admin/products";
    }

    //new 
    @GetMapping (path= "/admin/products/new")
    String showNewProductForm (Model model)
    {
        model.addAttribute("product", new Product());
        return "admin/new_product_form";
    }

    @PostMapping(path = "/admin/products/save")
    String saveProduct (Product product)
    {
        productService.save(product);
        return "redirect:/admin/products";
    }

  


}
