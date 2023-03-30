package practice.kotlin_webflux_jpa_jooq.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {

    @GetMapping("/main")
    fun main(model: Model):String {
        model.addAttribute("greeting", "Hello Thymeleaf")
        return "main"
    }
}