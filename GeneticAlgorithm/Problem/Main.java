package GeneticAlgorithm.Problem;


public class Main {
    public static Integer INF = 1000000;
    public static void main(String[] args) {
        

        System.out.println("Fuzzy Logic Inference Engine");
    }
    public <T extends Number> double AND(T A, T B) {
           return (A.doubleValue() < B.doubleValue()) ? A.doubleValue() : B.doubleValue();
}
    public <T extends Number> double OR(T A, T B) {
      return (A.doubleValue() > B.doubleValue()) ? A.doubleValue() : B.doubleValue();
    }
}
