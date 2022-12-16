package se.systementor.supershoppen1.shop.services;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import se.systementor.supershoppen1.shop.model.Subscriber;
import se.systementor.supershoppen1.shop.model.SubscriberRepository;


import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriberService {

    @Autowired
    private SubscriberRepository subscriberRepository;

    SubscriberService(SubscriberRepository repository) {
        super();
        this.subscriberRepository = repository;
    }

    public List<Subscriber> getAll() {
        var l = new ArrayList<Subscriber>();
        for (Subscriber r : subscriberRepository.findAll()) {
            l.add(r);
        }
        return l;

    }

    public Subscriber get (Integer id) { return subscriberRepository.findById(id).get(); }
    public void save (Subscriber added1) { subscriberRepository.save(added1); }




    public boolean isSubscriber(String email){
        return subscriberRepository.findByEmail(email) != null;
    }


    public void addSubscriber(String email){
        if(isSubscriber(email)) return;
        Subscriber sub = new Subscriber();
        sub.setEmail(email);
        subscriberRepository.save(sub);
    }

}






