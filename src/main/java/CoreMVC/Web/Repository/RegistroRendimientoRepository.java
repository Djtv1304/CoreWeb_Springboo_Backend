package CoreMVC.Web.Repository;

import CoreMVC.Web.Document.RegistroRendimiento;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RegistroRendimientoRepository extends MongoRepository<RegistroRendimiento, String> {

    @Override
    @NonNull
    ArrayList<RegistroRendimiento> findAll();

    ArrayList<RegistroRendimiento> findAllByIdVehiculo(ObjectId idVehiculo);

    @Override
    @NonNull
    Optional<RegistroRendimiento> findById(@NonNull String idRegistroRendimiento);

    @Override
    @NonNull
    <S extends RegistroRendimiento> S save(@NonNull S entity);

    @Override
    void deleteById(@NonNull String s);

}
