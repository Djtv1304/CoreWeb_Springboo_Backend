package CoreMVC.Web.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "VehiculoCombustion")
public class VehiculoCombustion {

    @Id
    private ObjectId idVehiculoCombustion;

    private String marca;

    private String modelo;

    private String anio;

    private ObjectId idUsuario;

    private String clasificacion;

    // Nuevos atributos

    private String color;

    // Consumo medido en L/km.
    private double consumoCombustible;

    // Consumo medido en g/km.
    private double emisionesCO2;

    // Costo o Precio del combustible por litro.
    private double costoCombustible;

    // Capacidad del tanque en litros.
    private double capacidadTanque;

    // URL de la imagen del vehículo.
    private String imageURL;

}
