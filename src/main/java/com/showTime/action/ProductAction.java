package com.showTime.action;

import com.showTime.common.tools.ReturnJson;
import com.showTime.entity.Production;
import com.showTime.service.ProductionService;
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
@RequestMapping("/ProductAction")

public class ProductAction {
    @Autowired
    private ProductionService productionService;
    @RequestMapping("/getRecommendProduction")
    public @ResponseBody void getRecommendProduction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Production> productions=productionService.getRecommendProduction((String) request.getSession().getAttribute("userType"));
        ReturnJson.returnJsonString(response,productions);
    }
    @RequestMapping("/getHotProduction")
    public @ResponseBody void getHotProduction(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<Production> productions=productionService.getHotProduction((String) request.getSession().getAttribute("userType"));
        ReturnJson.returnJsonString(response,productions);
    }
    @RequestMapping("/addProduction")
    public @ResponseBody void addProduction(){

    }

}

