package controle;

import exceptions.LeituraEscritaException;
import exceptions.UsuarioExistenteException;
import exceptions.UsuarioOuSenhaIncorretosException;
import manArqTxt.ManArqTxt;
import principal.Aerobico;
import principal.Anaerobico;
import principal.Pessoa;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.util.HashMap;

public class Gerenciamento {
    ArrayList<Pessoa> usuarios;
    int usuario_logado;
    ManArqTxt manipulartxt;
    GeraTreino geratreino;

    public Gerenciamento(ManArqTxt manipulartxt){
        usuarios = new ArrayList<>();
        this.usuario_logado = -1;
        this.manipulartxt = manipulartxt;
        ArrayList<Pessoa> tmp = null;
        try {
            tmp = manipulartxt.leituraInicial();
        } catch (LeituraEscritaException e) {
            System.err.println("Não foi possível carregar arquivo com salvamento!");
        }
        if (tmp != null){
            usuarios.addAll(tmp);
        }

        geratreino = new GeraTreino();
    }

    public void CadastrarPessoa(String usuario, String nome, String email, String telefone, String senha) throws UsuarioExistenteException, NoSuchAlgorithmException, LeituraEscritaException {
        if (pesquisarUsuario(usuario) != -1){
            throw new UsuarioExistenteException("Nome de usuário em uso");
        }
        String hashSenha;
        try{
            hashSenha = hashPass(senha);
        }
        catch (NoSuchAlgorithmException e){
            System.err.println("Erro ao tratar senha!");
            throw e;
        }
        Pessoa p = new Pessoa(usuario, nome, email, telefone, hashSenha);
        usuarios.add(p);
        try {
            manipulartxt.gravarLogin(usuario, nome, email, telefone, hashSenha);
        } catch (LeituraEscritaException e) {
            throw e;
        }
    }

    public void alterarPessoa(String nome, String email, String telefone, String senha) throws NoSuchAlgorithmException, LeituraEscritaException{
        usuarios.get(usuario_logado).setEmail(email);
        usuarios.get(usuario_logado).setNome(nome);
        usuarios.get(usuario_logado).setTelefone(telefone);
//        System.out.println(usuarios.get(usuario_logado).getSenha());
        if (!senha.isBlank()){
            String hashSenha;
            try{
                hashSenha = hashPass(senha);
            }
            catch (NoSuchAlgorithmException e){
                System.err.println("Erro ao tratar senha!");
                throw e;
            }
            usuarios.get(usuario_logado).setSenha(hashSenha);
            try {
                manipulartxt.alterarCadastro(usuarios.get(usuario_logado).getUsuario(), nome, email, telefone, hashSenha);
            } catch (LeituraEscritaException e) {
                throw e;
            }
        }
        else{
            try{
                manipulartxt.alterarCadastro(usuarios.get(usuario_logado).getUsuario(), nome, email, telefone, null);
            } catch (LeituraEscritaException e) {
                throw e;
            }
        }
//        System.out.println(usuarios.get(usuario_logado).getSenha());
    }

    private int pesquisarUsuario(String usuario){
        for (int i = 0; i < usuarios.size(); i++){
            if (usuarios.get(i).getUsuario().equals(usuario)){
                return i;
            }
        }
        return -1;
    }

    public boolean parseLogin(String usuario, String senha) throws UsuarioOuSenhaIncorretosException, NoSuchAlgorithmException {
        int i;
        if ((i = pesquisarUsuario(usuario)) == -1){
            throw new UsuarioOuSenhaIncorretosException("Usuário ou senha incorretos!");
        }
        String hashSenha;
        try{
            hashSenha = hashPass(senha);
        }
        catch (NoSuchAlgorithmException e){
            throw e;
        }
        if (usuarios.get(i).getSenha().equals(hashSenha)){
            this.usuario_logado = i;
            return true;
        }
        else{
            throw new UsuarioOuSenhaIncorretosException("Usuário ou senha incorretos!");
        }
    }

    private String hashPass(String senha) throws NoSuchAlgorithmException{
        String hashSenha;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] encodedhash = digest.digest(senha.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (byte b : encodedhash){
                String hex = Integer.toHexString(0xff & b); //garantir que todos os ints sejam positivos ao fazer um AND com 255 (8 bits) e valor do byte
                if (hex.length() == 1){
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            hashSenha = hexString.toString();
        }
        catch (NoSuchAlgorithmException e){
            throw e;
        }
        return hashSenha;
    }

    public Pessoa getUsuarioLogado(){
        return usuarios.get(this.usuario_logado);
    }

    public void gerarTreino(HashMap<Anaerobico.tipo, ArrayList<String>> formulario, ArrayList<String> formulario_aerobico){
        Ficha ficha = new Ficha();
        for (Anaerobico.tipo e : formulario.keySet()){
            Anaerobico[] anaerobicos = GeraTreino.gerarAnaerobico(Integer.parseInt(formulario.get(e).get(0)), formulario.get(e).get(1), e);
            for (int i = 0; i < anaerobicos.length; i++){
                ficha.adicionarExercicio(anaerobicos[i]);
            }
        }
        if (formulario_aerobico != null){
            Aerobico[] aerobicos = GeraTreino.gerarAerobico(Integer.parseInt(formulario_aerobico.get(0)), formulario_aerobico.get(1));
            for (int i = 0; i < aerobicos.length; i++){
                ficha.adicionarExercicio(aerobicos[i]);
            }
        }

        usuarios.get(usuario_logado).setFicha(ficha);
        usuarios.get(usuario_logado).getFicha();
    }
}
