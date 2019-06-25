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
        List<Category> categories=categoryService.getCategoryByModel((String) request.getSession().getAttribute("userType"));
        ReturnJson.returnJsonString(response,categories,200);
    }
    @RequestMapping("/addCategory")
    public @ResponseBody int  addCategory(HttpServletRequest request) throws Exception {
       if(request.getSession().getAttribute("userType").equals("0")){
             if(categoryService.existsByCategoryName(request.getParameter("categoryName"))){
                  return -2;//已存在分类名
             }else{
               Category category=new Category();
               category.setCategoryName(request.getParameter("categoryName"));
               if(request.getParameter("model").equals("0"))
               category.setModel(Model.child);
               else category.setModel(Model.adult);
               categoryService.save(category);
               return 1;//成功添加
           }
       }else{
           return -1;//非管理员，权限不足
       }
    }

    @RequestMapping("/deleteCategory")
    public @ResponseBody int deleteCategory(HttpServletRequest request) throws Exception {
        if (request.getSession().getAttribute("userType").equals("0")) {
            if (categoryService.existsByCategoryName(request.getParameter("categoryName"))) {
                categoryService.delete(request.getParameter("categoryName"));
                return 1;//成功删除
            } else {
                return -2;//不存在分类名
            }
        } else {
            return -1;//非管理员，权限不足
        }
    }
    @RequestMapping("/updateCategory")
    public @ResponseBody int updateCategory(HttpServletRequest request) throws Exception {
        if(request.getSession().getAttribute("userType").equals("0")){
            if(categoryService.existsByCategoryName(request.getParameter("categoryName"))){
                if(categoryService.existsByCategoryName(request.getParameter("newCategoryName"))){
                    return -3;//更改名已存在
                }else {
                    Category category = categoryService.findAllByCategoryName(request.getParameter("subclassName"));
                    category.setCategoryName(request.getParameter("newCategoryName"));
                    categoryService.save(category);
                    return 1;//成功更改
                }
            }else {
                return -2;//不存在分类名
            }
        }else{
            return -1;//非管理员，权限不足
        }
    }
}
