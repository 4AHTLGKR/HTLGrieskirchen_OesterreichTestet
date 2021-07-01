package at.htlgkr.htltestet.controller;

import at.htlgkr.htltestet.Mail.SendEmails;
import at.htlgkr.htltestet.data.RegistrationData;
import at.htlgkr.htltestet.data.RegistrationDataRepository;
import at.htlgkr.htltestet.data.ScreeningStation;
import at.htlgkr.htltestet.data.ScreeningStationRepository;
import at.htlgkr.htltestet.pdf.RegistrationPDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationDataRepository registrationDataRepository;

    @Autowired
    private ScreeningStationRepository screeningStationRepository;

    @GetMapping("start")
    public String start(Model model) {
        model.addAttribute("registration", new RegistrationData());
        return "Booking/Start";
    }

    @PostMapping("screeningstation")
    public String screeningstation(@ModelAttribute("registration") RegistrationData registration, Model model){
        model.addAttribute("registration", registration);
        model.addAttribute("screeningstation", screeningStationRepository.findAll());
        return "Booking/screeningstation";
    }

    @PostMapping("timeSlot")
    public String timeSlot(@ModelAttribute("registration") RegistrationData registration, Model model) {
        model.addAttribute("registration", registration);
        return "Booking/TimeSlot";
    }

    @PostMapping("completed")
    public String completed(@ModelAttribute("registration") RegistrationData registration, Model model) {

        new Thread(() -> SendEmails.sendRegistrationMail(registration)).start();
        registrationDataRepository.save(registration);
        RegistrationPDF pdf = new RegistrationPDF();
        pdf.setCode(registration.getId() + "");
        pdf.setScreeningStation(screeningStationRepository.findById(registration.getScreeningStationId()).get().getName());
        pdf.setCreationDate(LocalDateTime.now());
        model.addAttribute("regpdf", pdf);
        return "Booking/Completed";
    }

    @GetMapping("authentication")
    public String authentication(@RequestParam int registrationId, Model model) {
        model.addAttribute("registrationId", registrationId);
        return "Booking/Authentication";
    }
    @GetMapping("appointment")
    public String appointment(@ModelAttribute("registration") RegistrationData registration, Model model) {
        model.addAttribute("registration", registration);
        int screeningStationId = registration.getScreeningStationId();
        ScreeningStation screeningStation = screeningStationRepository.getOne(screeningStationId);
        model.addAttribute("screening_station", screeningStation);
        return "Booking/AppointmentVerification";
    }
}
