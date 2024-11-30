package CoreMVC.Web.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Vehiculo")
public class Vehiculo {

    @Id
    private ObjectId idVehiculo;

    private String marca;

    private String modelo;

    private String anio;

    private double capacidadBateria;

    // Lo borre porque no se necesita, ya tengo el consumoEnergetico.
    //private double consumoPromedio;

    private ObjectId idUsuario;

    private String clasificacion;

    // Nuevos atributos

    private String color;

    private String autonomia;

    // kWh por kilómetro (En algunos carros se presenta el consumo cada 100KM).
    private double consumoEnergetico;

    private String tiempoCarga; // Formato "HH:mm"

    private double costoMantenimiento;

    // URL de la imagen del vehículo.
    private String imageURL;

    // Verificar estos atributos en el formulario del FrontEnd.
    private double promedioRendimiento;

    private double precio;

}
