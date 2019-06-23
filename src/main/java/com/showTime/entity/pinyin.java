package com.showTime.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import  com.showTime.common.entity.IdEntity;
@Entity
@Table(name="pinyin")
public class pinyin extends IdEntity{
    private String character_value; //汉字
    private String character_pinyin;//拼音
    private String character_tone;  //声调

    public String getCharacter_value() {
        return character_value;
    }

    public String getCharacter_pinyin() {
        return character_pinyin;
    }

    public String getCharacter_tone() {
        return character_tone;
    }

    public void setCharacter_value(String character_value) {
        this.character_value = character_value;
    }

    public void setCharacter_pinyin(String character_pinyin) {
        this.character_pinyin = character_pinyin;
    }

    public void setCharacter_tone(String character_tone) {
        this.character_tone = character_tone;
    }
}
