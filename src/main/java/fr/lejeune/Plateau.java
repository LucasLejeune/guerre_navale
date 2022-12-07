package fr.lejeune;

import java.util.ArrayList;

public class Plateau {
    private int ligne;
    private int colonne;
    private int nbNavireUp = 5;
    private int nbPorteAvionDispo = 1; // 4 case (/)
    private int nbCroiseursDispo = 2; // 3 cases (#)
    private int nbTorpilleursDispo = 3; // 2 cases (!)
    private int nbSousMarinsDispos = 4; // 1 case (@)

    private String[][] grille;

    private String[][] ChampBataille;

    private ArrayList<String> nombres = new ArrayList<String>();

    public Plateau(int numJoueur, int dimention) {

        grille = new String[dimention][dimention];
        ChampBataille = new String[dimention][dimention];

        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[0].length; j++) {
                grille[i][j] = "-";
            }
        }

        for (int i = 0; i < ChampBataille.length; i++) {
            for (int j = 0; j < ChampBataille[0].length; j++) {
                ChampBataille[i][j] = "-";
            }
        }

        ArrayList<String> tab = new ArrayList<String>();

        for (int i = 0; i < dimention; i++) {
            nombres.add(Integer.toString(i));
        }

        int max = dimention - 1;

        System.out.println(
                "JOUEUR " + numJoueur
                        + ",entrez les coordonnées de vos navires, sous la forme 'L C'\n(L représente la ligne entre 0 et "
                        + max + " et C la colonne entre 0 et " + max + ").");
        int i = 1;
        while (i < 6) {
            System.out.println("Entrez les coordonnées du navire " + i + ":");
            String ligne = System.console().readLine();
            System.out.println("Selectionner le type de navire souhaité:\n1: Porte-avion (taille: 4, disponible(s):"
                    + nbPorteAvionDispo + ", représenté par: '/')\n2: Croiseur (taille: 3, disponible(s): "
                    + nbCroiseursDispo + ", représenté par: '#')\n3: Torpilleur (taille: 2, disponible(s): "
                    + nbTorpilleursDispo + ", représenté par: '!')\n4: Sous-marin (taille: 1, disponible(s): "
                    + nbSousMarinsDispos + ", représenté par: '@') ");
            String navire = System.console().readLine();

            if (!navire.equals("1")) {
                System.out.println(
                        "Dans quelle direction l'orienter ? (G pour gauche, D pour droite, H pour haut ou B pour bas)");
                String direction = System.console().readLine();

            }
            int verif = i;
            for (String j : tab) {
                if (j.equals(ligne)) {
                    System.out.println("Vous avez déjà un navire à cet endroit. Veuillez réessayer.");
                    verif--;
                }
            }

            if (ligne.length() != 3) {
                System.out.println("Coordonnées invalides. Veuillez réessayer.");
                verif--;
            } else {
                Boolean bonCord = nombres.contains(ligne.substring(0, 1)) && nombres.contains(ligne.substring(2, 3));
                if (bonCord == false) {
                    System.out.println("Coordonnées invalides. Veuillez réessayer.");
                    verif--;
                }
            }

            if (i == verif) {
                i++;
                tab.add(ligne);
            }

        }
        for (String k : tab) {
            ligne = Integer.parseInt(k.substring(0, 1));
            colonne = Integer.parseInt(k.substring(2, 3));
            grille[ligne][colonne] = "@";
        }
    }

    public String[][] getGrille() {
        return grille;
    }

    public void tir(int numJoueur) {

        System.out.println("JOUEUR " + numJoueur + ", entrez les coordonnées de tir:");
        String coordonnes = System.console().readLine();
        if (coordonnes.length() != 3) {
            System.out.println("Coordonnées invalides. Veuillez réessayer.");
        } else {
            Boolean bonCord = nombres.contains(coordonnes.substring(0, 1))
                    && nombres.contains(coordonnes.substring(2, 3));
            if (bonCord == false) {
                System.out.println("Coordonnées invalides. Veuillez réessayer.");
            }
        }
        System.out.println("\nJOUEUR " + numJoueur + ":");

        tirRecu(coordonnes);

    }

    public void affichageGrille() {
        String nbs = "";
        for (String nb : nombres) {
            nbs += nb + " ";
        }
        System.out.println("  " + nbs);
        int compteur = 0;
        for (String[] elements : grille) {
            String retour = compteur + " ";
            for (String element : elements) {
                retour += element + " ";
            }
            System.out.println(retour);
            compteur++;
        }
    }

    public void tirRecu(String coordonnes) {
        if (coordonnes.length() != 3 && !coordonnes.substring(0, 1).equals(" ")) {
            System.out.println("Coordonnées invalides veuillez réessayer");
            tirRecu(System.console().readLine());
            return;
        }
        ligne = Integer.parseInt(coordonnes.substring(0, 1));
        colonne = Integer.parseInt(coordonnes.substring(2, 3));
        if (ChampBataille[ligne][colonne] == "O" || ChampBataille[ligne][colonne] == "X") {
            System.out.println("Vous avez déjà ciblé cet endroit. Choisissez des coordonnées différentes.");
            tirRecu(System.console().readLine());
            return;
        } else if (grille[ligne][colonne].equals("-")) {
            System.out.println("COUP DANS L'EAU !");
            ChampBataille[ligne][colonne] = "O";
        } else {
            System.out.println("COULÉ");
            ChampBataille[ligne][colonne] = "X";
            nbNavireUp--;
        }
        String nbs = "";
        for (String nb : nombres) {
            nbs += nb + "  ";
        }
        System.out.println(" " + nbs);
        int compteur = 0;
        for (String[] elements : ChampBataille) {
            String retour = compteur + " ";
            for (String element : elements) {
                retour += element + " ";
            }
            System.out.println(retour);
            compteur++;
        }
        System.out.println("\n\n");

    }

    public boolean aPerdu() {
        if (nbNavireUp == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int getNbNavireUp() {
        return nbNavireUp;
    }

    public void afficherFinale() {
        String nbs = "";
        for (String nb : nombres) {
            nbs += nb + "  ";
        }
        System.out.println(" " + nbs);
        int compteur = 0;
        int colonne = 0;
        for (String[] elements : grille) {
            int ligne = 0;
            for (String element : elements) {
                if (element == "@" && ChampBataille[ligne][colonne] != "X") {
                    ChampBataille[ligne][colonne] = "@";
                }
                ligne++;
            }
            colonne++;
        }
        for (String[] elements : ChampBataille) {
            String retour = compteur + " ";
            for (String element : elements) {
                retour += element + " ";
            }
            System.out.println(retour);
            compteur++;
        }
    }

}
