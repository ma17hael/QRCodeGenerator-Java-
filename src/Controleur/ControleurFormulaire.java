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
			String cheminPDF = "C:\\Users\\Malthael\\Downloads\\Test.pdf";
			ServicePDF.generatePDF(donnéeStockée.getTexte(), donnéeStockée.getLien(), cheminPDF);
		
			String cheminQR = "C:\\Users\\Malthael\\Downloads\\QRTEST.png";
			String data = donnéeStockée.getTexte() + "\n" + donnéeStockée.getLien();
			ServiceQR.genererQRCode(data, cheminQR, 200, 200);
	 	} catch (IllegalArgumentException e) {
         System.err.println("Données invalides : " + e.getMessage());
	 	} catch (Exception e) {
         System.err.println("Erreur inattendue : " + e.getMessage());
	 	}
	}
	
}
