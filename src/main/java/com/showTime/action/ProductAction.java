package com.showTime.action;

import com.showTime.common.tools.*;
import com.showTime.dao.ProductionDao;
import com.showTime.entity.*;
import com.showTime.service.ProductionService;
import com.sun.org.apache.bcel.internal.generic.RET;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/ProductAction")
public class ProductAction {
    @Autowired
    private ProductionService productionService;
    public void setSomeItemNull(List<Production> productions){
        for(int i=0;i<productions.size();i++){
            productions.get(i).setSubclass(null);
            productions.get(i).setCategory(null);
            productions.get(i).setRealPath(null);
            productions.get(i).getUser().setSomeItemNull();
        }
    }
    @RequestMapping("/report")
    public @ResponseBody void report(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        String account= (String) request.getSession().getAttribute("account");
        String account=request.getParameter("account");
        if(account==null||"".equals(account)){
            ReturnJson.returnJsonString(response,"未登录",471);
        }else {
            Production production =new Production();
            production.setId(request.getParameter("productionId"));
            User user=new User();
            user.setAccount(account);
//            String reason=request.getParameter("reason");
            Report report=new Report();
            report.setProduction(production);
            report.setUser(user);
            productionService.saveReport(report);
            ReturnJson.returnJsonString(response, "举报成功，等待审核", 200);
        }
    }
    @RequestMapping("/searchProduction")
    public @ResponseBody void searchProduction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String search="%"+new String(request.getParameter("search").getBytes("iso-8859-1"), "utf-8")+"%";
        List<Production> productions;
        if(String.valueOf(request.getSession().getAttribute("userType")).equals("1")) {
            productions = productionService.findAllByTitleLikeOrContextLikeAndModelAndIsShow(search,Model.adult,IsShow.PUBLIC);
        }else{
            productions = productionService.findAllByTitleLikeOrContextLikeAndModelAndIsShow(search,Model.child,IsShow.PUBLIC);
        }
        setSomeItemNull(productions);
        ReturnJson.returnJsonString(response,productions,200);
    }
    @RequestMapping("/getProductionByCategoryId")
    public @ResponseBody void getProductionByCategoryId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryId=request.getParameter("categoryId");
        List<Production> productions;
        if(String.valueOf(request.getSession().getAttribute("userType")).equals("1")) {
            productions = productionService.findAllByCategoryIdAndModelAndIsShow(categoryId,Model.adult,IsShow.PUBLIC);
        }else{
            productions = productionService.findAllByCategoryIdAndModelAndIsShow(categoryId,Model.child,IsShow.PUBLIC);
        }
//        for(int i=0;i<productions.size();i++){
//            productions.get(i).setSubclass(null);
//            productions.get(i).setCategory(null);
//            productions.get(i).setRealPath(null);
//            productions.get(i).getUser().setSomeItemNull();
//        }
        setSomeItemNull(productions);
        ReturnJson.returnJsonString(response,productions,200);
    }
    @RequestMapping("/getProductionById")
    public @ResponseBody void getProductionById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("Id");
        List<Production> productions;
        if(String.valueOf(request.getSession().getAttribute("userType")).equals("1")) {
            productions = productionService.findAllByIdAndModelAndIsShow(id,Model.adult,IsShow.PUBLIC);
        }else{
            productions = productionService.findAllByIdAndModelAndIsShow(id,Model.child,IsShow.PUBLIC);
        }
//        for(int i=0;i<productions.size();i++){
//            productions.get(i).setSubclass(null);
//            productions.get(i).setCategory(null);
//            productions.get(i).setRealPath(null);
//            productions.get(i).getUser().setSomeItemNull();
//        }
        setSomeItemNull(productions);
        ReturnJson.returnJsonString(response,productions,200);
    }
    @RequestMapping("/getProductionBySubclassId")
    public @ResponseBody void getProductionBySubclassId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subckassId=request.getParameter("subclassId");
        List<Production> productions;
        if(String.valueOf(request.getSession().getAttribute("userType")).equals("1")) {
            productions = productionService.findAllBySubclassIdAndModelAndIsShow(subckassId,Model.adult,IsShow.PUBLIC);
        }else{
            productions = productionService.findAllBySubclassIdAndModelAndIsShow(subckassId,Model.child,IsShow.PUBLIC);
        }
