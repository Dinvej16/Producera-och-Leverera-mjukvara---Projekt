package se.systementor.supershoppen1.shop.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import se.systementor.supershoppen1.shop.configuration.WebSecurityConfig;



@ExtendWith(SpringExtension.class)
@Import(WebSecurityConfig.class)

@WebMvcTest(controllers = HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mvc;


    @Test
    public void getProductById() throws Exception {

    }

    @Test
    public void getArticles() {

    }
}