package principal;

/**
 * Classe abstrata que representa um exercício genérico no sistema de academia.
 * 
 * <p>Esta classe serve como base para todos os tipos de exercícios (aeróbicos e anaeróbicos)
 * e define os atributos e comportamentos comuns a todos eles. Todo exercício possui um nome
 * que o identifica.</p>
 * 
 * <p><b>Hierarquia de classes:</b></p>
 * <ul>
 *   <li>{@link Aerobico} - exercícios aeróbicos (cardio)</li>
 *   <li>{@link Anaerobico} - exercícios anaeróbicos (musculação)</li>
 * </ul>
 * 
 * @author POO_academia
 * @version 1.0
 * @see Aerobico
 * @see Anaerobico
 * @see Ficha
 */
public abstract class Exercicio {
    
    /**
     * Nome do exercício (ex: "Corrida", "Supino", "Agachamento").
     */
    protected String nome;

    /**
     * Construtor que cria um exercício com um nome específico.
     * 
     * @param nome o nome do exercício
     */
    public Exercicio(String nome){
        this.nome = nome;
    }

    /**
     * Construtor padrão que cria um exercício sem nome.
     * O nome é inicializado como string vazia.
     */
    public Exercicio(){
        this.nome = "";
    }

    /**
     * Obtém o nome do exercício.
     * 
     * @return o nome do exercício
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define um novo nome para o exercício.
     * 
     * @param nome o novo nome a ser atribuído ao exercício
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}

