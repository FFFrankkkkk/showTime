package com.showTime.common.filter;

import com.showTime.dao.UserDao;
import com.showTime.entity.User;
import com.showTime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.interceptor.Interceptor;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.List;

@Component
public class CheckLoginInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String account= (String) request.getSession().getAttribute("account");//session里检查是否登陆
        if(account==null){
        Cookie[] cookies=request.getCookies();//session里没有账户信息则从cookie里查找再保存在session里
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("account")){
//                  request.getSession().setAttribute("account",cookie.getValue());
                    User user=new User();
                    user.setAccount(cookie.getValue());

                }
            }
        }
        }
        if(account==null){
            request.getSession().setAttribute("userType","0");
            return true;
        }
        int iAge = 0;
        Object[][] user=userService.getUserByAccount(account);
        Calendar cal = Calendar.getInstance();
        System.out.println();
        String year = String.valueOf(user[0][1]).substring(6, 10);
        int iCurrYear = cal.get(Calendar.YEAR);
        iAge = iCurrYear - Integer.valueOf(year);
        request.getSession().setAttribute("type",user[0][0]);//是否是管理员0是，1不是
        request.getSession().setAttribute("userType",iAge<18?"0":"1");//是否是成年人
        System.out.println("====================");
        return true;
    }

}
