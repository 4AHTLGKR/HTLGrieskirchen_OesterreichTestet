package at.htlgkr.prototype;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientController {
    @GetMapping("/")
    public String patientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "addPatient";
    }

    @PostMapping("/")
    public String patientSubmit(@ModelAttribute Patient patient, Model model) {
        model.addAttribute("patient", patient);
        return "addedPatient";
    }
}