//        for(int i=0;i<productions.size();i++){
//            productions.get(i).setSubclass(null);
//            productions.get(i).setCategory(null);
//            productions.get(i).setRealPath(null);
//            productions.get(i).getUser().setSomeItemNull();
//        }
        setSomeItemNull(productions);
        ReturnJson.returnJsonString(response,productions,200);
    }
    @RequestMapping("/getRecommendProduction")
    public @ResponseBody void getRecommendProduction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Production> productions=productionService.getRecommendProduction((String) request.getSession().getAttribute("userType"));
        String categoryId=request.getParameter("categoryId");
        List<Production> productions;
        if(String.valueOf(request.getSession().getAttribute("userType")).equals("1")) {
           productions = productionService.findAllByRecommendAndModelAndIsShow(Recommend.YES,Model.adult,IsShow.PUBLIC,categoryId);
        }else{
            productions = productionService.findAllByRecommendAndModelAndIsShow(Recommend.YES,Model.child,IsShow.PUBLIC,categoryId);
        }
//       for(int i=0;i<productions.size();i++){
//           productions.get(i).setSubclass(null);
//           productions.get(i).setCategory(null);
//           productions.get(i).setRealPath(null);
////           productions.get(i).setUser(null);
//           productions.get(i).getUser().setSomeItemNull();
//       }
        setSomeItemNull(productions);
        ReturnJson.returnJsonString(response,productions,200);
    }
    @RequestMapping("/getHotProduction")
    public @ResponseBody void getHotProduction(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
//        List<Production> highPlaybacks=productionService.getHotProduction((String) request.getSession().getAttribute("userType"));
        String categoryId=request.getParameter("categoryId");
        List<Production> productions;
        if(String.valueOf(request.getSession().getAttribute("userType")).equals("1")) {
            productions = productionService.findAllByModelAndIsShowOrderByAccountDesc(Model.adult,IsShow.PUBLIC,categoryId);
        }else{
            productions = productionService.findAllByModelAndIsShowOrderByAccountDesc(Model.child,IsShow.PUBLIC,categoryId);
        }
        User user;
        for(int i=0;i<productions.size();i++){
            user =new User();
//            productions.get(i).setSubclass(null);
//            productions.get(i).setCategory(null);
//            productions.get(i).setUser(null);
//            user.setAccount(  productions.get(i).getUser().getAccount());
//            user.setFace(  productions.get(i).getUser().getFace());
//            user.setUserName(  productions.get(i).getUser().getUserName());
//            productions.get(i).setUser(user);
//            productions.get(i).setRealPath(null);
//            productions.get(i).getUser().setSomeItemNull();
        }
        setSomeItemNull(productions);
        ReturnJson.returnJsonString(response,productions,200);
    }
    @RequestMapping("/addProduction")
    public @ResponseBody void addProduction(HttpServletRequest request, HttpServletResponse response, String account,String title, String model, MultipartFile img,MultipartFile production,String context,String categoryId,String subClassId,String isShow) throws Exception {
        //        String account= (String) request.getSession().getAttribute("account");

            if(account!=null){
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
                     String randomName=FileOperation.getRandomFileNameByCurrentTime();
                     production1.setContext(context);
                     String realPath=request.getServletContext().getRealPath("\\\\upload\\\\productions" + "\\" + randomName);
                     String extendName= FileOperation.download(realPath,production);
                     production1.setRealPath(request.getServletContext().getRealPath("\\\\upload\\\\productions" + "\\" + randomName+extendName));
//                     production1.setAddress("http://localhost:8080/showTime/upload/productions/"+randomName+extendName);
                     production1.setAddress("upload/productions/"+randomName+extendName);
                     realPath=request.getServletContext().getRealPath("\\\\upload\\\\images\\\\productionImgs" + "\\" +randomName+"Img");
                     extendName=FileOperation.download(realPath,img);
//                     production1.setImg("http://localhost:8080/showTime/upload/images/productionImgs/"+randomName+"Img"+extendName);
                     production1.setImg("upload/images/productionImgs/"+randomName+"Img"+extendName);
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
//                     user.setAccount((String) request.getSession().getAttribute("account"));
                     user.setAccount(account);
                     production1.setUser(user);
                     production1.setRecommend(Recommend.NO);
                     production1.setState(State.CHECK);
                     productionService.save(production1);;
                     ReturnJson.returnJsonString(response,"添加成功",200);
                 }
          }else{
              ReturnJson.returnJsonString(response,"未登录",471);
          }
    }
    @RequestMapping("/productionPlayer")
    public @ResponseBody void productionPlayer(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String address=request.getParameter("address");//作品流地址
        Production production=productionService.findAllByAddress(address);
        File productionFile=new File(production.getRealPath());
//      FileUtils.copyFile(production,response.getOutputStream());
        FileUtils.copyFile(productionFile,response.getOutputStream());
     }
}

