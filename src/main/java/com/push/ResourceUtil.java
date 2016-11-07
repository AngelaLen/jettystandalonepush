package com.push;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

	
	public static List<String> getHtmlImgReferences(String fileName) throws IOException {

		// put requested webapp file in a string so that Jsoup can parse it
		String content = Files.toString(new File("webapps/standalonepush/"+fileName), Charsets.UTF_8);

		Document doc = Jsoup.parseBodyFragment(content);
		Elements media = doc.select("img[src]");
		
		//Add all the elements in a list so we can return this to te 
		List<String> results = new ArrayList<String>();
		for (Element src : media) {
			results.add(src.attr("src"));
		}
		return results;

	}

}
