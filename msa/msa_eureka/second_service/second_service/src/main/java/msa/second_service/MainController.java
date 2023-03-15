package msa.second_service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
@Slf4j
public class MainController {
    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the Second Service";
    }
    @GetMapping("/message")
    public String message(@RequestHeader("second-request") String header){
        log.info(header);
        return "해당 서비스는 second-service 입니다";
    }
    @GetMapping("/check")
    public String check(){
        return "해당 서비스는 커스텀필터와 연결된 second-service 입니다";
    }

}
