package principal;

import controle.Ficha;

/**
 * Classe que representa um usuário/membro da academia no sistema.
 * 
 * <p>Esta classe encapsula todas as informações pessoais de um usuário cadastrado,
 * incluindo dados de identificação, contato e sua ficha de treino personalizada.</p>
 * 
 * <p><b>Dados armazenados:</b></p>
 * <ul>
 *   <li><b>Identificação:</b> usuário (username) e senha (hash SHA-1)</li>
 *   <li><b>Dados pessoais:</b> nome completo</li>
 *   <li><b>Contato:</b> email e telefone</li>
 *   <li><b>Treino:</b> ficha de exercícios personalizada</li>
 * </ul>
 * 
 * @author POO_academia
 * @version 1.0
 * @see Ficha
 * @see Gerenciamento
 */
public class Pessoa {
    
    /**
     * Nome de usuário (username) único para login no sistema.
     */
    private String usuario;
    
    /**
     * Nome completo da pessoa.
     */
    private String nome;
    
    /**
     * Endereço de email da pessoa.
     */
    private String email;
    
    /**
     * Número de telefone para contato.
     */
    private String telefone;
    
    /**
     * Senha da pessoa (armazenada como hash SHA-1 para segurança).
     */
    private String senha;
    
    /**
     * Ficha de treino contendo todos os exercícios do usuário.
     */
    private Ficha ficha;

    /**
     * Construtor completo que cria uma pessoa com todos os dados.
     * A ficha de treino é inicializada vazia.
     * 
     * @param usuario nome de usuário único para login
     * @param nome nome completo da pessoa
     * @param email endereço de email
     * @param telefone número de telefone
     * @param senha senha (deve ser passada já em formato hash SHA-1)
     */
    public Pessoa(String usuario, String nome, String email, String telefone, String senha) {
        this.usuario = usuario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.ficha = new Ficha();
    }
    
    /**
     * Construtor padrão que cria uma pessoa com dados vazios.
     * Todos os campos de texto são inicializados como strings vazias
     * e uma ficha vazia é criada.
     */
    public Pessoa() {
        this.usuario = "";
        this.nome = "";
        this.email = "";
        this.telefone = "";
        this.senha = "";
        this.ficha = new Ficha();
    }

    /**
     * Obtém o nome de usuário.
     * 
     * @return o nome de usuário
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Obtém o nome completo da pessoa.
     * 
     * @return o nome completo
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define um novo nome para a pessoa.
     * 
     * @param nome o novo nome completo
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o email da pessoa.
     * 
     * @return o endereço de email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define um novo email para a pessoa.
     * 
     * @param email o novo endereço de email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém o telefone da pessoa.
     * 
     * @return o número de telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define um novo telefone para a pessoa.
     * 
     * @param telefone o novo número de telefone
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Obtém a senha da pessoa (em formato hash SHA-1).
     * 
     * @return a senha em formato hash
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define uma nova senha para a pessoa.
     * <b>Atenção:</b> A senha deve ser passada já em formato hash SHA-1.
     * 
     * @param senha a nova senha (hash SHA-1)
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Obtém a ficha de treino da pessoa.
     * 
     * @return a ficha contendo todos os exercícios
     */
    public Ficha getFicha() {
        return ficha;
    }

    /**
     * Define uma nova ficha de treino para a pessoa.
     * 
     * @param ficha a nova ficha de exercícios
     */
    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    /**
     * Retorna uma representação em texto dos dados da pessoa.
     * Não inclui a ficha de treino, apenas dados pessoais.
     * 
     * @return string formatada com os dados da pessoa
     */
    @Override
    public String toString() {
        return "Pessoa{" + "usuario=" + usuario + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", senha=" + senha + '}';
    }
    
}
