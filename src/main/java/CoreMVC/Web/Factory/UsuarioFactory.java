package CoreMVC.Web.Factory;

import CoreMVC.Web.Document.Usuario;
import org.bson.types.ObjectId;

import java.util.Date;

public class UsuarioFactory {
    public static Usuario createUsuario(ObjectId idUsuario, String email, String primerNombre, String apellido, String contrasenia, Date fechaNacimiento) {
        return new Usuario(idUsuario, email, primerNombre, apellido, contrasenia, fechaNacimiento, new Date());
    }
}
