package Modele;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

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
	
	public void validation() throws IllegalArgumentException {
		if (texte == null || texte.trim().isEmpty()) {
			throw new IllegalArgumentException("Le texte est requis !");
		}
		if (lien != null && !lien.trim().isEmpty()) {
			try {new URL(lien); }
			catch (MalformedURLException mauvaisLien) { throw new IllegalArgumentException("Lien invalide.");
			}
		}
	}
	
	@Override
	public boolean equals(Object o) {
		return Objects.equals(this, o);
	}
}
