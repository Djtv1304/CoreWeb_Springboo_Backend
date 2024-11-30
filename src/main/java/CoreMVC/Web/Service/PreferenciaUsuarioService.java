package CoreMVC.Web.Service;

import CoreMVC.Web.Document.PreferenciaUsuario;
import CoreMVC.Web.Document.RutinaUsuario;
import CoreMVC.Web.Document.Vehiculo;
import CoreMVC.Web.JWT.JwtUtil;
import CoreMVC.Web.Repository.PreferenciaUsuarioRepository;
import CoreMVC.Web.Repository.RutinaUsuarioRepository;
import CoreMVC.Web.Repository.VehiculoRepository;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PreferenciaUsuarioService {

    private final PreferenciaUsuarioRepository preferenciaUsuarioRepository;

    private final RutinaUsuarioRepository rutinaUsuarioRepository;

    private final VehiculoService vehiculoService;

    private final VehiculoRepository vehiculoRepository;

    private final JwtUtil jwtUtil;

    @Autowired
    public PreferenciaUsuarioService(PreferenciaUsuarioRepository preferenciaUsuarioRepository,
                                     JwtUtil jwtUtil, VehiculoService vehiculoService,
                                     RutinaUsuarioRepository rutinaUsuarioRepository,
                                     VehiculoRepository vehiculoRepository) {
        this.preferenciaUsuarioRepository = preferenciaUsuarioRepository;
        this.jwtUtil = jwtUtil;
        this.vehiculoService = vehiculoService;
        this.rutinaUsuarioRepository = rutinaUsuarioRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    public PreferenciaUsuario savePreferenciaUsuario(String token, PreferenciaUsuario preferenciaUsuario) {

        String refinedToken = jwtUtil.refineJwtToken(token);
        preferenciaUsuario.setIdUsuario(new ObjectId(jwtUtil.extractUserId(refinedToken)));

        return preferenciaUsuarioRepository.save(preferenciaUsuario);
    }

    public Vehiculo recomendarVehiculo(String token) {

        String refinedToken = jwtUtil.refineJwtToken(token);
        PreferenciaUsuario preferencia = preferenciaUsuarioRepository.findByIdUsuario(new ObjectId(jwtUtil.extractUserId(refinedToken)));
        System.out.println("Preferencia: " + preferencia);
        ArrayList<RutinaUsuario> rutina = rutinaUsuarioRepository.findRutinaUsuarioByIdUsuario(new ObjectId(jwtUtil.extractUserId(refinedToken)));
        System.out.println("Rutina: " + rutina.size());

        double totalKilometraje = rutina.stream().mapToDouble(r -> Double.parseDouble(r.getKilometrajeRecorrido())).sum();
        System.out.println("Kilometraje: " + totalKilometraje);
        double presupuesto = preferencia.getPresupuesto();
        System.out.println("Presupuesto: " + presupuesto);
        String clasificacion = preferencia.getClasificacionVehiculo();
        System.out.println("Clasificacion: " + clasificacion);

        double precio = presupuesto;

        ArrayList<Vehiculo> vehiculos = vehiculoRepository.findByClasificacionAndPrecio(clasificacion, precio);

        Vehiculo mejorVehiculo = null;
        double mejorEficiencia = Double.MAX_VALUE;
        System.out.println("Vehiculos: " + vehiculos.size());

        for (Vehiculo vehiculo : vehiculos) {
            System.out.println("He iterado");
            double eficiencia = totalKilometraje / Double.parseDouble(vehiculo.getAutonomia());
            if (eficiencia < mejorEficiencia) {
                mejorEficiencia = eficiencia;
                mejorVehiculo = vehiculo;
            }
        }

        return mejorVehiculo;
    }
}
