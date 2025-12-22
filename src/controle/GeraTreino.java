package controle;

import principal.Aerobico;
import java.util.ArrayList;
import java.util.Random;

public class GeraTreino {
    Random gerador = new Random();

    public Aerobico geraraerobico(int n, int dificuldade){
        Aerobico[] aerobicos;
        ArrayList<String> nomes = new ArrayList<>();
        nomes.add("Corrida");
        nomes.add("Bicicleta");
        nomes.add("Natacao");
        int g, length;
        switch (n){
            case 1:
                aerobicos = new Aerobico[1];
                aerobicos[0] = new Aerobico();
                g = gerador.nextInt(2);
                aerobicos[0].setNome(nomes.get(g));
                break;
            case 2:
                aerobicos = new Aerobico[2];
                length = aerobicos.length;
                for (int i = 0; i < aerobicos.length; i++){
                    aerobicos[i] = new Aerobico();
                    g = gerador.nextInt(length);
                    aerobicos[i].setNome(nomes.get(g));
                    nomes.remove(g);
                    length--;
                }
                break;
            case 3:
                aerobicos = new Aerobico[3];
                length = aerobicos.length;
                for (int i = 0; i < aerobicos.length; i++){
                    aerobicos[i] = new Aerobico();
                    g = gerador.nextInt(length);
                    aerobicos[i].setNome(nomes.get(g));
                    nomes.remove(g);
                    length--;
                }
                break;
        }
        return null;
    }
}