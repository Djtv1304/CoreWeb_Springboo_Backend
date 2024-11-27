package CoreMVC.Web.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "RegistroRendimiento")
public class RegistroRendimiento {

    @Id
    private ObjectId idRegistroRendimiento;

    private ObjectId idVehiculo;

    private Date fecha;

    private String kilometraje;

    private double bateriaInicial;

    private double bateriaFinal;

    // Nuevos atributos
    private double rendimiento;

}
