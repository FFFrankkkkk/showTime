package com.showTime.common.tools;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ReturnJson {
        static public  void returnJsonString(HttpServletResponse response, Object object,int status) throws ServletException, IOException {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
                    response.setStatus(status);
                    PrintWriter out = response.getWriter();
                    Gson gson = new Gson();
                    String jsonString = gson.toJson(object);//将对象转换成json格式的字符串
                    out.write(jsonString);
                    out.flush();
            }
}

