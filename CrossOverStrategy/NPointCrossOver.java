package CrossOverStrategy;
import Chromosomes.Chromosome;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;


public class NPointCrossOver<T> implements ICrossOver<T> {

    private final Random random = new Random();
    private final int crossPointsNum;

    public NPointCrossOver(int crossPointsNum) {
        this.crossPointsNum = crossPointsNum;
    }

    @Override
    public List<Chromosome<T>> crossOver(Chromosome<T> firstChromosome, Chromosome<T> secondChromosome) {
       
        // Get a copy of the parents
        Chromosome<T> firstOffSpring = firstChromosome.copy();
        Chromosome<T> secondOffSpring = secondChromosome.copy();

        // Generate sorted Random Cross Points
        List<Integer> crossPoints = generateRandomCrossPoints(firstOffSpring.getSize());
        List<Chromosome<T>> offsprings = new ArrayList<>();

        crossPoints.add(0, 0);
        crossPoints.add(firstOffSpring.getSize());

     

        boolean swap = false;
        for(int i = 0;i < crossPoints.size() - 1;i++) {
           
            if(swap) {
                int l = crossPoints.get(i);
                int r = crossPoints.get(i + 1);
                // For each two consecutive points, swap the genes between them
               
                for(int j = l ;j < r;j++) {
                    T firstGene = firstOffSpring.getIndex(j);
                    firstOffSpring.setIndex(j, secondOffSpring.getIndex(j));
                    secondOffSpring.setIndex(j, firstGene);

                }
            }
            swap = !swap;
           
        }
        
        offsprings.add(firstOffSpring);
        offsprings.add(secondOffSpring);

        return offsprings;
    }

    private List<Integer> generateRandomCrossPoints(int chromosomeLength) {
        Set<Integer> pointSet = new HashSet<>();

        
        while (pointSet.size() < crossPointsNum) {
            int point = random.nextInt(chromosomeLength-1) + 1; // ensure point is between 1 and length-1
            pointSet.add(point);
        }

        List<Integer> points = new ArrayList<>(pointSet);
        Collections.sort(points);
        return points;

    }
}
