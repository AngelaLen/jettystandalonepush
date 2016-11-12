package com.push;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class ResourceUtil {

	// TODO this singleton needs to be enhanced (CDI) - for now suffices
	// to make sure static algoritm is executed one time to prevent overhead

	private static List<String> results = new ArrayList<String>();

	public static List<String> getHtmlImgReferences(String fileName) throws IOException {

		if (results.isEmpty()) {

			// put requested webapp file in a string so that Jsoup can parse it
			String content = Files.toString(new File("webapps/standalonepush/" + fileName), Charsets.UTF_8);

			Document doc = Jsoup.parseBodyFragment(content);
			Elements media = doc.select("img[src]");

			// Add all the elements in a list so we can return this
			for (Element src : media) {
				results.add(src.attr("src"));
			}
		}
		return results;

	}

}
