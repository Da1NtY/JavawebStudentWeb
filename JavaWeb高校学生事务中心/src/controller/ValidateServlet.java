package controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/ValidateServlet")
public class ValidateServlet extends HttpServlet {
    //对验证码图片进行初始化
    private int width = 60;
    private int height = 20;
    //定义验证码字符个数为4
    private int codeCount = 4;
    private int x = 0;
    //字体高度
    private int fontHeight;
    private int codeY;
    //定义数据字典
    char[] codeSequence = {'a','b','c','d','e','f','g','h','i','j','k','m','n','q','r','s','t',
            'u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','M','N','Q','R','S','T','U',
            'V','W','X','Y','Z','2','3','4','5','6','7','8','9'};

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //创建一个随机数生成器类
        Random random = new Random();

        //1.生成一副图片
        x = width/(codeCount + 1);
        fontHeight = height - 2;
        codeY = height - 4;
        //定义图像buffer
        BufferedImage buffImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();
        //将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0,0,width,height);
        //创建字体，字体的大小应该根据图片的高度来决定
        Font font = new Font("Fixedays",Font.PLAIN,fontHeight);
        //设置字体
        g.setFont(font);
        //画边框
        g.setColor(Color.BLACK);
        g.drawRect(0,0,width - 1,height - 1);
        //随机产生8条干扰线，使图像中的认证码不易被其他程序探测到
        g.setColor(Color.BLACK);
        for (int i = 0;i < 8;i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(8);
            int yl = random.nextInt(8);
            g.drawLine(x,y,x+xl,y+yl);
        }

        //2.产生随机数
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;
        //随机产生codeCount个数字的验证码
        for(int i = 0; i<codeCount;i++){
            //得到随机产生的验证码数字
            String strRand = String.valueOf(codeSequence[random.nextInt(54)]);
            //产生随机的颜色分量来构造色值，这样输出的每位数字的颜色值将不同
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            //用随机产生的颜色将验证码绘制到图像中
            g.setColor(new Color(red,green,blue));
            g.drawString(strRand,(i + 1)*x,codeY);
            //将产生的四个随机数组合在一起
            randomCode.append(strRand);
        }
        //3.把随机数放在session中
        HttpSession session = request.getSession();
        session.setAttribute("validateCode",randomCode.toString());

        //4.输出图片，禁止图片缓存
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);
        response.setContentType("image/jpeg");

        //将图像输出到ServletS输出流中
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(buffImg,"jpeg",out);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
