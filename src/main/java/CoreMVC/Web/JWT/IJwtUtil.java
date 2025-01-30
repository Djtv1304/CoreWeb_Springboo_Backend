package CoreMVC.Web.JWT;

public interface IJwtUtil {
    String generateToken(String userId);
    String extractUserId(String token);
    String refineJwtToken(String token);
}