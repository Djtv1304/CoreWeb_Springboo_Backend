package CoreMVC.Web.Repository;

import CoreMVC.Web.Document.VehiculoCombustion;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface VehiculoCombustionRepository extends MongoRepository<VehiculoCombustion, String> {

    @NonNull
    ArrayList<VehiculoCombustion> findAll();

    @Override
    @NonNull
    <S extends VehiculoCombustion> S save(@NonNull S nuevoVehiculoCombustion);

    void deleteById(@NonNull String idVehiculoParaEliminar);

    VehiculoCombustion findVehiculoCombustionByMarca(String marca);

    VehiculoCombustion findVehiculoCombustionByModelo(String modelo);

    VehiculoCombustion findVehiculoCombustionByAnio(String anio);

    VehiculoCombustion findVehiculoCombustionByColor(String color);

    VehiculoCombustion findVehiculoCombustionByClasificacion(String clasificacion);

}
