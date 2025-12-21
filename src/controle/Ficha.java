package controle;

import principal.Exercicio;
import java.util.ArrayList;

public class Ficha {
    private ArrayList<Exercicio> exercicios;
    
    public Ficha() {
        this.exercicios = new ArrayList<>();
    }
    
    public void adicionarExercicio(Exercicio exercicio) {
        exercicios.add(exercicio);
    }
    
    public void removerExercicio(Exercicio exercicio) {
        exercicios.remove(exercicio);
    }
    
    public ArrayList<Exercicio> getExercicios() {
        return exercicios;
    }
    
    public void setExercicios(ArrayList<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }
}
