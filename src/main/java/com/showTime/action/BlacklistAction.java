package com.showTime.action;

import com.showTime.common.tools.ReturnJson;
import com.showTime.entity.BlackList;
import com.showTime.service.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/BlacklistAction")
public class BlacklistAction {
    @Autowired
    BlackListService blackListService;
    @RequestMapping("/getBlacklist")
    public @ResponseBody  void  getBlacklist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BlackList> blackLists=blackListService.findAll();
        for(int i=0;i<blackLists.size();i++){
            blackLists.get(i).getUser().setSomeItemNull();
        }
         ReturnJson.returnJsonString(response,blackLists,200);
    }
}
