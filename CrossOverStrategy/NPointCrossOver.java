package CrossOverStrategy;
import Chromosomes.Chromosome;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class NPointCrossOver<T> implements ICrossOver<T> {

    private final Random random = new Random();
    private final int crossPointsNum;

    public NPointCrossOver(int crossPointsNum) {
        this.crossPointsNum = crossPointsNum;
    }

    @Override
    public List<Chromosome<T>> crossOver(Chromosome<T> firstChromosome, Chromosome<T> secondChromosome) {
       
        // Get a copy of the parents
        Chromosome<T> firstOffSpring =firstChromosome.copy();
        Chromosome<T> secondOffSpring = secondChromosome.copy();

        // Generate sorted Random Cross Points
        List<Integer> crossPoints = generateRandomCrossPoints(firstOffSpring.getSize());
        List<Chromosome<T>> offsprings = new ArrayList<>();

        crossPoints.add(firstOffSpring.getSize());

        for(int i = 0;i < crossPointsNum - 1;i += 2) {
            int l = crossPoints.get(i);
            int r = crossPoints.get(i + 1);
            // For each two consecutive points, swap the genes between them
            for(int j = l ;j < r;j++) {
                T firstGene = firstOffSpring.getIndex(j);
                firstOffSpring.setIndex(j, secondOffSpring.getIndex(j));
                secondOffSpring.setIndex(j, firstGene);

            }

        }
        
        offsprings.add(firstOffSpring);
        offsprings.add(secondOffSpring);

        return offsprings;
    }

    private List<Integer> generateRandomCrossPoints(int chromosomeLength) {
        List<Integer> points = new ArrayList<>();

        // Generate unique random cross points in range [0 , chromosomeLength - 1]
        // because here we are dealing with arrays 0 based index
        for(int i = 0;i < crossPointsNum;i++) {
            int point = random.nextInt(chromosomeLength - 1); 
            points.add(point);
        }

        Collections.sort(points);
        return points;

    }
}
