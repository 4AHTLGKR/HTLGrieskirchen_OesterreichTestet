package at.htlgkr.htltestet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InternalController {

    @GetMapping("barcode_reading")
    public String barcode_reading(Model model) {
        return "Internal/Barcode_Reading";
    }
}
