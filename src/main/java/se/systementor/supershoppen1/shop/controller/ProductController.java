package se.systementor.supershoppen1.shop.controller;

import org.springframework.stereotype.Controller;

@Controller
    public class ProductController {

        public double calculateDiscount(double totalOrderAmount, String productCategory) {
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

