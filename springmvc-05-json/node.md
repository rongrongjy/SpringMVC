1. 导包
2. 配置web.xml（配置DispatcherServlet和过滤器）
3. 配置web.xml中的springmvc-servlet.xml
4. 创建pri.rong.controller
5. 接下来只需专注Controller和界面即可
   
@ResponseBody//它就不会走视图解析器，会直接返回一个字符串

```java
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pri.rong.pojo.User;

@Controller
public class UserController {
        @RequestMapping("/j1")
        @ResponseBody//它就不会走视图解析器，会直接返回一个字符串
        public String json1(){
            //创建一个对象
            User user = new User("赵丽颖", 29, "女");
            //直接将对象返回前端
            return user.toString();
        }
}
```
以下格式解决前端乱码问题：

@RequestMapping(value = "/j1",produces = "application/json;charset=utf-8")

jackson是目前比较好的json解析工具

使用Jackson必须导入Jackson的包（jackson-databind），最好在官网找最新的包
```java
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.12.3</version>
        </dependency>
```
配置乱码问题：
```java
<mvc:annotation-driven>
        <mvc:message-converters register-defaults="true">
            <bean class="org.springframework.http.converter.StringHttpMessageConverter">
                <constructor-arg value="UTF-8"/>
            </bean>
            <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
                <property name="objectMapper">
                    <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                        <property name="failOnEmptyBeans" value="false"/>
                    </bean>
                </property>
            </bean>
        </mvc:message-converters>
    </mvc:annotation-driven>
```
可以将Jackson方法写到一个工具类中使用，提高了代码的复用性
```java
package pri.rong.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

/**
 * @description：
 * @auther lurongrong
 * @create 2021-06-14 16:28
 */
public class JsonUtiles {
    public static String getJson(Object object){
        return getJson(object,"yyyy-MM-dd HH:mm:ss");
    }
    public static String getJson(Object object,String dateFormat){
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);



        SimpleDateFormat format = new SimpleDateFormat(dateFormat);

        mapper.setDateFormat(format);
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;

    }
}

```


jackson中的ObjectMapper可以将对象转化为字符串

//@Controller//走视图解析器

@RestController//不走视图解析器，标志着下面类的全部方法返回的是字符串

@ResponseBody是配合@Controller来使用的，使方法不通过视图解析器来执行

使用以下代码可以是时间以想要的格式输出到前端
```java
@RequestMapping("/j4")
    public String json4(){
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
```
使用Jackson中ObjectMapper的方式格式化输出时间，，Jackson默认实惠吧时间转成timestamps（时间戳）形式


