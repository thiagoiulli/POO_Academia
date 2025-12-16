package principal;

public class Anaerobico extends Exercicio{
    public enum tipo {PEITO, COSTAS, BICEPS, TRICEPS, QUADRICEPS, GLUTEO, PANTURRILHA}

    private int n_repeticoes;
    private int n_series;
    private float sugestaoDeCarga;
    private tipo atividade;

    public Anaerobico(){
        n_repeticoes = 0;
        n_series = 0;
        sugestaoDeCarga = 0;
        atividade = tipo.PEITO;
    }

    public int getN_repeticoes() {
        return n_repeticoes;
    }

    public void setN_repeticoes(int n_repeticoes) {
        this.n_repeticoes = n_repeticoes;
    }

    public int getN_series() {
        return n_series;
    }

    public void setN_series(int n_series) {
        this.n_series = n_series;
    }

    public float getSugestaoDeCarga() {
        return sugestaoDeCarga;
    }

    public void setSugestaoDeCarga(float sugestaoDeCarga) {
        this.sugestaoDeCarga = sugestaoDeCarga;
    }

    public tipo getAtividade() {
        return atividade;
    }

    public void setAtividade(tipo atividade) {
        this.atividade = atividade;
    }
}
