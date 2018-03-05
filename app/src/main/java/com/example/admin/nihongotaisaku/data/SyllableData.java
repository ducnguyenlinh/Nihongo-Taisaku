package com.example.admin.nihongotaisaku.data;

import android.os.AsyncTask;

import com.example.admin.nihongotaisaku.models.Syllable;
import com.example.admin.nihongotaisaku.utils.InterfaceParseData;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class SyllableData extends AsyncTask<String, Integer, ArrayList<Syllable>>{
    String url;
    ArrayList<Syllable> listSyllable = new ArrayList<>();
    ArrayList<String> arrNihongo = new ArrayList<>();

    String[] arr = {"a", "i", "u", "e", "o"
            , "ka", "ki", "ku", "ke", "ko"
            , "sa", "shi", "su", "se", "so"
            , "ta", "chi", "tsu", "te", "to"
            , "na", "ni", "nu", "ne", "no"
            , "ha", "hi", "fu", "he", "ho"
            , "ma", "mi", "mu", "me", "mo"
            , "ya", "", "yu", "", "yo"
            , "ra", "ri", "ru", "re", "ro"
            , "wa", "", "", "", "o"
            , "", "", "", "", "n"
            , "ga", "gi", "gu", "ge", "go"
            , "za", "ji", "zu", "ze", "zo"
            , "da", "ji", "zu", "de", "do"
            , "ba", "bi", "bu", "be", "bo"
            , "pa", "pi", "pu", "pe", "po"
            , "kya", "", "kyu", "", "kyo"
            , "sha", "", "shu", "", "sho"
            , "cha", "", "chu", "", "cho"
            , "nya", "", "nyu", "", "nyo"
            , "hya", "", "hyu", "", "hyo"
            , "mya", "", "myu", "", "myo"
            , "rya", "", "ryu", "", "ryo"
            , "gya", "", "gyu", "", "gyo"
            , "ja", "", "ju", "", "jo"
            , "bya", "", "byu", "", "byo"
            , "pya", "", "pyu", "", "pyo"
    };

    Document document;
    private InterfaceParseData output;

    public SyllableData(InterfaceParseData output) {
        this.output = output;
    }

    @Override
    protected ArrayList<Syllable> doInBackground(String... strings) {
        url = "https://www.nhk.or.jp/lesson/vietnamese/syllabary/" + strings[0];
        document = null;

        try {
            document = Jsoup.connect(url).get();
            Elements page1 = document.select("div#page1.sy-inner");
            Element ul = page1.get(0);
            Elements aa = ul.select("div.letter");

            for (int i = 0; i < aa.size(); i++){
                Elements aaa = aa.select("div.jp-play.sy-play-button1");
                String syl = aaa.get(i).text();
                arrNihongo.add(syl);
            }

            arrNihongo.add(36, "");
            arrNihongo.add(38, "");
            arrNihongo.add(46, "");
            arrNihongo.add(47, "");
            arrNihongo.add(48, "");
            arrNihongo.add(50, "");
            arrNihongo.add(51, "");
            arrNihongo.add(52, "");
            arrNihongo.add(53, "");

            Elements page2 = document.select("div#page2.sy-inner");
            Element ul2 = page2.get(0);
            Elements aa2 = ul2.select("div.letter");
            for(int i = 0; i < aa2.size(); i++){
                Elements aaa2 = aa2.select("div.jp-play.sy-play-button1");
                String syl = aaa2.get(i).text();
                arrNihongo.add(syl);
            }
            arrNihongo.add(81, "");
            arrNihongo.add(83, "");
            arrNihongo.add(86, "");
            arrNihongo.add(88, "");
            arrNihongo.add(91, "");
            arrNihongo.add(93, "");
            arrNihongo.add(96, "");
            arrNihongo.add(98, "");
            arrNihongo.add(101, "");
            arrNihongo.add(103, "");
            arrNihongo.add(106, "");
            arrNihongo.add(108, "");
            arrNihongo.add(111, "");
            arrNihongo.add(113, "");
            arrNihongo.add(116, "");
            arrNihongo.add(118, "");
            arrNihongo.add(121, "");
            arrNihongo.add(123, "");
            arrNihongo.add(126, "");
            arrNihongo.add(128, "");
            arrNihongo.add(131, "");
            arrNihongo.add(133, "");

        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < arrNihongo.size(); i++){
            listSyllable.add(new Syllable(arrNihongo.get(i),arr[i]));
        }

        return listSyllable;
    }

    @Override
    protected void onPostExecute(ArrayList<Syllable> strings) {
        super.onPostExecute(strings);
        output.processFinish(strings);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }
}
