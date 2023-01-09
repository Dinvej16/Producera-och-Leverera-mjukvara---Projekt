package se.systementor.supershoppen1.shop.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import se.systementor.supershoppen1.shop.configuration.WebSecurityConfig;
import se.systementor.supershoppen1.shop.services.ShopUserDetailsService;

import javax.mail.internet.MimeMessage;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(SpringExtension.class)
@Import(WebSecurityConfig.class)
@WebMvcTest(controllers = ContactController.class)
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JavaMailSender mailSender;

    @MockBean
    private ShopUserDetailsService shopUserDetailsService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    void submitContact() throws Exception {
        when(mailSender.createMimeMessage()).thenReturn(mock(MimeMessage.class));
        mockMvc.perform(post("/contact")
                        .with(csrf())
                .param("fullname", "Stefan")
                .param("email", "Stefan@aaa.se")
                .param("subject","Hej hej")
                .param("content","bla bla bla bla")).andExpect(status().isOk());
    }
}