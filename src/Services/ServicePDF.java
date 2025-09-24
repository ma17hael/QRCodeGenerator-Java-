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
	@SuppressWarnings("resource")
	public static void generatePDF(String titre, String texte, String lien, String cheminPDF) {
        if (titre == null || texte == null || lien == null || cheminPDF == null) {
        	throw new IllegalArgumentException("Paramètres invalides pour la génération du PDF");
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
            	throw new IllegalArgumentException("QR code non trouvé lors de la création du PDF");
            }

            document.close();

        } catch (IOException e) {
        	 throw new IllegalArgumentException("Erreur lors de la generation du PDF : " + e.getMessage());
        } catch (Exception e) {
        	throw new IllegalArgumentException("Erreur innatendu : " + e.getMessage());
        }
    }
}