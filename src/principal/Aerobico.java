package principal;

public class Aerobico extends Exercicio{
    private float tempoMinutos;
    private int intensidade; //1-5 ou 1-10, nao sei ainda

    public Aerobico(){
        tempoMinutos = 0;
        intensidade = 0;
        nome = "";
    }

    public float getTempoMinutos() {
        return tempoMinutos;
    }

    public void setTempoMinutos(float tempoMinutos) {
        this.tempoMinutos = tempoMinutos;
    }

    public int getIntensidade() {
        return intensidade;
    }

    public void setIntensidade(int intensidade) {
        this.intensidade = intensidade;
    }
}
