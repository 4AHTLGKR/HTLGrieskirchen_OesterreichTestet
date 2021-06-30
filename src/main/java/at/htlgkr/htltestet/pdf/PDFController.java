package at.htlgkr.htltestet.pdf;


import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.IOException;

@Controller
@CrossOrigin
public class PDFController {

    @RequestMapping(produces = MediaType.APPLICATION_PDF_VALUE, method = RequestMethod.POST, value = "/pdf/result")
    public byte[] getResult(@RequestBody ResultPDF result){
        try {
            return result.createPDF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(produces = MediaType.APPLICATION_PDF_VALUE, method = RequestMethod.POST, value = "/pdf/registration")
    public byte[] getRegistration(@RequestBody RegistrationPDF registration){

        try {
            return registration.createPDF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
