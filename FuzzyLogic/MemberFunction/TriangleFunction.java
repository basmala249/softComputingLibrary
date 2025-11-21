package MemberFunction;

import java.util.ArrayList;
import java.util.List;

import Shape.IShape;
import Shape.Line;

public class TriangleFunction implements IMemberFunction {
    List<Double> points;
    List<Double> yValues;
    List<IShape> equations;
    String name;
    public TriangleFunction(String name , List<Double> points) {
        this.points = points;
        this.name = name;
        this.yValues = new ArrayList<>();
        this.equations = new ArrayList<>(); 
        generateEquations();
    }
    public List<IShape> getEquations() {
        return equations;
    }
    private void generateEquations() {
         // Generate equations based on the trapezoidal shape
        equations = new ArrayList<>();
        for(int i = 0; i < points.size() - 1; i++) {
            Double x1 = points.get(i);
            Double x2 = points.get(i + 1);
            Double y1 = yValues.get(i);
            Double y2 = yValues.get(i + 1);
            IShape line = new Line();
            line.setSlope((y2.doubleValue() - y1.doubleValue()) / (x2.doubleValue() - x1.doubleValue()));
            line.setIntercept(y1.doubleValue() - line.getSlope() * x1.doubleValue());
            equations.add(line);
        }
    }
    @Override
    public boolean inRange(Double x) {
        return x.doubleValue() >= points.get(0).doubleValue() && x.doubleValue() <= points.get(points.size() - 1).doubleValue();
    }
    @Override
    public Double getMembershipValue(Double x) {
        for(int i = 0; i < points.size() - 1; i++) {
            Double x1 = points.get(i);
            Double x2 = points.get(i + 1);
            if(x.doubleValue() >= x1.doubleValue() && x.doubleValue() <= x2.doubleValue()) {
                IShape line = equations.get(i);
                double y = line.getSlope() * x.doubleValue() + line.getIntercept();
                return Double.valueOf(y);
            }
        }
        return Double.valueOf(0);
    }
    @Override
    public void setY(List<Double> yValues) {
        this.yValues = yValues;
    }

    @Override
    public List<Double> getPoints() {
        return points;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean isDefault(){
        return points == null || points.isEmpty();
    }

    @Override
    void setDefault(double lb,double up ,int index , int n){
        double w =(b-a) / (n -1);
        double left  = a + (index - 1) * w;
        double peak  = a + index * w;
        double right = a + (index + 1) * w;

        left = Math.max(left ,a);
        right = Math.min(right,b);

        points = new ArrayLsit<>();
        points.add(left);
        points.add(right);
        points.add(peak);
        yValues=List.of(0.0,1.0,0.0);
        generateEquations();
    }

}
