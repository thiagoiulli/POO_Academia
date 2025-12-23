package controle;

import principal.Aerobico;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GeraTreino {
    Random gerador = new Random();

    public Aerobico[] gerarAerobico(int n, String dificuldade){
        Aerobico[] aerobicos;
        ArrayList<String> nomes = new ArrayList<>();
        nomes.add("Corrida");
        nomes.add("Bicicleta");
        nomes.add("Natacao");

        HashMap<String, Integer> tempo = new HashMap<>();
        tempo.put("Muito Leve", 10);
        tempo.put("Leve", 15);
        tempo.put("Médio", 30);
        tempo.put("Intermediário", 40);
        tempo.put("Difícil", 60);
        tempo.put("Muito Difícil", 90);
        tempo.put("Maromba", 120);

        int g, length;
        switch (n) {
            case 1:
                aerobicos = new Aerobico[1];
                g = gerador.nextInt(2);
                aerobicos[0] = new Aerobico(nomes.get(g), tempo.get(dificuldade));
                return aerobicos;
            case 2:
                aerobicos = new Aerobico[2];
                length = aerobicos.length;
                for (int i = 0; i < aerobicos.length; i++) {
                    g = gerador.nextInt(length);
                    aerobicos[i] = new Aerobico(nomes.get(g), tempo.get(dificuldade));
                    nomes.remove(g);
                    length--;
                }
                return aerobicos;
            case 3:
                aerobicos = new Aerobico[3];
                length = aerobicos.length;
                for (int i = 0; i < aerobicos.length; i++) {
                    g = gerador.nextInt(length);
                    aerobicos[i] = new Aerobico(nomes.get(g), tempo.get(dificuldade));
                    nomes.remove(g);
                    length--;
                }
                return aerobicos;
        }
        return null;
    }

    public void gerarPeito(int n, int dificuldade) {

    }
}