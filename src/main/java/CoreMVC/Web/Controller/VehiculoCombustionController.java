package CoreMVC.Web.Controller;

import CoreMVC.Web.Document.VehiculoCombustion;
import CoreMVC.Web.Service.VehiculoCombustionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehiculoCombustion")
public class VehiculoCombustionController {

    private final VehiculoCombustionService vehiculoCombustionService;

    @Autowired
    public VehiculoCombustionController(VehiculoCombustionService vehiculoCombustionService) {
        this.vehiculoCombustionService = vehiculoCombustionService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarVehiculoCombustion(@RequestBody VehiculoCombustion newVehiculoCombustion, @RequestHeader("Authorization") String tokenJWT) {
        return vehiculoCombustionService.registrarVehiculoCombustion(newVehiculoCombustion, tokenJWT);
    }

}
