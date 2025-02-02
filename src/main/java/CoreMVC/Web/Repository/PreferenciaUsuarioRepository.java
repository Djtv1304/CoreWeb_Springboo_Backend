package CoreMVC.Web.Repository;

import CoreMVC.Web.Document.PreferenciaUsuario;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface PreferenciaUsuarioRepository extends MongoRepository<PreferenciaUsuario, String> {

    @Override
    @NonNull
    ArrayList<PreferenciaUsuario> findAll();

    @Override
    @NonNull
    Optional<PreferenciaUsuario> findById(@NonNull String idPreferenciaUsuario);

    PreferenciaUsuario findByIdUsuario(ObjectId idUsuario);

    @Override
    @NonNull
    <S extends PreferenciaUsuario> S save(@NonNull S entity);

    @Override
    void deleteById(@NonNull String s);

    ArrayList<PreferenciaUsuario> findAllByIdUsuario(ObjectId idUsuario);

    @Override
    void deleteAll();
}
