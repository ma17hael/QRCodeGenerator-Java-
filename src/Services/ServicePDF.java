package Services;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.IOException;

public class ServicePDF {
	public static void generatePDF(String texte, String lien, String cheminQRCode, String cheminPDF) {
        if (texte == null || lien == null || cheminPDF == null || cheminPDF.trim().isEmpty()) {
            System.err.println("Erreur : Texte, lien ou chemin PDF invalide.");
            return;
        }

        PdfWriter writer;
        try {
            writer = new PdfWriter(cheminPDF);
        } catch (IOException e) {
            System.err.println("Erreur lors de la création du PdfWriter : " + e.getMessage());
            return;
        }

        PdfDocument pdf;
        Document document = null;

        try {
            pdf = new PdfDocument(writer);
            document = new Document(pdf);

            document.add(new Paragraph("Texte : " + texte));
            document.add(new Paragraph("Lien : " + lien));

            if (cheminQRCode != null && !cheminQRCode.trim().isEmpty()) {
                try {
                    Image qrCode = new Image(ImageDataFactory.create(cheminQRCode));
                    qrCode.setWidth(150);
                    qrCode.setHeight(150);
                    document.add(qrCode);
                } catch (IOException e) {
                    System.err.println("Impossible d'ajouter le QR code : " + e.getMessage());
                }
            }

            System.out.println("PDF généré avec succès : " + cheminPDF);

        } catch (Exception e) {
            System.err.println("Erreur lors de la génération du PDF : " + e.getMessage());
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (Exception e) {
                    System.err.println("Erreur lors de la fermeture du document PDF : " + e.getMessage());
                }
            }
        }
    }
}
