package principal;

import controle.Gerenciamento;
import controle.ManArqTxt;
import visao.JanelaLogin;

/**
 * Classe principal do sistema de gerenciamento de academia.
 * 
 * <p>Esta é a classe que contém o método {@code main} e serve como ponto de entrada
 * da aplicação. Ela é responsável por inicializar os componentes principais do sistema:</p>
 * 
 * <ul>
 *   <li><b>ManArqTxt:</b> Manipulador de arquivos de texto para persistência de dados</li>
 *   <li><b>Gerenciamento:</b> Controlador central com regras de negócio</li>
 *   <li><b>JanelaLogin:</b> Interface gráfica inicial (tela de login)</li>
 * </ul>
 * 
 * <p><b>Fluxo de execução:</b></p>
 * <ol>
 *   <li>Cria instância do manipulador de arquivos</li>
 *   <li>Cria instância do gerenciador passando o manipulador</li>
 *   <li>Cria e exibe a janela de login passando o gerenciador</li>
 *   <li>A partir daí, a navegação é controlada pelas janelas da interface</li>
 * </ol>
 * 
 * @author POO_academia
 * @version 1.0
 * @see ManArqTxt
 * @see Gerenciamento
 * @see JanelaLogin
 */
public class Academia {
    
    /**
     * Método principal que inicia a aplicação.
     * 
     * <p>Inicializa os componentes do sistema na ordem correta e exibe
     * a tela de login para o usuário.</p>
     * 
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        // Cria manipulador de arquivos para persistência
        ManArqTxt manipulartxt = new ManArqTxt();
        
        // Cria gerenciador central com as regras de negócio
        Gerenciamento gerenciamento = new Gerenciamento(manipulartxt);
        
        // Cria e exibe a janela de login
        JanelaLogin janelaLogin = new JanelaLogin(gerenciamento);
        janelaLogin.setVisible(true);
    }
}

