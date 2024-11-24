package CoreMVC.Web.Repository;

import CoreMVC.Web.Document.RegistroRendimiento;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public interface RegistroRendimientoRepository extends MongoRepository<RegistroRendimiento, String> {

    @Override
    @NonNull
    ArrayList<RegistroRendimiento> findAll();

    @Override
    @NonNull
    Optional<RegistroRendimiento> findById(@NonNull String idRegistroRendimiento);

    @Override
    @NonNull
    <S extends RegistroRendimiento> S save(@NonNull S entity);

    @Override
    void deleteById(@NonNull String s);

    ArrayList<RegistroRendimiento> findRegistroRendimientoByIdVehiculo(@NonNull String idVehiculo);

    ArrayList<RegistroRendimiento> findRegistroRendimientoByFechaBetween(@NonNull Date fechaInicio, @NonNull Date fechaFin);

    ArrayList<RegistroRendimiento> findRegistroRendimientoByIdVehiculoAndFechaBetween(@NonNull String idVehiculo, @NonNull Date fechaInicio, @NonNull Date fechaFin);

    ArrayList<RegistroRendimiento> findRegistroRendimientoByFechaBetweenOrderByFechaAsc(@NonNull Date fechaInicio, @NonNull Date fechaFin);

    ArrayList<RegistroRendimiento> findRegistroRendimientoByIdVehiculoAndFechaBetweenOrderByFechaAsc(@NonNull String idVehiculo, @NonNull Date fechaInicio, @NonNull Date fechaFin);

    ArrayList<RegistroRendimiento> findRegistroRendimientoByFechaBetweenOrderByFechaDesc(@NonNull Date fechaInicio, @NonNull Date fechaFin);

    ArrayList<RegistroRendimiento> findRegistroRendimientoByIdVehiculoAndFechaBetweenOrderByFechaDesc(@NonNull String idVehiculo, @NonNull Date fechaInicio, @NonNull Date fechaFin);

    ArrayList<RegistroRendimiento> findRegistroRendimientoByFechaBetweenOrderByKilometrajeAsc(@NonNull Date fechaInicio, @NonNull Date fechaFin);

    ArrayList<RegistroRendimiento> findRegistroRendimientoByIdVehiculoAndFechaBetweenOrderByKilometrajeAsc(@NonNull String idVehiculo, @NonNull Date fechaInicio, @NonNull Date fechaFin);

    ArrayList<RegistroRendimiento> findRegistroRendimientoByFechaBetweenOrderByKilometrajeDesc(@NonNull Date fechaInicio, @NonNull Date fechaFin);

    ArrayList<RegistroRendimiento> findRegistroRendimientoByIdVehiculoAndFechaBetweenOrderByKilometrajeDesc(@NonNull String idVehiculo, @NonNull Date fechaInicio, @NonNull Date fechaFin);


}
