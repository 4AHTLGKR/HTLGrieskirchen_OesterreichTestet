package at.htlgkr.htltestet.Mail;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailController {

    @GetMapping("Mail/Registration")
    public String registrationMail(Model model) {
        return "Mails/Registrierungsbestaetigung";
    }

    @GetMapping("Mail/Storno")
    public String stornoMail(Model model) {
        return "Mails/Storno-Besteatigung";
    }

    @GetMapping("Mail/Termin")
    public String terminMail(Model model) {
        return "Mails/Terminbestatigung";
    }

}
