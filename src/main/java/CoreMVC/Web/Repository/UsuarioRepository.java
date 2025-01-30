package CoreMVC.Web.Repository;

import CoreMVC.Web.Document.Usuario;
import com.mongodb.lang.NonNull;
import com.mongodb.lang.NonNullApi;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String>, IUsuarioRepository {

    @Override
    @NonNull
    List<Usuario> findAll();

    @Override
    @NonNull
    Optional<Usuario> findById(@NonNull String idUsuario);

    Usuario findUsuarioByIdUsuario(ObjectId idUsuario);

    Usuario findUsuarioByEmail(@NonNull String email);

    Optional<Usuario> findUsuarioByEmailAndContrasenia(@NonNull String email,@NonNull String contrasenia);

    @Override
    @NonNull
    <S extends Usuario> S save(@NonNull S entity);

    @Override
    void deleteById(@NonNull String s);

}
