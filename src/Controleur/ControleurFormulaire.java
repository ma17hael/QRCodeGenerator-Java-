package Controleur;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Modele.DonnéeFormulaire;
import Modele.DonnéesPersonnalisation;
import Modele.ProjectPDF;
import Services.ServicePDF;
import Vue.PersonalizationInterface;
import Vue.UserInterface;

public class ControleurFormulaire {
	private UserInterface uinterface;
	private PersonalizationInterface pinterface;
	private DonnéeFormulaire donnéeStockée;
	private String CheminPdf;
	private String CheminImage;
	
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
		ServicePDF.generatePDF(donnéeStockée.getTitre(), donnéeStockée.getTexte(), donnéeStockée.getLien(), CheminPdf, CheminImage, donnéeStockée.getDonnéesPers());
		
	}
	
	public void demandeUInterfaceDonnées(String titre, String texte, String lien, String CheminPDF, String CheminIMG) {
		if (titre == null || titre.isEmpty() ||
		        texte == null || texte.isEmpty() ||
		        lien == null || lien.isEmpty() ||
		        CheminPDF == null || CheminPDF.isEmpty() ||
		        CheminIMG == null || CheminIMG.isEmpty()) {
		        throw new IllegalArgumentException("Champs manquants ou chemin invalide");
		    }
        try {
            donnéeStockée = new DonnéeFormulaire(titre, texte, lien);
            CheminPdf = CheminPDF;
            CheminImage = CheminIMG;
        } catch (IllegalArgumentException e) {
        	throw new IllegalArgumentException("Champs manquants ou chemin invalide :" + e.getMessage());
        } catch (Exception e) {
        	throw new IllegalArgumentException("Erreur Innatendu : " + e.getMessage());
        }
    }

	public void SauvegardeProject(DonnéesPersonnalisation perso, int choix) {
		JFileChooser chooser = new JFileChooser();
	    if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
	        File fichier = chooser.getSelectedFile();
	        String chemin = fichier.getAbsolutePath();
	        if (!chemin.toLowerCase().endsWith(".json")) {
	            chemin += ".json";
	        }

	        // Création du projet
	        ProjectPDF projet = new ProjectPDF();
	        // Récupérer le contenu depuis ta JFrame "contenu"
	        projet.setTitre(donnéeStockée.getTitre().trim());
	        projet.setTexte(donnéeStockée.getTexte().trim());
	        projet.setLien(donnéeStockée.getLien().trim());
	        projet.setCheminPDF(CheminPdf);
	        projet.setCheminImage(CheminImage); // ton champ chemin image

	        // Vérifie si on doit inclure la personnalisation
	        if (choix == JOptionPane.YES_OPTION && perso != null) {
	            projet.setPersonnalisation(perso);
	        } else {
	            projet.setPersonnalisation(null);
	        }

	        // Sauvegarde dans le fichier
	        try {
	            ProjectPDF.sauvegarderProjet(projet, chemin);
	            JOptionPane.showMessageDialog(null, "Projet sauvegardé avec succès !");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Erreur lors de la sauvegarde : " + ex.getMessage());
	        }
	    }
	}
	
	public void ChargerProject() {
	    JFileChooser chooser = new JFileChooser();
	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	        File fichier = chooser.getSelectedFile();
	        try {
	            ProjectPDF projet = ProjectPDF.chargetProjet(fichier.getAbsolutePath());

	            // --- Remplissage de la JFrame contenu ---
	            uinterface.TitleTextField.setText(projet.getTitre());
	            uinterface.TextField.setText(projet.getTexte());
	            uinterface.LinkTextField.setText(projet.getLien());
	            uinterface.cheminPDFField.setText(projet.getCheminPDF());
	            uinterface.IMGPath.setText(projet.getCheminImage());

	            // --- Remplissage de la JFrame personnalisation ---
	            DonnéesPersonnalisation perso = projet.getPersonnalisation();
	            if (perso != null) {
	                // Titre
	            	pinterface.FontComboBox.setSelectedItem(perso.getTitleFontName());
	            	pinterface.SizeComboBox.setSelectedItem(perso.getTitleFontSize());
	            	pinterface.BoldCheckBox.setSelected(perso.isTitleBold());
	            	pinterface.ItalicCheckBox.setSelected(perso.isTitleItalic());
	            	pinterface.UnderlineCheckBox.setSelected(perso.isTitleUnderline());
	            	pinterface.TextPersonalizationPreview.setForeground(perso.getTitleColor().toColor());

	                // Texte
	            	pinterface.FontComboBox_1.setSelectedItem(perso.getTextFontName());
	            	pinterface.SizeComboBox_1.setSelectedItem(perso.getTextFontSize());
	            	pinterface.BoldCheckBox_1.setSelected(perso.isTextBold());
	            	pinterface.ItalicCheckBox_1.setSelected(perso.isTextItalic());
	            	pinterface.UnderlineCheckBox_1.setSelected(perso.isTextUnderline());
	            	pinterface.TextPersonalizationPreview_1.setForeground(perso.getTextColor().toColor());

	                // IMAGE / QRCode
	            	pinterface.ImageLengthField.setText(String.valueOf(perso.getIMGLength()));
	            	pinterface.ImageWidthField.setText(String.valueOf(perso.getIMGWidth()));
	            	pinterface.ImagePositionComboBox.setSelectedItem(perso.getIMGPosition());

	            	pinterface.QRCodeLengthField.setText(String.valueOf(perso.getQRLength()));
	            	pinterface.QRCodeWidthField.setText(String.valueOf(perso.getQRWidth()));
	            	pinterface.QRCodePositionComboBox.setSelectedItem(perso.getQRPosition());
	            }

	            JOptionPane.showMessageDialog(null, "Projet chargé avec succès !");
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Erreur lors du chargement : " + ex.getMessage());
	        }
	    }
	}
	
}
