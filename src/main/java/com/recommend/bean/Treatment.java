package com.recommend.bean;
import javax.persistence.*;

@Entity
@Table(name = "treatment")
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;

    @Column(name = "ill_type")
    private String illType;

    @Column(name = "ill_state")
    private String illState;

    @Column(name = "medicine_cnt")
    private Integer medicineCnt;

    @Column(name = "publish_time")
    private String publish_time;

    @Column(name = "author")
    private String author;

    public String getIllType() {
        return illType;
    }

    public void setIllType(String illType) {
        this.illType = illType;
    }

    public String getIllState() {
        return illState;
    }

    public void setIllState(String illState) {
        this.illState = illState;
    }

    public Integer getMedicineCnt() {
        return medicineCnt;
    }

    public void setMedicineCnt(Integer medicineCnt) {
        this.medicineCnt = medicineCnt;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public Treatment() {
    }
    public Treatment(String title, String content, String author, String publish_time, String illState,
                   Integer medicineCnt, String illType) {

        this.title = title;
        this.content = content;
        this.author = author;
        this.publish_time = publish_time;
        this.medicineCnt = medicineCnt;
        this.illState = illState;
        this.illType = illType;
    }
    public long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Article [id=" + id + ", title=" + title + ", content=" + content + ", author=" + author + ", publish_time=" + publish_time +
                ", ill_type=" + illType + ", medicine_cnt=" + String.valueOf(medicineCnt) + ", " +
                "ill_state=" + illState + "]";
    }
}