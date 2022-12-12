package se.systementor.supershoppen1.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.systementor.supershoppen1.shop.model.Newsletter;
import se.systementor.supershoppen1.shop.model.NewsletterRepository;
import se.systementor.supershoppen1.shop.model.Product;

import java.util.List;

@Service
public class NewsletterService {

    @Autowired
    NewsletterRepository newsletterRepository;

    public void createNewsletter(Newsletter newsletter) {
        newsletterRepository.save(newsletter);
    }

    public Newsletter get(Integer id){
        return newsletterRepository.findById(id).get();
    }

    public List<Newsletter> getNewsletter() {
        return (List<Newsletter>) newsletterRepository.findAll();
    }
}