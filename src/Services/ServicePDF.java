package Services;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import java.io.File;
import java.io.IOException;

public class ServicePDF {
	public static void generatePDF(String titre, String texte, String lien, String cheminPDF, String cheminQRCode) {
        if (titre == null || texte == null || lien == null || cheminPDF == null || cheminQRCode == null) {
            System.err.println("Erreur : Paramètres invalides.");
            return;
        }

        try {
            // Générer le QR code
            ServiceQR.genererQRCode(lien, cheminQRCode, 150, 150);

            PdfWriter writer = new PdfWriter(cheminPDF);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            PdfFont boldFont = PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.HELVETICA_BOLD);

            // Titre centré et gros
            Paragraph titrePara = new Paragraph(titre)
                    .setFontSize(24)
                    .setFont(boldFont)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20);
            document.add(titrePara);

            // Paragraphe de texte
            Paragraph textePara = new Paragraph(texte)
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setMarginBottom(20);
            document.add(textePara);

            // QR code
            File qrFile = new File(cheminQRCode);
            if (qrFile.exists()) {
                ImageData imageData = ImageDataFactory.create(cheminQRCode);
                Image qrImage = new Image(imageData)
                        .setWidth(150)
                        .setHeight(150)
                        .setHorizontalAlignment(com.itextpdf.layout.properties.HorizontalAlignment.CENTER)
                        .setMarginBottom(10);
                document.add(qrImage);

                // Lien sous le QR code
                Paragraph lienPara = new Paragraph(lien)
                        .setFontSize(10)
                        .setFontColor(ColorConstants.BLUE)
                        .setTextAlignment(TextAlignment.CENTER);
                document.add(lienPara);
            } else {
                System.err.println("QR code non trouvé pour ajout dans le PDF : " + cheminQRCode);
            }

            document.close();
            System.out.println("PDF généré avec succès : " + cheminPDF);

        } catch (IOException e) {
            System.err.println("Erreur lors de la génération du PDF : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur inattendue : " + e.getMessage());
        }
    }
}