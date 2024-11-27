package CoreMVC.Web.Repository;

import CoreMVC.Web.Document.Vehiculo;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface VehiculoRepository extends MongoRepository<Vehiculo, ObjectId> {

    @Override
    @NonNull
    ArrayList<Vehiculo> findAll();

    @Override
    @NonNull
    Optional<Vehiculo> findById(@NonNull ObjectId objectId);

    ArrayList<Vehiculo> findAllByIdUsuario(ObjectId idUsuario);

    @Override
    @NonNull
    <S extends Vehiculo> S save(@NonNull S entity);

    void deleteByIdUsuario(ObjectId idUsuario);

    ArrayList<Vehiculo> findVehiculoByMarca(@NonNull String marca);

    ArrayList<Vehiculo> findVehiculoByModelo(@NonNull String modelo);

    ArrayList<Vehiculo> findVehiculoByAnio(@NonNull String anio);

    ArrayList<Vehiculo> findVehiculoByColor(@NonNull String color);


}
