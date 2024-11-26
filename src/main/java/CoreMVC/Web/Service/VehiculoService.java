package CoreMVC.Web.Service;

import CoreMVC.Web.Document.Vehiculo;
import CoreMVC.Web.JWT.JwtUtil;
import CoreMVC.Web.Repository.VehiculoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public VehiculoService (VehiculoRepository vehiculoRepository, JwtUtil jwtUtil) {
        this.vehiculoRepository = vehiculoRepository;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<?> registrarVehiculo(Vehiculo newVehiculo, String tokenJWT) {
        try {
            String token = tokenJWT.replace("Bearer ", "").trim();
            // Aquí se puede agregar la lógica para validar el token JWT
            String idUsuario = jwtUtil.extractUserId(token);
            ObjectId idUsuarioObjectId = new ObjectId(idUsuario);
            newVehiculo.setIdUsuario(idUsuarioObjectId);
            vehiculoRepository.save(newVehiculo);
            return ResponseEntity.status(HttpStatus.CREATED).body("Vehiculo registrado con éxito");
        } catch (Exception e) {
            System.out.println("Error al registrar el vehiculo: " + e.getMessage());
            return ResponseEntity.badRequest().body("No se pudo registrar el vehiculo \n Descripción del error: " + e);
        }
    }

    public void deleteById(String idVehiculo) {
        vehiculoRepository.deleteById(idVehiculo);
    }

    public void actualizarVehiculo(Vehiculo vehiculoToUpdate) {
        vehiculoRepository.save(vehiculoToUpdate);
    }

    public Optional<Vehiculo> encontrarVehiculoById(String idVehiculo) {
        return vehiculoRepository.findById(idVehiculo);
    }

}
