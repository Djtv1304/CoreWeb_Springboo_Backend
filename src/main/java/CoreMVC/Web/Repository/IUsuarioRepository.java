package CoreMVC.Web.Repository;

import CoreMVC.Web.Document.Usuario;
import com.mongodb.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepository {

    List<Usuario> findAll();
    Optional<Usuario> findById(String idUsuario);
    Usuario findUsuarioByEmail(String email);
    Optional<Usuario> findUsuarioByEmailAndContrasenia(String email, String contrasenia);

}
