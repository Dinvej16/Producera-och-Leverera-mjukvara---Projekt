package se.systementor.supershoppen1.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import se.systementor.supershoppen1.shop.model.Product;
import se.systementor.supershoppen1.shop.services.ArticleService;
import se.systementor.supershoppen1.shop.services.OrderDetailsService;
import se.systementor.supershoppen1.shop.services.ProductService;
import se.systementor.supershoppen1.shop.services.SubscriberService;

@Controller
public class HomeController {
    private  ProductService productService;
    private SubscriberService subscriberService;
    private OrderDetailsService orderDetailsService;
    private ArticleService articleService;

    @Autowired
    public HomeController(ProductService productService, SubscriberService subscriberService, ArticleService articleService, OrderDetailsService orderDetailsService) {
        this.productService = productService;
        this.subscriberService = subscriberService;
        this.articleService = articleService;
        this.orderDetailsService = orderDetailsService;
    }    

    @GetMapping(path="/")
    String empty(Model model)
    {

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Object ud = auth.getPrincipal();
            if(ud instanceof UserDetails)
            {
                String user = ((UserDetails)ud).getUsername();
                var va2 = subscriberService.isSubscriber(user);
                model.addAttribute("hideSubscription", va2);
            }
            else{
                model.addAttribute("hideSubscription", false);

            }
            model.addAttribute("products", productService.getTenLatestProducts());
            model.addAttribute("articles", articleService.getArticleList());

        return "home";
        }

    @GetMapping("/product/{id}")
    String getProductById(@PathVariable("id") Integer id, Model model){
        model.addAttribute("product", productService.getById(id));
        model.addAttribute("articles", articleService.getArticleList());

        return "product-page";
    }

    @GetMapping("/article/{id}")
    String getArticlesById(Model model, @PathVariable("id") Integer id){
        model.addAttribute("article", articleService.getArticleByID(id));
        model.addAttribute("articles", articleService.getArticleList());


        return "articles";
    }
    @GetMapping("/orders")
    String getOrders(Model model){
        model.addAttribute("orders", orderDetailsService.getAllOrderDetails());
        return "orders";
    }

    @GetMapping("/orders/{id}")
    String getOrdersFromProduct(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("products", orderDetailsService.getProductsById(id));
        model.addAttribute("price", orderDetailsService.getFullPrice(id));
        return "order-product-page";
    }

    @GetMapping("/account")
    String getAccount(){
        return "myAccount";
    }

    @GetMapping(path="/test2")
    List<Product> getAll(){
        return productService.getAll();
    }


}
