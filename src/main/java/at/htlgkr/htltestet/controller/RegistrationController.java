package at.htlgkr.htltestet.controller;

import at.htlgkr.htltestet.Mail.SendEmails;
import at.htlgkr.htltestet.data.RegistrationData;
import at.htlgkr.htltestet.data.RegistrationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import at.htlgkr.htltestet.data.Registration;
import at.htlgkr.htltestet.pdf.RegistrationPDF;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    @GetMapping("authentication")
    public String authentication(@RequestParam int registrationId, Model model) {
        model.addAttribute("registrationId", registrationId);
        return "Booking/Authentication";
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
