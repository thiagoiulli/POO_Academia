package controle;

import principal.Exercicio;
import java.util.ArrayList;

/**
 * Classe que representa a ficha de treino de um usuário da academia.
 * 
 * <p>Uma ficha de treino é uma coleção de exercícios (aeróbicos e anaeróbicos) que
 * compõem o programa de treinamento personalizado de cada membro da academia.
 * Esta classe gerencia a lista de exercícios e oferece operações para manipulá-la.</p>
 * 
 * <p><b>Funcionalidades principais:</b></p>
 * <ul>
 *   <li>Adicionar e remover exercícios</li>
 *   <li>Pesquisar exercícios por nome</li>
 *   <li>Verificar existência de exercícios</li>
 *   <li>Obter contagem total de exercícios</li>
 * </ul>
 * 
 * @author POO_academia
 * @version 1.0
 * @see Exercicio
 * @see Pessoa
 */
public class Ficha {
    
    /**
     * Lista de exercícios que compõem a ficha de treino.
     * Pode conter objetos Aerobico e/ou Anaerobico.
     */
    private ArrayList<Exercicio> exercicios;
    
    /**
     * Construtor que cria uma ficha de treino vazia.
     * Inicializa a lista de exercícios pronta para receber novos itens.
     */
    public Ficha() {
        this.exercicios = new ArrayList<>();
    }
    
    /**
     * Adiciona um novo exercício à ficha de treino.
     * 
     * <p>O exercício só é adicionado se não for {@code null}.
     * Exercícios duplicados são permitidos.</p>
     * 
     * @param exercicio o exercício a ser adicionado (Aerobico ou Anaerobico)
     */
    public void adicionarExercicio(Exercicio exercicio) {
        if (exercicio != null) {
            exercicios.add(exercicio);
        }
    }
    
    /**
     * Remove um exercício específico da ficha de treino.
     * 
     * @param exercicio o exercício a ser removido
     */
    public void removerExercicio(Exercicio exercicio) {
        exercicios.remove(exercicio);
    }

    /**
     * Remove um exercício da ficha pelo seu índice na lista.
     * 
     * <p>Verifica se o índice é válido antes de remover para evitar exceções.</p>
     * 
     * @param indice o índice do exercício a ser removido (começando em 0)
     */
    public void removerExercicioPorIndice(int indice) {
        if (indice >= 0 && indice < exercicios.size()) {
            exercicios.remove(indice);
        }
    }
    
    /**
     * Obtém a lista completa de exercícios da ficha.
     * 
     * @return ArrayList contendo todos os exercícios
     */
    public ArrayList<Exercicio> getExercicios() {
        return exercicios;
    }
    
    /**
     * Define uma nova lista de exercícios para a ficha.
     * Substitui completamente a lista existente.
     * 
     * @param exercicios a nova lista de exercícios
     */
    public void setExercicios(ArrayList<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }

    /**
     * Retorna o número total de exercícios na ficha.
     * 
     * @return quantidade de exercícios cadastrados
     */
    public int totalExercicios() {
        return exercicios.size();
    }

    /**
     * Pesquisa um exercício na ficha pelo nome (ou parte dele).
     * 
     * <p>A busca é case-insensitive e retorna o primeiro exercício encontrado
     * cujo nome contenha o texto pesquisado.</p>
     * 
     * @param nome o nome (ou parte do nome) do exercício a pesquisar
     * @return o primeiro exercício encontrado, ou {@code null} se não encontrar
     */
    public Exercicio pesquisarExercicioPorNome(String nome) {
        return exercicios.stream()
                .filter(e -> e.getNome().toLowerCase().contains(nome.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Verifica se um exercício com determinado nome existe na ficha.
     * 
     * <p>A comparação é case-insensitive e busca por nome exato.</p>
     * 
     * @param nomeExercicio o nome do exercício a verificar
     * @return {@code true} se o exercício existe, {@code false} caso contrário
     */
    public boolean contemExercicio(String nomeExercicio) {
        return exercicios.stream()
                .anyMatch(e -> e.getNome().equalsIgnoreCase(nomeExercicio));
    }

    /**
     * Retorna uma representação em texto da ficha de treino.
     * Mostra apenas o número total de exercícios.
     * 
     * @return string formatada com informações da ficha
     */
    @Override
    public String toString() {
        return "Ficha{" +
                "totalExercicios=" + exercicios.size() +
                '}';
    }
}
