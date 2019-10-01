package com._520.student.servlet;

import com._520.student.mybatis.mapper.StudentMapper;
import com._520.student.mybatis.mapper.User;
import com._520.student.mybatis.mapper.UserMapper;
import com._520.student.mybatis.util.MybatisUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserMapper userMapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        userMapper = MybatisUtil.getMapper(UserMapper.class);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        List<User> list = userMapper.get();
        for (User user:list
             ) {
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                System.out.println(user);
                //resp.sendRedirect("/student");
                req.getSession().setAttribute("LOGIN_IN_SESSION","success");
                System.out.println("登录成功");
                req.getRequestDispatcher("/student").forward(req,resp);
            }else {
                req.setAttribute("errorMsg","账号或者密码错误!");
                req.getRequestDispatcher("/login.jsp").forward(req,resp);
            }
        }
    }
}
