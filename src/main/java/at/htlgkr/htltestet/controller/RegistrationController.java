package at.htlgkr.htltestet.controller;

import at.htlgkr.htltestet.Mail.SendEmails;
import at.htlgkr.htltestet.data.RegistrationData;
import at.htlgkr.htltestet.data.RegistrationDataRepository;
import at.htlgkr.htltestet.data.ScreeningStation;
import at.htlgkr.htltestet.data.ScreeningStationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;

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
    public String appointment(@RequestParam LocalDate birthday, @RequestParam int registrationId, Model model) {
        RegistrationData registration = registrationDataRepository.findById(registrationId).get();
        if(!registration.getBirthdate().equals(birthday)){
            model.addAttribute("registrationId", registrationId);
            model.addAttribute("wrongBirthdate", true);
            return "Booking/Authentication";
        }
        model.addAttribute("registration", registration);
        int screeningStationId = registration.getScreeningStationId();
        ScreeningStation screeningStation = screeningStationRepository.getOne(screeningStationId);
        model.addAttribute("screening_station", screeningStation);
        return "Booking/AppointmentVerification";

    }

    /**This endpoint is called if the cancellation
     * of the appointment was successful*/

    @GetMapping("cancelled")
    public String cancelled(@ModelAttribute("registration") RegistrationData registration, Model model) {
        registrationDataRepository.deleteById(registration.getId());
        return "Booking/Cancelled";
    }
}
