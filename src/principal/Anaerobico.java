package principal;

/**
 * Classe que representa um exercício anaeróbico (musculação) no sistema de academia.
 * 
 * <p>Exercícios anaeróbicos são atividades de força/musculação que visam o desenvolvimento
 * muscular e ganho de força. São caracterizados por repetições, séries, carga e grupo
 * muscular trabalhado.</p>
 * 
 * <p><b>Exemplos de exercícios anaeróbicos:</b></p>
 * <ul>
 *   <li>Supino reto (PEITO)</li>
 *   <li>Remada inclinada (COSTAS)</li>
 *   <li>Rosca direta (BICEPS)</li>
 *   <li>Agachamento (QUADRICEPS)</li>
 * </ul>
 * 
 * @author POO_academia
 * @version 1.0
 * @see Exercicio
 * @see GeraTreino#gerarAnaerobico(int, String, tipo)
 */
public class Anaerobico extends Exercicio{

    /**
     * Enumeração que define os tipos de grupos musculares trabalhados em exercícios anaeróbicos.
     * 
     * <p>Cada constante representa um grupo muscular específico:</p>
     * <ul>
     *   <li><b>PEITO</b> - Músculo peitoral</li>
     *   <li><b>COSTAS</b> - Músculos dorsais</li>
     *   <li><b>BICEPS</b> - Músculo bíceps braquial</li>
     *   <li><b>TRICEPS</b> - Músculo tríceps braquial</li>
     *   <li><b>QUADRICEPS</b> - Músculo quadríceps femoral (coxa)</li>
     *   <li><b>GLUTEO</b> - Músculo glúteo</li>
     *   <li><b>PANTURRILHA</b> - Músculo gastrocnêmio (panturrilha)</li>
     * </ul>
     */
    public enum tipo {PEITO, COSTAS, BICEPS, TRICEPS, QUADRICEPS, GLUTEO, PANTURRILHA}
    
    /**
     * Número de repetições a serem executadas em cada série do exercício.
     */
    private int n_repeticoes;
    
    /**
     * Número de séries do exercício.
     */
    private int n_series;
    
    /**
     * Sugestão de carga em quilogramas (kg) para o exercício.
     */
    private int sugestaoDeCarga;
    
    /**
     * Grupo muscular trabalhado pelo exercício.
     */
    /**
     * Grupo muscular trabalhado pelo exercício.
     */
    private tipo atividade;
    
    /**
     * Construtor completo que cria um exercício anaeróbico com todos os parâmetros.
     * 
     * @param nome o nome do exercício (ex: "Supino reto", "Rosca direta")
     * @param atividade o grupo muscular trabalhado
     * @param n_repeticoes o número de repetições por série
     * @param n_series o número de séries do exercício
     * @param sugestaoDeCarga a carga sugerida em kg
     */
    public Anaerobico(String nome, tipo atividade, int n_repeticoes, int n_series, int sugestaoDeCarga) {
        super(nome);
        this.atividade = atividade;
        this.n_repeticoes = n_repeticoes;
        this.n_series = n_series;
        this.sugestaoDeCarga = sugestaoDeCarga;
    }
    
    /**
     * Construtor padrão que cria um exercício anaeróbico sem valores definidos.
     * Inicializa repetições, séries e carga com zero e atividade como PEITO.
     */
    public Anaerobico(){
        super();
        n_repeticoes = 0;
        n_series = 0;
        sugestaoDeCarga = 0;
        atividade = tipo.PEITO;
    }

    /**
     * Obtém o número de repetições do exercício.
     * 
     * @return o número de repetições por série
     */
    public int getN_repeticoes() {
        return n_repeticoes;
    }

    /**
     * Define o número de repetições do exercício.
     * 
     * @param n_repeticoes o novo número de repetições por série
     */
    public void setN_repeticoes(int n_repeticoes) {
        this.n_repeticoes = n_repeticoes;
    }

    /**
     * Obtém o número de séries do exercício.
     * 
     * @return o número de séries
     */
    public int getN_series() {
        return n_series;
    }

    /**
     * Define o número de séries do exercício.
     * 
     * @param n_series o novo número de séries
     */
    public void setN_series(int n_series) {
        this.n_series = n_series;
    }

    /**
     * Obtém a sugestão de carga para o exercício.
     * 
     * @return a carga sugerida em quilogramas
     */
    public int getSugestaoDeCarga() {
        return sugestaoDeCarga;
    }

    /**
     * Define a sugestão de carga para o exercício.
     * 
     * @param sugestaoDeCarga a nova carga sugerida em quilogramas
     */
    public void setSugestaoDeCarga(int sugestaoDeCarga) {
        this.sugestaoDeCarga = sugestaoDeCarga;
    }

    /**
     * Obtém o grupo muscular trabalhado pelo exercício.
     * 
     * @return o tipo de atividade (grupo muscular)
     */
    public tipo getAtividade() {
        return atividade;
    }

    /**
     * Define o grupo muscular trabalhado pelo exercício.
     * 
     * @param atividade o novo tipo de atividade (grupo muscular)
     */
    public void setAtividade(tipo atividade) {
        this.atividade = atividade;
    }
}
