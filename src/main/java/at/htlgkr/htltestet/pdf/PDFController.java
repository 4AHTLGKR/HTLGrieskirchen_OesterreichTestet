package at.htlgkr.htltestet.pdf;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@CrossOrigin
public class PDFController {

    @RequestMapping(produces = MediaType.APPLICATION_PDF_VALUE, method = RequestMethod.POST, value = "/pdf/result")
    public @ResponseBody byte[] getResult(@ModelAttribute ResultPDF result){
        try {
            return result.createPDF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(produces = MediaType.APPLICATION_PDF_VALUE, method = RequestMethod.POST, value = "/pdf/registration")
    public @ResponseBody byte[] getRegistration(@ModelAttribute("regpdf") RegistrationPDF registration){

        try {
            return registration.createPDF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
