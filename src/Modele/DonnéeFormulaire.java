package Modele;

public class DonnéeFormulaire {
	private String texte;
	private String lien;
	
	public DonnéeFormulaire(String texte, String lien) {
		this.texte = texte;
		this.lien = lien;
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
}
