package CoreMVC.Web.Service;

import CoreMVC.Web.Document.VehiculoCombustion;
import CoreMVC.Web.JWT.JwtUtil;
import CoreMVC.Web.Repository.VehiculoCombustionRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VehiculoCombustionService {

    private final VehiculoCombustionRepository vehiculoCombustionRepository;

    private final JwtUtil jwtUtil;

    @Autowired
    public VehiculoCombustionService(VehiculoCombustionRepository vehiculoCombustionRepository, JwtUtil jwtUtil) {
        this.vehiculoCombustionRepository = vehiculoCombustionRepository;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<?> registrarVehiculoCombustion(VehiculoCombustion newVehiculoCombustion, String tokenJWT) {

        try {
            // Refinamos el token JWT y quitamos la palabra Bearer
            String token = jwtUtil.refineJwtToken(tokenJWT);
            // Extraemos el id del usuario del token JWT
            String idUsuario = jwtUtil.extractUserId(token);
            // Convertimos el id del usuario a ObjectId
            ObjectId idUsuarioObjectId = new ObjectId(idUsuario);
            // Asignamos el id del usuario al vehículo de combustión y lo guardamos en la base de datos.
            newVehiculoCombustion.setIdUsuario(idUsuarioObjectId);
            vehiculoCombustionRepository.save(newVehiculoCombustion);
            return ResponseEntity.status(HttpStatus.CREATED).body("Vehiculo de combustión registrado con éxito");
        } catch (Exception e) {
            System.out.println("Error al registrar el vehiculo de combustión: " + e.getMessage());
            return ResponseEntity.badRequest().body("No se pudo registrar el vehiculo de combustión \n Descripción del error: " + e);
        }

    }
}
