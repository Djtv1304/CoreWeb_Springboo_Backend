package CoreMVC.Web.Repository;

import CoreMVC.Web.Document.Vehiculo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface VehiculoRepositoryCustom {

    ArrayList<Vehiculo> findByClasificacionAndPrecio(String clasificacion, double precio);

}
