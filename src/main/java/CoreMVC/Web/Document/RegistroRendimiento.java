package CoreMVC.Web.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegistroRendimiento {

    private ObjectId idRegistroRendimiento;

    private ObjectId idVehiculo;

    private Date fecha;

    private String kilometraje;

    private double bateriaInicial;

    private double bateriaFinal;

}
