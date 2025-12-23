package principal;

public class Aerobico extends Exercicio{
    private int tempoMinutos;

    public Aerobico(){
        super();
        tempoMinutos = 0;
    }

    public Aerobico(String nome, int tempoMinutos) {
        super(nome);
        this.tempoMinutos = tempoMinutos;
    }

    public int getTempoMinutos() {
        return tempoMinutos;
    }

    public void setTempoMinutos(int tempoMinutos) {
        this.tempoMinutos = tempoMinutos;
    }
}
