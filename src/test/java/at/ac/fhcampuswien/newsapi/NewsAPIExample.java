package at.ac.fhcampuswien.newsapi;

import at.ac.fhcampuswien.newsapi.beans.Article;
import at.ac.fhcampuswien.newsapi.beans.NewsResponse;
import at.ac.fhcampuswien.newsapi.enums.Category;
import at.ac.fhcampuswien.newsapi.enums.Country;
import at.ac.fhcampuswien.newsapi.enums.Endpoint;

import java.util.List;

public class NewsAPIExample {

    public static final String APIKEY = "889f4de6f3bf4029b384f8c5a3ad8c56";

    public static void main(String[] args) throws NewsApiException {

        NewsApi newsApi = new NewsApiBuilder()
                .setApiKey(APIKEY)
                .setQ("corona")
                .setEndPoint(Endpoint.TOP_HEADLINES)// example of how to use enums
                .setSourceCountry(Country.at)       // example of how to use enums
                .setSourceCategory(Category.health) // example of how to use enums
                .createNewsApi();

            NewsResponse newsResponse = newsApi.getNews();
            if(newsResponse != null){
                List<Article> articles = newsResponse.getArticles();
                articles.stream().forEach(article -> System.out.println(article.toString()));
            }

        newsApi = new NewsApiBuilder()
                .setApiKey(APIKEY)
                .setQ("corona")
                .setEndPoint(Endpoint.EVERYTHING)
                .setFrom("2021-05-20")
                .setExcludeDomains("Lifehacker.com")
                .createNewsApi();

            newsResponse = newsApi.getNews();

        if(newsResponse != null){
            List<Article> articles = newsResponse.getArticles();
            articles.stream().forEach(article -> System.out.println(article.toString()));
        }

    }
}
