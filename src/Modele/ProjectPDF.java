package Modele;

import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Color;
import Services.ColorAdapter;

public class ProjectPDF {
	private String titre;
	private String texte;
	private String lien;
	private String cheminPDF;
	private String cheminImage;
	private DonnéesPersonnalisation personnalisation;
	private static Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Color.class, new ColorAdapter()).create();
	
	public static void sauvegarderProjet(ProjectPDF projet, String Chemin) throws Exception {
		try (FileWriter writer = new FileWriter(Chemin)) {
			gson.toJson(projet, writer);
		}
	}
	
	public static ProjectPDF chargetProjet(String chemin) throws Exception {
		try (FileReader reader = new FileReader(chemin)) {
			return gson.fromJson(reader, ProjectPDF.class);
		}
	}
	
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	
	public String getTexte() {
		return texte;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
	
	public String getLien() {
		return lien;
	}
	public void setLien(String lien) {
		this.lien = lien;
	}
	
	public String getCheminPDF() {
		return cheminPDF;
	}
	public void setCheminPDF(String cheminPDF) {
		this.cheminPDF = cheminPDF;
	}

	public String getCheminImage() {
		return cheminImage;
	}
	public void setCheminImage(String cheminImage) {
		this.cheminImage = cheminImage;
	}
	
	public DonnéesPersonnalisation getPersonnalisation() {
		return personnalisation;
	}
	public void setPersonnalisation(DonnéesPersonnalisation personnalisation) {
		this.personnalisation = personnalisation;
	}
	
	
}
