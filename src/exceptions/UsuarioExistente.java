package exceptions;

public class UsuarioExistente extends Exception {
    public UsuarioExistente(String message) {
        super(message);
    }
}
