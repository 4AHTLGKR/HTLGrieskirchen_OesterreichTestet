package at.htlgkr.prototype;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {
    private PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/")
    public String patientForm(Model model) {
        model.addAttribute("patient", new Patient());
        model.addAttribute("patients", patientRepository.findAll());
        return "addPatient";
    }

    @ResponseBody
    @GetMapping("/show")
    public Patient showInfos(Long searchId) {
        return patientRepository.findById(searchId).orElseGet(null);
    }

    @PostMapping("/")
    public String patientSubmit(@ModelAttribute("patient") Patient patient, Model model) {
        model.addAttribute("patient", patient);
        patientRepository.save(patient);
        return "subFolder/addedPatient";
    }
}
