package at.htlgkr.htltestet.controller;

import at.htlgkr.htltestet.data.Registration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @GetMapping("start")
    public String start(Model model) {
        model.addAttribute("registration", new Registration());
        return "Booking/Start";
    }

    @PostMapping("screeningstation")
    public String screeningstation(@ModelAttribute("registration") Registration registration, Model model){
        model.addAttribute("registration", registration);
        return "Booking/screeningstation";
    }

    @PostMapping("timeSlot")
    public String timeSlot(@ModelAttribute("registration") Registration registration, Model model) {
        model.addAttribute("registration", registration);
        return "Booking/TimeSlot";
    }

    @PostMapping("completed")
    public String completed(@ModelAttribute("registration") Registration registration, Model model) {
        model.addAttribute("registration", registration);
        return "Booking/Completed";
    }



}
