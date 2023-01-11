package se.systementor.supershoppen1.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public double calculateDiscount(@RequestParam("totalOrderAmount") double totalOrderAmount,
                                    @RequestParam("productCategory") String productCategory) {
        if (productCategory.equals("Beverages")) {
            if (totalOrderAmount < 1000) {
                return 0;
            } else if (totalOrderAmount >= 1000 && totalOrderAmount < 5000) {
                return 0.02;
            } else if (totalOrderAmount >= 5000 && totalOrderAmount < 15000) {
                return 0.03;
            } else {
                return 0.04;
            }
        } else {
            if (totalOrderAmount < 1000) {
                return 0;
            } else if (totalOrderAmount >= 1000 && totalOrderAmount < 5000) {
                return 0.01;
            } else if (totalOrderAmount >= 5000 && totalOrderAmount < 15000) {
                return 0.02;
            } else {
                return 0.03;
            }
        }
    }
}

