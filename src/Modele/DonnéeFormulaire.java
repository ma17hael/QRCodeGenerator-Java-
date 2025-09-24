package Modele;

public class DonnéeFormulaire {
	private String texte;
	private String lien;
	private String titre;
	
	public DonnéeFormulaire(String titre, String texte, String lien) {
		this.titre = titre;
		this.texte = texte;
		this.lien = lien;
	}
	

	public String getTitre() {
		return titre;
	}


	public void setTitre(String titre) {
		if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre ne peux pas être vide");
        }
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
		if (texte == null || texte.trim().isEmpty()) {
            throw new IllegalArgumentException("Le lien ne peut pas être vide.");
        }
		this.lien = lien;
	}
}
