package springmvc.advanced.basic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String textBasic(Model model){
        model.addAttribute("data", "Hello Spring!");
        return "basic/text-basic";
    }

    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model){
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "basic/text-unescape";
    }

    @GetMapping("/variable")
    public String variable(Model model){

        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "basic/variable";
    }

    @GetMapping("/basic-objects")
    public String basicObjects(Model model, HttpServletRequest request,
                               HttpServletResponse response, HttpSession session) {
        /**
         * 스프링 부트 3.0 부터는 타임리프 기본 객체를 제공하지 않는다. 사용하려면 직접 Model 에 담아야함
         */
        session.setAttribute("sessionData", "Hello Session");
        model.addAttribute("request", request);
        model.addAttribute("response", response);
        model.addAttribute("servletContext", request.getServletContext());
        return "basic/basic-objects";
    }

    @Component("helloBean") //스프링 빈 접근가능
    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    }

    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping("/link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }

    @GetMapping("/literal")
    public String literal(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/literal";
    }

    @GetMapping("/operation")
    public String operation(Model model) {
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring!");
        return "basic/operation";
    }

    @GetMapping("/attribute")
    public String attribute() {
        return "basic/attribute";
    }

    @GetMapping("/each")
    public String each(Model model) {
        addUsers(model);
        return "basic/each";
    }

    private void addUsers(Model model) {
        List<User> list = new ArrayList<>();
        list.add(new User("userA", 10));
        list.add(new User("userB", 20));
        list.add(new User("userC", 30));
        model.addAttribute("users", list);
    }

    @GetMapping("/condition")
    public String condition(Model model) {
        addUsers(model);
        return "basic/condition";
    }

    @GetMapping("/comments")
    public String comments(Model model) {
        model.addAttribute("data", "Spring!");
        return "basic/comments";
    }

    @GetMapping("/block")
    public String block(Model model) {
        addUsers(model);
        return "basic/block";
    }

    @GetMapping("/javascript")
    public String javascript(Model model) {
        model.addAttribute("user", new User("userA", 10));
        addUsers(model);
        return "basic/javascript";
    }
    @Data
    static class User {
        private String username;
        public int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }

}
