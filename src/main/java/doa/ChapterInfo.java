package doa;

public class ChapterInfo {
    private String id; //id
    private String chapterTitle; //章節標題
    private String chapterUrl; //當前章節URL
    private String chapterText; //章節內容
    private String nextChapterUrl; //下一章節URL

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public String getNextChapterUrl() {
        return nextChapterUrl;
    }

    public void setNextChapterUrl(String nextChapterUrl) {
        this.nextChapterUrl = nextChapterUrl;
    }



    public String getChapterUrl() {
        return chapterUrl;
    }

    public void setChapterUrl(String chapterUrl) {
        this.chapterUrl = chapterUrl;
    }

    public String getChapterText() {
        return chapterText;
    }

    public void setChapterText(String chapterText) {
        this.chapterText = chapterText;
    }
    public String chptertoString(){
        return "ChapterInfo[id="+id+",chapterTitle="+chapterTitle+",chapterText="+chapterText+
                ",chapterUrl="+chapterUrl+",nextChapterUrl="+nextChapterUrl+"]";
    }
}
