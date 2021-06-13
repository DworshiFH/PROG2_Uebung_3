package at.ac.fhcampuswien.newsanalyzer.ui;


import at.ac.fhcampuswien.newsanalyzer.ctrl.Controller;
import at.ac.fhcampuswien.newsapi.enums.Category;
import at.ac.fhcampuswien.newsapi.enums.Country;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Parameter;
import java.util.Scanner;

public class UserInterface {

	Scanner scanner = new Scanner(System.in);

	private Controller ctrl1 = new Controller();
	public void getDataFromCtrl1(){
		ctrl1.setTopic("corona");
		ctrl1.setCountry(Country.at);
		ctrl1.setCategory(Category.health);
		ctrl1.setLoadEverything(false);
		ctrl1.process();
	}

	private Controller ctrl2 = new Controller();
	public void getDataFromCtrl2(){
		ctrl2.setTopic("sports");
		ctrl2.setCountry(Country.at);
		ctrl2.setCategory(Category.sports);
		ctrl2.setLoadEverything(true);
		ctrl2.process();
	}

	public void getDataFromCtrl3(){
		// TODO implement me
	}

	private Controller ctrl4 = new Controller();
	public void getDataForCustomInput() {
		// TODO implement me
		boolean categorySet = false;
		boolean amountSet = false;
		boolean analysisSet = false;

		while(!categorySet){
			System.out.println("Wählen Sie eine der folgenden Kategorien: ");
			System.out.print(
					"b: Business\n"+
							"u: Unterhaltung\n"+
							"g: Gesundheit\n"+
							"w: Wissenschaft\n"+
							"t: Technologie\n"+
							"> ");
			String s = scanner.next();
			switch(s){
				case "b": ctrl4.setCategory(Category.business); categorySet=true; break;
				case "u": ctrl4.setCategory(Category.entertainment); categorySet=true; break;
				case "g": ctrl4.setCategory(Category.health); categorySet=true; break;
				case "w": ctrl4.setCategory(Category.science); categorySet=true; break;
				case "t": ctrl4.setCategory(Category.technology); categorySet=true; break;
				default:
					System.out.println("Ungültige Eingabe"); break;
			}
			System.out.println();
		}

		while(!amountSet){
			System.out.print("Möchten Sie alles verfügbare erhalten? (j/n): ");
			String s1 = scanner.next();
			switch(s1){
				case "j": ctrl4.setLoadEverything(false); amountSet=true; break;
				case "n": ctrl4.setLoadEverything(true); amountSet=true; break;
				default: System.out.println("Ungültige Eingabe"); break;
			}
			System.out.println();
		}

		while(!analysisSet){
			System.out.print("Möchten Sie unseren Algorithmus verwenden um Ihre Nachrichten zu analysieren? (j/n): ");
			String s2 = scanner.next();
			switch(s2){
				case "j": ctrl4.setUseAnalysis(false); analysisSet=true; break;
				case "n": ctrl4.setUseAnalysis(true); analysisSet=true; break;
				default: System.out.println("Ungültige Eingabe"); break;
			}
			System.out.println();
		}

		ctrl4.setCountry(Country.at);

		System.out.println("Hier Ihre Nachrichten in Österreich:");

		ctrl4.process();
	}


	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interface");
		menu.setTitle("Wählen Sie aus:");
		menu.insert("a", "Top Nachrichten zu COVID-19 in Österreich", this::getDataFromCtrl1);
		menu.insert("b", "Alle News zu Sport in Österreich", this::getDataFromCtrl2);
		menu.insert("c", "Choice 3", this::getDataFromCtrl3);
		menu.insert("d", "Choice User Input:",this::getDataForCustomInput);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}


    protected String readLine() {
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
        } catch (IOException ignored) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 	{
		Double number = null;
        while (number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                number = null;
				System.out.println("Please enter a valid number:");
				continue;
			}
            if (number < lowerlimit) {
				System.out.println("Please enter a higher number:");
                number = null;
            } else if (number > upperlimit) {
				System.out.println("Please enter a lower number:");
                number = null;
			}
		}
		return number;
	}
}
