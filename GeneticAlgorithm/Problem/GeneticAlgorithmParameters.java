package GeneticAlgorithm.Problem;

// Class to hold Genetic Algorithm Parameters
public class GeneticAlgorithmParameters {
        int populationSize;
        int generations ;
        int chromosomeLength;
        double crossoverRate;
        double mutationRate;
        
        public GeneticAlgorithmParameters(int populationSize, int generations, int chromosomeLength,
                        double crossoverRate, double mutationRate) {
                this.populationSize = populationSize;
                this.generations = generations;
                this.chromosomeLength = chromosomeLength;
                this.crossoverRate = crossoverRate;
                this.mutationRate = mutationRate;
        }
        public int getPopulationSize() {
                return populationSize;
        }
        public void setPopulationSize(int populationSize) {
                this.populationSize = populationSize;
        }
        public int getGenerations() {
                return generations;
        }
        public void setGenerations(int generations) {
                this.generations = generations;
        }
        public int getChromosomeLength() {
                return chromosomeLength;
        }
        public void setChromosomeLength(int chromosomeLength) {
                this.chromosomeLength = chromosomeLength;
        }
        public double getCrossoverRate() {
                return crossoverRate;
        }
        public void setCrossoverRate(double crossoverRate) {
                this.crossoverRate = crossoverRate;
        }
        public double getMutationRate() {
                return mutationRate;
        }
        public void setMutationRate(double mutationRate) {
                this.mutationRate = mutationRate;
        }
        
}
