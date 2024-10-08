package spaceApps.SpaceBackend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import spaceApps.SpaceBackend.model.AllPlanetData;
import spaceApps.SpaceBackend.model.PlanetDataRequest;
import spaceApps.SpaceBackend.model.PlanetDataReply;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;



@RestController
@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
@CrossOrigin(origins = "*")
public class AppController {

            @PostMapping("/getExoplanets")
            public List<PlanetDataReply> getPlanets(@RequestBody PlanetDataRequest request) {
                List<AllPlanetData> dbData = new ArrayList<>();
                try {
                    dbData = retrieveDataFromDB(request);
                }
                catch(Exception e) {
                    System.out.println("[!] NO HA PODIDO OBTENER LOS DATOS DE LA BASE DE DATOS");
                }
                List<PlanetDataReply> planets = processData(dbData, request);
                return planets;
                // String jsonReply = "";
                // try {
                //     ObjectMapper objectMapper = new ObjectMapper();
                //     jsonReply = objectMapper.writeValueAsString(planets);
                // }
                // catch (Exception e) {
                //     System.out.println("[!] NO HEMOS PODIDO PARSEAR EL JSON CORRECTAMENTE");
                // }
                // // * PRINT
                // System.out.println(jsonReply);

                // return jsonReply;
            }

        // * FUNCIONA
        private List<AllPlanetData> retrieveDataFromDB(PlanetDataRequest request) throws Exception{
            //TEMPLATE PARA HACER PETICIONES GET
            RestTemplate restTemplate = new RestTemplate();

            //API URL DEFAULT
            /* QUERY:
            * SELECT pl_insol,pl_rade,st_rad,sy_dist,pl_angsep,pl_name,disc_year,discoverymethod,pl_orbper,pl_masse FROM pscomppars
            *    WHERE pl_name = DE CVn b AND
            *       pl_insol IS NOT NULL AND 
            *       st_rad IS NOT NULL AND 
            *       sy_dist IS NOT NULL AND
            *       pl_angsep IS NOT NULL AND
            *       pl_masse IS NOT NULL AND
            *       sy_dist > request.distanciaTierra
            */

            String apiREAL = "https://exoplanetarchive.ipac.caltech.edu/TAP/sync?query=SELECT+pl_insol,pl_rade,st_rad,sy_dist,pl_angsep,pl_name,disc_year,discoverymethod,pl_orbper,pl_masse+FROM+pscomppars+WHERE+pl_insol+IS+NOT+NULL+AND+st_rad+IS+NOT+NULL+AND+sy_dist+IS+NOT+NULL+AND+pl_angsep+IS+NOT+NULL+AND+pl_masse+IS+NOT+NULL+AND+sy_dist+<" + +request.getDistanciaTierra() + "&format=json";
            
            String apiTEST = "https://exoplanetarchive.ipac.caltech.edu/TAP/sync?query=SELECT+pl_insol,pl_rade,st_rad,sy_dist,pl_angsep,pl_name,disc_year,discoverymethod,pl_orbper,pl_masse+FROM+pscomppars+WHERE+pl_name+=+'KOI-142+c'+AND+pl_insol+IS+NOT+NULL+AND+st_rad+IS+NOT+NULL+AND+sy_dist+IS+NOT+NULL+AND+pl_angsep+IS+NOT+NULL&format=json";
            
            ResponseEntity<String> apiResponse = restTemplate.getForEntity(apiREAL, String.class);
            
            if (apiResponse.getStatusCode().value() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                List<AllPlanetData> allPlanets = new ArrayList<>();
                try {
                    allPlanets = objectMapper.readValue(apiResponse.getBody(), new TypeReference<List<AllPlanetData>>() {});
                }
                catch (DatabindException e) {
                    System.out.println("[*] DATABIND EXC");
                }
                System.out.println("[!] FUNCIONAAAAAAA!!");
                return allPlanets;    
            }
            throw new Exception();
        }

        // * FUNCIONA
        private List<PlanetDataReply> processData(List<AllPlanetData> dbData, PlanetDataRequest request) {
            List<PlanetDataReply> habitablePlanets = new ArrayList<>();
            for (AllPlanetData planet : dbData) {
                double habitabilidad = calcularHabitabilidad(planet); 
                System.out.println("[+] Planeta: " + planet.getPl_name() + " habitabilidad: " + habitabilidad + " comparamos con " + request.getHabitabilidad());
                if( habitabilidad < request.getHabitabilidad()) {
                    String name = planet.getPl_name();
                    double gravedad = calcularGravedad(planet.getPl_masse(), planet.getPl_rade());
                    double distanceToStar = planet.getSy_dist();
                    double yearDuration = planet.getPl_orbper() / 365.25 ;
                    habitablePlanets.add(new PlanetDataReply(habitabilidad, name, gravedad, distanceToStar, yearDuration));
                }
            }
            System.out.println("[!] NUMERO DE PLANETAS EN LA LISTA: " + habitablePlanets.size());

            return habitablePlanets.stream()
                    .filter(p -> p.getHabitabilidad() < request.getHabitabilidad())
                    .sorted(Comparator.comparingDouble(p -> p.getHabitabilidad() - request.getHabitabilidad()))
                    .limit(10)
                    .collect(Collectors.toList());
        }

        // * FUNCIONA 
        private double calcularHabitabilidad(AllPlanetData Planet) {
            double flux = Planet.getPl_insol();
            double radio = Planet.getPl_rade();
            
            double aux1 = Math.pow((flux - 1) / (flux + 1),2);
            double aux2 = Math.pow((radio - 1) / (radio + 1),2);

            return 1 - Math.sqrt(0.5)*Math.sqrt(aux1 + aux2);
        }

        private double calcularGravedad(double masa, double radio) {
            return ((6.67 * Math.pow(10, -11)) * masa) / Math.pow(radio, 2);
        }
}
