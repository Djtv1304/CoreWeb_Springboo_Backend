package CoreMVC.Web.Implementation;

import CoreMVC.Web.Document.Vehiculo;
import CoreMVC.Web.Repository.VehiculoRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class VehiculoRepositoryCustomImpl implements VehiculoRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public VehiculoRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        System.out.println("VehiculoRepositoryCustomImpl me ejecuto como constructor");
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public ArrayList<Vehiculo> findByClasificacionAndPrecio(String clasificacion, double precio) {
        // Se busca un vehículo con un presupuesto entre 5000 por debajo y 5000 por encima del presupuesto del usuario
        double precioMin = precio - 5000;
        double precioMax = precio + 5000;

        System.out.println("Método findByClasificacionAndPrecio llamado");
        Query query = new Query();
        query.addCriteria(Criteria.where("clasificacion").is(clasificacion));
        query.addCriteria(Criteria.where("precio").gte(precioMin).lte(precioMax));
        System.out.println("Query: " + query);
        return (ArrayList<Vehiculo>) mongoTemplate.find(query, Vehiculo.class);
    }

}
