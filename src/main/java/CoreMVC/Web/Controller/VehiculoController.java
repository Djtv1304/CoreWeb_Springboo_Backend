package CoreMVC.Web.Controller;

import CoreMVC.Web.Document.Vehiculo;
import CoreMVC.Web.Repository.VehiculoRepository;
import CoreMVC.Web.Service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehiculo")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    @Autowired
    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarVehiculo(@RequestBody Vehiculo newVehiculo, @RequestHeader("Authorization") String tokenJWT) {
        return vehiculoService.registrarVehiculo(newVehiculo, tokenJWT);
    }

    @PostMapping("/eliminar/{idVehiculo}")
    public void eliminarVehiculo(@PathVariable String idVehiculo) {
        vehiculoService.deleteById(idVehiculo);
    }

    @PostMapping("/actualizar")
    public void actualizarVehiculo(@RequestBody Vehiculo vehiculoToUpdate) {
        vehiculoService.actualizarVehiculo(vehiculoToUpdate);
    }

    @PostMapping("/obtener/{idVehiculo}")
    public Vehiculo obtenerVehiculo(@PathVariable String idVehiculo) {
        return vehiculoService.encontrarVehiculoById(idVehiculo).orElse(null);
    }

}
