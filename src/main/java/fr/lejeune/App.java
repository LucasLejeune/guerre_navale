package fr.lejeune;

public class App {

    public static void main(String[] args) {
        System.out.println("Bienvenue dans la guerre marine !");
        Boolean isEnd = false;

        Plateau j1 = new Plateau(1, 10); // max 10
        j1.affichageGrille();

        System.out.println("Appuyer sur entrée pour passer au joueur suivant");
        System.console().readLine();

        for (int i = 0; i < 100; i++) {
            System.out.println("\n");
        }

        Plateau j2 = new Plateau(2, 10);
        j2.affichageGrille();

        System.out.println("Appuyer sur entrée pour commencer la partie");
        System.console().readLine();

        for (int i = 0; i < 100; i++) {
            System.out.println("\n");
        }

        while (isEnd == false) {
            j2.tir(1);
            if (j2.aPerdu()) {
                fin(j1, 1, j2);
                break;
            }
            j1.tir(2);
            if (j1.aPerdu()) {
                fin(j2, 2, j1);
                isEnd = true;
            }
        }

    }

    public static void fin(Plateau gagnant, int numJoueur, Plateau perdant) {
        System.out.println(
                "JOUEUR " + numJoueur + " A GAGNE ! " + gagnant.getNbNavireUp()
                        + " DE VOS NAVIRES SONT ENCORE DEBOUT\n\nGrilles finales:");
        gagnant.afficherFinale();
        System.out.println("\n");
        perdant.afficherFinale();
    }

}
