package Controleur;

import Modele.DonnéeFormulaire;
import Vue.UserInterface;

public class ControleurFormulaire {
	private UserInterface uinterface;
	
	public ControleurFormulaire() {
		uinterface = new UserInterface(this);
		uinterface.setVisible(true);
	}
	
	public void demandeUInterfaceDonnées(Object données) {
		String[] Data = (String[]) données;
		DonnéeFormulaire donnéeStockée = new DonnéeFormulaire(Data[0], Data[1]);
		System.out.println("Formulaire reçu :" + donnéeStockée);
	}
	
}
