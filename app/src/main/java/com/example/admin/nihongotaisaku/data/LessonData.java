package com.example.admin.nihongotaisaku.data;

import android.os.AsyncTask;
import android.util.Log;

import com.example.admin.nihongotaisaku.models.ListLesson;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;


public class LessonData extends AsyncTask <String, Integer, ArrayList<ListLesson>>{
    private String url = "http://www.vnjpclub.com/tieng-nhat-so-cap/minna-no-nihongo.html";
    Document document;
    private String href = "";
    private Element element;
    ArrayList<ListLesson> listListLesson;

    @Override
    protected ArrayList<ListLesson> doInBackground(String... strings) {
        document= null;
        listListLesson = new ArrayList<>();

        try {
            document = Jsoup.connect(url).get();
            Elements elements = document.select("td.list-title");
            for (int i = 0; i < elements.size(); i++){
                element = elements.get(i);
                Element elementHref = element.getElementsByTag("a").first();
                href = elementHref.attr("href");
                Log.e("@@@", href);
                listListLesson.add(new ListLesson(element.text(), href));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listListLesson;
    }
}
