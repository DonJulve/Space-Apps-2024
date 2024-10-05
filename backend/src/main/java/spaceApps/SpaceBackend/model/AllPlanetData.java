package spaceApps.SpaceBackend.model;

public class AllPlanetData {
    private Double pl_insol;     //FLUX
    private Double pl_rade;      //PLANET RADIUS
    private Double st_rad;       //RADIO DE SU ESTRELLA
    private Double sy_dist;      //DISTANCIA DESDE LA TIERRA
    private Double pl_angsep;    //DISTANCIA ENTRE ESTRELLA Y EXOPLANETA
    private String pl_name;     //NOMBRE EXOPLANETA
    private Integer disc_year;      //AÑO DE DESCUBRIMIENTO
    private String discoverymethod; //METODO DE DESCUBRIMIENTO
    private Double pl_orbper;    //PERIODO ORBITAL
    private Double pl_masse;   //MASA DEL PLANETA
    public Double getPl_insol() {
        return pl_insol;
    }
    public Double getPl_rade() {
        return pl_rade;
    }
    public Double getSt_rad() {
        return st_rad;
    }
    public Double getSy_dist() {
        return sy_dist;
    }
    public Double getPl_angsep() {
        return pl_angsep;
    }
    public String getPl_name() {
        return pl_name;
    }
    public Integer getDisc_year() {
        return disc_year;
    }
    public String getDiscoverymethod() {
        return discoverymethod;
    }
    public Double getPl_orbper() {
        return pl_orbper;
    }
    public Double getPl_masse() {
        return pl_masse;
    }
    
    
}
