package champollion;

public class ServicePrevu {

    private int volumeCM;
    private int volumeeTD;
    private int volumeTP;
    private UE ue;

    public ServicePrevu(UE ue, int volumeCM, int volumeeTD, int volumeTP) {
        this.volumeCM = volumeCM;
        this.volumeeTD = volumeeTD;
        this.volumeTP = volumeTP;
        this.ue = ue;
    }

    public int getVolumeCM() {
        return volumeCM;
    }

    public int getVolumeTD() {
        return volumeeTD;
    }

    public int getVolumeTP() {
        return volumeTP;
    }

    public UE getUe() {
        return ue;
    }

    public void addVolumeCM(int volumeCM) {
        this.volumeCM += volumeCM;
    }

    public void addVolumeeTD(int volumeeTD) {
        this.volumeeTD += volumeeTD;
    }

    public void addVolumeTP(int volumeTP) {
        this.volumeTP += volumeTP;
    }

}
