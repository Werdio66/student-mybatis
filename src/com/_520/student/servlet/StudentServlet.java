package com._520.student.servlet;

import com._520.student.mybatis.mapper.Student;
import com._520.student.mybatis.mapper.StudentMapper;
import com._520.student.mybatis.util.MybatisUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = String.valueOf(req.getSession().getAttribute("LOGIN_IN_SESSION"));
        System.out.println("login = " + login);
        if ("success".equals(login)){
            String cmd = req.getParameter("cmd");
            if("delete".equals(cmd)){
                this.delete(req,resp);
            }else if ("edit".equals(cmd)){
                this.edit(req,resp);
            }else if ("save".equals(cmd)){
                this.save(req,resp);
            }else if("condition".equals(cmd)) {
                this.condition(req,resp);
            }else {
                this.list(req,resp);
            }
        }else {
            req.setAttribute("errorMsg","请登录!");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }

    }

    private void condition(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentName = req.getParameter("studentName");
        Integer minAge = Integer.valueOf(req.getParameter("minAge"));
        Integer maxAge = Integer.valueOf(req.getParameter("maxAge"));
        StudentMapper studentMapper = MybatisUtil.getMapper(StudentMapper.class);
        List<Student> list = studentMapper.advanceQuery(studentName,minAge,maxAge);
        System.out.println("studentName = " +studentName+"minAge = " +minAge+"maxAge = " + maxAge);
        req.setAttribute("stu",list);
        req.getRequestDispatcher("/WEB-INF/jsp/student.jsp").forward(req,resp);
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentMapper studentMapper = MybatisUtil.getMapper(StudentMapper.class);
        List<Student> list = studentMapper.listAll();
        req.setAttribute("students",list);
        req.getRequestDispatcher("/WEB-INF/jsp/student.jsp").forward(req,resp);
    }
    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentMapper studentMapper = MybatisUtil.getMapper(StudentMapper.class);
        //接收请求参数
        String id = req.getParameter("id");
        studentMapper.delete(Long.valueOf(id));
        resp.sendRedirect("/student");
    }
    private void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        if (!isEmply(sid)){
            StudentMapper studentMapper = MybatisUtil.getMapper(StudentMapper.class);
            req.setAttribute("stu",studentMapper.get(Long.valueOf(sid)));
        }
        req.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(req,resp);

    }
    protected void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("id");
        System.out.println("edit  sid = " + sid);
        StudentMapper studentMapper = MybatisUtil.getMapper(StudentMapper.class);
        if (isEmply(sid)){
            studentMapper.save(getStu(req));
        }else {
            studentMapper.update(getStu(req));
        }
        resp.sendRedirect("/student");
    }
    public static Student getStu(HttpServletRequest req){
            String name = req.getParameter("name");
            Integer age = Integer.valueOf(req.getParameter("age"));
            Integer math = Integer.valueOf(req.getParameter("math"));
            Integer computer = Integer.valueOf(req.getParameter("computer"));
            Integer english = Integer.valueOf(req.getParameter("english"));
            String sid = req.getParameter("id");
            Student stu = new Student();
            stu.setEnglish(english);
            stu.setComputer(computer);
            stu.setMath(math);
            stu.setName(name);
            stu.setAge(age);
            if (!isEmply(sid)){
                stu.setId(Long.valueOf(sid));
            }
            return stu;
    }
    public static boolean isEmply(String sid){
        if ("".equals(sid) || sid == null){
            return true;
        }
        return false;
    }

}
