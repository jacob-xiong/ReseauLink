package com.reseau.link.mvp.data;

import java.io.Serializable;

/**
 * @author by xiongyan on 2017/11/20.
 */

public class TestData implements Serializable {
    private int result;
    private String name;
    private String passWord;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
