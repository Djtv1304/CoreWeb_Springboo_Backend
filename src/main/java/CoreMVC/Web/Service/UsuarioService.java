package CoreMVC.Web.Service;

import CoreMVC.Web.Document.Usuario;
import CoreMVC.Web.Repository.UsuarioRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Método para loguear un usuario en el sistema
     * @param email
     * @param contrasenia
     * @return boolean, verdadero si el usuario existe, falso si no
     */
    public boolean login(String email, String contrasenia) {
        return usuarioRepository.findUsuarioByEmailAndContrasenia(email, contrasenia).isPresent();
    }

    /**
     * Método para registrar un usuario en el sistema
     * @param email
     * @param primerNombre
     * @param apellido
     * @param contrasenia
     * @param fechaNacimiento
     * @return boolean, verdadero si el usuario se registró, falso si existe un usuario con el mismo email o hubo un error
     */
    public String registrarUsuario(String email, String primerNombre, String apellido, String contrasenia, Date fechaNacimiento) {
        if (usuarioRepository.findUsuarioByEmail(email) == null) {
            ObjectId idUsuario = new ObjectId();

            usuarioRepository.save(new Usuario(idUsuario, email, primerNombre, apellido, contrasenia, fechaNacimiento, new Date()));

            return "Usuario registrado correctamente";
        }
        return "Ya existe un usuario registrado con ese email";
    }

}
