package controle;

import exceptions.LeituraEscritaException;
import principal.Aerobico;
import principal.Anaerobico;
import principal.Exercicio;
import principal.Pessoa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

/**
 * Classe responsável pela manipulação de arquivos de texto para persistência de dados.
 * 
 * <p>Esta classe gerencia todas as operações de leitura e escrita no arquivo de dados
 * do sistema, que armazena informações de usuários e suas fichas de treino. O formato
 * do arquivo é texto puro com campos separados por ponto-e-vírgula (;).</p>
 * 
 * <p><b>Formato do arquivo (login.txt):</b></p>
 * <pre>
 * Linha básica (sem treino):
 * usuario;nome;email;telefone;hashSenha
 * 
 * Linha com treino:
 * usuario;nome;email;telefone;hashSenha;[exercícios]
 * 
 * Exercício Aeróbico:
 * AEROBICO;nome;tempoMinutos
 * 
 * Exercício Anaeróbico:
 * nome;grupoMuscular;repeticoes;series;carga
 * </pre>
 * 
 * <p><b>Operações disponíveis:</b></p>
 * <ul>
 *   <li>Abrir e fechar arquivos para leitura/gravação</li>
 *   <li>Carregar todos os usuários na inicialização</li>
 *   <li>Gravar novos usuários (append)</li>
 *   <li>Alterar dados de usuário existente</li>
 * </ul>
 * 
 * @author POO_academia
 * @version 1.0
 * @see Pessoa
 * @see Ficha
 * @see Gerenciamento
 */
public class ManArqTxt {
    
    /**
     * Caminho do arquivo de dados de login e usuários.
     */
    private String arquivo_login;
    
    /**
     * Objeto Formatter para gravação de novos registros (modo append).
     */
    private Formatter gravador_login;
    
    /**
     * Scanner para leitura do arquivo de dados.
     */
    private Scanner leitor_login;
    
    /**
     * Formatter para edição completa do arquivo (modo overwrite).
     */
    private Formatter gravador_editor;

    /**
     * Construtor que inicializa o manipulador de arquivos.
     * Define o caminho do arquivo de dados.
     */
    public ManArqTxt() {
        arquivo_login = "src/arquivos/login.txt";
    }

    // ============================================================
    // MÉTODOS PARA ABRIR E FECHAR ARQUIVOS
    // ============================================================

    /**
     * Abre o arquivo para gravação em modo append (adicionar ao final).
     * Usado para adicionar novos usuários sem sobrescrever dados existentes.
     * 
     * @throws LeituraEscritaException se houver erro ao abrir o arquivo
     */
    public void abrirArquivoGravacaoLogin() throws LeituraEscritaException{
        try{
            gravador_login = new Formatter(new FileWriter(arquivo_login, true));
        } catch (IOException e) {
            gravador_login = null;
            throw new LeituraEscritaException("erro abrindo arquivo!");
        }
    }

    /**
     * Fecha o arquivo de gravação (modo append).
     * Sempre verifica se o gravador não é null antes de fechar.
     */
    public void fecharArquivoGravacaoLogin(){
        if (gravador_login != null){
            gravador_login.close();
        }
    }

    /**
     * Abre o arquivo para leitura de dados.
     * Usado para carregar usuários ou verificar dados.
     * 
     * @throws LeituraEscritaException se o arquivo não for encontrado
     */
    public void abrirArquivoLeituraLogin() throws LeituraEscritaException{
        try{
            leitor_login = new Scanner(new File(arquivo_login));
        } catch (FileNotFoundException e) {
            throw new LeituraEscritaException("erro abrindo arquivo!");
        }
    }

    /**
     * Fecha o arquivo de leitura.
     * Sempre verifica se o leitor não é null antes de fechar.
     */
    public void fecharArquivoLeituraLogin(){
        if (leitor_login != null){
            leitor_login.close();
        }
    }

    /**
     * Abre o arquivo para gravação em modo overwrite (sobrescrever).
     * Usado para editar/atualizar dados existentes, reescrevendo todo o arquivo.
     * 
     * @throws LeituraEscritaException se houver erro ao abrir o arquivo
     */
    public void abrirArquivoEditor() throws LeituraEscritaException{
        try{
            gravador_editor = new Formatter(new FileWriter(arquivo_login, false));
        } catch (IOException e) {
            gravador_editor = null;
            throw new LeituraEscritaException("erro abrindo arquivo!");
        }
    }

    /**
     * Fecha o arquivo editor (modo overwrite).
     * Sempre verifica se o gravador não é null antes de fechar.
     */
    public void fecharArquivoEditor(){
        if (gravador_editor != null){
            gravador_editor.close();
        }
    }

