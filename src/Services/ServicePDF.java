package Services;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.File;
import java.io.IOException;

public class ServicePDF {
	public static void generatePDF(String texte, String lien, String cheminPDF) {
		PdfWriter writer = null;
		try {
			writer = new PdfWriter(cheminPDF);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf);
		document.add(new Paragraph("Texte :" + texte));
		document.add(new Paragraph("Lien :" + lien));
		document.close();
			
		System.out.println("PDF généré avec succès : " + cheminPDF);
	}

}
