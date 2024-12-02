package CoreMVC.Web.Service;

import CoreMVC.Web.Document.PreferenciaUsuario;
import CoreMVC.Web.Document.RutinaUsuario;
import CoreMVC.Web.Document.Vehiculo;
import CoreMVC.Web.JWT.JwtUtil;
import CoreMVC.Web.Repository.PreferenciaUsuarioRepository;
import CoreMVC.Web.Repository.RutinaUsuarioRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;


@Service
public class PreferenciaUsuarioService {

    private final PreferenciaUsuarioRepository preferenciaUsuarioRepository;

    private final VehiculoService vehiculoService;

    private final RutinaUsuarioRepository rutinaUsuarioRepository;

    private final JwtUtil jwtUtil;

    @Autowired
    public PreferenciaUsuarioService(PreferenciaUsuarioRepository preferenciaUsuarioRepository,
                                     JwtUtil jwtUtil, VehiculoService vehiculoService,
                                     RutinaUsuarioRepository rutinaUsuarioRepository) {
        this.preferenciaUsuarioRepository = preferenciaUsuarioRepository;
        this.jwtUtil = jwtUtil;
        this.vehiculoService = vehiculoService;
        this.rutinaUsuarioRepository = rutinaUsuarioRepository;
    }

    public PreferenciaUsuario savePreferenciaUsuario(String token, PreferenciaUsuario preferenciaUsuario) {

        String refinedToken = jwtUtil.refineJwtToken(token);
        preferenciaUsuario.setIdUsuario(new ObjectId(jwtUtil.extractUserId(refinedToken)));

        return preferenciaUsuarioRepository.save(preferenciaUsuario);
    }

    public PreferenciaUsuario findById (String idPreferenciaUsuario) {
        return preferenciaUsuarioRepository.findById(idPreferenciaUsuario).orElse(null);
    }

    public ResponseEntity<ArrayList<PreferenciaUsuario>> findAllPreferenciaUsuarioByIdUsuario(String token) {
        String refinedToken = jwtUtil.refineJwtToken(token);
        return ResponseEntity.ok(preferenciaUsuarioRepository.findAllByIdUsuario(new ObjectId(jwtUtil.extractUserId(refinedToken))));
    }

    public Vehiculo recomendarVehiculo(String token) {

        String refinedToken = jwtUtil.refineJwtToken(token);
        PreferenciaUsuario preferencia = preferenciaUsuarioRepository.findByIdUsuario(new ObjectId(jwtUtil.extractUserId(refinedToken)));
        ArrayList<RutinaUsuario> rutina = rutinaUsuarioRepository.findRutinaUsuarioByIdUsuario(new ObjectId(jwtUtil.extractUserId(refinedToken)));

        double totalKilometraje = rutina.stream().mapToDouble(r -> Double.parseDouble(r.getKilometrajeRecorrido())).sum();
        double presupuesto = preferencia.getPresupuesto();
        String clasificacion = preferencia.getClasificacionVehiculo();

        double precioMin = presupuesto - 5000;
        double precioMax = presupuesto + 5000;
        System.out.println("El precio debe ser mayor a " + precioMin + " y menor a " + precioMax);

        ArrayList<Vehiculo> vehiculos = vehiculoService.findByClasificacionAndPrecio(clasificacion, precioMin, precioMax);

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

    public ArrayList<Vehiculo> recomendarVehiculoPorPresupuesto(String token) {

        String refinedToken = jwtUtil.refineJwtToken(token);
        PreferenciaUsuario preferencia = preferenciaUsuarioRepository.findByIdUsuario(new ObjectId(jwtUtil.extractUserId(refinedToken)));
        ArrayList<RutinaUsuario> rutina = rutinaUsuarioRepository.findRutinaUsuarioByIdUsuario(new ObjectId(jwtUtil.extractUserId(refinedToken)));

        double totalKilometraje = rutina.stream().mapToDouble(r -> Double.parseDouble(r.getKilometrajeRecorrido())).sum();
        System.out.println("Total Kilometraje: " + totalKilometraje);
        double presupuesto = preferencia.getPresupuesto();
        String clasificacion = preferencia.getClasificacionVehiculo();

        System.out.println("El precio debe ser menor a " + presupuesto);

        ArrayList<Vehiculo> vehiculos = vehiculoService.findByClasificacionAndPrecioLessThanEqual(clasificacion, presupuesto);

        ArrayList<Vehiculo> vehiculosRecomendados = new ArrayList<>();
        double mejorEficiencia = Double.MAX_VALUE;
        System.out.println("Vehiculos: " + vehiculos.size());

        for (Vehiculo vehiculo : vehiculos) {
            double eficiencia = totalKilometraje / Double.parseDouble(vehiculo.getAutonomia());
            if (eficiencia < mejorEficiencia) {
                mejorEficiencia = eficiencia;
                vehiculosRecomendados.add(vehiculo);
            }
        }

        return vehiculosRecomendados;
    }

}
