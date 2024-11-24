package CoreMVC.Web.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PreferenciaUsuario {

    @Id
    private ObjectId idPreferenciaUsuario;

    private ObjectId idUsuario;

    private String clasificacionVeiculo;

    private double presupuesto;

}
