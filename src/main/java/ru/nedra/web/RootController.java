package ru.nedra.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nedra.service.ClickCounterService;

@Controller
@RequestMapping(value = "${server.api}")
@RequiredArgsConstructor
public class RootController {

    private final ClickCounterService clickCounterService;

    @Value("${server.api}")
    private String apiPrefix;

    @GetMapping("/counter")
    public String index (Model model) {
        Long counter = clickCounterService.getCounter();
        model.addAttribute("counter", counter);
        return "home";
    }

    @PostMapping("/counter")
    public String increment() {
        clickCounterService.click();
        return "redirect:" + apiPrefix + "/counter";
    }

}
