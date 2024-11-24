package CoreMVC.Web.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RutinaUsuario {

    @Id
    private ObjectId idRutinaUsuario;

    private ObjectId idUsuario;

    private String diaSemana;

    private String actividad;

    private String kilometrajeRecorrido;

}
