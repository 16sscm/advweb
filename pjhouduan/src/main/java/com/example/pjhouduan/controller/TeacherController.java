package com.example.pjhouduan.controller;

import com.example.pjhouduan.mybatis.SqlSessionLoader;
import com.example.pjhouduan.mybatis.po.Teacher;
import com.example.pjhouduan.request.TeacherLoginRequest;
import com.example.pjhouduan.request.TeacherRegisterRequest;
import com.example.pjhouduan.response.GreetingResponse;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/teacher")
public class TeacherController {
    @RequestMapping(path = "/register",method = RequestMethod.POST)
    public @ResponseBody Object register(TeacherRegisterRequest teacherRegisterRequest) throws IOException {
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        String fileName = teacherRegisterRequest.getHead().getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = UUID.randomUUID()+suffixName;
        //指定本地文件夹存储图片
        String filePath = "D:\\image\\";
        try {
            //将图片保存到static文件夹里
            teacherRegisterRequest.getHead().transferTo(new File(filePath+fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        sqlSession.insert("GJweb.Mapper.addTeacher",new Teacher(teacherRegisterRequest.getName(),teacherRegisterRequest.getPassword(),filePath+fileName));
        sqlSession.commit();
        sqlSession.close();
        return new GreetingResponse(12,"fjlak");
    }

    @RequestMapping(path = "/login",method = RequestMethod.POST)
    public @ResponseBody Object login(TeacherLoginRequest teacherLoginRequest) throws IOException{
        SqlSession sqlSession = SqlSessionLoader.getSqlSession();
        Teacher teacher = sqlSession.selectOne("GJweb.Mapper.checkLogin",teacherLoginRequest);
        return teacher;
    }

}
