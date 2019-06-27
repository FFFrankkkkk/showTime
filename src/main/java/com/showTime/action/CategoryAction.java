package com.showTime.action;

import com.showTime.common.tools.Model;
import com.showTime.common.tools.ReturnJson;
import com.showTime.entity.Category;
import com.showTime.entity.Subclass;
import com.showTime.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/CategoryAction")
public class CategoryAction {
    @Autowired
    CategoryService categoryService;
    @RequestMapping("/getCategoryByModel")
    public @ResponseBody void getCategoryByModel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories=categoryService.findAll();
        ReturnJson.returnJsonString(response,categories,200);
    }
    @RequestMapping("/addCategory")
    public @ResponseBody void   addCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        request.setCharacterEncoding("utf-8");
       if(request.getSession().getAttribute("account")!=null&&((Integer) request.getSession().getAttribute("type"))==0){
           String categoryName=new String(request.getParameter("categoryName").getBytes("iso-8859-1"), "utf-8");
             if(categoryService.existsByCategoryName(categoryName)){
                 ReturnJson.returnJsonString(response,"已存在分类名",471);
             }else{
               Category category=new Category();
               category.setCategoryName(categoryName);
//               if(request.getParameter("model").equals("0"))
//               category.setModel(Model.child);
//               else category.setModel(Model.adult);
               categoryService.save(category);
               ReturnJson.returnJsonString(response,"成功添加",200);

           }
       }else{
           ReturnJson.returnJsonString(response,"非管理员，权限不足",471);
       }
    }

    @RequestMapping("/deleteCategory")
    public @ResponseBody void deleteCategory(HttpServletRequest request,HttpServletResponse response) throws Exception {
        if (request.getSession().getAttribute("userType").equals("0")) {
            if (categoryService.existsByCategoryName(request.getParameter("categoryName"))) {
                categoryService.delete(request.getParameter("categoryName"));
                ReturnJson.returnJsonString(response,"成功删除",200);

            } else {
                ReturnJson.returnJsonString(response,"不存在分类名",471);
            }
        } else {
            ReturnJson.returnJsonString(response,"非管理员，权限不足",471);
        }
    }
    @RequestMapping("/updateCategory")
    public @ResponseBody void updateCategory(HttpServletRequest request,HttpServletResponse response) throws Exception {
        if(request.getSession().getAttribute("userType").equals("0")){
            if(categoryService.existsByCategoryName(request.getParameter("categoryName"))){
                if(categoryService.existsByCategoryName(request.getParameter("newCategoryName"))){
                    ReturnJson.returnJsonString(response,"更改名已存在",471);
                }else {
                    Category category = categoryService.findAllByCategoryName(request.getParameter("subclassName"));
                    category.setCategoryName(request.getParameter("newCategoryName"));
                    categoryService.save(category);
                    ReturnJson.returnJsonString(response,"成功更改",200);
                }
            }else {
                ReturnJson.returnJsonString(response,"不存在分类名",471);
            }
        }else{
            ReturnJson.returnJsonString(response,"非管理员，权限不足",471);
        }
    }
}
