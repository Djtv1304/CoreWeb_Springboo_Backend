package CoreMVC.Web.Controller;

import CoreMVC.Web.Document.RegistroRendimiento;
import CoreMVC.Web.Service.RegistroRendimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registroRendimiento")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class RegistroRendimientoController {

    private final RegistroRendimientoService registroRendimientoService;

    @Autowired
    public RegistroRendimientoController(RegistroRendimientoService registroRendimientoService) {
        this.registroRendimientoService = registroRendimientoService;
    }

    @GetMapping("/todosRegistrosVehiculo/{idVehiculo}")
    public ResponseEntity<?> findAllRendimientoByIdVehiculo(@PathVariable String idVehiculo) {
        return registroRendimientoService.findAllRendimientoByIdVehiculo(idVehiculo);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveRegistroRendimiento(@RequestBody RegistroRendimiento registroRendimiento) {
        return registroRendimientoService.registrarRendimiento
                (registroRendimiento.getIdVehiculo(),
                registroRendimiento.getKilometraje(),
                registroRendimiento.getBateriaInicial(),
                registroRendimiento.getBateriaFinal());
    }

}
