package exceptions;

/**
 * Exceção customizada lançada quando as credenciais de login (usuário ou senha) estão incorretas.
 * 
 * <p>Esta exceção é utilizada no processo de autenticação para indicar que o usuário forneceu
 * credenciais inválidas. Por questões de segurança, a mensagem não diferencia se o erro foi
 * no nome de usuário ou na senha.</p>
 * 
 * <p><b>Casos de uso:</b></p>
 * <ul>
 *   <li>Usuário não encontrado no sistema</li>
 *   <li>Senha incorreta para o usuário informado</li>
 *   <li>Falha na autenticação durante o login</li>
 * </ul>
 * 
 * @author POO_academia
 * @version 1.0
 * @see Gerenciamento#parseLogin(String, String)
 */
public class UsuarioOuSenhaIncorretosException extends Exception {
    
    /**
     * Construtor que cria uma nova exceção de credenciais incorretas com uma mensagem descritiva.
     * 
     * @param message mensagem informando que as credenciais estão incorretas
     */
    public UsuarioOuSenhaIncorretosException(String message) {
        super(message);
    }
}
