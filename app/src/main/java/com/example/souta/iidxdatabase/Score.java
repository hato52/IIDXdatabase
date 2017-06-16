package com.example.souta.iidxdatabase;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Souta on 2017/02/01.
 */

public class Score extends RealmObject {

    private long id;

    private String series;
    private String title;
    private String genre;
    private String artist;
    private int playnum;

    private int difficulty_n;
    private int exscore_n;
    private int pgreat_n;
    private int great_n;
    private String miss_n;
    private String clear_n;
    private String djlevel_n;

    private int difficulty_h;
    private int exscore_h;
    private int pgreat_h;
    private int great_h;
    private String miss_h;
    private String clear_h;
    private String  djlevel_h;

    private int difficulty_a;
    private int exscore_a;
    private int pgreat_a;
    private int great_a;
    private String miss_a;
    private String clear_a;
    private String djlevel_a;

    private String playdate;

    private int mode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getPlaynum() {
        return playnum;
    }

    public void setPlaynum(int playnum) {
        this.playnum = playnum;
    }

    public int getDifficulty_n() {
        return difficulty_n;
    }

    public void setDifficulty_n(int difficulty_n) {
        this.difficulty_n = difficulty_n;
    }

    public int getExscore_n() {
        return exscore_n;
    }

    public void setExscore_n(int exscore_n) {
        this.exscore_n = exscore_n;
    }

    public int getPgreat_n() {
        return pgreat_n;
    }

    public void setPgreat_n(int pgreat_n) {
        this.pgreat_n = pgreat_n;
    }

    public int getGreat_n() {
        return great_n;
    }

    public void setGreat_n(int great_n) {
        this.great_n = great_n;
    }

    public String getMiss_n() {
        return miss_n;
    }

    public void setMiss_n(String miss_n) {
        this.miss_n = miss_n;
    }

    public String getClear_n() {
        return clear_n;
    }

    public void setClear_n(String clear_n) {
        this.clear_n = clear_n;
    }

    public String getDjlevel_n() {
        return djlevel_n;
    }

    public void setDjlevel_n(String djlevel_n) {
        this.djlevel_n = djlevel_n;
    }

    public int getDifficulty_h() {
        return difficulty_h;
    }

    public void setDifficulty_h(int difficulty_h) {
        this.difficulty_h = difficulty_h;
    }

    public int getExscore_h() {
        return exscore_h;
    }

    public void setExscore_h(int exscore_h) {
        this.exscore_h = exscore_h;
    }

    public int getPgreat_h() {
        return pgreat_h;
    }

    public void setPgreat_h(int pgreat_h) {
        this.pgreat_h = pgreat_h;
    }

    public int getGreat_h() {
        return great_h;
    }

    public void setGreat_h(int great_h) {
        this.great_h = great_h;
    }

    public String getMiss_h() {
        return miss_h;
    }

    public void setMiss_h(String miss_h) {
        this.miss_h = miss_h;
    }

    public String getClear_h() {
        return clear_h;
    }

    public void setClear_h(String clear_h) {
        this.clear_h = clear_h;
    }

    public String getDjlevel_h() {
        return djlevel_h;
    }

    public void setDjlevel_h(String djlevel_h) {
        this.djlevel_h = djlevel_h;
    }

    public int getDifficulty_a() {
        return difficulty_a;
    }

    public void setDifficulty_a(int difficulty_a) {
        this.difficulty_a = difficulty_a;
    }

    public int getExscore_a() {
        return exscore_a;
    }

    public void setExscore_a(int exscore_a) {
        this.exscore_a = exscore_a;
    }

    public int getPgreat_a() {
        return pgreat_a;
    }

    public void setPgreat_a(int pgreat_a) {
        this.pgreat_a = pgreat_a;
    }

    public int getGreat_a() {
        return great_a;
    }

    public void setGreat_a(int great_a) {
        this.great_a = great_a;
    }

    public String getMiss_a() {
        return miss_a;
    }

    public void setMiss_a(String miss_a) {
        this.miss_a = miss_a;
    }

    public String getClear_a() {
        return clear_a;
    }

    public void setClear_a(String clear_a) {
        this.clear_a = clear_a;
    }

    public String getDjlevel_a() {
        return djlevel_a;
    }

    public void setDjlevel_a(String djlevel_a) {
        this.djlevel_a = djlevel_a;
    }

    public String getPlaydate() {
        return playdate;
    }

    public void setPlaydate(String playdate) {
        this.playdate = playdate;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}
