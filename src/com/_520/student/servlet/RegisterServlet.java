package com._520.student.servlet;

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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    UserMapper userMapper;
    User user;
    @Override
    public void init(ServletConfig config) throws ServletException {
        userMapper = MybatisUtil.getMapper(UserMapper.class);
        user = new User();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String randomCode = req.getParameter("randomCode");
        String randomCodeInSession = (String)req.getSession().getAttribute("RANDOM_IN_SESSION");
        if (!randomCode.equalsIgnoreCase(randomCodeInSession)){
            req.setAttribute("errorMsg","验证码错误!");
            req.getRequestDispatcher("/registerr.jsp").forward(req,resp);
        }else {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            user.setUsername(username);
            user.setPassword(password);
            System.out.println(user);
            userMapper.save(user);
            req.setAttribute("errorMsg","注册成功!");
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }
    }
}
