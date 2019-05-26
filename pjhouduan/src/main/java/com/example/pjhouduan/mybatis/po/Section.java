package com.example.pjhouduan.mybatis.po;

class Section {

    private  Question[] question;
    private String sectionname;
    private SingleChoice[] singleChoice;

    public Question[] getQuestion() {
        return question;
    }

    public void setQuestion(Question[] question) {
        this.question = question;
    }

    public String getSectionname() {
        return sectionname;
    }

    public void setSectionname(String sectionname) {
        this.sectionname = sectionname;
    }



    public SingleChoice[] getSingleChoice() {
        return singleChoice;
    }

    public void setSingleChoice(SingleChoice[] singleChoice) {
        this.singleChoice = singleChoice;
    }
}
