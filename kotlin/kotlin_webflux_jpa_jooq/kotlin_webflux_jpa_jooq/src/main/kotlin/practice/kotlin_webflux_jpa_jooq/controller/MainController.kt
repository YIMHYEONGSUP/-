package practice.kotlin_webflux_jpa_jooq.controller

import org.springframework.http.MediaType
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.awt.PageAttributes

@RestController
@RequestMapping("/")
class MainController {

    @GetMapping("/main")
    fun main():String {
        return "main"
    }
}