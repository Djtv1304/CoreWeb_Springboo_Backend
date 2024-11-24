package CoreMVC.Web.Controller;

import CoreMVC.Web.Document.Vehiculo;
import CoreMVC.Web.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehiculo")
public class VehiculoController {

    private final VehiculoRepository vehiculoRepository;

    @Autowired
    public VehiculoController(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    @PostMapping("/registrar")
    public void registrarVehiculo(@RequestBody Vehiculo newVehiculo) {
        vehiculoRepository.save(newVehiculo);
    }

    @PostMapping("/eliminar/{idVehiculo}")
    public void eliminarVehiculo(@PathVariable String idVehiculo) {
        vehiculoRepository.deleteById(idVehiculo);
    }

    @PostMapping("/actualizar")
    public void actualizarVehiculo(@RequestBody Vehiculo vehiculoToUpdate) {
        vehiculoRepository.save(vehiculoToUpdate);
    }

    @PostMapping("/obtener/{idVehiculo}")
    public Vehiculo obtenerVehiculo(@PathVariable String idVehiculo) {
        return vehiculoRepository.findById(idVehiculo).orElse(null);
    }

}
