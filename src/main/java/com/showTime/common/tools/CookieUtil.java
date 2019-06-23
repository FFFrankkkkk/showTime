package com.showTime.common.tools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;
/**
 * cookie的增删改查
 * @author Frank-Y
 * @version 1.0 Date:2019年5月27日14:02
 */
public class CookieUtil {
    public static Cookie getCookie(String name, HttpServletRequest request){
        Cookie[] cookies= request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals(name)){
                return cookie;
            }
        }
        return null;
    }
    public static void addCookie(String name, String value,String path,int age,HttpServletResponse response){
        Cookie cookie=new Cookie(name,value);
        cookie.setPath(path);
        cookie.setMaxAge(age);
        response.addCookie(cookie);
    }
    public static void removeCookie(String name,HttpServletResponse response){
      addCookie(name,null,null,0,response);
    }
    public void editCookie(HttpServletRequest request,HttpServletResponse response,String name,String value){
        Cookie[] cookies = request.getCookies();
        if (null==cookies) {
            System.out.println("没有cookie==============");
        } else {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(name)){
                    System.out.println("原值为:"+cookie.getValue());
                    cookie.setValue(value);
                    System.out.println("被修改的cookie名字为:"+cookie.getName()+",新值为:"+cookie.getValue());
                    response.addCookie(cookie);
                    break;
                }
            }
        }

    }
}
