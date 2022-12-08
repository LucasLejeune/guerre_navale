package fr.lejeune;

import java.util.ArrayList;

public class Navire {

    private int taille;
    private String representation;
    private String direction;
    private ArrayList<String> coordonnees = new ArrayList<String>();
    private int HP;

    public int getTaille() {
        return taille;
    }

    public String getRepresentation() {
        return representation;
    }

    public Navire(int taille, String representation, int type, String coordonnees, String direction) {
        this.direction = direction;
        if (type == 1) {
            this.taille = taille;
            this.representation = "@";
        } else if (type == 2) {
            this.taille = taille;
            this.representation = "!";
        } else if (type == 3) {
            this.taille = taille;
            this.representation = "#";
        } else {
            this.taille = taille;
            this.representation = "/";
        }
        if (direction.equals("D")) {
            for (int i = 0; i < type; i++) {
                int colonne = Integer.parseInt(coordonnees.substring(2, 3)) + i;
                String cos = coordonnees.substring(0, 1) + " " + Integer.toString(colonne);
                this.coordonnees.add(cos);
            }
        } else if (direction.equals("G")) {
            for (int i = 0; i < type; i++) {
                int colonne = Integer.parseInt(coordonnees.substring(2, 3)) - i;
                String cos = coordonnees.substring(0, 1) + " " + Integer.toString(colonne);
                this.coordonnees.add(cos);
            }
        } else if (direction.equals("H")) {
            for (int i = 0; i < type; i++) {
                int colonne = Integer.parseInt(coordonnees.substring(0, 1)) - i;
                String cos = Integer.toString(colonne) + " " + coordonnees.substring(2, 3);
                this.coordonnees.add(cos);
            }
        } else {
            for (int i = 0; i < type; i++) {
                int colonne = Integer.parseInt(coordonnees.substring(0, 1)) + i;
                String cos = Integer.toString(colonne) + " " + coordonnees.substring(2, 3);
                this.coordonnees.add(cos);
            }
        }

    }

    public ArrayList<String> getCoordonnees() {
        return coordonnees;
    }

}
