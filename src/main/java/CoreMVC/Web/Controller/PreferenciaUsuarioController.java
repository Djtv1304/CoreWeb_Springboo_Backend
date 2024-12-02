package CoreMVC.Web.Controller;

import CoreMVC.Web.Document.PreferenciaUsuario;
import CoreMVC.Web.Service.PreferenciaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/preferenciaUsuario")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class PreferenciaUsuarioController {

    private final PreferenciaUsuarioService preferenciaUsuarioService;

    @Autowired
    public PreferenciaUsuarioController(PreferenciaUsuarioService preferenciaUsuarioService) {
        this.preferenciaUsuarioService = preferenciaUsuarioService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePreferenciaUsuario(@RequestHeader("Authorization") String token, @RequestBody PreferenciaUsuario newPreferenciaUsuario) {
        try {



            return ResponseEntity.status(HttpStatus.CREATED).body(preferenciaUsuarioService.savePreferenciaUsuario(token, newPreferenciaUsuario));
        } catch (Exception e) {
            System.out.println("Error al guardar preferencia de usuario: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/recomendarVehiculo")
    public ResponseEntity<?> recomendarVehiculo(@RequestHeader("Authorization") String token) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(preferenciaUsuarioService.recomendarVehiculo(token));
        } catch (Exception e) {
            System.out.println("Error al recomendar vehículo: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
