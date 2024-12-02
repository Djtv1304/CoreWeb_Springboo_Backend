package CoreMVC.Web.Controller;

import CoreMVC.Web.Document.RutinaUsuario;
import CoreMVC.Web.Service.RutinaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/rutinaUsuario")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
public class RutinaUsuarioController {

    private final RutinaUsuarioService rutinaUsuarioService;

    @Autowired
    public RutinaUsuarioController(RutinaUsuarioService rutinaUsuarioService) {
        this.rutinaUsuarioService = rutinaUsuarioService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveRutinaUsuario(@RequestBody RutinaUsuario rutinaUsuario, @RequestHeader("Authorization") String token) {
        String diaSemana = rutinaUsuario.getDiaSemana();
        if (!diaSemana.matches("^(Monday|Tuesday|Wednesday|Thursday|Friday|Saturday|Sunday)$")) {
            return ResponseEntity.badRequest().body("El día de la semana no es válido");
        }

        //ArrayList<RutinaUsuario> rutinas = rutinaUsuarioService.getRutinaUsuario(token).getBody();
        //if ( rutinas != null &&  rutinas.size() >= 7) {
        //    return ResponseEntity.badRequest().body("The weekly routine associated to your user is already registered, no more routines can be registered.");
        //}

        return rutinaUsuarioService.saveRutinaUsuario(rutinaUsuario, token);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllRutinasUsuario(@RequestHeader("Authorization") String token) {
        return rutinaUsuarioService.getRutinaUsuario(token);
    }

}
