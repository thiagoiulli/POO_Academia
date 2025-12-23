package controle;

import exceptions.LeituraEscritaException;
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
    private Formatter gravador_editor;

    public ManArqTxt() {
        arquivo_login = "src/arquivos/login.txt";
    }

    // Metodos para abrir e fechar os arquivos

    public void abrirArquivoGravacaoLogin() throws LeituraEscritaException{
        try{
            gravador_login = new Formatter(new FileWriter(arquivo_login, true));
        } catch (IOException e) {
            gravador_login = null;
            throw new LeituraEscritaException("erro abrindo arquivo!");
        }
    }

    public void fecharArquivoGravacaoLogin(){
        if (gravador_login != null){
            gravador_login.close();
        }
    }

    public void abrirArquivoLeituraLogin() throws LeituraEscritaException{
        try{
            leitor_login = new Scanner(new File(arquivo_login));
        } catch (FileNotFoundException e) {
            throw new LeituraEscritaException("erro abrindo arquivo!");
        }
    }

    public void fecharArquivoLeituraLogin(){
        if (leitor_login != null){
            leitor_login.close();
        }
    }

    public void abrirArquivoEditor() throws LeituraEscritaException{
        try{
            gravador_editor = new Formatter(new FileWriter(arquivo_login, false));
        } catch (IOException e) {
            gravador_editor = null;
            throw new LeituraEscritaException("erro abrindo arquivo!");
        }
    }

    public void fecharArquivoEditor(){
        if (gravador_editor != null){
            gravador_editor.close();
        }
    }

    // Metodos para Login

    public void alterarCadastro(String usuario, String nome, String email, String telefone, String senha) throws LeituraEscritaException{
        try{
            abrirArquivoLeituraLogin();
        } catch (LeituraEscritaException e) {
            throw e;
        }
        String hashAntigo = "";
        ArrayList<String> dados = new ArrayList<>();
        try{
            while(leitor_login.hasNextLine()){
                String linha = leitor_login.nextLine();
                String[] itens = linha.split(";");
                if (!itens[0].equals(usuario)){
                    dados.add(linha);
                }
                else{
                    hashAntigo = itens[4];
                }
            }
        } catch (Exception e) {
            throw new LeituraEscritaException("erro abrindo arquivo!");
        }
        finally {
            fecharArquivoLeituraLogin();
        }
        try{
            abrirArquivoEditor();
            for (int i = 0; i < dados.size(); i++){
                gravador_editor.format("%s\n", dados.get(i).trim());
            }
            if(senha == null){
                gravador_editor.format("%s;%s;%s;%s;%s\n", usuario, nome, email, telefone, hashAntigo.trim());
            }
            else{
                gravador_editor.format("%s;%s;%s;%s;%s\n", usuario, nome, email, telefone, senha);
            }
        } catch (Exception e) {
            throw new LeituraEscritaException("erro abrindo arquivo!");
        }
        finally {
            fecharArquivoEditor();
        }
    }

    public void gravarLogin(String usuario, String nome, String email, String telefone, String senha) throws LeituraEscritaException {
        try{
            abrirArquivoGravacaoLogin();
            gravador_login.format("%s;%s;%s;%s;%s\n", usuario, nome, email, telefone, senha);
        } catch (Exception e) {
            throw new LeituraEscritaException("erro abrindo arquivo!");
        }
        fecharArquivoGravacaoLogin();
    }

    public ArrayList<Pessoa> leituraInicial() throws LeituraEscritaException{
        ArrayList<Pessoa> usuarios = new ArrayList<>();
        try {
            abrirArquivoLeituraLogin();
            while (leitor_login.hasNextLine()) {
                String linha = leitor_login.nextLine();
                String[] dados = linha.split(";");

                if (dados.length == 0){
                    break;
                }

                if (dados.length >= 5) {
                    Pessoa p = new Pessoa(dados[0], dados[1], dados[2], dados[3], dados[4]);
                    usuarios.add(p);
                }
            }
        } catch (Exception e) {
            throw new LeituraEscritaException("erro abrindo arquivo!");
        } finally {
            fecharArquivoLeituraLogin();
        }
        return usuarios;
    }
    
    public void gravarFicha(String usuario, Ficha ficha){

    }
}
