package se.systementor.supershoppen1.shop.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.systementor.supershoppen1.shop.model.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
public class ArticleService {

    List<Article> articleList = new ArrayList<>();

    public List<Article> getArticlesFromApi() {
        String url = "https://api.krisinformation.se/v1/themes?format=json";
        RestTemplate rt = new RestTemplate();
        JsonNode jsonNode = rt.getForObject(url, JsonNode.class);

        for (JsonNode i : jsonNode.get("ThemeList")) {
            Article article = new Article();
            article.setTitle(i.get("Title").asText());
            article.setText(i.get("Text").asText());
            article.setId(Integer.valueOf(i.get("ID").asText()));
            articleList.add(article);
        }
        return articleList;
    }

    public List<Article> getArticleList() {
        List<Article> articlesLatest = getArticlesFromApi().stream().limit(10).collect(Collectors.toList());
        return articlesLatest;
    }

    public Article getArticleByID(Integer id) {
        for(Article article : getArticleList()){
            if(article.getId().equals(id)){
                return article;
            }
        }
        return null;
    }


}