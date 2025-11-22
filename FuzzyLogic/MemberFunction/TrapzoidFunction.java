package MemberFunction;

import java.util.ArrayList;
import java.util.List;

import Shape.IShape;
import Shape.Line;

public class TrapzoidFunction implements IMemberFunction {
    List<Double> points;
    List<Double> yValues;
    List<IShape> equations; 
    String name;
    Double finalY;
    public TrapzoidFunction(String name, List<Double> points  ) {
        this.name = name;
        this.points = points; 
        this.yValues = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<IShape> getEquations() {
        return equations;
    }
    public List<Double> getPoints() {
        return points;
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
            double slope =  (Double.valueOf(y2.doubleValue() - y1.doubleValue()) / (x2.doubleValue() - x1.doubleValue()));
            line.setSlope(slope);
            line.setIntercept((Double.valueOf(y1.doubleValue() - slope * x1.doubleValue())));
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
        generateEquations();
    }

    @Override
    public boolean isDefault(){
        return points == null || points.isEmpty();
    }

    @Override
    public void setDefault(double lb,double up ,int index , int n){
        
    }

    @Override
    public void setfinalY(Double y) {
        this.finalY = y;
    }

    @Override
    public Double getMembership(){
        return finalY;
    }

}
