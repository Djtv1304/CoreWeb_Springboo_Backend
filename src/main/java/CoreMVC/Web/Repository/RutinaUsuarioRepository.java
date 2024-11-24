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

}
