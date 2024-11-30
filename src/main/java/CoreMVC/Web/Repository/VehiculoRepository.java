package CoreMVC.Web.Repository;

import CoreMVC.Web.Document.Vehiculo;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    @Query("{ 'clasificacion': ?0, 'precio': { $gte: ?1, $lte: ?2 } }")
    ArrayList<Vehiculo> findByClasificacionAndPrecioBetween(String clasificacion, double precioAfter, double precioBefore);

    ArrayList<Vehiculo> findByClasificacionAndPrecioLessThanEqual(String clasificacion, double precioIsLessThan);

    @Override
    @NonNull
    <S extends Vehiculo> S save(@NonNull S entity);

    void deleteByIdUsuario(ObjectId idUsuario);

    ArrayList<Vehiculo> findVehiculoByMarca(@NonNull String marca);

    ArrayList<Vehiculo> findVehiculoByModelo(@NonNull String modelo);

    ArrayList<Vehiculo> findVehiculoByAnio(@NonNull String anio);

    ArrayList<Vehiculo> findVehiculoByColor(@NonNull String color);

}
