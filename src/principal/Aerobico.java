package principal;

public class Aerobico extends Exercicio{
    private float tempoMinutos;

    public Aerobico(){
        super();
        tempoMinutos = 0;
    }

    public Aerobico(String nome, float tempoMinutos) {
        super(nome);
        this.tempoMinutos = tempoMinutos;
    }

    public float getTempoMinutos() {
        return tempoMinutos;
    }

    public void setTempoMinutos(float tempoMinutos) {
        this.tempoMinutos = tempoMinutos;
    }
}
