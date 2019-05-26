package com.example.pjhouduan.mybatis.po;


class Chapter {

    private Section[] section;
    private String chapter_name;
    public Chapter(){

    }

    public Chapter(String chapter_name, Section[] section) {
        this.chapter_name = chapter_name;
        this.section = section;
    }

    public Section[] getSection() {
        return section;
    }

    public void setSection(Section[] section) {
        this.section = section;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }
}