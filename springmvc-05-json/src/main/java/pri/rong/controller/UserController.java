package pri.rong.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pri.rong.pojo.User;
import pri.rong.utils.JsonUtiles;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description：
 * @auther lurongrong
 * @create 2021-06-13 10:30
 */
//@Controller//走视图解析器
@RestController//不走视图解析器，标志着下面这个类全部的方法返回的是字符串
public class UserController {

    @RequestMapping("/j2")
//    @ResponseBody//它就不会走视图解析器，会直接返回一个字符串
    public String json2(){
        //创建一个对象
        User user = new User("赵丽颖", 29, "女");
        //直接将对象返回前端
        return user.toString();
//        model.addAttribute("msg","123");
//        return "hello";
    }
//  @RequestMapping(value = "/j1",produces = "application/json;charset=utf-8")
  @RequestMapping("/j1")
  @ResponseBody
  public String json1() {
      //jackson,ObjectMapper
      ObjectMapper mapper = new ObjectMapper();

      User user = new User("赵丽颖", 28, "女");

      String str = null;
      try {
//          byte[] bytes = mapper.writeValueAsBytes(user);
          str = mapper.writeValueAsString(user);

      } catch (JsonProcessingException e) {
          e.printStackTrace();
      }

      return str;


  }

@RequestMapping("/j3")
public String json3() {

//    ObjectMapper objectMapper = new ObjectMapper();

    User user1 = new User("豆", 24, "女");
    User user2 = new User("融", 23, "女");
    User user3 = new User("宁", 21, "女");
    User user4 = new User("培", 20, "女");
    User user5 = new User("慢", 16, "女");
    User user6 = new User("怡", 11, "女");

    List<User> list = new ArrayList<>();

    list.add(user1);
    list.add(user2);
    list.add(user3);
    list.add(user4);
    list.add(user5);
    list.add(user6);
//    String s = null;
//    try {
//        s = objectMapper.writeValueAsString(list);
//    } catch (JsonProcessingException e) {
//        e.printStackTrace();
//    }
    return JsonUtiles.getJson(list);
}
    @RequestMapping("/j5")
    public String json5(){
        ObjectMapper mapper = new ObjectMapper();

        Date date = new Date();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String format = simpleDateFormat.format(date);
        String s = null;
        try {
            s = mapper.writeValueAsString(format);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }
@RequestMapping("/j4")
    public String json4(){
        Date date = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

         return JsonUtiles.getJson(date);
}
}


