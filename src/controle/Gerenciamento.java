package controle;

import exceptions.UsuarioExistente;
import exceptions.UsuarioOuSenhaIncorretos;
import principal.Pessoa;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;

public class Gerenciamento {
    ArrayList<Pessoa> usuarios;
    int usuario_logado;

    public Gerenciamento(){
        usuarios = new ArrayList<>();
        this.usuario_logado = -1;
    }

    public void CadastrarPessoa(String usuario, String nome, String email, String telefone, String senha) throws UsuarioExistente, NoSuchAlgorithmException {
        if (pesquisarUsuario(usuario) != -1){
            throw new UsuarioExistente("Nome de usuário em uso");
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
    }

    public void alterarPessoa(String nome, String email, String telefone, String senha) throws NoSuchAlgorithmException{
        usuarios.get(usuario_logado).setEmail(email);
        usuarios.get(usuario_logado).setNome(nome);
        usuarios.get(usuario_logado).setTelefone(telefone);
        System.out.println(usuarios.get(usuario_logado).getSenha());
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
        }
        System.out.println(usuarios.get(usuario_logado).getSenha());
    }

    private int pesquisarUsuario(String usuario){
        for (int i = 0; i < usuarios.size(); i++){
            if (usuarios.get(i).getUsuario().equals(usuario)){
                return i;
            }
        }
        return -1;
    }

    public boolean parseLogin(String usuario, String senha) throws UsuarioOuSenhaIncorretos, NoSuchAlgorithmException {
        int i;
        if ((i = pesquisarUsuario(usuario)) == -1){
            throw new UsuarioOuSenhaIncorretos("Usuário ou senha incorretos!");
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
            throw new UsuarioOuSenhaIncorretos("Usuário ou senha incorretos!");
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
}
