package CoreMVC.Web.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "PreferenciaUsuario")
public class PreferenciaUsuario {

    @Id
    private ObjectId idPreferenciaUsuario;

    private ObjectId idUsuario;

    private String clasificacionVehiculo;

    private double presupuesto;

}
