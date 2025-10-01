package Services;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.font.PdfFontFactory.EmbeddingStrategy;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import java.util.stream.*;
import java.nio.file.*;
import java.util.*;

import Modele.DonnéesPersonnalisation;

import java.io.IOException;

public class ServicePDF {
	private static Optional<Path> locateFontFile(String family, boolean bold, boolean italic) {
	    try {
	        Path fontsDir = Paths.get("C:\\Windows\\Fonts");
	        if (!Files.exists(fontsDir)) return Optional.empty();

	        String token = family.toLowerCase().replaceAll("\\s+", "");
	        try (Stream<Path> s = Files.list(fontsDir)) {
	            Map<Path, Integer> scores = new HashMap<>();
	            s.filter(Files::isRegularFile).forEach(p -> {
	                String name = p.getFileName().toString().toLowerCase();
	                int score = 0;
	                if (name.contains(token)) score += 10;
	                if (bold && (name.contains("bold") || name.contains("bd"))) score += 6;
	                if (italic && (name.contains("italic") || name.contains("oblique") || name.contains("it"))) score += 5;
	                // prefer exact extension .ttf/.otf slightly
	                if (name.endsWith(".ttf") || name.endsWith(".otf")) score += 1;
	                scores.put(p, score);
	            });
	            return scores.entrySet().stream()
	                    .max(Map.Entry.comparingByValue())
	                    .filter(e -> e.getValue() > 0)
	                    .map(Map.Entry::getKey);
	        }
	    } catch (Exception ex) {
	        return Optional.empty();
	    }
	}
	
	@SuppressWarnings("resource")
	public static void generatePDF(String titre, String texte, String lien, String cheminPDF, String cheminImage, DonnéesPersonnalisation perso) {
        if (titre == null || texte == null || lien == null || cheminPDF == null || perso == null || cheminImage == null) {
        	throw new IllegalArgumentException("Paramètres invalides pour la génération du PDF");
        }

        try {
            PdfWriter writer = new PdfWriter(cheminPDF);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            Optional<Path> titleCandidate = locateFontFile(perso.getTitleFontName(), perso.isTitleBold(), perso.isTitleItalic());
            PdfFont titleFont;
            try {
                if (titleCandidate.isPresent()) {
                    titleFont = PdfFontFactory.createFont(titleCandidate.get().toString(), PdfEncodings.IDENTITY_H, EmbeddingStrategy.PREFER_EMBEDDED, true);
                } else {
                    // fallback: essayer FamilyName.ttf, puis Helvetica-Bold
                    Path guess = Paths.get("C:\\Windows\\Fonts", perso.getTitleFontName() + ".ttf");
                    if (Files.exists(guess)) titleFont = PdfFontFactory.createFont(guess.toString(), PdfEncodings.IDENTITY_H, EmbeddingStrategy.PREFER_EMBEDDED, true);
                    else titleFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
                }
            } catch (IOException ioe) {
                titleFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
            }

            // Texte
            Optional<Path> textCandidate = locateFontFile(perso.getTextFontName(), perso.isTextBold(), perso.isTextItalic());
            PdfFont textFont;
            try {
                if (textCandidate.isPresent()) {
                    textFont = PdfFontFactory.createFont(textCandidate.get().toString(), PdfEncodings.IDENTITY_H, EmbeddingStrategy.PREFER_EMBEDDED, true);
                } else {
                    Path guess = Paths.get("C:\\Windows\\Fonts", perso.getTextFontName() + ".ttf");
                    if (Files.exists(guess)) textFont = PdfFontFactory.createFont(guess.toString(), PdfEncodings.IDENTITY_H, EmbeddingStrategy.PREFER_EMBEDDED, true);
                    else textFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);
                }
            } catch (IOException ioe) {
                textFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            }

            Paragraph titrePara = new Paragraph(titre)
            		.setFont(titleFont)
                    .setFontSize(perso.getTitleFontSize())
                    .setFontColor(new DeviceRgb(
                    		perso.getTitleColor().getRed(),
                    		perso.getTitleColor().getGreen(),
                    		perso.getTitleColor().getBlue()))
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginBottom(20);
            if (perso.isTitleUnderline()) {
            	titrePara.setUnderline();
            }
            document.add(titrePara);

            Paragraph textePara = new Paragraph(texte)
            		.setFont(textFont)
                    .setFontSize(perso.getTextFontSize())
                    .setFontColor(new DeviceRgb(
                    		perso.getTextColor().getRed(),
                    		perso.getTextColor().getGreen(),
                    		perso.getTextColor().getRed()))
                    .setTextAlignment(TextAlignment.LEFT)
                    .setMarginBottom(20);
            if (perso.isTextUnderline()) {
            	textePara.setUnderline();
            }
            document.add(textePara);
            
            if (cheminImage != null && !cheminImage.isEmpty()) {
                try {
                    ImageData imageData = ImageDataFactory.create(cheminImage);
                    Image image = new Image(imageData);

                    if (perso.getIMGWidth() > 0 && perso.getIMGLength() > 0) {
                        image.scaleToFit(perso.getIMGWidth(), perso.getIMGLength());
                    } else {
                        image.scaleToFit(50, 50); // fallback
                    }
                    Paragraph imgP = new Paragraph().add(image);
                    switch (perso.getIMGPosition()) {
                    case "A Droite":
                    	imgP.setTextAlignment(TextAlignment.RIGHT);
                    	break;
                    case "Centré":
                    	imgP.setTextAlignment(TextAlignment.CENTER);
                    	break;
                    case "A Gauche":
                    	imgP.setTextAlignment(TextAlignment.LEFT);
                    	break;
                    }
                    document.add(imgP);
                } catch (Exception e) {
                    System.err.println("Erreur lors de l’ajout de l’image : " + e.getMessage());
                }
            }
            Image qrCodeImage = ServiceQR.genererQRCode(lien, perso.getQRWidth(), perso.getQRLength());
            if (qrCodeImage != null) {
                qrCodeImage.setMarginTop(20);
                qrCodeImage.setMarginBottom(5);
                Paragraph qrP = new Paragraph().add(qrCodeImage);
                switch (perso.getQRPosition()) {
                case "A Droite":
                	qrP.setTextAlignment(TextAlignment.RIGHT);
                	break;
                case "Centré":
                	qrP.setTextAlignment(TextAlignment.CENTER);
                	break;
                case "A Gauche":
                	qrP.setTextAlignment(TextAlignment.LEFT);
                	break;
                }
                document.add(qrP);
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