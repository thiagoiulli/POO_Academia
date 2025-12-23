package controle;

import exceptions.LeituraEscritaException;
import exceptions.UsuarioExistenteException;
import exceptions.UsuarioOuSenhaIncorretosException;
import principal.Aerobico;
import principal.Anaerobico;
import principal.Exercicio;
import principal.Pessoa;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.util.HashMap;

/**
 * Classe controladora central que gerencia toda a lógica de negócio do sistema de academia.
 * 
 * <p>Esta classe é responsável por coordenar as operações principais do sistema, incluindo:</p>
 * <ul>
 *   <li><b>Autenticação:</b> Login e validação de credenciais</li>
 *   <li><b>Cadastro:</b> Registro e alteração de usuários</li>
 *   <li><b>Gerenciamento de treinos:</b> Geração e visualização de fichas de treino</li>
 *   <li><b>Segurança:</b> Hash de senhas usando SHA-1</li>
 *   <li><b>Persistência:</b> Integração com o sistema de arquivos</li>
 * </ul>
 * 
 * <p><b>Fluxo de autenticação:</b></p>
 * <ol>
 *   <li>Usuário fornece credenciais (usuário e senha)</li>
 *   <li>Sistema busca o usuário na lista</li>
 *   <li>Senha é convertida em hash SHA-1</li>
 *   <li>Hash é comparado com o hash armazenado</li>
 *   <li>Se válido, índice do usuário é armazenado em usuario_logado</li>
 * </ol>
 * 
 * @author POO_academia
 * @version 1.0
 * @see ManArqTxt
 * @see GeraTreino
 * @see Pessoa
 */
public class Gerenciamento {
    
    /**
     * Lista de todos os usuários cadastrados no sistema.
     */
    ArrayList<Pessoa> usuarios;
    
    /**
     * Índice do usuário atualmente logado na lista de usuários.
     * Valor -1 indica que nenhum usuário está logado.
     */
    int usuario_logado;
    
    /**
     * Manipulador de arquivos para persistência de dados.
     */
    ManArqTxt manipulartxt;
    
    /**
     * Gerador de treinos para criar fichas personalizadas.
     */
    /**
     * Gerador de treinos para criar fichas personalizadas.
     */
    GeraTreino geratreino;

    /**
     * Construtor que inicializa o sistema de gerenciamento.
     * 
     * <p>Realiza as seguintes operações:</p>
     * <ol>
     *   <li>Inicializa a lista de usuários</li>
     *   <li>Define usuario_logado como -1 (ninguém logado)</li>
     *   <li>Carrega dados salvos do arquivo (se existir)</li>
     *   <li>Inicializa o gerador de treinos</li>
     * </ol>
     * 
     * @param manipulartxt manipulador de arquivos para persistência
     */
    public Gerenciamento(ManArqTxt manipulartxt){
        usuarios = new ArrayList<>();
        this.usuario_logado = -1;
        this.manipulartxt = manipulartxt;
        
        // Tenta carregar usuários salvos do arquivo
        ArrayList<Pessoa> tmp = null;
        try {
            tmp = manipulartxt.leituraInicial();
        } catch (LeituraEscritaException e) {
            System.err.println("Não foi possível carregar arquivo com salvamento!");
        }
        if (tmp != null){
            usuarios.addAll(tmp);
        }

        geratreino = new GeraTreino();
    }

    /**
     * Cadastra uma nova pessoa no sistema.
     * 
     * <p>Valida se o nome de usuário já existe antes de cadastrar.
     * A senha é automaticamente convertida em hash SHA-1 para segurança.</p>
     * 
     * <p><b>Processo:</b></p>
     * <ol>
     *   <li>Verifica se o usuário já existe (lança exceção se sim)</li>
     *   <li>Converte a senha em hash SHA-1</li>
     *   <li>Cria novo objeto Pessoa</li>
     *   <li>Adiciona à lista de usuários</li>
     *   <li>Grava os dados no arquivo</li>
     * </ol>
     * 
     * @param usuario nome de usuário único para login
     * @param nome nome completo da pessoa
     * @param email endereço de email
     * @param telefone número de telefone
     * @param senha senha em texto puro (será convertida em hash)
     * @throws UsuarioExistenteException se o nome de usuário já estiver em uso
     * @throws NoSuchAlgorithmException se o algoritmo SHA-1 não estiver disponível
     * @throws LeituraEscritaException se houver erro ao gravar no arquivo
     */
    public void CadastrarPessoa(String usuario, String nome, String email, String telefone, String senha) throws UsuarioExistenteException, NoSuchAlgorithmException, LeituraEscritaException {
        // Verifica se o usuário já existe
        if (pesquisarUsuario(usuario) != -1){
            throw new UsuarioExistenteException("Nome de usuário em uso");
        }
        
        // Converte senha para hash SHA-1
        String hashSenha;
        try{
            hashSenha = hashPass(senha);
        }
        catch (NoSuchAlgorithmException e){
            System.err.println("Erro ao tratar senha!");
            throw e;
        }
        
        // Cria nova pessoa e adiciona à lista
        Pessoa p = new Pessoa(usuario, nome, email, telefone, hashSenha);
        usuarios.add(p);
        
        // Persiste os dados no arquivo
        try {
            manipulartxt.gravarLogin(usuario, nome, email, telefone, hashSenha);
        } catch (LeituraEscritaException e) {
            throw e;
        }
    }

