package com.showTime.action;

import com.showTime.common.tools.Model;
import com.showTime.entity.Subclass;
import com.showTime.service.SubclassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/SubclassAction")
public class SubclassAction {
    @Autowired
    SubclassService subclassService;
    @RequestMapping("/addSubclass")
    public @ResponseBody int addSubclass(HttpServletRequest request) throws Exception {
            if(request.getSession().getAttribute("type").equals("0")){
                if(subclassService.existsBySubclassName(request.getParameter("categoryName"))){
                    return -2;//已存在子分类名
                }else{
                    Subclass subclass=new Subclass();
                    subclass.setSubclassName(request.getParameter("subclassName"));
//                    if(request.getParameter("model").equals("0"))
//                        subclass.setModel(Model.child);
//                    else
//                        subclass.setModel(Model.adult);
                    subclassService.save(subclass);
                    return 1;//成功添加
                }
            }else{
                return -1;//非管理员，权限不足
            }
}
    @RequestMapping("/deleteSubclass")
    public @ResponseBody int deleteSubclass(HttpServletRequest request) throws Exception {
        if(request.getSession().getAttribute("userType").equals("0")){
            if(subclassService.existsBySubclassName(request.getParameter("subclassName"))){
//                Subclass subclass=subclassService.findAllBySubclassName(request.getParameter("subclassName"));
                 subclassService.delete(request.getParameter("subclassName"));
                 return 1;//成功删除
            }else {
                return -2;//不存在分类名
            }
        }else{
            return -1;//非管理员，权限不足
        }
    }

    @RequestMapping("/updateSubclass")
    public @ResponseBody int updateSubclass(HttpServletRequest request) throws Exception {
        if(request.getSession().getAttribute("userType").equals("0")){
            if(subclassService.existsBySubclassName(request.getParameter("subclassName"))){
                if(subclassService.existsBySubclassName(request.getParameter("newSubclassName"))){
                    return -3;//更改名已存在
                }else {
                    Subclass subclass = subclassService.findAllBySubclassName(request.getParameter("subclassName"));
                    subclass.setSubclassName(request.getParameter("newSubclassName"));
                    subclassService.save(subclass);
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
