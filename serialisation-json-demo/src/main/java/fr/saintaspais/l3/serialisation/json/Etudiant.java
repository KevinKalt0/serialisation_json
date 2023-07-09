package fr.saintaspais.l3.serialisation.json;

public class Etudiant {

	private String nom;
	
	private String prenom;
	
	private int age;
	
	private String ecole;
	private String specialite;
	private String entreprise;

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEcole() {
		return ecole;
	}
	public void setEcole(String ecole) {
		this.ecole = ecole;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	public String getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Etudiant [nom=");
		builder.append(nom);
		builder.append(", prenom=");
		builder.append(prenom);
		builder.append(", age=");
		builder.append(age);
		builder.append(", ecole=");
		builder.append(ecole);
		builder.append(", specialite=");
		builder.append(specialite);
		builder.append(", entreprise=");
		builder.append(entreprise);
		builder.append("]");
		return builder.toString();
	}
	
}
