package se.systementor.supershoppen1.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import se.systementor.supershoppen1.shop.model.Category;
import se.systementor.supershoppen1.shop.model.Newsletter;
import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.services.CategoryService;
import se.systementor.supershoppen1.shop.services.NewsletterService;
import se.systementor.supershoppen1.shop.services.ProductService;

@Controller
public class AdminController {
    private  ProductService productService;
    private CategoryService categoryService;
    private NewsletterService newsletterService;
    @Autowired
    public AdminController(ProductService productService, CategoryService categoryService, NewsletterService newsletterService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.newsletterService = newsletterService;
    }    

    @GetMapping(path="/admin/products")
    String empty(Model model)
    {
        model.addAttribute("products", productService.getAll());
        return "admin/products";
    }

    @GetMapping(path="/admin/categories")
    String showCategories (Model model)
    {
        model.addAttribute("categories", categoryService.getAll());
        return "admin/categories";
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

    @GetMapping("admin/newsletters")
    String newsletter(Model model){
        model.addAttribute("news", newsletterService.getNewsletter());
        return "newsletters";
    }

    @GetMapping("admin/register")
    String showNewsLetterForm(Model model){
        Newsletter newsletter = new Newsletter();
        model.addAttribute("newsletter", newsletter);
        return "newsletter_form";
    }

    @PostMapping("admin/register")
    public String submitForm(@ModelAttribute("newsletter") Newsletter newsletter) {
        newsletterService.createNewsletter(newsletter);
        System.out.println(newsletter.getName());
        return "redirect:/admin/newsletters";
    }


    @GetMapping (path= "/admin/category/new")
    String showNewCategoryForm (Model model)
    {
        model.addAttribute("category", new Category());
        return "admin/new_category_form";
    }
}
