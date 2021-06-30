package at.htlgkr.htltestet.controller;

import at.htlgkr.htltestet.Mail.SendEmails;
import at.htlgkr.htltestet.data.RegistrationData;
import at.htlgkr.htltestet.data.RegistrationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RegistrationDataRepository registrationDataRepository;

    @GetMapping("start")
    public String start(Model model) {
        model.addAttribute("registration", new RegistrationData());
        return "Booking/Start";
    }

    @PostMapping("screeningstation")
    public String screeningstation(@ModelAttribute("registration") RegistrationData registration, Model model){
        model.addAttribute("registration", registration);
        return "Booking/screeningstation";
    }

    @PostMapping("timeSlot")
    public String timeSlot(@ModelAttribute("registration") RegistrationData registration, Model model) {
        model.addAttribute("registration", registration);
        return "Booking/TimeSlot";
    }

    @PostMapping("completed")
    public String completed(@ModelAttribute("registration") RegistrationData registration, Model model) {

        model.addAttribute("registration", registration);
        new Thread(() -> SendEmails.sendRegistrationMail(registration)).start();
        registrationDataRepository.save(registration);
        return "Booking/Completed";
    }

    @GetMapping("appointment")
    public String appointment(@ModelAttribute("registration") RegistrationData registration, Model model, @ModelAttribute("registrationPDF") RegistrationPDF registrationPDF) {
        model.addAttribute("registration", registration);
        return "Booking/AppointmentVerification";
    }
}
