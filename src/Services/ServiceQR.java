package Services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.layout.element.Image;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ServiceQR {
	public static Image genererQRCode(String data, int largeur, int hauteur) {
        if (data == null || data.trim().isEmpty()) {
            System.err.println("Erreur : données ou chemin de QR code invalide.");
            return null;
        }

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, largeur, hauteur);
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", baos);

            ImageData imageData = ImageDataFactory.create(baos.toByteArray());
            return new Image(imageData);

        } catch (WriterException e) {
            System.err.println("Erreur lors de la génération du QR Code : " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erreur lors de la conversion du QR Code en image : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur inattendue lors du QR Code : " + e.getMessage());
        }
        return null;
    }
}
