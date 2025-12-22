package principal;

import controle.Gerenciamento;
import manArqTxt.ManArqTxt;
import visao.JanelaLogin;

public class Academia {
    public static void main(String[] args) {
        ManArqTxt manipulartxt = new ManArqTxt();
        Gerenciamento gerenciamento = new Gerenciamento(manipulartxt);
        JanelaLogin janelaLogin = new JanelaLogin(gerenciamento);
        janelaLogin.setVisible(true);
    }
}

