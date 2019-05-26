package com.example.pjhouduan.mybatis.po;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private int id;
    private int teacher_id;
    private String title;
    private String subtitle;
    private String background;
    private String introduce;
    private Chapter[] chapters;

    public Course(){
        System.out.println("person无参数构造函数");
    }

    public Course(int teacher_id, String title, String subtitle, String background, String introduce, Chapter[] chapters) {
        this.teacher_id = teacher_id;
        this.title = title;
        this.subtitle = subtitle;
        this.background = background;
        this.introduce = introduce;
        this.chapters = chapters;
    }

    public Course(int id, int teacher_id, String title, String subtitle, String background, String introduce, Chapter[] chapters) {
        this.id = id;
        this.teacher_id = teacher_id;
        this.title = title;
        this.subtitle = subtitle;
        this.background = background;
        this.introduce = introduce;
        this.chapters = chapters;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Chapter[] getChapters() {
        return chapters;
    }

    public void setChapters(Chapter[] chapters) {
        this.chapters = chapters;
    }

    public static Course coursejson2course(CourseJson courseJson) {
        Gson gson = new Gson();
        // 将 json 转化 成 List泛型
        List<Chapter> stus = gson.fromJson(courseJson.getContent(), new TypeToken<List<Chapter>>() {
        }.getType());
        Chapter[] chapters = new Chapter[stus.size()];
        stus.toArray(chapters);
        return new Course(courseJson.getId(), courseJson.getTeacher_id(), courseJson.getTitle(), courseJson.getSubtitle(), courseJson.getBackground(),
                courseJson.getIntroduce(), chapters);
    }

    public static CourseJson course2coursejson(Course course){
        Gson gson = new Gson();
        String content = gson.toJson(course.chapters);
        return new CourseJson(course.id,course.teacher_id,course.title,course.subtitle,course.background,course.introduce,content);
    }
}








