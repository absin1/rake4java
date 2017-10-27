package absin.rake4java;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

import rake4j.core.RakeAnalyzer;
import rake4j.core.model.Document;

public class CreateSkills {
	private static String listingTextPath = "/home/ab/Documents/job_listings_text/";

	public static void main(String[] args) {
		try {
			(new CreateSkills()).RAKE();
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void RAKE() throws IOException, URISyntaxException {
		File folder = new File(listingTextPath);
		for (File iterable_element : folder.listFiles()) {
			String text = "";
			FileReader in = new FileReader(iterable_element);
			BufferedReader bufferedReader = new BufferedReader(in);
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				text += line + "\n";
			}
			System.out.println(text);
			//normalRAKE(text);
			offsetStemmedRAKE(text);
		}

	}

	private void offsetStemmedRAKE(String text) throws URISyntaxException {
		Document doc = new Document(text);
		RakeAnalyzer rake = new RakeAnalyzer();
		rake.loadDocument(doc);
		rake.run();
		System.out.println(doc.termMapToString());

	}

	private void normalRAKE(String text) throws URISyntaxException {
		Document doc = new Document(text);
		RakeAnalyzer rake = new RakeAnalyzer();
		rake.loadDocument(doc);
		rake.runWithoutOffset();
		System.out.println(doc.termListToString());
	}
}
