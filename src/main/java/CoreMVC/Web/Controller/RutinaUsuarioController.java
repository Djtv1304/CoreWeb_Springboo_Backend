package CoreMVC.Web.Controller;

import CoreMVC.Web.Document.RutinaUsuario;
import CoreMVC.Web.Service.RutinaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rutinaUsuario")
public class RutinaUsuarioController {

    private final RutinaUsuarioService rutinaUsuarioService;

    @Autowired
    public RutinaUsuarioController(RutinaUsuarioService rutinaUsuarioService) {
        this.rutinaUsuarioService = rutinaUsuarioService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveRutinaUsuario(@RequestBody RutinaUsuario rutinaUsuario, @RequestHeader("Authorization") String token) {
        String diaSemana = rutinaUsuario.getDiaSemana();
        if (!diaSemana.matches("^(Lunes|Martes|Miércoles|Jueves|Viernes|Sábado|Domingo)$")) {
            return ResponseEntity.badRequest().body("El día de la semana no es válido");
        }
        return rutinaUsuarioService.saveRutinaUsuario(rutinaUsuario, token);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllRutinasUsuario(@RequestHeader("Authorization") String token) {
        return rutinaUsuarioService.getRutinaUsuario(token);
    }

}
