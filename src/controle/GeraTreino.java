package controle;

import principal.Aerobico;
import principal.Anaerobico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Classe responsável pela geração automática de treinos personalizados.
 * 
 * <p>Esta classe funciona como uma fábrica de exercícios, gerando treinos aleatórios
 * baseados em parâmetros de dificuldade, tipo de exercício e quantidade. Possui um
 * banco de dados estático de exercícios pré-cadastrados para cada grupo muscular.</p>
 * 
 * <p><b>Funcionalidades:</b></p>
 * <ul>
 *   <li>Gerar exercícios aeróbicos aleatórios</li>
 *   <li>Gerar exercícios anaeróbicos aleatórios por grupo muscular</li>
 *   <li>Definir parâmetros (repetições, séries, carga) baseados na dificuldade</li>
 * </ul>
 * 
 * <p><b>Níveis de dificuldade disponíveis:</b></p>
 * <ul>
 *   <li>Muito Leve</li>
 *   <li>Leve</li>
 *   <li>Médio</li>
 *   <li>Intermediário</li>
 *   <li>Difícil</li>
 *   <li>Muito Difícil</li>
 *   <li>Maromba</li>
 * </ul>
 * 
 * @author POO_academia
 * @version 1.0
 * @see Aerobico
 * @see Anaerobico
 * @see Gerenciamento#gerarTreino
 */
public class GeraTreino {
    
    /**
     * Gerador de números aleatórios usado para selecionar exercícios.
     */
    private static Random gerador;

    /**
     * Mapa que associa cada tipo de grupo muscular a uma lista de exercícios disponíveis.
     * Chave: tipo do grupo muscular (PEITO, COSTAS, BICEPS, etc.)
     * Valor: lista de nomes de exercícios para aquele grupo
     */
    private static HashMap<Anaerobico.tipo, ArrayList<String>> exercicios;

    /**
     * Mapa que define os parâmetros de intensidade para cada nível de dificuldade.
     * Chave: nome do nível de dificuldade (ex: "Médio", "Difícil")
     * Valor: ArrayList com 3 elementos:
     *   - Índice 0: número de repetições
     *   - Índice 1: número de séries
     *   - Índice 2: sugestão de carga em kg
     */
    private static HashMap<String, ArrayList<Integer>> intensidade;

    /**
     * Construtor que inicializa o gerador de treinos.
     * 
     * <p>Popula os mapas estáticos com:</p>
     * <ul>
     *   <li>Exercícios pré-cadastrados para cada grupo muscular</li>
     *   <li>Parâmetros de intensidade para cada nível de dificuldade</li>
     * </ul>
     */
    public GeraTreino(){
        gerador = new Random();
        intensidade = new HashMap<>();
        exercicios = new HashMap<>();

        // Populando exercícios para PEITO
        exercicios.put(Anaerobico.tipo.PEITO, new ArrayList<>());
        exercicios.get(Anaerobico.tipo.PEITO).add("Peitoral voador");
        exercicios.get(Anaerobico.tipo.PEITO).add("Crucifixo");
        exercicios.get(Anaerobico.tipo.PEITO).add("Supino reto");

        // Populando exercícios para COSTAS
        exercicios.put(Anaerobico.tipo.COSTAS, new ArrayList<>());
        exercicios.get(Anaerobico.tipo.COSTAS).add("Pulley frente");
        exercicios.get(Anaerobico.tipo.COSTAS).add("Remada Inclinada");
        exercicios.get(Anaerobico.tipo.COSTAS).add("Pulley inclinado");

        // Populando exercícios para BICEPS
        exercicios.put(Anaerobico.tipo.BICEPS, new ArrayList<>());
        exercicios.get(Anaerobico.tipo.BICEPS).add("Rosca direta");
        exercicios.get(Anaerobico.tipo.BICEPS).add("Rosca martelo");
        exercicios.get(Anaerobico.tipo.BICEPS).add("Rosca scott");

        // Populando exercícios para TRICEPS
        exercicios.put(Anaerobico.tipo.TRICEPS, new ArrayList<>());
        exercicios.get(Anaerobico.tipo.TRICEPS).add("Extensão de triceps deitado");
        exercicios.get(Anaerobico.tipo.TRICEPS).add("Triceps coice");
        exercicios.get(Anaerobico.tipo.TRICEPS).add("Triceps frances");

        // Populando exercícios para QUADRICEPS
        exercicios.put(Anaerobico.tipo.QUADRICEPS, new ArrayList<>());
        exercicios.get(Anaerobico.tipo.QUADRICEPS).add("Cadeira extensora");
        exercicios.get(Anaerobico.tipo.QUADRICEPS).add("Afundo");
        exercicios.get(Anaerobico.tipo.QUADRICEPS).add("Agachamento smith");

        // Populando exercícios para GLUTEO
        exercicios.put(Anaerobico.tipo.GLUTEO, new ArrayList<>());
        exercicios.get(Anaerobico.tipo.GLUTEO).add("Elevacão pélvica");
        exercicios.get(Anaerobico.tipo.GLUTEO).add("Búlgaro");
        exercicios.get(Anaerobico.tipo.GLUTEO).add("Agachamento sumo");

        // Populando exercícios para PANTURRILHA
        exercicios.put(Anaerobico.tipo.PANTURRILHA, new ArrayList<>());
        exercicios.get(Anaerobico.tipo.PANTURRILHA).add("Panturrilha em pé");
        exercicios.get(Anaerobico.tipo.PANTURRILHA).add("Panturrilha no leg press");
        exercicios.get(Anaerobico.tipo.PANTURRILHA).add("Panturrilha sentado");

        // Configurando parâmetros de intensidade para cada nível de dificuldade
        // Formato: [repetições, séries, carga em kg]
        
        intensidade.put("Muito Leve", new ArrayList<>());
        intensidade.get("Muito Leve").add(12);  // repetições
        intensidade.get("Muito Leve").add(3);   // séries
        intensidade.get("Muito Leve").add(10);  // carga (kg)
        
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

    /**
     * Gera um array de exercícios aeróbicos aleatórios.
     * 
     * <p>Seleciona aleatoriamente exercícios de uma lista de opções (Corrida, Bicicleta, Natação)
     * sem repetição. O tempo de duração é definido automaticamente baseado na dificuldade.</p>
     * 
     * <p><b>Tempos por dificuldade:</b></p>
     * <ul>
     *   <li>Muito Leve: 10 minutos</li>
     *   <li>Leve: 15 minutos</li>
     *   <li>Médio: 30 minutos</li>
     *   <li>Intermediário: 40 minutos</li>
     *   <li>Difícil: 60 minutos</li>
     *   <li>Muito Difícil: 90 minutos</li>
     *   <li>Maromba: 120 minutos</li>
     * </ul>
     * 
     * @param n quantidade de exercícios aeróbicos a gerar (1, 2 ou 3)
     * @param dificuldade nível de dificuldade para definir a duração
     * @return array de exercícios aeróbicos gerados, ou null se n for inválido
     */
    public static Aerobico[] gerarAerobico(int n, String dificuldade){
        Aerobico[] aerobicos;
        
        // Lista de exercícios aeróbicos disponíveis
        ArrayList<String> nomes = new ArrayList<>();
        nomes.add("Corrida");
        nomes.add("Bicicleta");
        nomes.add("Natacao");

        // Mapeamento de dificuldade para tempo em minutos
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
                // Gera 1 exercício aeróbico aleatório
                aerobicos = new Aerobico[1];
                g = gerador.nextInt(2);
                aerobicos[0] = new Aerobico(nomes.get(g), tempo.get(dificuldade));
                return aerobicos;
            case 2:
                // Gera 2 exercícios aeróbicos diferentes
                aerobicos = new Aerobico[2];
                length = aerobicos.length;
                for (int i = 0; i < aerobicos.length; i++) {
                    g = gerador.nextInt(length);
                    aerobicos[i] = new Aerobico(nomes.get(g), tempo.get(dificuldade));
                    nomes.remove(g);  // Remove para evitar repetição
                    length--;
                }
                return aerobicos;
            case 3:
                // Gera 3 exercícios aeróbicos diferentes
                aerobicos = new Aerobico[3];
                length = aerobicos.length;
                for (int i = 0; i < aerobicos.length; i++) {
                    g = gerador.nextInt(length);
                    aerobicos[i] = new Aerobico(nomes.get(g), tempo.get(dificuldade));
                    nomes.remove(g);  // Remove para evitar repetição
                    length--;
                }
                return aerobicos;
        }
        return null;  // Retorna null se quantidade for inválida
    }

