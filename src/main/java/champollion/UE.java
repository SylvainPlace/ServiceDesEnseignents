package champollion;

import java.util.Objects;

public class UE {
    private final String myIntitule;
    private int heureCM;
    private int heureTD;
    private int heureTP;

    public UE(String intitule) {
        myIntitule = intitule;
    }

    public String getIntitule() {
        return myIntitule;
    }

    public int getHeureCM() {
        return heureCM;
    }

    public void addHeureCM(int heureCM) {
        this.heureCM += heureCM;
    }

    public int getHeureTD() {
        return heureTD;
    }

    public void addHeureTD(int heureTD) {
        this.heureTD += heureTD;
    }

    public int getHeureTP() {
        return heureTP;
    }

    public void addHeureTP(int heureTP) {
        this.heureTP += heureTP;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.myIntitule);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UE other = (UE) obj;
        if (!Objects.equals(this.myIntitule, other.myIntitule)) {
            return false;
        }
        return true;
    }

    
}
