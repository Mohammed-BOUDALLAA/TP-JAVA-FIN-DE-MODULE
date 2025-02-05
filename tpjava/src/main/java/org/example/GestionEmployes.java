package org.example;

import java.util.*;

// Classe Employe
class Employe {
    private int id;
    private String nom;
    private String poste;
    private double salaire;

    // Constructeur avec tous les attributs
    public Employe(int id, String nom, String poste, double salaire) {
        this.id = id;
        this.nom = nom;
        this.poste = poste;
        this.salaire = salaire;
    }

    // Constructeur par défaut
    public Employe() {
        this(0, "", "", 0.0);
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "Employe [id=" + id + ", nom=" + nom + ", poste=" + poste + ", salaire=" + salaire + "]";
    }

    // Méthode statique pour comparer les salaires
    public static int compareParSalaire(Employe e1, Employe e2) {
        return Double.compare(e1.getSalaire(), e2.getSalaire());
    }
}

// Classe principale GestionEmployes
public class GestionEmployes {
    private static final int MAX_EMPLOYES = 50;
    private static Employe[] tableauEmployes = new Employe[MAX_EMPLOYES];
    private static int nombreEmployes = 0;

    // Afficher le menu principal
    public static void printMenu() {
        System.out.println("\nMenu Principal :");
        System.out.println("1. Ajouter un employé");
        System.out.println("2. Modifier un employé");
        System.out.println("3. Supprimer un employé");
        System.out.println("4. Afficher la liste des employés");
        System.out.println("5. Rechercher un employé");
        System.out.println("6. Calculer la masse salariale");
        System.out.println("7. Trier les employés par salaire");
        System.out.println("8. Quitter");
    }

    // Ajouter un employé
    public static void ajouterEmploye(Employe employe) {
        if (nombreEmployes >= MAX_EMPLOYES) {
            System.out.println("Le tableau des employés est plein.");
            return;
        }
        tableauEmployes[nombreEmployes++] = employe;
        System.out.println("Employé ajouté avec succès.");
    }

    // Modifier un employé
    public static void modifierEmploye(int id, String nouveauNom, String nouveauPoste, double nouveauSalaire) {
        for (int i = 0; i < nombreEmployes; i++) {
            if (tableauEmployes[i].getId() == id) {
                tableauEmployes[i].setNom(nouveauNom);
                tableauEmployes[i].setPoste(nouveauPoste);
                tableauEmployes[i].setSalaire(nouveauSalaire);
                System.out.println("Employé modifié avec succès.");
                return;
            }
        }
        System.out.println("Employé introuvable.");
    }

    // Supprimer un employé
    public static void supprimerEmploye(int id) {
        for (int i = 0; i < nombreEmployes; i++) {
            if (tableauEmployes[i].getId() == id) {
                tableauEmployes[i] = tableauEmployes[nombreEmployes - 1];
                tableauEmployes[--nombreEmployes] = null;
                System.out.println("Employé supprimé avec succès.");
                return;
            }
        }
        System.out.println("Employé introuvable.");
    }

    // Afficher tous les employés
    public static void afficherEmployes() {
        if (nombreEmployes == 0) {
            System.out.println("Aucun employé à afficher.");
            return;
        }
        for (int i = 0; i < nombreEmployes; i++) {
            System.out.println(tableauEmployes[i]);
        }
    }

    // Rechercher un employé
    public static void rechercherEmploye(String critere) {
        boolean trouve = false;
        for (int i = 0; i < nombreEmployes; i++) {
            if (tableauEmployes[i].getNom().equalsIgnoreCase(critere) ||
                    tableauEmployes[i].getPoste().equalsIgnoreCase(critere)) {
                System.out.println(tableauEmployes[i]);
                trouve = true;
            }
        }
        if (!trouve) {
            System.out.println("Aucun employé trouvé.");
        }
    }

    // Calculer la masse salariale
    public static void calculerMasseSalariale() {
        double masseSalariale = 0;
        for (int i = 0; i < nombreEmployes; i++) {
            masseSalariale += tableauEmployes[i].getSalaire();
        }
        System.out.println("Masse salariale totale : " + masseSalariale);
    }

    // Trier les employés par salaire
    public static void trierEmployesParSalaire(boolean ordreCroissant) {
        Arrays.sort(tableauEmployes, 0, nombreEmployes, ordreCroissant ?
                Comparator.comparingDouble(Employe::getSalaire) :
                Comparator.comparingDouble(Employe::getSalaire).reversed());
        afficherEmployes();
    }

    // Méthode principale
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            printMenu();
            System.out.print("\nEntrez votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la ligne restante

            switch (choix) {
                case 1:
                    System.out.print("Entrez l'ID : ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Entrez le nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrez le poste : ");
                    String poste = scanner.nextLine();
                    System.out.print("Entrez le salaire : ");
                    double salaire = scanner.nextDouble();
                    ajouterEmploye(new Employe(id, nom, poste, salaire));
                    break;
                case 2:
                    System.out.print("Entrez l'ID de l'employé à modifier : ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Entrez le nouveau nom : ");
                    nom = scanner.nextLine();
                    System.out.print("Entrez le nouveau poste : ");
                    poste = scanner.nextLine();
                    System.out.print("Entrez le nouveau salaire : ");
                    salaire = scanner.nextDouble();
                    modifierEmploye(id, nom, poste, salaire);
                    break;
                case 3:
                    System.out.print("Entrez l'ID de l'employé à supprimer : ");
                    id = scanner.nextInt();
                    supprimerEmploye(id);
                    break;
                case 4:
                    afficherEmployes();
                    break;
                case 5:
                    System.out.print("Entrez le nom ou le poste à rechercher : ");
                    String critere = scanner.nextLine();
                    rechercherEmploye(critere);
                    break;
                case 6:
                    calculerMasseSalariale();
                    break;
                case 7:
                    System.out.print("Trier par salaire (1 pour croissant, 2 pour décroissant) : ");
                    int ordre = scanner.nextInt();
                    trierEmployesParSalaire(ordre == 1);
                    break;
                case 8:
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Choix invalide, réessayez.");
            }
        } while (choix != 8);

        scanner.close();
    }
}

