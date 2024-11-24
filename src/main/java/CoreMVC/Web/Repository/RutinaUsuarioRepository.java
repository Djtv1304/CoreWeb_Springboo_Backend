package CoreMVC.Web.Repository;

import CoreMVC.Web.Document.RutinaUsuario;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public interface RutinaUsuarioRepository extends MongoRepository<RutinaUsuario, String> {

    @Override
    @NonNull
    ArrayList<RutinaUsuario> findAll();

    @Override
    @NonNull
    Optional<RutinaUsuario> findById(@NonNull String idRutinaUsuario);

    @Override
    @NonNull
    <S extends RutinaUsuario> S save(@NonNull S entity);

    @Override
    void deleteById(@NonNull String s);

    ArrayList<RutinaUsuario> findRutinaUsuarioByIdUsuario(@NonNull String idUsuario);

    ArrayList<RutinaUsuario> findRutinaUsuarioByFechaBetween(@NonNull Date fechaInicio, @NonNull Date fechaFin);

    ArrayList<RutinaUsuario> findRutinaUsuarioByIdUsuarioAndFechaBetween(@NonNull String idUsuario, @NonNull Date fechaInicio, @NonNull Date fechaFin);

    ArrayList<RutinaUsuario> findRutinaUsuarioByFechaBetweenOrderByFechaAsc(@NonNull Date fechaInicio, @NonNull Date fechaFin);

    ArrayList<RutinaUsuario> findRutinaUsuarioByIdUsuarioAndFechaBetweenOrderByFechaAsc(@NonNull String idUsuario, @NonNull Date fechaInicio, @NonNull Date fechaFin);

    ArrayList<RutinaUsuario> findRutinaUsuarioByFechaBetweenOrderByFechaDesc(@NonNull Date fechaInicio, @NonNull Date fechaFin);

    ArrayList<RutinaUsuario> findRutinaUsuarioByIdUsuarioAndFechaBetweenOrderByFechaDesc(@NonNull String idUsuario, @NonNull Date fechaInicio, @NonNull Date fechaFin);

    ArrayList<RutinaUsuario> findRutinaUsuarioByFechaBetweenOrderByKilometrajeAsc(@NonNull Date fechaInicio, @NonNull Date fechaFin);

    ArrayList<RutinaUsuario> findRutinaUsuarioByIdUsuarioAndFechaBetweenOrderByKilometrajeAsc(@NonNull String idUsuario, @NonNull Date fechaInicio, @NonNull Date fechaFin);

    ArrayList<RutinaUsuario> findRutinaUsuarioByFechaBetweenOrderByKilometrajeDesc(@NonNull Date fechaInicio, @NonNull Date fechaFin);

    ArrayList<RutinaUsuario> findRutinaUsuarioByIdUsuarioAndFechaBetweenOrderByKilometrajeDesc(@NonNull String idUsuario, @NonNull Date fechaInicio, @NonNull Date fechaFin);
}
