package spaceApps.SpaceBackend.model;

public class PlanetDataRequest {
    private float diametroLente;
    private int habitabilidad; //PORCENTAJE DE HABITABILIDAD
    private float distanciaTierra; //DISTANCIA EN PARSEC
    private float proporcionGravedad;   //Proporcion en cuanto a gravedad de la tierra
    
    public PlanetDataRequest() {
    } 
    public float getDiametroLente() {
        return diametroLente;
    }
    public void setDiametroLente(float diametroLente) {
        this.diametroLente = diametroLente;
    }
    
    public int getHabitabilidad() {
        return habitabilidad;
    }
    public void setHabitabilidad(int habitabilidad) {
        this.habitabilidad = habitabilidad;
    }
    
    public float getDistanciaTierra() {
        return distanciaTierra;
    }
    public void setDistanciaTierra(float distanciaTierra) {
        this.distanciaTierra = distanciaTierra;
    }
    
    public float getProporcionGravedad() {
        return proporcionGravedad;
    }
    public void setProporcionGravedad(float proporcionGravedad) {
        this.proporcionGravedad = proporcionGravedad;
    }
    

}
