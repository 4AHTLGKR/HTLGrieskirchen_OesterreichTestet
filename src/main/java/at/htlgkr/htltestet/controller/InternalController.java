package at.htlgkr.htltestet.controller;

import at.htlgkr.htltestet.data.RegistrationData;
import at.htlgkr.htltestet.data.RegistrationDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class InternalController {

    Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);


    @Autowired
    private RegistrationDataRepository registrationDataRepository;

    @GetMapping("/internal/barcode_reading")
    public String barcode_reading(Model model) {
        return "Internal/Barcode_Reading";
    }

    @GetMapping("/internal/enter_result")
    public String enter_result(Model model) {

        List<RegistrationData> registrationDataList;


        registrationDataList = this.getRegistrations();

        if(registrationDataList.isEmpty()){
            logger.warning("List is empty");
        }

        model.addAttribute("list", registrationDataList);

        return "Internal/Enter_Result";
    }

    @GetMapping("/internal/getRegistrations")
    public List<RegistrationData> getRegistrations() {
        return registrationDataRepository.findAll();
    }
}
