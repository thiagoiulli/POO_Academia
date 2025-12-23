package controle;

import principal.Aerobico;
import principal.Anaerobico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GeraTreino {
    private static Random gerador;

    private static HashMap<Anaerobico.tipo, ArrayList<String>> exercicios;

    private static HashMap<String, ArrayList<Integer>> intensidade;

    public GeraTreino(){
        gerador = new Random();
        intensidade = new HashMap<>();
        exercicios = new HashMap<>();

        exercicios.put(Anaerobico.tipo.PEITO, new ArrayList<>());
        exercicios.get(Anaerobico.tipo.PEITO).add("Peitoral voador");
        exercicios.get(Anaerobico.tipo.PEITO).add("Crucifixo");
        exercicios.get(Anaerobico.tipo.PEITO).add("Supino reto");

        exercicios.put(Anaerobico.tipo.COSTAS, new ArrayList<>());
        exercicios.get(Anaerobico.tipo.COSTAS).add("Pulley frente");
        exercicios.get(Anaerobico.tipo.COSTAS).add("Remada Inclinada");
        exercicios.get(Anaerobico.tipo.COSTAS).add("Pulley inclinado");

        exercicios.put(Anaerobico.tipo.BICEPS, new ArrayList<>());
        exercicios.get(Anaerobico.tipo.BICEPS).add("Rosca direta");
        exercicios.get(Anaerobico.tipo.BICEPS).add("Rosca martelo");
        exercicios.get(Anaerobico.tipo.BICEPS).add("Rosca scott");

        exercicios.put(Anaerobico.tipo.TRICEPS, new ArrayList<>());
        exercicios.get(Anaerobico.tipo.TRICEPS).add("Extensão de triceps deitado");
        exercicios.get(Anaerobico.tipo.TRICEPS).add("Triceps coice");
        exercicios.get(Anaerobico.tipo.TRICEPS).add("Triceps frances");

        exercicios.put(Anaerobico.tipo.QUADRICEPS, new ArrayList<>());
        exercicios.get(Anaerobico.tipo.QUADRICEPS).add("Cadeira extensora");
        exercicios.get(Anaerobico.tipo.QUADRICEPS).add("Afundo");
        exercicios.get(Anaerobico.tipo.QUADRICEPS).add("Agachamento smith");

        exercicios.put(Anaerobico.tipo.GLUTEO, new ArrayList<>());
        exercicios.get(Anaerobico.tipo.GLUTEO).add("Elevacão pélvica");
        exercicios.get(Anaerobico.tipo.GLUTEO).add("Búlgaro");
        exercicios.get(Anaerobico.tipo.GLUTEO).add("Agachamento sumo");

        exercicios.put(Anaerobico.tipo.PANTURRILHA, new ArrayList<>());
        exercicios.get(Anaerobico.tipo.PANTURRILHA).add("Panturrilha em pé");
        exercicios.get(Anaerobico.tipo.PANTURRILHA).add("Panturrilha no leg press");
        exercicios.get(Anaerobico.tipo.PANTURRILHA).add("Panturrilha sentado");

        intensidade.put("Muito Leve", new ArrayList<>());
        intensidade.get("Muito Leve").add(12);
        intensidade.get("Muito Leve").add(3);
        intensidade.get("Muito Leve").add(10);
        intensidade.put("Leve", new ArrayList<>());
        intensidade.get("Leve").add(8);
        intensidade.get("Leve").add(3);
        intensidade.get("Leve").add(20);
        intensidade.put("Médio", new ArrayList<>());
        intensidade.get("Médio").add(12);
        intensidade.get("Médio").add(3);
        intensidade.get("Médio").add(30);
        intensidade.put("Intermediário", new ArrayList<>());
        intensidade.get("Intermediário").add(8);
        intensidade.get("Intermediário").add(3);
        intensidade.get("Intermediário").add(40);
        intensidade.put("Difícil", new ArrayList<>());
        intensidade.get("Difícil").add(12);
        intensidade.get("Difícil").add(3);
        intensidade.get("Difícil").add(50);
        intensidade.put("Muito Difícil", new ArrayList<>());
        intensidade.get("Muito Difícil").add(12);
        intensidade.get("Muito Difícil").add(3);
        intensidade.get("Muito Difícil").add(60);
        intensidade.put("Maromba", new ArrayList<>());
        intensidade.get("Maromba").add(12);
        intensidade.get("Maromba").add(4);
        intensidade.get("Maromba").add(80);
    }

    public static Aerobico[] gerarAerobico(int n, String dificuldade){
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

    public static Anaerobico[] gerarAnaerobico(int n, String dificuldade, Anaerobico.tipo tipo) {
        Anaerobico[] anaerobico;

        int g, length;
        ArrayList<String> nomes = new ArrayList<>(exercicios.get(tipo));
        switch (n) {
            case 1:
                anaerobico = new Anaerobico[1];
                g = gerador.nextInt(2);
                anaerobico[0] = new Anaerobico(nomes.get(g), tipo, intensidade.get(dificuldade).get(0), intensidade.get(dificuldade).get(1), intensidade.get(dificuldade).get(2));
                return anaerobico;
            case 2:
                anaerobico = new Anaerobico[2];
                length = anaerobico.length;
                for (int i = 0; i < anaerobico.length; i++) {
                    g = gerador.nextInt(length);
                    anaerobico[i] = new Anaerobico(nomes.get(g), tipo, intensidade.get(dificuldade).get(0), intensidade.get(dificuldade).get(1), intensidade.get(dificuldade).get(2));
                    nomes.remove(g);
                    length--;
                }
                return anaerobico;
            case 3:
                anaerobico = new Anaerobico[3];
                length = anaerobico.length;
                for (int i = 0; i < anaerobico.length; i++) {
                    g = gerador.nextInt(length);
                    anaerobico[i] = new Anaerobico(nomes.get(g), tipo, intensidade.get(dificuldade).get(0), intensidade.get(dificuldade).get(1), intensidade.get(dificuldade).get(2));
                    nomes.remove(g);
                    length--;
                }
                return anaerobico;
        }
        return null;
    }
}