package champollion;

import java.util.ArrayList;
import java.util.HashMap;

public class Enseignant extends Personne {

    private HashMap<UE, ServicePrevu> lesServicePrevu;
    private ArrayList<Intervention> lesIntervention;
    public static final int HEURESSERVICE = 192;

    public Enseignant(String nom, String email) {
        super(nom, email);
        lesServicePrevu = new HashMap<>();
        lesIntervention = new ArrayList<>();
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures
     * équivalent TD" Pour le calcul : 1 heure de cours magistral vaut 1,5 h
     * "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut
     * 0,75h "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet
     * enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        int heures = 0;
        for (UE ue : lesServicePrevu.keySet()) {
            heures += heuresPrevuesPourUE(ue);
        }
        return heures;
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE
     * spécifiée en "heures équivalent TD" Pour le calcul : 1 heure de cours
     * magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent
     * TD" 1 heure de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet
     * enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        if (lesServicePrevu.containsKey(ue)) {
            ServicePrevu sp = lesServicePrevu.get(ue);
            return Math.round(sp.getVolumeCM() * 1.5f + sp.getVolumeTD() + sp.getVolumeTP() * 0.75f);
        } else {
            return 0;
        }
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        if (lesServicePrevu.containsKey(ue)) {
            ServicePrevu sp = lesServicePrevu.get(ue);
            sp.addVolumeCM(volumeCM);
            sp.addVolumeTP(volumeTP);
            sp.addVolumeeTD(volumeTD);
        } else {
            ServicePrevu sp = new ServicePrevu(ue, volumeCM, volumeTD, volumeTP);
            lesServicePrevu.put(ue, sp);
        }
        ue.addHeureCM(volumeCM);
        ue.addHeureTD(volumeTD);
        ue.addHeureTP(volumeTP);
    }

    public void ajouteIntervention(Intervention inter) {
        lesIntervention.add(inter);
    }

    public int resteAPlanifier(UE ue, TypeIntervention type) {
        int heures = 0;
        if (lesServicePrevu.containsKey(ue)) {
            ServicePrevu sp = lesServicePrevu.get(ue);
            switch (type) {
                case CM:
                    heures += sp.getVolumeCM();
                    break;
                case TD:
                    heures += sp.getVolumeTD();
                    break;
                case TP:
                    heures += sp.getVolumeTP();
                    break;
                default:
                    break;
            }

            for (Intervention inter : lesIntervention) {
                if (inter.getType() == type && inter.getUe() == ue) {
                    heures -= inter.getDuree();
                }
            }
        }
        return heures;
    }

    /**
     * On veut pouvoir connaître les enseignants en sous-service : le service
     * normal d’un enseignant est de 192h « équivalent TD »,
     *
     * @return si un enseignant en sous service
     */
    public boolean enSousService() {
        return (heuresPrevues() < HEURESSERVICE);

    }

}
