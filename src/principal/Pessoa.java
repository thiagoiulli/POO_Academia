package principal;

import controle.Ficha;

public class Pessoa {
    private String usuario;
    private String nome;
    private String email;
    private String telefone;
    private String senha;
    private Ficha ficha;

    public Pessoa(String usuario, String nome, String email, String telefone, String senha) {
        this.usuario = usuario;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.ficha = new Ficha();
    }
    
    public Pessoa() {
        this.usuario = "";
        this.nome = "";
        this.email = "";
        this.telefone = "";
        this.senha = "";
        this.ficha = new Ficha();
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Ficha getFicha() {
        return ficha;
    }

    public void setFicha(Ficha ficha) {
        this.ficha = ficha;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "usuario=" + usuario + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", senha=" + senha + '}';
    }
    
}
