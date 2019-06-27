package com.showTime.action;

import com.showTime.common.tools.Encryption;
import com.showTime.common.tools.FileOperation;
import com.showTime.common.tools.ReturnJson;
import com.showTime.common.tools.Sex;
import com.showTime.dao.UserDao;
import com.showTime.entity.User;
import com.showTime.service.UserService;
import com.sun.org.apache.regexp.internal.REUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/UserAction")
public class UserAction {
   @Autowired
   UserService userService;
   @RequestMapping("/login")
    public @ResponseBody void  Login(HttpServletRequest request, HttpServletResponse response,String name) throws ServletException, IOException, FileUploadException {
       if(request.getSession().getAttribute("account")!=null){
       ReturnJson.returnJsonString(response,"账户已经登陆",413);//账户已经登陆
       }else {
           String account = (String) request.getParameter("account");
           String loginType = (String) request.getParameter("loginType");
           User user;
           if ("0".equals(loginType)) {
               user = userService.findAllByPhone(account);
           } else {
               user = userService.findAllByMail(account);
           }
           if (user != null) {
               if(user.getState()==3){
                   ReturnJson.returnJsonString(response, "被加入黑名单", 471);
               }else if(user.getState()==4){
                   ReturnJson.returnJsonString(response, "注销", 471);
               }else {
                   String dataBasePassword = user.getPassword();
                   user.setPassword((String) request.getParameter("password"));
                   if (Encryption.checkPassword(user, dataBasePassword)) {
                       request.getSession().setAttribute("account", user.getAccount());
                       //remenberPassword为1时是记住密码，设置cookie
                       if (request.getParameter("remenberPassword").equals("1")) {
                           Cookie cookie = new Cookie("account", user.getAccount());
                           Cookie cookie1 = new Cookie("password", user.getPassword());
                           response.addCookie(cookie);
                           response.addCookie(cookie1);
                       }
                       user.setPassword("");
                       user.setSalt("");
                       Map<String, String> userInfo = new HashMap<String, String>();
                       userInfo.put("account", user.getAccount());
                       userInfo.put("userName", user.getUserName());
                       String year = user.getIdCard().substring(6, 10);
                       int iAge = 0;
                       Calendar cal = Calendar.getInstance();
                       int iCurrYear = cal.get(Calendar.YEAR);
                       iAge = iCurrYear - Integer.valueOf(year);
                       userInfo.put("isAdult", iAge < 18 ? "0" : "1");

                       ReturnJson.returnJsonString(response, userInfo, 200);//账户已经登陆
                   } else {
                       ReturnJson.returnJsonString(response, "密码不正确", 471);
                   }
               }
           } else {
               ReturnJson.returnJsonString(response, "用户不存在", 471);//用户不存在
           }
       }
   }
   @RequestMapping("/register")
    public @ResponseBody void register(HttpServletRequest request,HttpServletResponse response, String account, String idCard, String sex, String realName, String userName, String mail, String phone, String password, MultipartFile iconImage, MultipartFile  idcardImg) throws Exception {
      int status=200;
       if(userService.exists(account)){
        //   return -1;//已有相同账号
           ReturnJson.returnJsonString(response,"已有相同账号",417);
       }else if (userService.existsByIdCard(idCard)){
          // return -2;//已有相同身份证
           ReturnJson.returnJsonString(response,"已有相同身份证",417);
       }else if (userService.existsByMail(mail)){
          // return -3;//已有相同邮箱
           ReturnJson.returnJsonString(response,"已有相同邮箱",417);
       }else if(userService.existsByPhone(phone)){
          // return -4;//已有相同手机
           ReturnJson.returnJsonString(response,"已有相同手机",417);
       }else{
           User user=new User();
           user.setAccount(account);
           user.setUserName(userName);
           user.setRealName(realName);
           user.setIdCard(idCard);
           user.setPassword(password);
           user.setPhone(phone);
           user.setMail(mail);
           Encryption.encryptPassword(user);
           user.setType(1);
           if(sex.equals("0"))
           user.setSex(Sex.MALE);
           else user.setSex(Sex.FEMALE);
//           File uploadediconImage;
//           System.out.println(iconImage.getOriginalFilename());
//           int index=iconImage.getOriginalFilename().lastIndexOf(".");
//           String extendName= iconImage.getOriginalFilename().substring(index);
//           String full = request.getServletContext().getRealPath("\\\\upload\\\\images\\\\headIcon" + "\\" + user.getAccount()+extendName);
//           uploadediconImage = new File(full);
//           iconImage.transferTo(uploadediconImage);
           String realPath=request.getServletContext().getRealPath("\\\\upload\\\\images\\\\headIconImgs" + "\\" + user.getAccount());
           String extendName=FileOperation.download(realPath,iconImage);
           user.setFace("http://localhost:8080/showTime/upload/images/headIconImgs/" + user.getAccount()+extendName);
//           File uploadedidcardImage;
//           index=idcardImg.getOriginalFilename().lastIndexOf(".");
//           extendName= idcardImg.getOriginalFilename().substring(index);
//           full = request.getServletContext().getRealPath("\\\\upload\\\\images\\\\idcardImages" + "\\" + user.getIdCard()+extendName);
//           uploadedidcardImage = new File(full);
//           idcardImg.transferTo(uploadedidcardImage);
           realPath=request.getServletContext().getRealPath("\\\\upload\\\\images\\\\idcardImages" + "\\" + user.getIdCard());
           extendName=FileOperation.download(realPath,idcardImg);
           user.setIdcardImg("http://localhost:8080/showTime/upload/images/idcardImages/" + user.getIdCard()+extendName);
           userService.save(user);
           Map<String,String> userInfo=new HashMap<String,String>();
           userInfo.put("account",account);
           userInfo.put("userName",userName);
           String year = idCard.substring(6, 10);
           int iAge = 0;
           Calendar cal = Calendar.getInstance();
           int iCurrYear = cal.get(Calendar.YEAR);
           iAge = iCurrYear - Integer.valueOf(year);
           userInfo.put("isAdult",iAge<18?"0":"1");
           ReturnJson.returnJsonString(response,userInfo,200);
//           return 1;//上传成功
       }

//       User user = new User();
//       DiskFileItemFactory factory = new DiskFileItemFactory();
//       String fullPath = request.getServletContext()
//                                .getRealPath("\\\\upload\\\\temp");//获取相对路径的绝对路径
//       File repository = new File(fullPath);
//       factory.setRepository(repository);//设置临时文件存放的文件夹
//       // Create a new file upload handler
//       ServletFileUpload upload = new ServletFileUpload(factory);
//       List<FileItem> items = null;//items存放各表单元素
//       items = upload.parseRequest(request);
//       Iterator<FileItem> iter = items.iterator();
//       while (iter.hasNext()) {//遍历表单元素
//           FileItem item = iter.next();
//           if (item.isFormField()) {//非上传文件表单元素
//               if ("account".equals(item.getFieldName()))//获取表单元素的name属性
//               {
//                   user.setAccount(item.getString("UTF-8"));
//               } else if ("idCard".equals(item.getFieldName())) {
//                   user.setIdCard(item.getString("UTF-8"));
//               } else if ("sex".equals(item.getFieldName())) {
//                   if (item.getString("UTF-8").equals("0"))
//                       user.setSex(Sex.MALE);
//                   else user.setSex(Sex.FEMALE);
//               } else if ("idCard".equals(item.getFieldName())) {
//                   user.setIdCard(item.getString("UTF-8"));
//               } else if ("realName".equals(item.getFieldName())) {
//                   user.setRealName(item.getString("UTF-8"));
//               } else if ("userName".equals(item.getFieldName())) {
//                   user.setUserName(item.getString("UTF-8"));
//               } else if ("password".equals(item.getFieldName())) {
//                   user.setPassword(item.getString("UTF-8"));
//                   Encryption.encryptPassword(user);
//               }
//           } else {
//               if ("iconImage".equals(item.getFieldName())) {
//                   File uploadedFile;
//                   int index=item.getName().lastIndexOf(".");
//                   String extendName= item.getName().substring(index);
//                   String full = request.getServletContext().getRealPath("\\\\upload\\\\images\\\\headIcon" + "\\" + user.getAccount()+extendName);
//                   uploadedFile = new File(full);
//                   item.write(uploadedFile);
//                   user.setFace("http://localhost:8080/showTime/upload/images/headIcon/" + user.getAccount()+extendName);
//               } else {
//                   File uploadedidcardImage;
//                   int index=item.getName().lastIndexOf(".");
//                   String extendName= item.getName().substring(index);
//                   String fullIdCard = request.getServletContext().getRealPath("\\\\upload\\\\images\\\\idcardImages" + "\\" + user.getIdCard()+extendName);
//                   uploadedidcardImage = new File(fullIdCard);
//                   item.write(uploadedidcardImage);
//                   user.setIdcardImg("http://localhost:8080/showTime/upload/images/idcardImages/" + user.getIdCard()+extendName);
//               }
//           }
//       }
//           if (!userService.exists(user.getAccount()) && userService.existsIdCard(user.getIdCard()) == 0) {
//               userService.save(user);
//               return 1;//注册成果
//           }
//            return -1;//已有相同账号的用户或者身份证已被注册
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
