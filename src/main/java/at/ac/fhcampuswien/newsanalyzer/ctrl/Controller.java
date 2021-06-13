package at.ac.fhcampuswien.newsanalyzer.ctrl;

import at.ac.fhcampuswien.newsapi.NewsApi;
import at.ac.fhcampuswien.newsapi.NewsApiBuilder;
import at.ac.fhcampuswien.newsapi.beans.Article;
import at.ac.fhcampuswien.newsapi.beans.NewsResponse;
import at.ac.fhcampuswien.newsapi.enums.Category;
import at.ac.fhcampuswien.newsapi.enums.Country;
import at.ac.fhcampuswien.newsapi.enums.Endpoint;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;


import java.util.List;

public class Controller {

	public static final String APIKEY = "889f4de6f3bf4029b384f8c5a3ad8c56";

	private Country country;
	private String topic;
	private Category category;
	private Endpoint endpoint;

	private boolean useAnalysis;

	Date date = Calendar.getInstance().getTime();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
	String strDate = dateFormat.format(date);

	public void process() {

		System.out.println("Start process");

		//TODO implement Error handling

		//TODO load the news based on the parameters

		//TODO implement methods for analysis

		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(APIKEY)
				.setQ(this.topic) //				.setEndPoint(Endpoint.EVERYTHING)
				.setEndPoint(endpoint)
				.setSourceCountry(this.country)
				.setSourceCategory(category)
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
				.setFrom(strDate)
				.setExcludeDomains("Lifehacker.com")
				.createNewsApi();

		newsResponse = newsApi.getNews();

		if(newsResponse != null){
			List<Article> articles = newsResponse.getArticles();
			articles.stream().forEach(article -> System.out.println(article.toString()));
		}

		if(useAnalysis){
			analysis(newsResponse);
		}

		System.out.println("End process");
	}

	public void setUseAnalysis(boolean useAnalysis){
		this.useAnalysis=useAnalysis;
	}

	public void setTopic(String topic){
		this.topic=topic;
	}

	public void setCountry(Country country){
		this.country=country;
	}
	
	public void setCategory(Category category){
		this.category=category;
	}

	public void setLoadEverything(boolean loadEverything){
		if(loadEverything){
			endpoint=Endpoint.EVERYTHING;
		} else {
			endpoint=Endpoint.TOP_HEADLINES;
		}
	}

	public void analysis(NewsResponse newsResponse){
		if(newsResponse != null){
			List<Article> articles = newsResponse.getArticles();
			articles.stream().forEach(article -> System.out.println(article.toString()));
		}
	}

	public Object getData() {
		
		return null;
	}
}
