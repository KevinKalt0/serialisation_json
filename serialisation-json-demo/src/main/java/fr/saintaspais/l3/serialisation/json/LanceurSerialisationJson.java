package fr.saintaspais.l3.serialisation.json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class LanceurSerialisationJson {

	private static final String DISPLAY_SEP = "--------------------------------------------------";
	
	// 1 - System.getProperty("...") permet de lire les propriétés Java et Système
	// 2 - "user.dir" : dossier de lancement de l'application courante 
	// (généralement depuis le workspace qui contient ce projet)
	private static final String FILE_SEPARATOR = System.getProperty("file.separator"); 
	
	public static void main(String[] args) throws IOException {
		
		/*
		 * Vérification du répertoire de génération de fichiers 
		 * (s'il n'existe pas il sera créé)
		 * 
		 * 	Ex: le répertoire => USER_DIR/target/serialisation/json/
		 */
		String cheminRepertoireDeDemo = String.join("", 
				System.getProperty("user.dir"),
				FILE_SEPARATOR,
				"target",
				FILE_SEPARATOR,
				"serialisation",
				FILE_SEPARATOR,
				"json",
				FILE_SEPARATOR);
		
		checkIfFolderExistsOrElseCreate(cheminRepertoireDeDemo);

		separator();
		
		/*
		 * Vérification du fichier à utiliser (s'il n'existe pas il sera créé vide)
		 * 
		 * Ex: le répertoire => USER_DIR/serialisation/json/etudiant.json
		 */
		String cheminFichierJson = String.join("", cheminRepertoireDeDemo, "etudiant.json");
		File fichier = checkIfOutputFileExistsOrElseCreate(cheminFichierJson);

		separator();

		/*
		 * Sérialisation d'un objet en JSON
		 */
		
		System.out.println("Sérialisation d'un objet Etudiant en flux JSON (dans le fichier de sortie ci-dessus)");

		// Création de l'objet à sérialiser
		Etudiant c = new Etudiant();
		c.setNom("DOE");
		c.setPrenom("John");
		c.setAge(35);
		c.setEcole("SA");
		c.setEntreprise("Tudev");
		c.setSpecialite("Java/JEE");
		
		System.out.println("Objet à écrire : " + c);

		// Ecriture JSON
		try (FileOutputStream fos = new FileOutputStream(fichier)) {
			
			// Le ObjectMapper permet de transformer l'objet Java
			// en flux JSON
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			
			// Ecriture de l'objet dans le flux indiqué (flux de sortie de type écriture fichier)
			mapper
				.writerWithDefaultPrettyPrinter()
				.writeValue(fos, c);

			System.out.println("Fin d'écriture");
			System.out.println(DISPLAY_SEP);

		} catch (FileNotFoundException e) {
			System.err.println("Erreur : " + e);
			throw e;
		} catch (IOException e) {
			System.err.println("Erreur : " + e);
			e.printStackTrace();
		}

		/*
		 * Désérialisation de l'objet écrit ci-dessus à partir du même fichier
		 */
		System.out.println("Lecture d'un objet sérialisé");
		separator();

		ObjectMapper mapper = new ObjectMapper();
		Etudiant e = mapper.readValue(fichier, Etudiant.class);
		System.out.println("Lecture de l'objet sérialisé à partir du fichier : ");
		System.out.println(fichier);
		
		System.out.println();
		
		System.out.println("Objet obtenu : ");
		System.out.println(e);
	}

	private static void separator() {
		System.out.println(DISPLAY_SEP);
		System.out.println();
	}

	private static File checkIfOutputFileExistsOrElseCreate(String cheminFichierEtudiantJson) throws IOException {
		System.out.println("Vérification de l'existence du fichier : " + cheminFichierEtudiantJson);
		File fichier = new File(cheminFichierEtudiantJson);
		if (!fichier.exists()) {
			System.out.println("-- Fichier introuvable => Création ... ");
			boolean created = fichier.createNewFile();
			if(!created)  {
				System.err.println("Erreur de création du fichier : ");
				System.err.println(cheminFichierEtudiantJson);
			}
		}
		return fichier;
	}

	private static File checkIfFolderExistsOrElseCreate(String repertoireDeDemoSerialisation) {
		System.out.println("Vérification de l'existence du répertoire : " + repertoireDeDemoSerialisation);
		File repertoire = new File(repertoireDeDemoSerialisation);
		if (!repertoire.exists()) {
			System.out.println("-- Répertoire introuvable => Création ... ");
			boolean mkdirs = repertoire.mkdirs();
			if(!mkdirs) {
				System.err.println("Erreur de création du dossier : ");
				System.err.println(repertoireDeDemoSerialisation);
			}
		}
		return repertoire;
	}

}
