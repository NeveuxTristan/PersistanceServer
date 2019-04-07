package share.dataObject;

import java.util.Date;

/**
 * Classe représentant une visite dans le sytème
 */
public class Visite {

    /**
     * Date de création de la visite
     */
    private long timestamp;

    /**
     * Id unique de la visite
     */
    private int id;

    /**
     * Id du magasin visité
     */
    private int idMagasin;

    /**
     * Id du visiteur
     */
    private int idVisitor;

    /**
     * Date prévue de la visite
     */
    private String dateVisite;

    /**
     * La visite est-elle est effectuée
     */
    private boolean isVisiteDone;

    /**
     * commentaire facultatif
     */
    private String comment;

    public Visite() {
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }

    public int getIdVisitor() {
        return idVisitor;
    }

    public void setIdVisitor(int idVisitor) {
        this.idVisitor = idVisitor;
    }

    public String getDateVisite() {
        return dateVisite;
    }

    public void setDateVisite(String dateVisite) {
        this.dateVisite = dateVisite;
    }

    public boolean isVisiteDone() {
        return isVisiteDone;
    }

    public void setVisiteDone(boolean visiteDone) {
        isVisiteDone = visiteDone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Visite{" +
                "timestamp=" + timestamp +
                ", id=" + id +
                ", idMagasin=" + idMagasin +
                ", idVisitor=" + idVisitor +
                ", dateVisite='" + dateVisite + '\'' +
                ", isVisiteDone=" + isVisiteDone +
                ", comment='" + comment + '\'' +
                '}';
    }
}
