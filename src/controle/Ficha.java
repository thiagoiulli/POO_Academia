package controle;

import principal.Exercicio;
import java.util.ArrayList;

public class Ficha {
    private ArrayList<Exercicio> exercicios;
    
    public Ficha() {
        this.exercicios = new ArrayList<>();
    }
    
    public void adicionarExercicio(Exercicio exercicio) {
        if (exercicio != null) {
            exercicios.add(exercicio);
        }
    }
    
    public void removerExercicio(Exercicio exercicio) {
        exercicios.remove(exercicio);
    }

    public void removerExercicioPorIndice(int indice) {
        if (indice >= 0 && indice < exercicios.size()) {
            exercicios.remove(indice);
        }
    }
    
    public ArrayList<Exercicio> getExercicios() {
        return exercicios;
    }
    
    public void setExercicios(ArrayList<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }

    public int totalExercicios() {
        return exercicios.size();
    }

    public Exercicio pesquisarExercicioPorNome(String nome) {
        return exercicios.stream()
                .filter(e -> e.getNome().toLowerCase().contains(nome.toLowerCase()))
                .findFirst()
                .orElse(null);
    }

    public boolean contemExercicio(String nomeExercicio) {
        return exercicios.stream()
                .anyMatch(e -> e.getNome().equalsIgnoreCase(nomeExercicio));
    }

    @Override
    public String toString() {
        return "Ficha{" +
                "totalExercicios=" + exercicios.size() +
                '}';
    }
}
