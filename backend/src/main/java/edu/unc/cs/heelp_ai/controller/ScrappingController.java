package edu.unc.cs.heelp_ai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@RestController
@RequestMapping("/api")
public class ScrappingController {

    @GetMapping("/scrape")
    public String scrapeWebsite() { //@PathVariable String url
        try {
            String url = "https://www.dailytarheel.com/article/2025/04/university-compsci-60th-anniversary";
            Document doc = Jsoup.connect(url).get();
            Elements paragraphs = doc.select("p");
            StringBuilder content = new StringBuilder();

            for (Element p : paragraphs) {
                content.append(p.text()).append("\n");
            }
            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching the URL.";
        }
    }
}
