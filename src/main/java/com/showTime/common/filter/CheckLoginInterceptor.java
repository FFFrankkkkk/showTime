package com.showTime.common.filter;

import com.showTime.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.interceptor.Interceptor;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

@Component
public class CheckLoginInterceptor implements HandlerInterceptor {
    @Autowired
    UserDao userDao;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String account= (String) request.getSession().getAttribute("account");
//        if(account==null){
//        Cookie[] cookies=request.getCookies();
//        if(cookies!=null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("account")){
//                    request.getSession().setAttribute("account",cookie.getValue());
//                    account=cookie.getValue();
//                }
//            }
//        }
//        }
//        if(account==null){
//            request.getSession().setAttribute("userType","0");
//            return true;
//        }
//        int iAge = 0;
//        String idCard=userDao.getUserIdCardByAccount(account);
//        Calendar cal = Calendar.getInstance();
//        String year = idCard.substring(6, 10);
//        int iCurrYear = cal.get(Calendar.YEAR);
//        iAge = iCurrYear - Integer.valueOf(year);
//        request.getSession().setAttribute("userType",iAge<18?"0":"1");
         System.out.println("====================");
        return true;
    }

}
