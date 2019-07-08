package com.showTime.action;

import com.showTime.common.tools.*;
import com.showTime.dao.UserDao;
import com.showTime.entity.Follow;
import com.showTime.entity.Production;
import com.showTime.entity.User;
import com.showTime.service.UserService;
import com.sun.org.apache.regexp.internal.REUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.RandomStringUtils;
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
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/UserAction")
public class UserAction {

   @Autowired
   UserService userService;
    public void SendMail(String mail,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
            String mailVerificationCode = RandomStringUtils.randomAlphanumeric(10);
            try {
                SendMail.sendMail(mail, mailVerificationCode, "showTime");
            } catch (Exception e) {
                ReturnJson.returnJsonString(response, "发送失败", 471);
            }
            request.getSession().setAttribute(mail, mailVerificationCode);
            ReturnJson.returnJsonString(response, "发送成功", 200);
        }
    @RequestMapping("/getMessage")
    public @ResponseBody void getMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        Map<String, String> message = new HashMap<String, String>();
        Map<String, String> message1 = new HashMap<String, String>();
        Map<String, String> message2 = new HashMap<String, String>();
        List<Map<String, String>> mapList= new ArrayList<Map<String, String>>();
        message.put("messageId","1");
        message.put("title","管理员的问候");
        message.put("context","欢迎你");
        message.put("time:",dateFormat.format(date));
        message1.put("messageId","2");
        message1.put("title","好");
        message1.put("context","好消息好消息");
        message1.put("time:",dateFormat.format(date));
        message2.put("messageId","3");
        message2.put("title","rrr");
        message2.put("context","垃圾信息");
        message2.put("time:",dateFormat.format(date));
        mapList.add(message);
        mapList.add(message1);
        mapList.add(message2);
        ReturnJson.returnJsonString(response, mapList, 200);

    }
    @RequestMapping("/getDynamic")
     public @ResponseBody void getDynamic(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //        String account= (String) request.getSession().getAttribute("account");
        String account=request.getParameter("account");
        if(account==null||"".equals(account)){
            ReturnJson.returnJsonString(response, "未登录", 471);
        }else{
//            String followAcount=request.getParameter("account");
            List<Follow> follows=userService.findAllByAccount(account);
            List<User>  users=new ArrayList<User>();
            for(int i=0;i<follows.size();i++){
                User user=new User();
                user=userService.findByAccount(follows.get(i).getFollowAccount());
                user.setSomeItemNull2();
                for(int j=0;j<user.getProductionList().size();j++){
                    user.getProductionList().get(j).setSubclass(null);
                    user.getProductionList().get(j).setCategory(null);
                    user.getProductionList().get(j).setRealPath(null);
                    user.getProductionList().get(j).setUser(null);
                }
                users.add(user);
            }
            ReturnJson.returnJsonString(response, users, 200);
        }
    }
    @RequestMapping("/followUser")
    public @ResponseBody void followUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //        String account= (String) request.getSession().getAttribute("account");
        String account=request.getParameter("account");
        if(account==null||"".equals(account)){
            ReturnJson.returnJsonString(response, "0", 471);//未登录
        }else {
            try {
                String followAcount = request.getParameter("followAccount");
                Follow follow = new Follow();
                follow.setAccount(account);
                follow.setFollowAccount(followAcount);
                userService.saveFollow(follow);
                ReturnJson.returnJsonString(response, "1", 200);//关注成功
            }catch (Exception e){
                ReturnJson.returnJsonString(response, "-1", 200);//关注失败
            }
        }
    }
    @RequestMapping("/getFans")
    public @ResponseBody void getFans(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //        String account= (String) request.getSession().getAttribute("account");
        String account=request.getParameter("account");
        if(account==null||"".equals(account)){
            ReturnJson.returnJsonString(response, "未登录", 471);
        }else{
            List<Follow> follows=userService.findAllByFollowAccount(account);
            List<User>  users=new ArrayList<User>();
            for(int i=0;i<follows.size();i++){
                User user=new User();
                user=userService.findByAccount(follows.get(i).getAccount());
                user.setSomeItemNull();
                users.add(user);
            }
            ReturnJson.returnJsonString(response, users, 200);
        }
    }
    @RequestMapping("/getFollowUser")
    public @ResponseBody void getFollowUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //        String account= (String) request.getSession().getAttribute("account");
        String account=request.getParameter("account");
        if(account==null||"".equals(account)){
            ReturnJson.returnJsonString(response, "未登录", 471);
        }else{
//            String followAcount=request.getParameter("account");
            List<Follow> follows=userService.findAllByAccount(account);
            List<User>  users=new ArrayList<User>();
            for(int i=0;i<follows.size();i++){
                User user=new User();
                user=userService.findByAccount(follows.get(i).getFollowAccount());
                user.setSomeItemNull();
                users.add(user);
            }
            ReturnJson.returnJsonString(response, users, 200);
        }
    }
    @RequestMapping("/cancelFollowUser")
    public @ResponseBody void cancelFollowUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //        String account= (String) request.getSession().getAttribute("account");
        try {
            String account = request.getParameter("account");
            String followaccount = request.getParameter("followAccount");
            Follow follow = userService.findAllByAccountAndFollowAccount(account, followaccount);
            userService.deleteFollow(follow);
            ReturnJson.returnJsonString(response, "1", 200);//已取消
        }catch (Exception e){
            ReturnJson.returnJsonString(response, "-1", 200);//失败
        }
    }
    @RequestMapping("/isFollow")
    public @ResponseBody void isFollow(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String userAccount= (String) request.getSession().getAttribute("account");
          String userAccount=request.getParameter("account");
        if(userAccount==null||"".equals(userAccount)) {
            ReturnJson.returnJsonString(response, "0", 200);//未登录
        }else{
            String account=request.getParameter("otheraccount");
            boolean isExists=userService.existsByAccountAndFollowAccount(userAccount,account);
            if (isExists)
                ReturnJson.returnJsonString(response, "1", 200);//已关注
            else
                ReturnJson.returnJsonString(response, "0", 200);//未关注
        }
    }
    @RequestMapping("/countFollow")
    public @ResponseBody void countfollow(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String account= (String) request.getSession().getAttribute("account");
        String account=request.getParameter("account");
        int number=userService.countAllByFollowAccount(account);
        ReturnJson.returnJsonString(response,number, 200);
    }
   @RequestMapping("/getOtherUserInformation")
   public @ResponseBody void getOtherUserInformation(HttpServletRequest request, HttpServletResponse response) throws Exception {
       String account=request.getParameter("account");
       User user=userService.findByAccount(account);
       user.setSomeItemNull2();
       user.setProductionListsomeItemNull();
       ReturnJson.returnJsonString(response, user, 200);
   }

    @RequestMapping("/getMyInformation")
    public @ResponseBody void getMyInformation(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String account= (String) request.getSession().getAttribute("account");
        String account=request.getParameter("account");
        User user=userService.findById(account);
        user.setPassword(null);
        user.setSalt(null);
        user.setProductionListsomeItemNull();
//        for(int i=0;i<user.getProductionList().size();i++){
//            user.getProductionList().get(i).setSubclass(null);
//            user.getProductionList().get(i).setCategory(null);
//            user.getProductionList().get(i).setRealPath(null);
//            user.getProductionList().get(i).setUser(null);
//        }
        ReturnJson.returnJsonString(response, user, 200);
    }
   @RequestMapping("/sendMailVerificationCode")
   public @ResponseBody void sendMailVerificationCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String type=request.getParameter("type");
       String mail = request.getParameter("mail");
       if("0".equals(type)) {//注册验证码
           if (!userService.existsByMail(mail)) {
              SendMail(mail,request,response);
           } else {
               ReturnJson.returnJsonString(response, "邮箱已注册", 471);
           }
       }else {//重改密码验证码
           if (userService.existsByMail(mail)) {
               SendMail(mail,request,response);
           } else {
               ReturnJson.returnJsonString(response, "邮箱未注册", 471);
           }
       }
   }
   @RequestMapping("/completeInformation")
   public @ResponseBody void completeInformation(HttpServletRequest request, HttpServletResponse response,String realName,String idCard,MultipartFile  idcardImg) throws Exception {
//        String account= (String) request.getSession().getAttribute("account");
       String account=request.getParameter("account");
       if(account==null||"".equals(account)){
            ReturnJson.returnJsonString(response, "账号未登录", 471);
        }else{
            if(userService.existsByIdCard(idCard)){
                ReturnJson.returnJsonString(response, "身份证已存在", 471);
            }else {
                try {
                    User user = userService.findById(account);
                    File uploadedidcardImage;
                    int index = idcardImg.getOriginalFilename().lastIndexOf(".");
                    String extendName = idcardImg.getOriginalFilename().substring(index);
                    String full = request.getServletContext().getRealPath("\\\\upload\\\\images\\\\idcardImages" + "\\" + idCard + extendName);
                    uploadedidcardImage = new File(full);
                    idcardImg.transferTo(uploadedidcardImage);
                    String realPath = request.getServletContext().getRealPath("\\\\upload\\\\images\\\\idcardImages" + "\\" + idCard);
//                    extendName = FileOperation.download(realPath, idcardImg);
                    user.setIdcardImg("upload/images/idcardImages/" + idCard+ extendName);
                    user.setState(1);
                    user.setIdCard(idCard);
                    user.setRealName(realName);
                    userService.update(user);
                    ReturnJson.returnJsonString(response, "上传成功", 200);
                }catch (Exception e){
                    ReturnJson.returnJsonString(response, "上传失败", 471);
                }
            }
        }
   }
    @RequestMapping("/changeInformation")
    public @ResponseBody void changeInformation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //        String account= (String) request.getSession().getAttribute("account");
            String account=request.getParameter("account");
        if(account==null||"".equals(account)){
            ReturnJson.returnJsonString(response, "账号未登录", 471);
        }else{
             User user=userService.findByAccount(account);
             String sex=request.getParameter("sex");
             if(sex.equals("0")){
                 user.setSex(Sex.MALE);
             }else{
                 user.setSex(Sex.FEMALE);
             }
             user.setUserName(new String(request.getParameter("userName").getBytes("iso-8859-1"), "utf-8"));
             user.setPhone(request.getParameter("phone"));
             userService.update(user);
            ReturnJson.returnJsonString(response, "更改成功", 200);

        }
    }
   @RequestMapping("/reSetPassword")
   public @ResponseBody void reSetPassword(HttpServletRequest request, HttpServletResponse response) throws Exception {
       String mailVerificationCode=request.getParameter("mailVerificationCode");
       String mail=request.getParameter("mail");
       if(mailVerificationCode==null||mailVerificationCode.equals("")){
           ReturnJson.returnJsonString(response,"请填邮箱验证码",471);
       }else{
//       else if(mailVerificationCode.equals(request.getSession().getAttribute(mail))) {
           User user=userService.findAllByMail(mail);
           String newPassword=request.getParameter("newPassword");
           user.setPassword(newPassword);
           Encryption.encryptPassword(user);
           userService.update(user);
           ReturnJson.returnJsonString(response,"更改密码成功",200);

       }
//       else {
//           ReturnJson.returnJsonString(response,"箱验证码不正确",471);
//       }
   }
   @RequestMapping("/exit")
   public  @ResponseBody void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileUploadException{
       request.getSession().removeAttribute("account");
       ReturnJson.returnJsonString(response,"注销成功",200);//账户已经登陆

   }
   @RequestMapping("/login")
    public @ResponseBody void  Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileUploadException {
       if(request.getSession().getAttribute("account")!=null){
       ReturnJson.returnJsonString(response,"账户已经登陆",471);//账户已经登陆
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
//                       if (request.getParameter("remenberPassword").equals("1")) {
//                           Cookie cookie = new Cookie("account", user.getAccount());
//                           Cookie cookie1 = new Cookie("password", user.getPassword());
//                           response.addCookie(cookie);
//                           response.addCookie(cookie1);
//                       }
                       user.setPassword("");
                       user.setSalt("");
                       Map<String, String> userInfo = new HashMap<String, String>();
                       userInfo.put("account", user.getAccount());
                       userInfo.put("face",user.getFace());
                       userInfo.put("userName", user.getUserName());
                       if(user.getState()==2) {
                           String year = user.getIdCard().substring(6, 10);
                           int iAge = 0;
                           Calendar cal = Calendar.getInstance();
                           int iCurrYear = cal.get(Calendar.YEAR);
                           iAge = iCurrYear - Integer.valueOf(year);
                           userInfo.put("isAdult", iAge < 18 ? "0" : "1");
                       }else {
                           userInfo.put("isAdult", "1");
                       }
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
    public @ResponseBody void register(HttpServletRequest request,HttpServletResponse response) throws Exception {
//       String account, String idCard, String sex, String realName, String userName, String mail, String phone, String password, MultipartFile iconImage, MultipartFile  idcardImg,String mailVerificationCode
       String userName=new String(request.getParameter("userName").getBytes("iso-8859-1"), "utf-8");
       String mail=request.getParameter("mail");
       String mailVerificationCode=request.getParameter("mailVerificationCode");
       String password=request.getParameter("password");
       if(mailVerificationCode==null||mailVerificationCode.equals("")) {
           ReturnJson.returnJsonString(response, "请填邮箱验证码", 471);
//       }else if(mailVerificationCode.equals(request.getSession().getAttribute(mail))) {
       }else {
           User user = new User();
//           if(account!=null){
//           if (userService.exists(account)) {
//               //   return -1;//已有相同账号
//               ReturnJson.returnJsonString(response, "已有相同账号", 417);
//           }else{
//               user.setAccount(account);
//           }
//           } else if (userService.existsByIdCard(idCard)) {
//               // return -2;//已有相同身份证
//               ReturnJson.returnJsonString(response, "已有相同身份证", 417);
//           }
//       else if (userService.existsByMail(mail)){
//          // return -3;//已有相同邮箱
//           ReturnJson.returnJsonString(response,"已有相同邮箱",417);
//       }
//           else if (userService.existsByPhone(phone)) {
//               // return -4;//已有相同手机
//               ReturnJson.returnJsonString(response, "已有相同手机", 417);
//           } else {
//               user.setAccount(account);
               user.setUserName(userName);
//               user.setRealName(realName);
//               user.setIdCard(idCard);
               user.setPassword(password);
//               user.setPhone(phone);
               user.setMail(mail);
               Encryption.encryptPassword(user);
               user.setType(1);
//               if (sex.equals("0"))
//                   user.setSex(Sex.MALE);
//               else user.setSex(Sex.FEMALE);
//           File uploadediconImage;
//           System.out.println(iconImage.getOriginalFilename());
//           int index=iconImage.getOriginalFilename().lastIndexOf(".");
//           String extendName= iconImage.getOriginalFilename().substring(index);
//           String full = request.getServletContext().getRealPath("\\\\upload\\\\images\\\\headIcon" + "\\" + user.getAccount()+extendName);
//           uploadediconImage = new File(full);
//           iconImage.transferTo(uploadediconImage);
//               String realPath = request.getServletContext().getRealPath("\\\\upload\\\\images\\\\headIconImgs" + "\\" + user.getAccount());
//               String realPath = request.getServletContext().getRealPath("\\\\upload\\\\images\\\\headIconimgs" + "\\" + user.getAccount());
//               String extendName = FileOperation.download(realPath, iconImage);
//               user.setFace("http://localhost:8080/showTime/upload/images/headIconImgs/" + user.getAccount() + extendName);
               user.setFace("upload/images/headIconimgs/0.jpg");
//           File uploadedidcardImage;
//           index=idcardImg.getOriginalFilename().lastIndexOf(".");
//           extendName= idcardImg.getOriginalFilename().substring(index);
//           full = request.getServletContext().getRealPath("\\\\upload\\\\images\\\\idcardImages" + "\\" + user.getIdCard()+extendName);
//           uploadedidcardImage = new File(full);
//           idcardImg.transferTo(uploadedidcardImage);
//               realPath = request.getServletContext().getRealPath("\\\\upload\\\\images\\\\idcardImages" + "\\" + user.getIdCard());
//               extendName = FileOperation.download(realPath, idcardImg);
//               user.setIdcardImg("http://localhost:8080/showTime/upload/images/idcardImages/" + user.getIdCard() + extendName);
//               user.setIdcardImg("upload/images/idcardImages/" + user.getIdCard() + extendName);
               userService.save(user);
               Map<String, String> userInfo = new HashMap<String, String>();
               User user1=userService.findAllByMail(mail);
               userInfo.put("account", user1.getAccount());
               userInfo.put("userName", userName);
//               String year = idCard.substring(6, 10);
//               int iAge = 0;
//               Calendar cal = Calendar.getInstance();
//               int iCurrYear = cal.get(Calendar.YEAR);
//               iAge = iCurrYear - Integer.valueOf(year);
//               userInfo.put("isAdult", iAge < 18 ? "0" : "1");
               userInfo.put("isAdult","0");
               ReturnJson.returnJsonString(response, userInfo, 200);
//           return 1;//上传成功
//           }
       }
//       else{
//           ReturnJson.returnJsonString(response, "邮箱验证码不正确", 471);
//       }

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
