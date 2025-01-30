package CoreMVC.Web.Service;

import CoreMVC.Web.Document.Usuario;
import CoreMVC.Web.Factory.UsuarioFactory;
import CoreMVC.Web.JWT.JwtUtil;
import CoreMVC.Web.Repository.UsuarioRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Método para loguear un usuario en el sistema
     * @param email
     * @param contrasenia
     * @return ResponseEntity con el token JWT si el login es exitoso, o un mensaje de error si no lo es
     */
    public ResponseEntity<String> login(String email, String contrasenia) {
        Usuario usuarioLogueado = usuarioRepository.findUsuarioByEmailAndContrasenia(email, contrasenia).orElse(null);
        if (usuarioLogueado != null) {
            return ResponseEntity.ok(jwtUtil.generateToken(usuarioLogueado.getIdUsuario().toString()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email o contraseña incorrectos");
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

            Usuario nuevoUsuario = UsuarioFactory.createUsuario(idUsuario, email, primerNombre, apellido, contrasenia, fechaNacimiento);
            usuarioRepository.save(nuevoUsuario);

            return "Usuario registrado correctamente";
        }
        return "Ya existe un usuario registrado con ese email";
    }

}
