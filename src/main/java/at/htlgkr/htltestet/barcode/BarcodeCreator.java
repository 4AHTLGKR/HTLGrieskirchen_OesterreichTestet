package at.htlgkr.htltestet.barcode;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import java.awt.image.BufferedImage;

public class BarcodeCreator {
    public static BufferedImage generateCode128BarcodeImage(String code) throws Exception{
        Barcode barcode = BarcodeFactory.createCode128(code);
        return BarcodeImageHandler.getImage(barcode);
    }
}
