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
		if (lien == null || lien.trim().isEmpty()) {
            throw new IllegalArgumentException("Le lien ne peut pas être vide.");
        }
		this.texte = texte;
	}

	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		if (texte == null || texte.trim().isEmpty()) {
            throw new IllegalArgumentException("Le texte ne peut pas être vide.");
        }
		this.lien = lien;
	}
}
