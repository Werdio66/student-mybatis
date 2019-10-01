package com._520.student.randomCode;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

@WebServlet("/randomCode")
public class RandomCodeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //生成随机数
        String randomCode = UUID.randomUUID().toString().substring(0,5);
        //设置大小
        int width = 73;
        int height = 27;
        //图像类型
        int imageType = BufferedImage.TYPE_INT_RGB;
        //创建图片对象
        BufferedImage image = new BufferedImage(width,height,imageType);
        //获取画笔
        Graphics g = image.getGraphics();
        g.setColor(Color.YELLOW);
        //画一个矩形
        g.fillRect(1,1,width,height);
        g.setColor(Color.BLACK);//设置随机数颜色
        //设置随机数的字体和大小
        g.setFont(new Font("",Font.BOLD + Font.ITALIC, 22));
        g.drawString(randomCode, 10, 25);
        //干扰点
        g.setColor(Color.DARK_GRAY);
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            g.drawOval(x,y,1,1);
        }
        //关闭
        g.dispose();
        //将验证码放入session中
        req.getSession().setAttribute("RANDOM_IN_SESSION",randomCode);
        //以流的方式输出图像
        ImageIO.write(image,"jpg",resp.getOutputStream());
    }
}
