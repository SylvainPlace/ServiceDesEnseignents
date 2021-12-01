package champollion;

import java.util.Date;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ChampollionJUnitTest {

    Enseignant untel;
    UE uml, java;

    @BeforeEach
    public void setUp() {
        untel = new Enseignant("untel", "untel@gmail.com");
        uml = new UE("UML");
        java = new UE("Programmation en java");
    }

    @Test
    public void testNouvelEnseignantSansService() {
        assertEquals(0, untel.heuresPrevues(),
                "Un nouvel enseignant doit avoir 0 heures prévues");
    }

    @Test
    public void testAjouteHeures() {
        // 10h TD pour UML
        untel.ajouteEnseignement(uml, 0, 10, 0);

        assertEquals(10, untel.heuresPrevuesPourUE(uml),
                "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

        // 20h TD pour UML
        untel.ajouteEnseignement(uml, 0, 20, 0);

        assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");

    }

    @Test
    public void testHeurePrevuPourUE() {
        // 10h TD pour UML
        untel.ajouteEnseignement(uml, 0, 10, 0);

        assertEquals(0, untel.heuresPrevuesPourUE(java),
                "L'enseignant doit avoir aucune heure prévue pour l'UE 'java'");
    }

    @Test
    public void testIntervention() {
        Salle salle = new Salle("B105", 20);
        Date date = new Date();
        Intervention inter1 = new Intervention(date, 4, 0, salle, uml, TypeIntervention.TD);
        Intervention inter2 = new Intervention(date, 2, 0, salle, uml, TypeIntervention.TP);
        untel.ajouteIntervention(inter1);
        untel.ajouteIntervention(inter2);
        // 10h TD pour UML
        untel.ajouteEnseignement(uml, 5, 10, 2);

        assertEquals(6, untel.resteAPlanifier(uml, TypeIntervention.TD),
                "Il y a des heures restantes a planifier");
        assertEquals(5, untel.resteAPlanifier(uml, TypeIntervention.CM),
                "Il y a des heures restantes a planifier");
        assertEquals(0, untel.resteAPlanifier(uml, TypeIntervention.TP),
                "Il n'y a pzs d'heures restantes a planifier");
    }

    @Test
    public void testSousService() {
        // 10h TD pour UML
        untel.ajouteEnseignement(uml, 0, 10, 0);

        assertTrue(untel.enSousService(),
                "L'enseignant doit être en sous-service");

        untel.ajouteEnseignement(java, 0, Enseignant.HEURESSERVICE - 10, 0);
        assertFalse(untel.enSousService(),
                "L'enseignant ne doit plus être en sous-service");

    }

}
