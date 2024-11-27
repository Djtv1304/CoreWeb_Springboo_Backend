package CoreMVC.Web.Service;

import CoreMVC.Web.Document.Vehiculo;
import CoreMVC.Web.JWT.JwtUtil;
import CoreMVC.Web.Repository.VehiculoRepository;
import org.apache.coyote.Response;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public VehiculoService (VehiculoRepository vehiculoRepository, JwtUtil jwtUtil) {
        this.vehiculoRepository = vehiculoRepository;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<?> getVehiculos(String tokenJWT) {
        try {
            // Aquí se puede agregar la lógica para validar el token JWT
            String refinedToken = jwtUtil.refineJwtToken(tokenJWT);
            String userId = jwtUtil.extractUserId(refinedToken);
            ObjectId userIdObjectId = new ObjectId(userId);
            ArrayList<Vehiculo> vehiculos = vehiculoRepository.findAllByIdUsuario(userIdObjectId);
            // Crear un nuevo JSON con los campos necesarios
            ArrayList<Object> vehiculoInfo = vehiculos.stream().map(vehiculo -> new Object() {
                public final String idVehiculo = vehiculo.getIdVehiculo().toHexString();
                public final String marca = vehiculo.getMarca();
                public final String modelo = vehiculo.getModelo();
                public final double promedioRendimiento = vehiculo.getPromedioRendimiento();
            }).collect(Collectors.toCollection(ArrayList::new));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(vehiculoInfo);
        } catch (Exception e) {
            System.out.println("Error al obtener los vehiculos del usuario: " + e.getMessage());
            return ResponseEntity.badRequest().body("No se pudo obtener los vehiculos del usuario \n Descripción del error: " + e);
        }
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

    public void actualizarVehiculo(Vehiculo vehiculoToUpdate) {
        vehiculoRepository.save(vehiculoToUpdate);
    }

    public Optional<Vehiculo> encontrarVehiculoById(String idVehiculo) {
        return vehiculoRepository.findById(new ObjectId(idVehiculo));
    }

}
