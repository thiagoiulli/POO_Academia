package exceptions;

/**
 * Exceção customizada lançada quando se tenta cadastrar um usuário com um nome de usuário já existente.
 * 
 * <p>Esta exceção é utilizada no processo de cadastro para garantir a unicidade dos nomes de usuário
 * no sistema. Quando um usuário tenta se registrar com um nome de usuário já em uso, esta exceção
 * é lançada para impedir o cadastro duplicado.</p>
 * 
 * <p><b>Casos de uso:</b></p>
 * <ul>
 *   <li>Tentativa de cadastro com nome de usuário duplicado</li>
 *   <li>Validação de unicidade durante o registro</li>
 * </ul>
 * 
 * @author POO_academia
 * @version 1.0
 * @see Gerenciamento#CadastrarPessoa(String, String, String, String, String)
 */
public class UsuarioExistenteException extends Exception {
    
    /**
     * Construtor que cria uma nova exceção de usuário existente com uma mensagem descritiva.
     * 
     * @param message mensagem explicando que o nome de usuário já está em uso
     */
    public UsuarioExistenteException(String message) {
        super(message);
    }
}
