package com.example.demo.controller;


import com.example.demo.jwt.LoginToken;
import com.example.demo.mapper.*;
import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

/**
 * 测试控制器
 */
@RestController 
public class Hello {
    @Autowired
    UserMap userMap;
    @Autowired
    AdoptMap adoptMap;
    @Autowired
    AdopterMap adopterMap;
    @Autowired
    CommentMap commentMap;
    @Autowired
    LikeMap likeMap;

    @ResponseBody
    @RequestMapping("/register")
    public User regist(User userEntity, HttpServletRequest request) {
        Example<User> example = Example.of(userEntity);
        if (userMap.exists(example)) {
            return userEntity;
        } else {
            User user = userMap.save(userEntity);
            return user;
        }
    }

    @ResponseBody
    @RequestMapping("/login")
    public User login(User userEntity, HttpServletRequest request) {
        Example<User> userExample = Example.of(userEntity);
        Optional<User> userOptional = userMap.findOne(userExample);

        return userOptional.get();
    }

    @ResponseBody
    @RequestMapping("/getuser")
    public User getuser(@RequestParam(name = "id") Integer id) {
        User user = userMap.findById(id).get();
        user.setPassword("");
        return user;
    }


    @RequestMapping("/myadmin")
    public String admin(@RequestParam(name = "name")String name,@RequestParam(name = "password") String password) {
        if(name.equals("admin")&&password.equals("root")){
            return "ok";
        }else {
            return  "no";
        }

    }
    @LoginToken
    @ResponseBody
    @RequestMapping("/updateuser")
    public User updateuser(User userEntity, HttpServletRequest request) {
        User user = new User();
        //  user.setUsername(userEntity.username);;
        user.setPassword(userEntity.password);
        user.id = userEntity.id;
        System.out.println(userEntity);
        Example<User> userExample = Example.of(user);
        Optional<User> userOptional = userMap.findOne(userExample);
        userOptional.get().setEmail(userEntity.email);
        userOptional.get().setPhone(userEntity.phone);
        userOptional.get().setImg(userEntity.img);
        userOptional.get().setUsername(userEntity.username);
        userMap.save(userOptional.get());
        return userEntity;
    }

    @ResponseBody
    @RequestMapping("/adopt")
    public List<Adoption> adopt(@RequestParam(name = "id") Integer id, @RequestParam(name = "like") Integer like) {
        if (id == -1) {
            return adoptMap.findAll();
        } else {
            if (like == 0) {
                List<Integer> list = new ArrayList<>();
                list.add(id);
                return adoptMap.findAllById(list);
            } else {
                Like like1 = new Like();
                like1.setUserid(id);
                Example<Like> example = Example.of(like1);
                List<Like> likes = likeMap.findAll(example);
                System.out.println(likes);
                List<Integer> list = new ArrayList<>();
                for (Like l : likes
                ) {
                    list.add(l.getAdid());
                }
                System.out.println(list);
                return adoptMap.findAllById(list);
            }
        }
    }


    @ResponseBody
    @RequestMapping("/radopt")
    public Adoption radopt() {
        Random random = new Random();
        List<Adoption> adoptionList = adoptMap.findAll();
        int n = random.nextInt(adoptionList.size());
        return adoptionList.get(n);
    }


    @ResponseBody
    @RequestMapping("/adopter")
    public List<Adopter> adopter(@RequestParam(name = "id") Integer id) {
        if (id == -1) {
            return adopterMap.findAll();
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(id);
            return adopterMap.findAllById(list);
        }
    }

    @ResponseBody
    @RequestMapping("/getadopt")
    public List<Adoption> getadopt(User user) {
        Adoption adoption = new Adoption();
//        Example<User> userExample=Example.of(user);
//        Optional<User> userOptional= userMap.findOne(userExample);
        adoption.setUserid(user.id);
        Example<Adoption> adoptionExample = Example.of(adoption);
        return adoptMap.findAll(adoptionExample);
    }

    @ResponseBody
    @RequestMapping("/getadopter")
    public List<Adopter> getadopter(@RequestParam(name = "id") Integer id) {
        Adopter adopter = new Adopter();
        adopter.setUserid(id);
        Example<Adopter> adopterExample = Example.of(adopter);
        return adopterMap.findAll(adopterExample);
    }

    @ResponseBody
    @RequestMapping("/add_comment")
    public Comment add_comment(Comment comment, HttpServletRequest request) {

        System.out.println(comment);
        return commentMap.save(comment);
    }

    @ResponseBody
    @RequestMapping("/comment")
    public List<Comment> comment() {
        return commentMap.findAll();
    }

    @ResponseBody
    @RequestMapping("/add_adopt")
    public Adoption add_adopt(Adoption adoption, HttpServletRequest request) {

        System.out.println(adoption);
        return adoptMap.save(adoption);
    }


    @ResponseBody
    @RequestMapping("/add_adopter")
    public Adopter add_adopter(Adopter adopter, HttpServletRequest request) {
        return adopterMap.save(adopter);
    }

    //这里使用@RequestMapping注解表示该方法对应的二级上下文路径
    @ResponseBody
    @RequestMapping("like")
    public Like like(Like like) {
        Example<Like> example = Example.of(like);
        if (likeMap.exists(example)) {
        } else {
            return likeMap.save(like);

        }
        return like;
    }


    @PostMapping("/SingleFile/upload")
    public String SingleFileUpLoad(@RequestParam("myfile") MultipartFile file) {
        //判断文件是否为空
        if (file.isEmpty()) {

            return "index";
        }

        //创建输入输出流
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String path = "";
        String fileName = "";
        try {
            //指定上传的位置为 d:/upload/
            path = ResourceUtils.getURL("classpath:").getPath() + "static/imgs/";
            //获取文件的输入流
            inputStream = file.getInputStream();
            //获取上传时的文件名
            Date date = new Date();
            fileName = date.getTime() + file.getOriginalFilename();
            //注意是路径+文件名
            File targetFile = new File(path + fileName);
            //如果之前的 String path = "d:/upload/" 没有在最后加 / ，那就要在 path 后面 + "/"
            System.out.println(path);
            //判断文件父目录是否存在
            if (!targetFile.getParentFile().exists()) {
                //不存在就创建一个
                targetFile.getParentFile().mkdir();
            }


            //获取文件的输出流
            outputStream = new FileOutputStream(targetFile);
            //最后使用资源访问器FileCopyUtils的copy方法拷贝文件
            FileCopyUtils.copy(inputStream, outputStream);
            /*参数是通过源码
                public static int copy(InputStream in, OutputStream out) throws IOException {
                    ......
                }
           而得知的*/

            //告诉页面上传成功了

        } catch (IOException e) {
            e.printStackTrace();
            //出现异常，则告诉页面失败

        } finally {
            //无论成功与否，都有关闭输入输出流
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileName;
    }
}





