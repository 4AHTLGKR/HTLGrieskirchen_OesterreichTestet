package at.htlgkr.prototype;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientController {
    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/")
    public String patientForm(Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("patients", patientRepository.findAll());
        return "addPatient";
    }

    @PostMapping("/")
    public String patientSubmit(@ModelAttribute("patient") Patient patient, Model model) {
        model.addAttribute("patient", patient);
        patientRepository.save(patient);
        return "subFolder/addedPatient";
    }
}
