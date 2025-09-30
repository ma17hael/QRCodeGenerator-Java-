package Controleur;
import Modele.DonnéeFormulaire;
import Modele.DonnéesPersonnalisation;
import Services.ServicePDF;
import Vue.PersonalizationInterface;
import Vue.UserInterface;

public class ControleurFormulaire {
	private UserInterface uinterface;
	private PersonalizationInterface pinterface;
	private DonnéeFormulaire donnéeStockée;
	private String CheminPdf;
	
	public ControleurFormulaire() {
		uinterface = new UserInterface(this);
		pinterface = new PersonalizationInterface(this);
		uinterface.setVisible(true);
		uinterface.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosed(java.awt.event.WindowEvent e) {
				pinterface.setVisible(true);
			}
		});
	}
	
	public static void main(String[] args) {
		new ControleurFormulaire();
	}
	
	public void demandePersonalization(DonnéesPersonnalisation pers) {
		donnéeStockée.setDonnéesPers(pers);
		ServicePDF.generatePDF(donnéeStockée.getTitre(), donnéeStockée.getTexte(), donnéeStockée.getLien(), CheminPdf, donnéeStockée.getDonnéesPers());
		
	}
	
	public void demandeUInterfaceDonnées(String titre, String texte, String lien, String CheminPDF) {
        try {
            donnéeStockée = new DonnéeFormulaire(titre, texte, lien);
            CheminPdf = CheminPDF;
        } catch (IllegalArgumentException e) {
        	throw new IllegalArgumentException("Champs manquants ou chemin invalide :" + e.getMessage());
        } catch (Exception e) {
        	throw new IllegalArgumentException("Erreur Innatendu : " + e.getMessage());
        }
    }
	
}
