package CoreMVC.Web.Service;

import CoreMVC.Web.Document.PreferenciaUsuario;
import CoreMVC.Web.Document.RutinaUsuario;
import CoreMVC.Web.Document.Usuario;
import CoreMVC.Web.Document.Vehiculo;
import CoreMVC.Web.JWT.JwtUtil;
import CoreMVC.Web.Repository.PreferenciaUsuarioRepository;
import CoreMVC.Web.Repository.RutinaUsuarioRepository;
import CoreMVC.Web.Repository.UsuarioRepository;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RutinaUsuarioService {

    private final RutinaUsuarioRepository rutinaUsuarioRepository;

    private final JwtUtil jwtUtil;

    @Autowired
    public RutinaUsuarioService(RutinaUsuarioRepository rutinaUsuarioRepository, JwtUtil jwtUtil) {
        this.rutinaUsuarioRepository = rutinaUsuarioRepository;
        this.jwtUtil = jwtUtil;

    }

    public ResponseEntity<?> saveRutinaUsuario(RutinaUsuario rutinaUsuario, String token) {
        try {
            String refinedToken = jwtUtil.refineJwtToken(token);
            rutinaUsuario.setIdUsuario(new ObjectId(jwtUtil.extractUserId(refinedToken)));
            rutinaUsuarioRepository.save(rutinaUsuario);
            return ResponseEntity.ok("Rutina de usuario guardada con éxito");
        } catch (Exception e) {
            System.out.println("Error al guardar la rutina de usuario: " + e.getMessage());
            return ResponseEntity.badRequest().body("No se pudo guardar la rutina de usuario \n Descripción del error: " + e);
        }
    }

    public ResponseEntity<ArrayList<RutinaUsuario>> getRutinaUsuario(String token) {
        try {
            String refinedToken = jwtUtil.refineJwtToken(token);
            return ResponseEntity.ok(rutinaUsuarioRepository.findRutinaUsuarioByIdUsuario(new ObjectId(jwtUtil.extractUserId(refinedToken))));
        } catch (Exception e) {
            System.out.println("Error al obtener las rutinas del usuario: " + e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
    }

}
