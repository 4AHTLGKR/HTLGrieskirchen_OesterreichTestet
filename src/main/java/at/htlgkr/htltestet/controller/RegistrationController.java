package at.htlgkr.htltestet.controller;

import at.htlgkr.htltestet.data.Registration;
import at.htlgkr.htltestet.pdf.RegistrationPDF;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    @GetMapping("appointment")
    public String appointment(@ModelAttribute("registration") Registration registration, Model model, @ModelAttribute("registrationPDF") RegistrationPDF registrationPDF) {
        model.addAttribute("registration", registration);
        RegistrationPDF rpdf = new RegistrationPDF();
        rpdf.setCode((int)(Math.random()*10000000) + "");
        rpdf.setScreeningStation("Schulzentrum - Raiffeisen Arena, Grieskirchen \n 4710 Grieskirchen \n Parzer Schulstraße 1");
        model.addAttribute("registrationPDF", rpdf);
        registration.setTestDateTime(LocalDateTime.of(LocalDate.of(2021, 6, 30),LocalTime.of(12,47)));
        model.addAttribute("testDate", registration.testDateTime.toLocalDate());
        return "Booking/AppointmentVerification";
    }

}
