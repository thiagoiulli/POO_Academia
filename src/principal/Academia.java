package principal;

import controle.Gerenciamento;
import visao.JanelaLogin;

public class Academia {
    public static void main(String[] args) {
        Gerenciamento gerenciamento = new Gerenciamento();
        JanelaLogin janelaLogin = new JanelaLogin(gerenciamento);
        janelaLogin.setVisible(true);
    }
}