    /**
     * Altera os dados do usuário atualmente logado.
     * 
     * <p>Permite alterar nome, email, telefone e opcionalmente a senha.
     * Se a senha for fornecida (não vazia), ela também será atualizada.</p>
     * 
     * @param nome novo nome completo
     * @param email novo endereço de email
     * @param telefone novo número de telefone
     * @param senha nova senha (deixar em branco para não alterar)
     * @throws NoSuchAlgorithmException se o algoritmo SHA-1 não estiver disponível
     * @throws LeituraEscritaException se houver erro ao salvar no arquivo
     */
    public void alterarPessoa(String nome, String email, String telefone, String senha) throws NoSuchAlgorithmException, LeituraEscritaException{
        usuarios.get(usuario_logado).setEmail(email);
        usuarios.get(usuario_logado).setNome(nome);
        usuarios.get(usuario_logado).setTelefone(telefone);
        
        // Atualiza senha apenas se fornecida
        if (!senha.isBlank()){
            String hashSenha;
            try{
                hashSenha = hashPass(senha);
            }
            catch (NoSuchAlgorithmException e){
                System.err.println("Erro ao tratar senha!");
                throw e;
            }
            usuarios.get(usuario_logado).setSenha(hashSenha);
        }
        
        // Persiste as alterações no arquivo
        try {
            manipulartxt.alterarCadastro(usuarios.get(usuario_logado));
        } catch (LeituraEscritaException e) {
            throw e;
        }
    }

