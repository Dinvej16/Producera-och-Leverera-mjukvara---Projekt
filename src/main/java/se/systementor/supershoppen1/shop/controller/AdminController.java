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
import se.systementor.supershoppen1.shop.services.ArticleService;
import se.systementor.supershoppen1.shop.services.CategoryService;
import se.systementor.supershoppen1.shop.services.NewsletterService;
import se.systementor.supershoppen1.shop.services.ProductService;

@Controller
public class AdminController {
    private  ProductService productService;
    private CategoryService categoryService;
    private NewsletterService newsletterService;
    private ArticleService articleService;
    @Autowired
    public AdminController(ProductService productService, CategoryService categoryService, NewsletterService newsletterService, ArticleService articleService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.newsletterService = newsletterService;
        this.articleService = articleService;
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

    @GetMapping(path = "/admin/product/edit/{id}")
    String showEditForm(@PathVariable("id")Integer id, Model model){
        Product product = productService.get(id);
        model.addAttribute("product", product);
        return "admin/edit_product_form";

    }

    @GetMapping("admin/newsletters")
    String newsletter(Model model){
        model.addAttribute("news", newsletterService.getNewsletter());
        return "admin/newsletters";
    }

    @GetMapping("/admin/register")
    String showNewsLetterForm(Model model){
        Newsletter newsletter = new Newsletter();
        model.addAttribute("newsletter", newsletter);
        return "admin/newsletter_form";
    }

    @PostMapping("admin/register")
    public String submitForm(@ModelAttribute("newsletter") Newsletter newsletter) {
        newsletterService.createNewsletter(newsletter);
        System.out.println(newsletter.getName());
        return "redirect:/admin/newsletters";
    }

    @GetMapping(path = "/admin/newsletter/show/{id}")
    String showNewsletterInfoForm(@PathVariable("id")Integer id, Model model){
        Newsletter newsletter = newsletterService.get(id);
        model.addAttribute("newsletter", newsletter);
        return "admin/newsletter_info";

    }




    @GetMapping (path= "/admin/category/new")
    String showNewCategoryForm (Model model)
    {
        model.addAttribute("category", new Category());
        return "admin/new_category_form";
    }

    @GetMapping(path = "/admin/category/edit/{id}")
    String showCategoryEditForm(@PathVariable("id")Integer id, Model model){
        Category category = categoryService.get(id);
        model.addAttribute("category", category);
        return "admin/edit_category_form";

    }

    @PostMapping(path = "/admin/category/save")
    String saveCategory (Category category)
    {
        categoryService.save(category);
        return "redirect:/admin/categories";
    }
    @GetMapping("admin/articles")
    String getArticle (Model model){
        model.addAttribute("articles", articleService.getTenLatestArticleList());
        return "admin/articles";
    }
}
