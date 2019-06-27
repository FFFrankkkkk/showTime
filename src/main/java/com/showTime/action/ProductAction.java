package com.showTime.action;

import com.showTime.common.tools.*;
import com.showTime.dao.ProductionDao;
import com.showTime.entity.Category;
import com.showTime.entity.Production;
import com.showTime.entity.Subclass;
import com.showTime.entity.User;
import com.showTime.service.ProductionService;
import com.sun.org.apache.bcel.internal.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
//        List<Production> productions=productionService.getRecommendProduction((String) request.getSession().getAttribute("userType"));
        List<Production> productions;
        if(String.valueOf(request.getSession().getAttribute("userType")).equals("1")) {
           productions = productionService.findAllByRecommendAndModel(Recommend.YES,Model.adult);
        }else{
            productions = productionService.findAllByRecommendAndModel(Recommend.YES,Model.child);
        }
        ReturnJson.returnJsonString(response,productions,200);
    }
    @RequestMapping("/getHotProduction")
    public @ResponseBody void getHotProduction(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<Production> highPlaybacks=productionService.getHotProduction((String) request.getSession().getAttribute("userType"));
        ReturnJson.returnJsonString(response,highPlaybacks,200);
    }
    @RequestMapping("/addProduction")
    public @ResponseBody void addProduction(HttpServletRequest request, HttpServletResponse response, String title, String model, MultipartFile img,MultipartFile production,String context,String categoryId,String subClassId,String isShow) throws Exception {
          if(request.getSession().getAttribute("account")!=null){
                 if(productionService.existsByTitle(title)){
                     ReturnJson.returnJsonString(response,"已存在作品标题",471);
                 }else{
                     Production production1=new Production();
                     production1.setTitle(title);
                     if("0".equals(model)){
                         production1.setModel(Model.child);
                     }else{
                         production1.setModel(Model.adult);
                     }
                     if("0".equals(isShow)){
                         production1.setIsShow(IsShow.PRIVATE);
                     }else{
                         production1.setIsShow(IsShow.PUBLIC);
                     }
                     production1.setContext(context);
                     String realPath=request.getServletContext().getRealPath("\\\\upload\\\\productions" + "\\" + title);
                     String extendName= FileOperation.download(realPath,production);
                     production1.setAddress("http://localhost:8080/showTime/upload/productions/"+title+extendName);
                     realPath=request.getServletContext().getRealPath("\\\\upload\\\\images\\\\productionImgs" + "\\" +title+"Img");
                     extendName=FileOperation.download(realPath,img);
                     production1.setImg("http://localhost:8080/showTime/upload/images/productionImgs/"+title+"Img"+extendName);
                     if(subClassId!=null){
                         Subclass subclass=new Subclass();
                         Category category=new Category();
                         subclass.setId(subClassId);
                         category.setId(categoryId);
                         production1.setCategory(category);
                         production1.setSubclass(subclass);
                     }else{
                         Category category=new Category();
                         category.setId(categoryId);
                         production1.setCategory(category);
                     }
                     User user=new User();
                     user.setAccount((String) request.getSession().getAttribute("account"));
                     production1.setUser(user);
                     productionService.save(production1);
                     production1.setRecommend(Recommend.NO);
                     production1.setState(State.CHECK);
                     ReturnJson.returnJsonString(response,"添加成功",200);
                 }
          }else{
              ReturnJson.returnJsonString(response,"未登录",471);
          }
    }

}

