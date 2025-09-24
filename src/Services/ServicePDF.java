package Services;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import java.io.IOException;

public class ServicePDF {
	public static void generatePDF(String titre, String texte, String lien, String cheminPDF) {
        if (titre == null || texte == null || lien == null || cheminPDF == null) {
            System.err.println("Erreur : Paramètres invalides.");
            return;
        }

        try {
            PdfWriter writer = new PdfWriter(cheminPDF);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            PdfFont boldFont = PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.HELVETICA_BOLD);

            Paragraph titrePara = new Paragraph(titre)
                    .setFontSize(24)
                    .setFont(boldFont)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20);
            document.add(titrePara);

            Paragraph textePara = new Paragraph(texte)
                    .setFontSize(14)
                    .setTextAlignment(TextAlignment.LEFT)
                    .setMarginBottom(20);
            document.add(textePara);

            Image qrCodeImage = ServiceQR.genererQRCode(lien, 200, 200);
            if (qrCodeImage != null) {
                qrCodeImage.setAutoScale(true);
                qrCodeImage.setMarginTop(20);
                qrCodeImage.setMarginBottom(5);
                document.add(qrCodeImage);
                Paragraph lienPara = new Paragraph(lien)
                        .setFontSize(10)
                        .setFontColor(ColorConstants.BLUE)
                        .setTextAlignment(TextAlignment.CENTER);
                document.add(lienPara);
            } else {
                System.err.println("QR code non trouvé pour ajout dans le PDF");
            }

            document.close();

        } catch (IOException e) {
            System.err.println("Erreur lors de la génération du PDF : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur inattendue : " + e.getMessage());
        }
    }
}