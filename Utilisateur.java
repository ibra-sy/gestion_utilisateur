package gestion_utilisateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utilisateur {
    private int id;
    private String identifiant;
    private String motDePasse;
    private static int nextId = 1;
    private static List<Utilisateur> utilisateurs = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public Utilisateur(String identifiant, String motDePasse) {
        this.id = nextId++;
        this.identifiant = identifiant;
        this.motDePasse = motDePasse;
    }

    public boolean authentification(String identifiant, String motDePasse) {
        return this.identifiant.equals(identifiant) && this.motDePasse.equals(motDePasse);
    }

    public static void afficherMenu() {
        while (true) {
            System.out.println("\n******************************");
            System.out.println("      GESTION DES UTILISATEURS      ");
            System.out.println("******************************");
            System.out.println("1: Ajouter un utilisateur");
            System.out.println("2: Lister les utilisateurs");
            System.out.println("3: Modifier un utilisateur");
            System.out.println("4: Supprimer un utilisateur");
            System.out.println("5: Authentification");
            System.out.println("0: Quitter");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    ajouterUtilisateur();
                    break;
                case 2:
                    listerUtilisateurs();
                    break;
                case 3:
                    modifierUtilisateur();
                    break;
                case 4:
                    supprimerUtilisateur();
                    break;
                case 5:
                    verifierAuthentification();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private static void ajouterUtilisateur() {
        System.out.print("Identifiant : ");
        String identifiant = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();
        utilisateurs.add(new Utilisateur(identifiant, motDePasse));
        System.out.println("✅ Utilisateur ajouté !");
    }

    private static void listerUtilisateurs() {
        if (utilisateurs.isEmpty()) {
            System.out.println("Aucun utilisateur enregistré.");
        } else {
            for (Utilisateur u : utilisateurs) {
                System.out.println("ID: " + u.id + " | Identifiant: " + u.identifiant);
            }
        }
    }

    private static void modifierUtilisateur() {
        System.out.print("ID de l'utilisateur à modifier : ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        for (Utilisateur u : utilisateurs) {
            if (u.id == id) {
                System.out.print("Nouveau identifiant : ");
                u.identifiant = scanner.nextLine();
                System.out.print("Nouveau mot de passe : ");
                u.motDePasse = scanner.nextLine();
                System.out.println("✅ Utilisateur modifié !");
                return;
            }
        }
        System.out.println("❌ Utilisateur introuvable.");
    }

    private static void supprimerUtilisateur() {
        System.out.print("ID de l'utilisateur à supprimer : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Utilisateur u : utilisateurs) {
            if (u.id == id) {
                utilisateurs.remove(u);
                System.out.println("✅ Utilisateur supprimé !");
                return;
            }
        }
        System.out.println("❌ Utilisateur introuvable.");
    }

    private static void verifierAuthentification() {
        System.out.print("Identifiant : ");
        String identifiant = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String motDePasse = scanner.nextLine();

        for (Utilisateur u : utilisateurs) {
            if (u.authentification(identifiant, motDePasse)) {
                System.out.println("✅ Connexion réussie !");
                return;
            }
        }
        System.out.println("❌ Identifiant ou mot de passe incorrect.");
    }
}
