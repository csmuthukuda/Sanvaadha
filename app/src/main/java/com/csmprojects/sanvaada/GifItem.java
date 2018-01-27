package com.csmprojects.sanvaada;

/**
 * Created by chathuranga on 7/26/17.
 */
public class GifItem {
    private int gifId,position;
    private String meaning,path;

    public GifItem(int gifId, int position,String meaning,String path) {
        this.gifId = gifId;
        this.position = position;
        this.meaning=meaning;
        this.path=path;
    }

    public int getGifId() {
        return gifId;
    }

    public void setGifId(int gifId) {
        this.gifId = gifId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
