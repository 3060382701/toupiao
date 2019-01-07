package com.cssl.webMvc;

import com.cssl.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class MysessionListener implements HttpSessionListener, ServletContextListener {


    private ServletContext application;
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session=se.getSession();
        System.out.println("session被创建");
        session.setMaxInactiveInterval(120);
    }
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        User user=(User)session.getAttribute("user");
        List<String> users= (List<String>)application.getAttribute("users");
        if(users.contains(user.getUsername())){
            users.remove(user.getUsername());
        }
        System.out.println("session销毁");
    }
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext初始化");
        System.out.println(sce.getServletContext().getServerInfo());
        application = sce.getServletContext();
        application.setAttribute("users",new ArrayList<String>());
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext销毁");
    }
}
