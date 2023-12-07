package core.algorithm.ga;

import core.algorithm.base.SolutionEvaluator;
import core.base.OptimizationProblem;
import core.base.Solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TournamentSelection implements SelectionPolicy {

    private final int tournamentSize;
    final SolutionEvaluator evaluator;


    public TournamentSelection(int tournamentSize, SolutionEvaluator evaluator) {
        this.tournamentSize = tournamentSize;
        this.evaluator = evaluator;
    }

    // Function to perform tournament selection
    public List<Solution> select(OptimizationProblem problem,List<Solution> population, int tournamentCount) {
        List<Solution> result = new ArrayList<>();


        List<Solution> tournament = new ArrayList<>();
        Random random = new Random();

        for (int tc = 0; tc < tournamentCount; tc++) {
            // Select random individuals for the tournament
            for (int i = 0; i < tournamentSize; i++) {
                int randomIndex = random.nextInt(population.size());
                tournament.add(population.get(randomIndex));
            }

            // Find the fittest individual in the tournament
            Solution fittest = tournament.stream().max((x, y) -> Double.compare(evaluator.evaluate(x),evaluator.evaluate(y))).get();
            result.add(fittest);
            tournament.clear();
        }

        return result;
    }
}
    