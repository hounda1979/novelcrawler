import doa.ChapterInfo;
import utils.UtilsMathod;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Testurl {
    public static void main(String[] args) throws InterruptedException, IOException {
        String firstUrl = "https://tw.uukanshu.com/b/73340/19093.html";
        File file = new File("D:/104job/temp/學霸的黑科技系統.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            file.delete();
            System.out.println("renew");
        }
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        ChapterInfo chapterInfo = UtilsMathod.getChapterInfo(firstUrl);
        long startime = System.currentTimeMillis();
        long endtime;
        while (chapterInfo.getNextChapterUrl() != null && chapterInfo.getChapterText() != null && ! chapterInfo.getId().equals("102622")){
            chapterInfo = UtilsMathod.getChapterInfo(chapterInfo.getNextChapterUrl());
            try {
                bufferedWriter.write(chapterInfo.getChapterTitle());
                bufferedWriter.write("\r\n");
                bufferedWriter.write(chapterInfo.getChapterText()+"\r\n");

                endtime = System.currentTimeMillis();
                System.out.println("第"+chapterInfo.getId()+"章下載完成共花費  "+(endtime-startime)/1000+" 秒");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
