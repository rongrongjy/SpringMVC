package pri.rong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description：
 * @auther lurongrong
 * @create 2021-06-11 15:04
 */
@Controller//已被spring装配
//@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("/h1")
    public String hello(Model model){
        //封装参数
        String attributeValue = "我是一只小小鸟，快快乐乐长大了！！！！";
        model.addAttribute("msg",attributeValue);
        return "hello";//返回的字符串就是视图的名字，会被视图解析器处理
    }
//    @RequestMapping(value="/add/{a}/{b}")
    @GetMapping("/add/{a}/{b}")
    public String test1(@PathVariable int a, @PathVariable int b, Model model){
        int res = a + b;
        model.addAttribute("msg","结果为：" + res);
        //redirect:index.jsp：重定向（不需要视图解析器）
//        return "redirect:index.jsp";//这里的字符串必须是web下真实存在的.jsp的文件名
        return "hello";
    }


}
