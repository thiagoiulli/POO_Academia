package principal;

public abstract class Exercicio {
    protected String nome;

    public Exercicio(String nome){
        this.nome = nome;
    }

    public Exercicio(){
        this.nome = "";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

