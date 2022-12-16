package se.systementor.supershoppen1.shop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Subscriber {
    private String email;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer subscriberId;


    public Integer getSubscriberId() {
        return subscriberId;
    }

    public void setSubscriberId(Integer subscriberId) {
        this.subscriberId = subscriberId;
    }


    public void setEmail(String v)
    {
        email = v;
    }


    public String getEmail()
    {
        return email;
    }




}


