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
import java.util.NoSuchElementException;

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


        registrationDataRepository.save(registration);
        RegistrationPDF pdf = new RegistrationPDF();
        pdf.setCode(registration.getId() + "");
        pdf.setScreeningStation(screeningStationRepository.findById(registration.getScreeningStationId()).get().getName());
        pdf.setCreationDate(LocalDateTime.now());
        pdf.setFirstname(registration.getFirstname());
        pdf.setLastname(registration.getLastname());
        pdf.setBirthdate(registration.getBirthdate());
        pdf.setLocation(registration.getPlace());
        pdf.setPlz(registration.getPlz());
        pdf.setStreet(registration.getStreet());
        pdf.setStreetNr(registration.getStreetNumber());
        pdf.setGender(registration.getGender());
        pdf.setEmail(registration.getEmail());
        pdf.setPhoneNumber(registration.getPhoneNumber());
        new Thread(() -> SendEmails.sendRegistrationMail(registration,pdf)).start();
        model.addAttribute("regpdf", pdf);
        return "Booking/Completed";
    }

    @GetMapping("authentication")
    public String authentication(@RequestParam int registrationId, Model model) {
        model.addAttribute("registrationId", registrationId);
        return "Booking/Authentication";
    }
    @GetMapping("appointment")

    public String appointment(@RequestParam LocalDate birthday, @RequestParam int registrationId, Model model) {


        RegistrationData registration = null;

        try {
            registration = registrationDataRepository.getOne(registrationId);
            
        }
        catch (NoSuchElementException ex) {
            System.out.println("A registration for given registrationId (" + registrationId + ") could not be found");
            model.addAttribute("hasWrongRegistrationId", true);
            return "Booking/Authentication";
        }
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

        new Thread(() -> SendEmails.sendStornoMail(registrationDataRepository.findById(registration.getId()).get())).start();
        registrationDataRepository.deleteById(registration.getId());
        return "Booking/Cancelled";
    }
}
