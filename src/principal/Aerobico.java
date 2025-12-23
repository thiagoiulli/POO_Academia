package principal;

/**
 * Classe que representa um exercício aeróbico (cardiovascular) no sistema de academia.
 * 
 * <p>Exercícios aeróbicos são atividades de cardio que visam melhorar a capacidade cardiovascular
 * e queimar calorias. São caracterizados principalmente pela duração em minutos.</p>
 * 
 * <p><b>Exemplos de exercícios aeróbicos:</b></p>
 * <ul>
 *   <li>Corrida</li>
 *   <li>Bicicleta</li>
 *   <li>Natação</li>
 * </ul>
 * 
 * @author POO_academia
 * @version 1.0
 * @see Exercicio
 * @see GeraTreino#gerarAerobico(int, String)
 */
public class Aerobico extends Exercicio{
    
    /**
     * Tempo de duração do exercício aeróbico em minutos.
     */
    private int tempoMinutos;

    /**
     * Construtor padrão que cria um exercício aeróbico sem valores definidos.
     * Inicializa o tempo com zero minutos.
     */
    public Aerobico(){
        super();
        tempoMinutos = 0;
    }

    /**
     * Construtor que cria um exercício aeróbico com nome e tempo especificados.
     * 
     * @param nome o nome do exercício aeróbico (ex: "Corrida", "Bicicleta")
     * @param tempoMinutos a duração do exercício em minutos
     */
    public Aerobico(String nome, int tempoMinutos) {
        super(nome);
        this.tempoMinutos = tempoMinutos;
    }

    /**
     * Obtém a duração do exercício aeróbico.
     * 
     * @return o tempo em minutos
     */
    public int getTempoMinutos() {
        return tempoMinutos;
    }

    /**
     * Define a duração do exercício aeróbico.
     * 
     * @param tempoMinutos o novo tempo em minutos
     */
    public void setTempoMinutos(int tempoMinutos) {
        this.tempoMinutos = tempoMinutos;
    }
}