    // ============================================================
    // MÉTODOS PARA MANIPULAÇÃO DE DADOS
    // ============================================================

    /**
     * Grava um novo registro de login no arquivo (modo append).
     * 
     * <p>Adiciona uma nova linha ao final do arquivo com os dados básicos do usuário.
     * A ficha de treino é inicializada vazia e não é gravada neste momento.</p>
     * 
     * <p><b>Formato da linha:</b> usuario;nome;email;telefone;senha</p>
     * 
     * @param usuario nome de usuário único
     * @param nome nome completo
     * @param email endereço de email
     * @param telefone número de telefone
     * @param senha hash SHA-1 da senha
     * @throws LeituraEscritaException se houver erro ao gravar
     */
    public void gravarLogin(String usuario, String nome, String email, String telefone, String senha) throws LeituraEscritaException {
        try{
            abrirArquivoGravacaoLogin();
            gravador_login.format("%s;%s;%s;%s;%s\n", usuario, nome, email, telefone, senha);
        } catch (Exception e) {
            throw new LeituraEscritaException("erro abrindo arquivo!");
        }
        fecharArquivoGravacaoLogin();
    }

    /**
     * Carrega todos os usuários e suas fichas do arquivo na inicialização do sistema.
     * 
     * <p>Este método é chamado quando o sistema inicia e é responsável por reconstruir
     * todos os objetos Pessoa a partir dos dados salvos em formato texto.</p>
     * 
     * <p><b>Processo de leitura:</b></p>
     * <ol>
     *   <li>Abre o arquivo para leitura</li>
     *   <li>Para cada linha, faz o split por ";"</li>
     *   <li>Se tiver 5 campos: usuário sem treino (apenas dados básicos)</li>
     *   <li>Se tiver mais de 5 campos: usuário com ficha de treino</li>
     *   <li>Reconstrói exercícios baseado nos dados sequenciais</li>
     *   <li>Adiciona pessoa à lista de retorno</li>
     * </ol>
     * 
     * <p><b>Identificação de exercícios:</b></p>
     * <ul>
     *   <li>Se campo contém "AEROBICO": próximos 2 campos são nome e tempo</li>
     *   <li>Senão: próximos 4 campos são nome, tipo, reps, séries e carga</li>
     * </ul>
     * 
     * @return lista de todos os usuários carregados do arquivo
     * @throws LeituraEscritaException se houver erro ao ler o arquivo
     */
    public ArrayList<Pessoa> leituraInicial() throws LeituraEscritaException{
        ArrayList<Pessoa> usuarios = new ArrayList<>();
        try {
            abrirArquivoLeituraLogin();
            
            // Processa cada linha do arquivo
            while (leitor_login.hasNextLine()) {
                String linha = leitor_login.nextLine();
                String[] dados = linha.split(";");

                // Verifica se a linha está vazia
                if (dados.length == 0){
                    break;
                }

                if (dados.length == 5) {
                    // Usuário sem ficha de treino (apenas dados básicos)
                    Pessoa p = new Pessoa(dados[0], dados[1], dados[2], dados[3], dados[4]);
                    usuarios.add(p);
                }
                else{
                    // Usuário com ficha de treino
                    Pessoa p = new Pessoa(dados[0], dados[1], dados[2], dados[3], dados[4]);
                    Ficha f = new Ficha();
                    
                    // Processa exercícios sequencialmente a partir do índice 5
                    for (int i = 5; i < dados.length; ){
                        if(dados[i].equals("AEROBICO")){
                            // Exercício aeróbico: AEROBICO;nome;tempo
                            f.adicionarExercicio(new Aerobico(dados[i+1], Integer.parseInt(dados[i+2])));
                            i = i+3;  // Avança 3 posições
                        }
                        else{
                            // Exercício anaeróbico: nome;tipo;reps;series;carga
                            f.adicionarExercicio(new Anaerobico(
                                dados[i],                              // nome
                                Anaerobico.tipo.valueOf(dados[i+1]),   // tipo/grupo muscular
                                Integer.parseInt(dados[i+2]),          // repetições
                                Integer.parseInt(dados[i+3]),          // séries
                                Integer.parseInt(dados[i+4])           // carga
                            ));
                            i = i+5;  // Avança 5 posições
                        }
                    }
                    p.setFicha(f);
                    usuarios.add(p);
                }
            }
        } catch (LeituraEscritaException e) {
            throw new LeituraEscritaException("erro abrindo arquivo!");
        } finally {
            fecharArquivoLeituraLogin();
        }
        return usuarios;
    }
    
