package at.ac.fhcampuswien;

import at.ac.fhcampuswien.newsanalyzer.ui.UserInterface;
import at.ac.fhcampuswien.newsapi.NewsApiException;

public class MCP {
    public static void main(String[] args) throws NewsApiException {
        UserInterface ui = new UserInterface();
        ui.start();
    }
}
