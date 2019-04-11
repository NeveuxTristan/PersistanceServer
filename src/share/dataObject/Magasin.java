package share.dataObject;

import share.enumUtils.EnumEnseigne;

import java.io.Serializable;

/**
 * Classe représentant un magasin dans le système
 */
public class Magasin implements Serializable {

    /**
     * Identifiant unique du magasin
     */
    private int id;

    /**
     * Enseigne du magasin
     */
    private EnumEnseigne enseigne;

    /**
     * Ville du magasin
     */
    private int idVille;

    public Magasin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnumEnseigne getEnseigne() {
        return enseigne;
    }

    public void setEnseigne(EnumEnseigne enseigne) {
        this.enseigne = enseigne;
    }

    public int getVille() {
        return idVille;
    }

    public void setVille(int ville) {
        this.idVille = ville;
    }

    public String getDisplayName() {
        return this.enseigne + "";
    }

    @Override
    public String toString() {
        return "Magasin[" + this.id + "] " + getDisplayName();
    }
}
