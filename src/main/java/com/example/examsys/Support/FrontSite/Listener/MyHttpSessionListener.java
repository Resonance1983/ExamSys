package com.example.examsys.Support.FrontSite.Listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/listener")
@Component
public class MyHttpSessionListener implements HttpSessionListener {

    //日志记录
    private static final Logger logger = LoggerFactory.getLogger(MyHttpSessionListener.class);

    //记录用户在线的数量
    public Integer count = 0;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.info("=========新用户上线了========");
        count++;
        httpSessionEvent.getSession().getServletContext().setAttribute("count", count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        logger.info("==========用户下线了=========");
        count--;
        httpSessionEvent.getSession().getServletContext().setAttribute("count", count);
    }

    @GetMapping("/total")
    public String getTotalUser(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie;
        try {
            cookie = new Cookie("JSESSIONID", URLEncoder.encode(request.getSession().getId(), "utf-8"));
            //设置Cookie有效期
            cookie.setMaxAge(2 * 24 * 60 * 60);
            response.addCookie(cookie);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Integer count = (Integer) request.getSession().getServletContext().getAttribute("count");
        return "当前在线人数：" + count;
    }
}

