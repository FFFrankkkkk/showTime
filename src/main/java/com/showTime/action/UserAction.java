package com.showTime.action;

import com.showTime.common.tools.Encryption;
import com.showTime.common.tools.ReturnJson;
import com.showTime.common.tools.Sex;
import com.showTime.dao.UserDao;
import com.showTime.entity.User;
import com.showTime.service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/UserAction")
public class UserAction {
   @Autowired
   UserService userService;
   @RequestMapping("/login")
    public @ResponseBody void  Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileUploadException {
//       if(request.getSession().getAttribute("account")!=null){
//       ReturnJson.returnJsonString(response,"-1");//账户已经登陆
//       }
//       String account= (String) request.getAttribute("account");
//        if(userService.exists(account)){
//             User user=userService.findPasswordByAccount(account);
//             String dataBasePassword=user.getPassword();
//             user.setPassword((String) request.getAttribute("password"));
//             if(Encryption.checkPassword(user,dataBasePassword)){
//                 user.setPassword("");
//                 user.setSalt("");
//                 ReturnJson.returnJsonString(response,user);//账户已经登陆
//             }
//        }else{
//            ReturnJson.returnJsonString(response,"-2");//用户不存在
//        }
       DiskFileItemFactory factory = new DiskFileItemFactory();
       String fullPath=request.getServletContext()
                              .getRealPath("\\\\upload\\\\temp");//获取相对路径的绝对路径
       File repository = new File(fullPath);
       factory.setRepository(repository);//设置临时文件存放的文件夹
       // Create a new file upload handler
       ServletFileUpload upload = new ServletFileUpload(factory);
       List<FileItem> items = null;//items存放各表单元素
       items = upload.parseRequest(request);
       Iterator<FileItem> iter = items.iterator();
       while (iter.hasNext()) {//遍历表单元素
           FileItem item = iter.next();
           if (item.isFormField()) {//非上传文件表单元素
               if ("name".equals(item.getFieldName()))//获取表单元素的name属性
               {
                   System.out.println(item.getString("UTF-8"));
               }
           }
           }
   }
   @RequestMapping("/register")
    public @ResponseBody int register(HttpServletRequest request) throws Exception {
       User user = new User();
       DiskFileItemFactory factory = new DiskFileItemFactory();
       String fullPath = request.getServletContext()
                                .getRealPath("\\\\upload\\\\temp");//获取相对路径的绝对路径
       File repository = new File(fullPath);
       factory.setRepository(repository);//设置临时文件存放的文件夹
       // Create a new file upload handler
       ServletFileUpload upload = new ServletFileUpload(factory);
       List<FileItem> items = null;//items存放各表单元素
       items = upload.parseRequest(request);
       Iterator<FileItem> iter = items.iterator();
       while (iter.hasNext()) {//遍历表单元素
           FileItem item = iter.next();
           if (item.isFormField()) {//非上传文件表单元素
               if ("account".equals(item.getFieldName()))//获取表单元素的name属性
               {
                   user.setAccount(item.getString("UTF-8"));
               } else if ("idCard".equals(item.getFieldName())) {
                   user.setIdCard(item.getString("UTF-8"));
               } else if ("sex".equals(item.getFieldName())) {
                   if (item.getString("UTF-8").equals("0"))
                       user.setSex(Sex.MALE);
                   else user.setSex(Sex.FEMALE);
               } else if ("idCard".equals(item.getFieldName())) {
                   user.setIdCard(item.getString("UTF-8"));
               } else if ("realName".equals(item.getFieldName())) {
                   user.setRealName(item.getString("UTF-8"));
               } else if ("userName".equals(item.getFieldName())) {
                   user.setUserName(item.getString("UTF-8"));
               } else if ("password".equals(item.getFieldName())) {
                   user.setPassword(item.getString("UTF-8"));
                   Encryption.encryptPassword(user);
               }
           } else {
               if ("iconImage".equals(item.getFieldName())) {
                   File uploadedFile;
                   int index=item.getName().lastIndexOf(".");
                   String extendName= item.getName().substring(index);
                   String full = request.getServletContext().getRealPath("\\\\upload\\\\images\\\\headIcon" + "\\" + user.getAccount()+index);
                   uploadedFile = new File(full);
                   item.write(uploadedFile);
                   user.setFace("http://localhost:8080/showTime/upload/images/headIcon/" + user.getAccount());
               } else {
                   File uploadedidcardImage;
                   int index=item.getName().lastIndexOf(".");
                   String extendName= item.getName().substring(index);
                   String fullIdCard = request.getServletContext().getRealPath("\\\\upload\\\\images\\\\idcardImages" + "\\" + user.getIdCard()+index);
                   uploadedidcardImage = new File(fullIdCard);
                   item.write(uploadedidcardImage);
                   user.setIdcardImg("http://localhost:8080/showTime/upload/images/idcardImages/" + user.getIdCard());
               }
           }
       }
           if (!userService.exists(user.getAccount()) && userService.existsIdCard(user.getIdCard()) == 0) {
               userService.save(user);
               return 1;//注册成果
           }
            return -1;//已有相同账号的用户或者身份证已被注册
   }
//      String account = (String) request.getAttribute("account");
//      String idCard= (String) request.getAttribute("idCard");
//      if (!userService.exists(account)&&userService.existsIdCard(idCard)==0) {
//         User user=new User();
//         if(request.getSession().getAttribute("sex").equals("0"))
//         user.setSex(Sex.MALE);
//         else  user.setSex(Sex.FEMALE);
//         user.setType(1);
//
//         user.setRealName((String) request.getAttribute("realName"));
//         user.setUserName ((String) request.getAttribute("userName"));
//         user.setPassword((String) request.getAttribute("password"));
//         user.setIdCard (idCard);
//         //加盐加密
//         Encryption.encryptPassword(user);
//         //上传头像
//         FileItem iconImage = (FileItem) request.getAttribute("iconImage");
//         File uploadedFile;
//         String full = request.getServletContext().getRealPath("\\\\upload\\\\images\\\\headIcon" + "\\" + account);
//         uploadedFile = new File(full);
//         iconImage.write(uploadedFile);
//         user.setFace("http://localhost:8080/showTime/upload/images/headIcon/" + account);
//         //上传身份证
//         FileItem idcardImage = (FileItem) request.getAttribute("idcardImage");
//         File uploadedidcardImage;
//         String fullIdCard = request.getServletContext().getRealPath("\\\\upload\\\\images\\\\idcardImages" + "\\" + request.getAttribute("idCard"));
//         uploadedidcardImage = new File(fullIdCard);
//         idcardImage.write(uploadedidcardImage);
//         user.setFace("http://localhost:8080/showTime/upload/images/idcardImages/" +idCard);
//         userService.save(user);
//         return 1;//注册成果
//      }
//   }

}
