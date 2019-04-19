package utils;

import doa.ChapterInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilsMathod {
    /**
     *依據URL取得Document對象
     * @param url 章節URL
     * @return Document對象
     */
    public static Document gegDocumet(String url) throws InterruptedException {
        Document doc = null;
        while (true){
            try {
                doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
                        .timeout(6000)
                        .get();
                break;
            } catch (IOException e) {
                e.printStackTrace();
                Thread.sleep(100000);
                continue;
            }
    }
        return doc;
    }

    /**
     *依據Document對象取得標題(Title)
     * @param document
     * @return 章節標題
     */
    public static String getTitle(Document document){
        return document.getElementById("timu").text();
    }

    /**
     * 依據Document對象取得章節內容
     * @param document
     * @return 章節內容
     */
    public static String getContent(Document document){
        if(document.getElementById("contentbox") != null){
            return document.getElementById("contentbox").text();
        }else {
            return null;
        }

    }

    /**
     * 依據Document對象找到下一章節的URL
     * @param document
     * @return 下一章節URL
     */
    public static String getNextUrl(Document document){
        Element geturl = document.getElementsByClass("fanye_cen").first(); //找到下一章URL位置元素
        String regex = "<a href=\"(.*?)\">下一章<\\/a>"; //設定比較的正則
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(geturl.toString());
        Document nextUrl = null;
        if(matcher.find()){
            nextUrl = Jsoup.parse(matcher.group());
            Element href = nextUrl.select("a").first();
            return "https://tw.uukanshu.com"+href.attr("href");

        }else {
            return null;
        }

    }

    /**
     * 依據當前URL取得ID
     * @param url
     * @return ID
     */
    public static String getId(String url){
        String[] urlSpilts = url.split("/");
        return urlSpilts[urlSpilts.length-1].split("\\.")[0];
    }

    /**
     * 依據小說的URL取得ChapterInfo對象
     * @param url
     * @return ChapterInfo
     */
    public static ChapterInfo getChapterInfo(String url) throws InterruptedException {
        ChapterInfo chapterInfo = new ChapterInfo();
        chapterInfo.setChapterUrl(url);
        Document document = gegDocumet(url);
        chapterInfo.setId(getId(url));
        chapterInfo.setChapterTitle(getTitle(document));
        chapterInfo.setChapterText(getContent(document));
        chapterInfo.setNextChapterUrl(getNextUrl(document));
        return chapterInfo;
    }

}
