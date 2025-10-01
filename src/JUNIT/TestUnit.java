package JUNIT;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import Modele.DonnéesPersonnalisation;
import Modele.ProjectPDF;
import Services.ServicePDF;
import Services.ServiceQR;
import Controleur.ControleurFormulaire;

public class TestUnit {

    private File tempPDF;
    private File tempJSON;
    private File tempIMG;
    private ControleurFormulaire controleur;

    @BeforeEach
    void setup() throws Exception {
        controleur = new ControleurFormulaire();
        tempPDF = File.createTempFile("test-pdf", ".pdf");
        tempPDF.deleteOnExit();
        BufferedImage img = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        tempIMG = File.createTempFile("test-png", ".png");
        ImageIO.write(img, "png", tempIMG);
        tempIMG.deleteOnExit();
        tempJSON = File.createTempFile("test-json", ".json");
        tempJSON.deleteOnExit();
    }

    // ==== Tests QRCode ====
    @Test
    void testGenererQRCodeValide() {
        assertNotNull(ServiceQR.genererQRCode("https://github.com", 200, 200));
    }

    @Test
    void testGenererQRCodeLienVide() {
        assertNull(ServiceQR.genererQRCode("", 200, 200));
    }

    @Test
    void testGenererQRCodeNull() {
        assertNull(ServiceQR.genererQRCode(null, 200, 200));
    }

    @Test
    void testGenererQRCodeDimension() {
        var qr = ServiceQR.genererQRCode("test", 300, 300);
        assertNotNull(qr);
        assertEquals(300, qr.getImageWidth());
    }

    // ==== Tests PDF ====
    @Test
    void testGeneratePDFValide() {
        DonnéesPersonnalisation perso = new DonnéesPersonnalisation();
        perso.setQRWidth(200);
        perso.setQRLength(200);
        assertDoesNotThrow(() -> {
            ServicePDF.generatePDF(
                    "Titre Test",
                    "Ceci est un texte de test",
                    "https://openai.com",  // lien valide pour QR
                    tempPDF.getAbsolutePath(),
                    null,                  // pas d'image
                    perso
            );
        });

        assertTrue(tempPDF.exists(), "Le fichier PDF devrait être généré");
        assertTrue(tempPDF.length() > 0, "Le PDF ne doit pas être vide");
    }

    @Test
    void testGeneratePDFChampsVides() {
        DonnéesPersonnalisation perso = new DonnéesPersonnalisation();
        assertThrows(IllegalArgumentException.class, () -> {
            ServicePDF.generatePDF("", "", "", tempPDF.getAbsolutePath(), null, perso);
        });
    }

    // ==== Tests sauvegarde et chargement JSON ====
    @Test
    void testSauvegardeEtChargementProjet() throws Exception {
        // Création projet avec perso
        DonnéesPersonnalisation perso = new DonnéesPersonnalisation();
        perso.setTitleFontName("Arial");
        perso.setTitleFontSize(12);
        perso.setTitleUnderline(true);
        perso.setTextFontName("Times New Roman");
        perso.setTextFontSize(10);

        ProjectPDF projet = new ProjectPDF();
        projet.setTitre("Mon titre");
        projet.setTexte("Mon texte");
        projet.setLien("https://example.com");
        projet.setPersonnalisation(perso);

        // Sauvegarde
        ProjectPDF.sauvegarderProjet(projet, tempJSON.getAbsolutePath());
        assertTrue(tempJSON.exists() && tempJSON.length() > 0);

        // Chargement
        ProjectPDF loaded = ProjectPDF.chargetProjet(tempJSON.getAbsolutePath());
        assertEquals("Mon titre", loaded.getTitre());
        assertEquals("Mon texte", loaded.getTexte());
        assertEquals("https://example.com", loaded.getLien());
        assertNotNull(loaded.getPersonnalisation());
        assertEquals("Arial", loaded.getPersonnalisation().getTitleFontName());
        assertTrue(loaded.getPersonnalisation().isTitleUnderline());
    }

    // ==== Tests du Controleur ====
    @Test
    void testDemandeDonneesValides() {
        assertDoesNotThrow(() -> {
            controleur.demandeUInterfaceDonnées(
                "Titre Test",
                "Un texte exemple",
                "https://openai.com",
                tempPDF.getAbsolutePath(),
                tempIMG.getAbsolutePath()
            );
        });
    }

    @Test
    void testDemandeDonneesInvalides() {
        assertThrows(IllegalArgumentException.class, () -> {
            controleur.demandeUInterfaceDonnées("", "", "", "", "");
        });
    }
}
