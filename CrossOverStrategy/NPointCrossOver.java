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
    public List<Chromosome<T>> crossOver(List<Chromosome<T>> chromosomes, boolean isMinimization) {
        if(chromosomes.size() <= 1)
            return null;

        Chromosome<T> firstOffSpring = chromosomes.getFirst();
        Chromosome<T> secondOffSpring = chromosomes.get(1);

        List<Integer> crossPoints = generateRandomCrossPoints(firstOffSpring.getSize());
        crossPoints.add(firstOffSpring.getSize());
        for(int i = 0;i < crossPointsNum - 1;i += 2) {
            int l = crossPoints.get(i);
            int r = crossPoints.get(i + 1);

            for(int j = l ;j < r;j++) {
                T firstGene = firstOffSpring.getIndex(j);
                firstOffSpring.setIndex(j, secondOffSpring.getIndex(j));
                secondOffSpring.setIndex(j, firstGene);

            }

        }
        List<Chromosome<T>> offsprings = new ArrayList<>();
        offsprings.add(firstOffSpring);
        offsprings.add(secondOffSpring);

        return offsprings;
    }

    private List<Integer> generateRandomCrossPoints(int chromosomeLength) {
        List<Integer> points = new ArrayList<>();
        for(int i = 0;i < crossPointsNum;i++) {
            int point = random.nextInt(0,chromosomeLength - 1);
            points.add(point);
        }

        Collections.sort(points);
      
        return points;

    }
}
