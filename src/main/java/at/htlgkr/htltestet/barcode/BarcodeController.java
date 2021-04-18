package at.htlgkr.htltestet.barcode;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Controller
public class BarcodeController {
    @RequestMapping(produces = MediaType.IMAGE_JPEG_VALUE, method = RequestMethod.GET, value = "/barcode")
    public @ResponseBody
    byte[] getBarcode(@RequestParam String code) throws Exception {
        BufferedImage img = BarcodeCreator.generateCode128BarcodeImage(code);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", bao);

        return bao.toByteArray();
    }
}
