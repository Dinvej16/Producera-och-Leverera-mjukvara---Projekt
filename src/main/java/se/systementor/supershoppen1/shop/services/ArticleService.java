package se.systementor.supershoppen1.shop.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import se.systementor.supershoppen1.shop.model.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
public class ArticleService {

    List<Article> articles = new ArrayList<Article>();


    public List<Article> getArticleList() {
        String url = "https://api.krisinformation.se/v1/themes?format=json";
        RestTemplate rt =new RestTemplate();
        JsonNode jsonNode = rt.getForObject(url, JsonNode.class);

        for(JsonNode i : jsonNode.get("ThemeList")){
            Article article = new Article();
            article.setTitle(i.get("Title").asText());
            article.setText(i.get("Text").asText());
            article.setId(Integer.valueOf(i.get("ID").asText()));
            articles.add(article);
        }

        return articles;
    }
    public List<Article> getTenLatestArticleList() {
        List<Article> articlesLatest = getArticleList().stream().sorted(comparing(Article::getId , comparing(Math::abs)).reversed()).limit(2).collect(Collectors.toList());
        return articlesLatest;
    }

    @GetMapping("/articles/{id}")
    public Article getArticleByID(@PathVariable("id") Integer id) {
        for(Article article : getTenLatestArticleList()){
            if(article.getId().equals(id)){
                return article;
            }
        }
        return null;
    }
}
