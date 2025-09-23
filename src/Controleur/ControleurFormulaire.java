package Controleur;

import Modele.DonnéeFormulaire;
import Services.ServicePDF;
import Services.ServiceQR;
import Vue.UserInterface;

public class ControleurFormulaire {
	private UserInterface uinterface;
	
	public ControleurFormulaire() {
		uinterface = new UserInterface(this);
		uinterface.setVisible(true);
	}
	
	public static void main(String[] args) {
		new ControleurFormulaire();
	}
	
	public void demandeUInterfaceDonnées(String texte, String lien) {
        try {
            DonnéeFormulaire donnéeStockée = new DonnéeFormulaire(texte, lien);

            String cheminQRCode = "C:\\temp\\qrcode.png";
            ServiceQR.genererQRCode(donnéeStockée.getLien(), cheminQRCode, 200, 200);

            String cheminPDF = "C:\\temp\\document.pdf";
            ServicePDF.generatePDF(donnéeStockée.getTexte(), donnéeStockée.getLien(), cheminQRCode, cheminPDF);

        } catch (IllegalArgumentException e) {
            System.err.println("Données invalides : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur inattendue : " + e.getMessage());
        }
    }
	
}
