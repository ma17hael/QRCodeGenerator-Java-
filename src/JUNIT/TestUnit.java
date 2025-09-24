package JUNIT;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.itextpdf.layout.element.Image;

import Controleur.ControleurFormulaire;

import java.io.File;
import org.junit.jupiter.api.*;

import Services.ServicePDF;
import Services.ServiceQR;

public class TestUnit {

	private File tempFile;
	private ControleurFormulaire controleur;

	@BeforeEach
	void setup() throws Exception {
		controleur = new ControleurFormulaire();
		tempFile = File.createTempFile("test-pdf", ".pdf");
		tempFile.deleteOnExit();
	}

	
	//Test unitaires du Service QR
	@Test
	void testGenererQRCodeValide() {
		Image qr = ServiceQR.genererQRCode("https://github.com/ma17hael/QRCodeGenerator-Java-", 200, 200);
		assertNotNull(qr, "Le QRCode ne devrait pas être null");
	}

	@Test
	void testGenererQRCodeLienVide() {
		Image qr = ServiceQR.genererQRCode("", 200, 200);
		assertNull(qr, "Le QRCode devrait être null si le lien est vide");
	}

	@Test
	void testGenererQRCodeNull() {
		Image qr = ServiceQR.genererQRCode(null, 200, 200);
		assertNull(qr, "Le QRCode doit être null si les données sont null");
	}

	@Test
	void testGenererQRCodeDimension() {
		Image qr = ServiceQR.genererQRCode("test", 300, 300);
		assertNotNull(qr, "Le QRCode doit exister avec des dimensions valides");
		assertEquals(300, qr.getImageWidth(), "La largeur de l’image doit être 300");
	}
	
	
	//Tests unitaires du Service PDF
	@Test
	void testGeneratePDFValide() {
		assertDoesNotThrow(() -> {
			ServicePDF.generatePDF(
					"Titre Test", 
					"Ceci est un texte de test", 
					"https://openai.com", 
					tempFile.getAbsolutePath()
					);
		});
		assertTrue(tempFile.exists(), "Le fichier PDF devrait être généré");
		assertTrue(tempFile.length() > 0, "Le PDF ne doit pas être vide");
	}

	@Test
	void testGeneratePDFChampsVides() {
		assertThrows(IllegalArgumentException.class, () -> {
			ServicePDF.generatePDF("", "", "", tempFile.getAbsolutePath());
		});
	}
	
	@Test
	void testGeneratePDFSansChemin() {
		assertThrows(IllegalArgumentException.class, () -> {
			ServicePDF.generatePDF("Titre", "Texte", "Lien", "");
		});
	}

	//Tests unitaires du Controleur
	@Test
	void testDemandeDonneesValides() {
		assertDoesNotThrow(() -> {
			controleur.demandeUInterfaceDonnées(
					"Titre Test", 
					"Un texte exemple", 
					"https://openai.com",
					tempFile.getAbsolutePath()
					);
		});
	}

	@Test
	void testDemandeDonneesInvalides() {
		assertThrows(IllegalArgumentException.class, () -> {
			controleur.demandeUInterfaceDonnées("", "", "", "");
		});
	}
}

