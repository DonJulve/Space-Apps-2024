package spaceApps.SpaceBackend.model;

public class PlanetDataReply {
    private double habitabilidad;
    private String name;
    private double gravity;
    private double distanceToStar;
    private double yearDuration;
    public PlanetDataReply(double habitabilidad, String name, double gravity, double distanceToStar, double yearDuration) {
        this.habitabilidad = habitabilidad;
        this.name = name;
        this.gravity = gravity;
        this.distanceToStar = distanceToStar;
        this.yearDuration = yearDuration;
    }
    
    public double getHabitabilidad() {
        return habitabilidad;
    }
    public void setHabitabilidad(double habitabilidad) {
        this.habitabilidad = habitabilidad;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public double getGravity() {
        return gravity;
    }
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }
    
    public double getDistanceToStar() {
        return distanceToStar;
    }
    public void setDistanceToStar(double distanceToStar) {
        this.distanceToStar = distanceToStar;
    }
    
    public double getYearDuration() {
        return yearDuration;
    }
    public void setYearDuration(double yearDuration) {
        this.yearDuration = yearDuration;
    }
    
  
}