    /**
     * Gera um array de exercícios anaeróbicos aleatórios para um grupo muscular específico.
     * 
     * <p>Seleciona aleatoriamente exercícios do banco de dados interno para o grupo muscular
     * especificado, sem repetição. Os parâmetros (repetições, séries, carga) são definidos
     * automaticamente baseados na dificuldade.</p>
     * 
     * @param n quantidade de exercícios anaeróbicos a gerar (1, 2 ou 3)
     * @param dificuldade nível de dificuldade para definir rep/séries/carga
     * @param tipo grupo muscular a ser trabalhado (PEITO, COSTAS, BICEPS, etc.)
     * @return array de exercícios anaeróbicos gerados, ou null se n for inválido
     */
    public static Anaerobico[] gerarAnaerobico(int n, String dificuldade, Anaerobico.tipo tipo) {
        Anaerobico[] anaerobico;

        int g, length;
        // Cria cópia da lista de exercícios para poder remover sem afetar o original
        ArrayList<String> nomes = new ArrayList<>(exercicios.get(tipo));
        
        switch (n) {
            case 1:
                // Gera 1 exercício anaeróbico aleatório
                anaerobico = new Anaerobico[1];
                g = gerador.nextInt(2);
                anaerobico[0] = new Anaerobico(
                    nomes.get(g), 
                    tipo, 
                    intensidade.get(dificuldade).get(0),  // repetições
                    intensidade.get(dificuldade).get(1),  // séries
                    intensidade.get(dificuldade).get(2)   // carga
                );
                return anaerobico;
            case 2:
                // Gera 2 exercícios anaeróbicos diferentes
                anaerobico = new Anaerobico[2];
                length = anaerobico.length;
                for (int i = 0; i < anaerobico.length; i++) {
                    g = gerador.nextInt(length);
                    anaerobico[i] = new Anaerobico(
                        nomes.get(g), 
                        tipo, 
                        intensidade.get(dificuldade).get(0),
                        intensidade.get(dificuldade).get(1),
                        intensidade.get(dificuldade).get(2)
                    );
                    nomes.remove(g);  // Remove para evitar repetição
                    length--;
                }
                return anaerobico;
            case 3:
                // Gera 3 exercícios anaeróbicos diferentes
                anaerobico = new Anaerobico[3];
                length = anaerobico.length;
                for (int i = 0; i < anaerobico.length; i++) {
                    g = gerador.nextInt(length);
                    anaerobico[i] = new Anaerobico(
                        nomes.get(g), 
                        tipo, 
                        intensidade.get(dificuldade).get(0),
                        intensidade.get(dificuldade).get(1),
                        intensidade.get(dificuldade).get(2)
                    );
                    nomes.remove(g);  // Remove para evitar repetição
                    length--;
                }
                return anaerobico;
        }
        return null;  // Retorna null se quantidade for inválida
    }
}