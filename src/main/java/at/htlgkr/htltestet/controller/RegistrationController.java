package at.htlgkr.htltestet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

    @GetMapping("start")
    public String start(Model model) {
        return "Booking/Start";
    }

    @GetMapping("screeningstation")
    public String screeningstation(Model model) {
        return "Booking/screeningstation";
    }

    @GetMapping("timeSlot")
    public String timeSlot(Model model) {
        return "Booking/TimeSlot";
    }

    @GetMapping("completed")
    public String completed(Model model) {
        return "Booking/Completed";
    }
}
