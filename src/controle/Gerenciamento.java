package controle;

import exceptions.UsuarioExistente;
import principal.Pessoa;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;

public class Gerenciamento {
    ArrayList<Pessoa> usuarios;

    public Gerenciamento(){
        usuarios = new ArrayList<>();
    }

    public void CadastrarPessoa(String usuario, String nome, String email, String telefone, String senha) throws UsuarioExistente, NoSuchAlgorithmException {
        String hashSenha;
        if (pesquisarUsuario(usuario)){
            throw new UsuarioExistente("Nome de usuário em uso");
        }
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
            System.err.println("Erro ao tratar senha!");
            throw e;
        }
        Pessoa p = new Pessoa(usuario, nome, email, telefone, hashSenha);
        usuarios.add(p);
    }

    private boolean pesquisarUsuario(String usuario){
        for (int i = 0; i < usuarios.size(); i++){
            if (usuarios.get(i).getUsuario().equals(usuario)){
                return true;
            }
        }
        return false;
    }
}
