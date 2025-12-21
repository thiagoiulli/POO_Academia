package controle;

import principal.Exercicio;
import java.util.ArrayList;

public class Ficha {
    private ArrayList<Exercicio> exercicios;
    private String dataCreate;
    private String ultimaAtualizacao;
    
    public Ficha() {
        this.exercicios = new ArrayList<>();
        this.dataCreate = java.time.LocalDate.now().toString();
        this.ultimaAtualizacao = java.time.LocalDate.now().toString();
    }

    public Ficha(String dataCreate) {
        this.exercicios = new ArrayList<>();
        this.dataCreate = dataCreate;
        this.ultimaAtualizacao = java.time.LocalDate.now().toString();
    }
    
    public void adicionarExercicio(Exercicio exercicio) {
        if (exercicio != null) {
            exercicios.add(exercicio);
            atualizarData();
        }
    }
    
    public void removerExercicio(Exercicio exercicio) {
        exercicios.remove(exercicio);
        atualizarData();
    }

    public void removerExercicioPorIndice(int indice) {
        if (indice >= 0 && indice < exercicios.size()) {
            exercicios.remove(indice);
            atualizarData();
        }
    }
    
    public ArrayList<Exercicio> getExercicios() {
        return exercicios;
    }
    
    public void setExercicios(ArrayList<Exercicio> exercicios) {
        this.exercicios = exercicios;
        atualizarData();
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

    private void atualizarData() {
        this.ultimaAtualizacao = java.time.LocalDate.now().toString();
    }

    public String getDataCreate() {
        return dataCreate;
    }

    public String getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    @Override
    public String toString() {
        return "Ficha{" +
                "totalExercicios=" + exercicios.size() +
                ", dataCriacao='" + dataCreate + '\'' +
                ", ultimaAtualizacao='" + ultimaAtualizacao + '\'' +
                '}';
    }
}