    /**
     * Altera os dados de um usuário existente no arquivo.
     * 
     * <p>Este método reescreve completamente o arquivo mantendo todos os outros usuários
     * inalterados e substituindo apenas a linha do usuário alterado. Isso garante que
     * mudanças nos dados pessoais ou na ficha de treino sejam persistidas.</p>
     * 
     * <p><b>Processo de alteração:</b></p>
     * <ol>
     *   <li>Lê todo o arquivo e armazena linhas de outros usuários</li>
     *   <li>Fecha arquivo de leitura</li>
     *   <li>Abre arquivo em modo overwrite</li>
     *   <li>Reescreve todas as linhas dos outros usuários</li>
     *   <li>Escreve nova linha do usuário alterado com ficha atualizada</li>
     *   <li>Fecha arquivo</li>
     * </ol>
     * 
     * <p><b>Formato da linha gravada:</b></p>
     * <pre>
     * usuario;nome;email;telefone;senha;[exercícios]
     * 
     * Para cada exercício da ficha:
     * - Aeróbico: AEROBICO;nome;tempo;
     * - Anaeróbico: nome;tipo;reps;series;carga;
     * - Último exercício: sem ";" no final, apenas "\n"
     * </pre>
     * 
     * @param usuario objeto Pessoa com dados atualizados a serem salvos
     * @throws LeituraEscritaException se houver erro ao ler ou gravar
     */
    public void alterarCadastro(Pessoa usuario) throws LeituraEscritaException{
        // FASE 1: Leitura - coleta linhas de outros usuários
        try{
            abrirArquivoLeituraLogin();
        } catch (LeituraEscritaException e) {
            throw e;
        }
        
        ArrayList<String> dados = new ArrayList<>();
        try{
            // Lê todas as linhas, mantendo apenas as de outros usuários
            while(leitor_login.hasNextLine()){
                String linha = leitor_login.nextLine();
                String[] itens = linha.split(";");
                if (!itens[0].equals(usuario.getUsuario())){
                    dados.add(linha);  // Mantém linha de outro usuário
                }
            }
        } catch (Exception e) {
            throw new LeituraEscritaException("erro abrindo arquivo!");
        }
        finally {
            fecharArquivoLeituraLogin();
        }
        
        // FASE 2: Gravação - reescreve arquivo com dados atualizados
        try{
            abrirArquivoEditor();
            
            // Reescreve linhas dos outros usuários
            for (int i = 0; i < dados.size(); i++){
                gravador_editor.format("%s\n", dados.get(i).trim());
            }
            
            // Escreve dados básicos do usuário alterado
            gravador_editor.format("%s;%s;%s;%s;%s;", 
                usuario.getUsuario(), 
                usuario.getNome(), 
                usuario.getEmail(), 
                usuario.getTelefone(), 
                usuario.getSenha()
            );
            
            // Escreve exercícios da ficha
            for (int i = 0; i < usuario.getFicha().totalExercicios(); i++){
                Exercicio e = usuario.getFicha().getExercicios().get(i);
                
                // Verifica se é o último exercício (para não colocar ";" no final)
                boolean isUltimo = (i == usuario.getFicha().totalExercicios()-1);
                
                if (e.getClass() == Aerobico.class) {
                    // Formato aeróbico: AEROBICO;nome;tempo
                    if (isUltimo) {
                        gravador_editor.format("%s;%s;%d", 
                            "AEROBICO", 
                            e.getNome(), 
                            ((Aerobico) e).getTempoMinutos()
                        );
                    } else {
                        gravador_editor.format("%s;%s;%d;", 
                            "AEROBICO", 
                            e.getNome(), 
                            ((Aerobico) e).getTempoMinutos()
                        );
                    }
                } else {
                    // Formato anaeróbico: nome;tipo;reps;series;carga
                    if (isUltimo) {
                        gravador_editor.format("%s;%s;%d;%d;%d", 
                            e.getNome(), 
                            ((Anaerobico) e).getAtividade(), 
                            ((Anaerobico) e).getN_repeticoes(), 
                            ((Anaerobico) e).getN_series(), 
                            ((Anaerobico) e).getSugestaoDeCarga()
                        );
                    } else {
                        gravador_editor.format("%s;%s;%d;%d;%d;", 
                            e.getNome(), 
                            ((Anaerobico) e).getAtividade(), 
                            ((Anaerobico) e).getN_repeticoes(), 
                            ((Anaerobico) e).getN_series(), 
                            ((Anaerobico) e).getSugestaoDeCarga()
                        );
                    }
                }
                
                // Adiciona quebra de linha apenas após o último exercício
                if (isUltimo) {
                    gravador_editor.format("\n");
                }
            }
        } catch (Exception e) {
            throw new LeituraEscritaException("erro abrindo arquivo!");
        }
        finally {
            fecharArquivoEditor();
        }
    }
}
