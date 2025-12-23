package exceptions;

/**
 * Exceção customizada para tratar erros relacionados a operações de leitura e escrita de arquivos.
 * 
 * <p>Esta exceção é lançada quando ocorrem problemas durante operações de I/O com arquivos,
 * como falhas ao abrir, ler ou gravar dados em arquivos de texto.</p>
 * 
 * <p><b>Casos de uso:</b></p>
 * <ul>
 *   <li>Erro ao abrir arquivo para leitura ou escrita</li>
 *   <li>Falha ao gravar dados no arquivo</li>
 *   <li>Problemas de permissão ou arquivo não encontrado</li>
 * </ul>
 * 
 * @author POO_academia
 * @version 1.0
 * @see ManArqTxt
 */
public class LeituraEscritaException extends Exception {
    
    /**
     * Construtor que cria uma nova exceção de leitura/escrita com uma mensagem descritiva.
     * 
     * @param message mensagem detalhada explicando o erro de I/O que ocorreu
     */
    public LeituraEscritaException(String message) {
        super(message);
    }
}
