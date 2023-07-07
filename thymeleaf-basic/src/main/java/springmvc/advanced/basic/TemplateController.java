package springmvc.advanced.basic;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/template")
public class TemplateController {

    @GetMapping("/fragment")
    public String template() {
        return "fragment/fragmentMain";
    }

    @GetMapping("/layout")
    public String layout() {
        return "layout/layoutMain";
    }

    @GetMapping("/layoutExtend")
    public String layoutExtends() {
        return "layoutExtend/layoutExtendMain";
    }

}
