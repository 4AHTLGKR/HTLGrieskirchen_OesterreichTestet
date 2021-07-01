package at.htlgkr.htltestet.controller;

import at.htlgkr.htltestet.Mail.SendEmails;
import at.htlgkr.htltestet.data.*;
import at.htlgkr.htltestet.pdf.ResultPDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
public class InternalController {

    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    @Autowired
    private RegistrationDataRepository registrationDataRepository;

    @Autowired
    private ScreeningStationRepository screeningStationRepository;

    @GetMapping("/internal/barcode_reading")
    public String barcode_reading(Model model) {
        return "Internal/Barcode_Reading";
    }

    @GetMapping("/internal/enter_result")
    public String enter_result(Model model) {

        List<RegistrationData> registrationDataList;
        registrationDataList = this.getRegistrations().stream().filter(x->!x.getIsTested()).collect(Collectors.toList());

        RegistrationDataDto regForm = new RegistrationDataDto();

        logger.warning("RegForm vor befüllen: " + regForm);
        for (RegistrationData registrationData : registrationDataList) {
            regForm.addRegs(registrationData);
        }
        logger.warning("RegForm nach befüllen:" + regForm);
        if(registrationDataList.isEmpty()){
            logger.warning("List is empty");
        }

        model.addAttribute("form", regForm);


        return "Internal/Enter_Result";
    }

    @GetMapping("/internal/getRegistrations")
    public List<RegistrationData> getRegistrations() {
        return registrationDataRepository.findAll();
    }

    @PostMapping("/internal/enter_result")
    public String enterResultTwo(@ModelAttribute("form")RegistrationDataDto ts, Model model) {

        for(RegistrationData rd : ts.getRegs()){
            if(rd.getIsTested() && rd.getTestResult()!=null) {
                RegistrationData r = registrationDataRepository.findById(rd.getId()).get();
                ResultPDF pdf = ResultPDF.ConvertRegistrationToPdf(r,rd.getTestResult());
                new Thread(() -> SendEmails.sendResultMail(pdf,r.getEmail())).start();
            }
        }
        return "/internal/barcode_reading";
    }

    @PostMapping("/internal/sendCode")
    public String sendCode(@RequestParam int barcode, HttpServletRequest request, Model model) {
        int screeningStationId = Integer.parseInt(request.getSession().getAttribute("screeningStationId").toString());
        ScreeningStation screeningStation = screeningStationRepository.getOne(screeningStationId);
        screeningStation.getCurrentRegistrations().add(barcode);

        screeningStationRepository.save(screeningStation);
        return barcode_reading(model);
    }
}
