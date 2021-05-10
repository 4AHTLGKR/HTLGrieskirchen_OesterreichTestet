package at.htlgkr.htltestet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("test")
    public String test(Model model) {
        return "Booking/Start";
    }
}
