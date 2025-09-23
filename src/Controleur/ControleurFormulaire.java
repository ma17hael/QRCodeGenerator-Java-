package Controleur;

import Modele.DonnéeFormulaire;
import Services.ServicePDF;
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
		DonnéeFormulaire donnéeStockée = new DonnéeFormulaire(texte, lien);
		String cheminPDF = "C:\\Users\\Malthael\\Downloads\\Test.pdf";
		ServicePDF.generatePDF(donnéeStockée.getTexte(), donnéeStockée.getLien(), cheminPDF);
	}
	
}
