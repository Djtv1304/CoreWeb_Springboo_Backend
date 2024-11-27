package CoreMVC.Web.Service;

import CoreMVC.Web.Document.RegistroRendimiento;
import CoreMVC.Web.Repository.RegistroRendimientoRepository;
import CoreMVC.Web.Repository.VehiculoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RegistroRendimientoService {

    private final RegistroRendimientoRepository registroRendimientoRepository;

    private final VehiculoRepository vehiculoRepository;

    @Autowired
    public RegistroRendimientoService(RegistroRendimientoRepository registroRendimientoRepository, VehiculoRepository vehiculoRepository) {
        this.registroRendimientoRepository = registroRendimientoRepository;
        this.vehiculoRepository = vehiculoRepository;
    }

    public double calcularRendimiento(ObjectId idVehiculo, double kilometraje, double bateriaInicial, double bateriaFinal) {
        double consumo = bateriaInicial - bateriaFinal;
        return kilometraje / consumo;
    }

    public double promedioRendimiento(ObjectId idVehiculo) {
        double promedio = 0;
        double total = 0;
        int count = 0;
        for (RegistroRendimiento registro : registroRendimientoRepository.findAll()) {
            if (registro.getIdVehiculo().equals(idVehiculo)) {
                total += registro.getRendimiento();
                count++;
            }
        }
        if (count != 0) {
            promedio = total / count;
        }
        return promedio;
    }

    public ResponseEntity<?> findAllRendimientoByIdVehiculo(String idVehiculo) {
        return ResponseEntity.ok(registroRendimientoRepository.findAllByIdVehiculo(new ObjectId(idVehiculo)));
    }

    public ResponseEntity<?> registrarRendimiento(ObjectId idVehiculo, String kilometraje, double bateriaInicial, double bateriaFinal) {
        try {
            double rendimiento = calcularRendimiento(idVehiculo, Double.parseDouble(kilometraje), bateriaInicial, bateriaFinal);
            RegistroRendimiento registro = new RegistroRendimiento();
            registro.setIdVehiculo(idVehiculo);
            registro.setFecha(new Date());
            registro.setKilometraje(kilometraje);
            registro.setBateriaInicial(bateriaInicial);
            registro.setBateriaFinal(bateriaFinal);
            registro.setRendimiento(rendimiento);
            registroRendimientoRepository.save(registro);

            // Actualizar el rendimiento promedio del vehículo cada que se registra un rendimiento.
            vehiculoRepository.findById(idVehiculo)
                    .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"))
                    .setPromedioRendimiento(promedioRendimiento(idVehiculo));

            return ResponseEntity.ok("Rendimiento registrado con éxito y promedio actualizado");
        } catch (Exception e) {
            System.out.println("Error al registrar el rendimiento: " + e.getMessage());
            return ResponseEntity.badRequest().body("No se pudo registrar el rendimiento \n Descripción del error: " + e);
        }
    }

}
