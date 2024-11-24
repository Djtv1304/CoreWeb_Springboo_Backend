package CoreMVC.Web.Repository;

import CoreMVC.Web.Document.Vehiculo;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface VehiculoRepository extends MongoRepository<Vehiculo, String> {

    @Override
    @NonNull
    ArrayList<Vehiculo> findAll();

    @Override
    @NonNull
    Vehiculo save(@NonNull Vehiculo entity);

    @Override
    void deleteById(@NonNull String s);

    ArrayList<Vehiculo> findVehiculoByMarca(@NonNull String marca);

    ArrayList<Vehiculo> findVehiculoByModelo(@NonNull String modelo);

    ArrayList<Vehiculo> findVehiculoByAnio(@NonNull String anio);

    ArrayList<Vehiculo> findVehiculoByColor(@NonNull String color);


}
