package com.showTime.action;

import com.showTime.common.tools.Model;
import com.showTime.common.tools.ReturnJson;
import com.showTime.entity.Category;
import com.showTime.entity.Subclass;
import com.showTime.service.SubclassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/SubclassAction")
public class SubclassAction {
    @Autowired
    SubclassService subclassService;
    @RequestMapping("/addSubclass")
    public @ResponseBody void addSubclass(HttpServletRequest request, HttpServletResponse response) throws Exception {
            if(request.getSession().getAttribute("account")!=null&&((Integer) request.getSession().getAttribute("type"))==0) {
                String subclassName=new String(request.getParameter("subclassName").getBytes("iso-8859-1"), "utf-8");
                Subclass subclass = subclassService.findAllBySubclassName(subclassName);
                if (subclass == null || !request.getParameter("categoryId").equals(subclass.getCategory().getId())) {
                    Subclass subclass1 = new Subclass();
                    Category category=new Category();
                    category.setId(request.getParameter("categoryId"));
                    subclass1.setSubclassName(subclassName);
                    subclass1.setCategory(category);
                    subclassService.save(subclass1);
                    ReturnJson.returnJsonString(response,"添加成功",200);
                } else {
                    ReturnJson.returnJsonString(response,"该分类下已存在该子分类",471);
                }
            }else{
                ReturnJson.returnJsonString(response,"非管理员，权限不足",471);
            }
}
    @RequestMapping("/deleteSubclass")
    public @ResponseBody void  deleteSubclass(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getSession().getAttribute("userType").equals("0")){
            if(subclassService.existsBySubclassName(request.getParameter("subclassName"))){
//                Subclass subclass=subclassService.findAllBySubclassName(request.getParameter("subclassName"));
                 subclassService.delete(request.getParameter("subclassName"));

                ReturnJson.returnJsonString(response," 删除成功",200);
            }else {
                ReturnJson.returnJsonString(response," 不存在分类名",471);
            }
        }else{
             ReturnJson.returnJsonString(response," 非管理员，权限不足",471);
        }
    }

    @RequestMapping("/updateSubclass")
    public @ResponseBody void updateSubclass(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if(request.getSession().getAttribute("userType").equals("0")){
            if(subclassService.existsBySubclassName(request.getParameter("subclassName"))){
                if(subclassService.existsBySubclassName(request.getParameter("newSubclassName"))){
                    ReturnJson.returnJsonString(response,"更改名已存在",471);
                }else {
                    Subclass subclass = subclassService.findAllBySubclassName(request.getParameter("subclassName"));
                    subclass.setSubclassName(request.getParameter("newSubclassName"));
                    subclassService.save(subclass);
                    ReturnJson.returnJsonString(response,"成功更改",471);
                }
            }else {
                ReturnJson.returnJsonString(response,"不存在分类",471);
            }
        }else{
            ReturnJson.returnJsonString(response,"非管理员，权限不足",471);
        }
    }
}
