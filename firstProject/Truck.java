package com.onepoint.enseirb.firstProject;

public class Truck {
	private String matricule;
	private String modele;
	private String couleur;

	public Truck(String matricule, String modele, String couleur) {
		super();
		this.matricule = matricule;
		this.modele = modele;
		this.couleur = couleur;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

}
