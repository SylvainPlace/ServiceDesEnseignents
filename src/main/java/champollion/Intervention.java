/*
 */
package champollion;

import java.util.Date;

/**
 *
 * @author SylvainPlc
 */
class Intervention {
    private Date debut;
    private int duree;
    private boolean annulee;
    private int heureDebut;
    private Salle salle;
    private UE ue;
    private TypeIntervention type;

    public Intervention(Date debut, int duree, int heureDebut, Salle salle, UE ue, TypeIntervention type) {
        this.debut = debut;
        this.duree = duree;
        this.annulee = false;
        this.heureDebut = heureDebut;
        this.salle = salle;
        this.ue = ue;
        this.type = type;
    }
    

    public Date getDebut() {
        return debut;
    }

    public int getDuree() {
        return duree;
    }

    public boolean isAnnee() {
        return annulee;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public Salle getSalle() {
        return salle;
    }

    public UE getUe() {
        return ue;
    }

    public TypeIntervention getType() {
        return type;
    }
    
    
}
