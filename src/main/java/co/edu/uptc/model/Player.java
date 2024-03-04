package co.edu.uptc.model;

public class Player {
    private String name;
    private String ubication;
    private String zoneId;
    private int numParties;
    private int points;
    private int lauchess;

    public int getLauchess() {
        return lauchess;
    }

    public void setLauchess(int lauchess) {
        this.lauchess = lauchess;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    public int getNumParties() {
        return numParties;
    }

    public void setNumParties(int numParties) {
        this.numParties = numParties;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
