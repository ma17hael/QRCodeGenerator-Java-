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
	
	public void demandeUInterfaceDonnées(String titre, String texte, String lien, String CheminPDF) {
        try {
            DonnéeFormulaire donnéeStockée = new DonnéeFormulaire(titre, texte, lien);

            String cheminQRCode = "C:\\temp\\qrcode.png";
            ServiceQR.genererQRCode(donnéeStockée.getLien(), cheminQRCode, 200, 200);

            ServicePDF.generatePDF(donnéeStockée.getTitre(), donnéeStockée.getTexte(), donnéeStockée.getLien(), CheminPDF,  cheminQRCode);

        } catch (IllegalArgumentException e) {
            System.err.println("Données invalides : " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur inattendue : " + e.getMessage());
        }
    }
	
}
