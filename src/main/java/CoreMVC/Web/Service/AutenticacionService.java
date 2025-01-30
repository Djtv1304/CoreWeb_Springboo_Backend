package CoreMVC.Web.Service;

import CoreMVC.Web.Document.Usuario;
import CoreMVC.Web.JWT.JwtUtil;
import CoreMVC.Web.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public AutenticacionService(UsuarioRepository usuarioRepository, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<String> login(String email, String contrasenia) {
        Usuario usuarioLogueado = usuarioRepository.findUsuarioByEmailAndContrasenia(email, contrasenia).orElse(null);
        if (usuarioLogueado != null) {
            return ResponseEntity.ok(jwtUtil.generateToken(usuarioLogueado.getIdUsuario().toString()));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email o contraseña incorrectos");
    }
}
