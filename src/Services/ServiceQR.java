package Services;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class ServiceQR {
	public static void genererQRCode(String data, String cheminFichier, int largeur, int hauteur) {
        if (data == null || data.trim().isEmpty() || cheminFichier == null || cheminFichier.trim().isEmpty()) {
            System.err.println("Erreur : données ou chemin de QR code invalide.");
            return;
        }

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, largeur, hauteur);
            Path path = FileSystems.getDefault().getPath(cheminFichier);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            System.out.println("QR Code généré : " + cheminFichier);
        } catch (WriterException e) {
            System.err.println("Erreur lors de la génération du QR Code : " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Erreur lors de l’écriture du fichier QR Code : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur inattendue lors du QR Code : " + e.getMessage());
        }
    }
}
