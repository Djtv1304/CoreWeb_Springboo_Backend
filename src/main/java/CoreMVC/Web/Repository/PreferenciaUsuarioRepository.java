package CoreMVC.Web.Repository;

import CoreMVC.Web.Document.PreferenciaUsuario;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface PreferenciaUsuarioRepository extends MongoRepository<PreferenciaUsuario, String> {

    @Override
    @NonNull
    ArrayList<PreferenciaUsuario> findAll();

    @Override
    @NonNull
    Optional<PreferenciaUsuario> findById(@NonNull String idPreferenciaUsuario);

    @Override
    @NonNull
    <S extends PreferenciaUsuario> S save(@NonNull S entity);

    @Override
    void deleteById(@NonNull String s);

    PreferenciaUsuario findPreferenciaUsuarioByIdUsuario(@NonNull String idUsuario);

    @Override
    void deleteAll();
}