    /**
     * Pesquisa um usuário na lista pelo nome de usuário.
     * 
     * <p>Método privado auxiliar usado internamente para validação.</p>
     * 
     * @param usuario nome de usuário a pesquisar
     * @return índice do usuário na lista, ou -1 se não encontrado
     */
    private int pesquisarUsuario(String usuario){
        for (int i = 0; i < usuarios.size(); i++){
            if (usuarios.get(i).getUsuario().equals(usuario)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Valida as credenciais de login e autentica o usuário.
     * 
     * <p>Este método realiza a autenticação completa:</p>
     * <ol>
     *   <li>Busca o usuário pelo nome</li>
     *   <li>Converte a senha fornecida em hash SHA-1</li>
     *   <li>Compara o hash com o hash armazenado</li>
     *   <li>Se válido, armazena o índice em usuario_logado</li>
     * </ol>
     * 
     * <p><b>Segurança:</b> A mensagem de erro não diferencia se foi o usuário
     * ou a senha que estava incorreto, seguindo boas práticas de segurança.</p>
     * 
     * @param usuario nome de usuário para login
     * @param senha senha em texto puro
     * @return true se o login foi bem-sucedido
     * @throws UsuarioOuSenhaIncorretosException se as credenciais forem inválidas
     * @throws NoSuchAlgorithmException se o algoritmo SHA-1 não estiver disponível
     */
    public boolean parseLogin(String usuario, String senha) throws UsuarioOuSenhaIncorretosException, NoSuchAlgorithmException {
        int i;
        
        // Verifica se o usuário existe
        if ((i = pesquisarUsuario(usuario)) == -1){
            throw new UsuarioOuSenhaIncorretosException("Usuário ou senha incorretos!");
        }
        
        // Converte senha para hash
        String hashSenha;
        try{
            hashSenha = hashPass(senha);
        }
        catch (NoSuchAlgorithmException e){
            throw e;
        }
        
        // Compara hash da senha fornecida com hash armazenado
        if (usuarios.get(i).getSenha().equals(hashSenha)){
            this.usuario_logado = i;  // Armazena índice do usuário logado
            return true;
        }
        else{
            throw new UsuarioOuSenhaIncorretosException("Usuário ou senha incorretos!");
        }
    }

    /**
     * Converte uma senha em texto puro para hash SHA-1.
     * 
     * <p>Utiliza o algoritmo SHA-1 para gerar um hash hexadecimal da senha,
     * proporcionando uma camada de segurança ao armazenar senhas.</p>
     * 
     * <p><b>Processo:</b></p>
     * <ol>
     *   <li>Obtém instância do MessageDigest para SHA-1</li>
     *   <li>Converte senha para bytes usando UTF-8</li>
     *   <li>Gera hash de bytes</li>
     *   <li>Converte bytes para string hexadecimal</li>
     * </ol>
     * 
     * @param senha senha em texto puro
     * @return hash SHA-1 da senha em formato hexadecimal
     * @throws NoSuchAlgorithmException se SHA-1 não estiver disponível
     */
    private String hashPass(String senha) throws NoSuchAlgorithmException{
        String hashSenha;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] encodedhash = digest.digest(senha.getBytes(StandardCharsets.UTF_8));
            
            // Converte array de bytes para string hexadecimal
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash){
                // AND com 255 (0xff) garante que todos os valores sejam positivos
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1){
                    hexString.append('0');  // Adiciona zero à esquerda se necessário
                }
                hexString.append(hex);
            }
            hashSenha = hexString.toString();
        }
        catch (NoSuchAlgorithmException e){
            throw e;
        }
        return hashSenha;
    }

    /**
     * Obtém o objeto Pessoa do usuário atualmente logado.
     * 
     * @return objeto Pessoa do usuário logado
     */
    public Pessoa getUsuarioLogado(){
        return usuarios.get(this.usuario_logado);
    }

    /**
     * Gera um treino personalizado para o usuário logado baseado nos parâmetros fornecidos.
     * 
     * <p>Este método cria uma nova ficha de treino combinando exercícios anaeróbicos e
     * aeróbicos conforme as escolhas do usuário. Cada tipo de exercício pode ter quantidade
     * e dificuldade personalizadas.</p>
     * 
     * <p><b>Processo:</b></p>
     * <ol>
     *   <li>Cria uma nova ficha vazia</li>
     *   <li>Para cada grupo muscular selecionado, gera exercícios anaeróbicos</li>
     *   <li>Se selecionado, gera exercícios aeróbicos</li>
     *   <li>Atribui a ficha ao usuário logado</li>
     *   <li>Persiste as alterações no arquivo</li>
     * </ol>
     * 
     * @param formulario HashMap contendo as configurações para exercícios anaeróbicos
     *                   Chave: tipo de grupo muscular (PEITO, COSTAS, etc.)
     *                   Valor: ArrayList com 2 elementos [quantidade, dificuldade]
     * @param formulario_aerobico ArrayList com configurações de aeróbicos [quantidade, dificuldade]
     *                            ou null se não foram selecionados
     * @throws LeituraEscritaException se houver erro ao salvar no arquivo
     */
    public void gerarTreino(HashMap<Anaerobico.tipo, ArrayList<String>> formulario, ArrayList<String> formulario_aerobico) throws LeituraEscritaException{
        Ficha ficha = new Ficha();
        
        // Gera exercícios anaeróbicos para cada grupo muscular selecionado
        for (Anaerobico.tipo e : formulario.keySet()){
            Anaerobico[] anaerobicos = GeraTreino.gerarAnaerobico(
                Integer.parseInt(formulario.get(e).get(0)),  // quantidade
                formulario.get(e).get(1),                     // dificuldade
                e                                              // tipo/grupo muscular
            );
            
            // Adiciona todos os exercícios gerados à ficha
            for (int i = 0; i < anaerobicos.length; i++){
                ficha.adicionarExercicio(anaerobicos[i]);
            }
        }
        
        // Gera exercícios aeróbicos se foram selecionados
        if (formulario_aerobico != null){
            Aerobico[] aerobicos = GeraTreino.gerarAerobico(
                Integer.parseInt(formulario_aerobico.get(0)),  // quantidade
                formulario_aerobico.get(1)                      // dificuldade
            );
            
            // Adiciona todos os exercícios aeróbicos à ficha
            for (int i = 0; i < aerobicos.length; i++){
                ficha.adicionarExercicio(aerobicos[i]);
            }
        }

        // Atribui a nova ficha ao usuário logado e persiste
        usuarios.get(usuario_logado).setFicha(ficha);
        try{
            manipulartxt.alterarCadastro(usuarios.get(usuario_logado));
        } catch (LeituraEscritaException e) {
            throw e;
        }
    }

    /**
     * Retorna uma string formatada com todos os exercícios do treino do usuário logado.
     * 
     * <p>Formata os exercícios de forma legível, separando aeróbicos e anaeróbicos
     * com suas respectivas informações:</p>
     * <ul>
     *   <li><b>Aeróbico:</b> Nome - Tempo: X minutos</li>
     *   <li><b>Anaeróbico:</b> GRUPO_MUSCULAR: Nome - Repetições: RxS - Sug. carga: Xkg</li>
     * </ul>
     * 
     * @return string formatada com a lista completa de exercícios do treino
     */
    public String verTreino() {
        Pessoa usuarioAtual = usuarios.get(usuario_logado);
        Ficha fichaAtual = usuarioAtual.getFicha();
        ArrayList<Exercicio> exerciciosAtuais = fichaAtual.getExercicios();
        String listaExercicios = "";
        
        // Formata cada exercício conforme seu tipo
        for (Exercicio e : exerciciosAtuais) {
            if (e.getClass() == Aerobico.class){
                // Formato para aeróbico: Nome - Tempo
                listaExercicios += e.getNome() + " - Tempo: " + ((Aerobico) e).getTempoMinutos() + " minutos\n";
            }
            else{
                // Formato para anaeróbico: Grupo: Nome - Rep: RxS - Carga
                listaExercicios += ((Anaerobico) e).getAtividade() + ": " + e.getNome() + 
                                  " - Repeticões: " + ((Anaerobico) e).getN_repeticoes() + "x" + 
                                  ((Anaerobico) e).getN_series() + " - Sug. carga: " + 
                                  ((Anaerobico) e).getSugestaoDeCarga() + "kg\n";
            }
        }
        return listaExercicios;
    }
}
