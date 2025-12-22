package manArqTxt;

import principal.Pessoa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class ManArqTxt {
    private String arquivo_login;
    private Formatter gravador_login;
    private Scanner leitor_login;

    public ManArqTxt() {
        arquivo_login = "src/arquivos/login.txt";
    }

    // Metodos para abrir e fechar os arquivos

    public void abrirArquivoGravacaoLogin(){
        try{
            gravador_login = new Formatter(new FileWriter(arquivo_login, true));
        } catch (IOException e) {
            System.err.println("Não foi possivel criar/abrir o arquivo " + arquivo_login);
            gravador_login = null;
        }
    }

    public void fecharArquivoGravacaoLogin(){
        if (gravador_login != null){
            gravador_login.close();
        }
    }

    public void abrirArquivoLeituraLogin(){
        try{
            leitor_login = new Scanner(new File(arquivo_login));
        } catch (FileNotFoundException e) {
            System.err.println("Nao foi possivel abrir o arquivo " + arquivo_login);
        }
    }

    public void fecharArquivoLeituraLogin(){
        if (leitor_login != null){
            leitor_login.close();
        }
    }

    // Metodos para Login

    public boolean usuarioJaExiste(String usuarioProcurado) {
        abrirArquivoLeituraLogin();
        if (leitor_login == null) {
            return false;
        }
        try {
            while (leitor_login.hasNextLine()) {
                String linha = leitor_login.nextLine();
                String[] dados = linha.split(";");

                if (dados.length > 0 && dados[0].equalsIgnoreCase(usuarioProcurado)) {
                    fecharArquivoLeituraLogin();
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao ler o arquivo para verificação.");
        } finally {
            fecharArquivoLeituraLogin();
        }
        return false;
    }

    public void gravarLogin(String usuario, String nome, String email, String telefone, String senha) {
        abrirArquivoGravacaoLogin();
        if (!usuarioJaExiste(usuario)){
            try{
                gravador_login.format("%s;%s;%s;%s;%s\n", usuario, nome, email, telefone, senha);
            } catch (Exception e) {
                System.err.println("Erro ao gravar arquivo de login: " + e.getMessage());
            }
        }
        else{
            //tem que atualizar o usuario
        }
        fecharArquivoGravacaoLogin();
    }

    public ArrayList<Pessoa> leituraInicial(){
        abrirArquivoLeituraLogin();
        ArrayList<Pessoa> usuarios = new ArrayList<>();
        try {
            while (leitor_login.hasNextLine()) {
                String linha = leitor_login.nextLine();
                String[] dados = linha.split(";");

                if (dados.length == 0){
                    return null;
                }

                if (dados.length >= 5) {
                    Pessoa p = new Pessoa(dados[0], dados[1], dados[2], dados[3], dados[4]);
                    usuarios.add(p);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao ler arquivo para login: " + e.getMessage());
        } finally {
            fecharArquivoLeituraLogin();
        }
        return usuarios;
    }
}
