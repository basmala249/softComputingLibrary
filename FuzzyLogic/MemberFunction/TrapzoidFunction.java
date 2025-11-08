package MemberFunction;

import java.util.ArrayList;
import java.util.List;

import Shape.IShape;
import Shape.Line;

public class TrapzoidFunction<T extends Number> implements IMemberFunction<T> {
    List <T> points;
    List<T> yValues;
    List<IShape<T>> equations; 
    public TrapzoidFunction(List<T> points  ) {
        this.points = points; 
        this.yValues = new ArrayList<>();
    }

    public List<IShape<T>> getEquations() {
        return equations;
    }
    public List<T> getPoints() {
        return points;
    }
    private void generateEquations() {
        // Generate equations based on the trapezoidal shape
        equations = new ArrayList<>();
        for(int i = 0; i < points.size() - 1; i++) {
            T x1 = points.get(i);
            T x2 = points.get(i + 1);
            T y1 = yValues.get(i);
            T y2 = yValues.get(i + 1);
            IShape<T> line = new Line<>();
            double slope =  (Double.valueOf(y2.doubleValue() - y1.doubleValue()) / (x2.doubleValue() - x1.doubleValue()));
            line.setSlope(slope);
            line.setIntercept((Double.valueOf(y1.doubleValue() - slope * x1.doubleValue())));
            equations.add(line);
        }
    }
    @Override
    public boolean inRange(T x) {
        return x.doubleValue() >= points.get(0).doubleValue() && x.doubleValue() <= points.get(points.size() - 1).doubleValue();
    }
    @Override
    public T getMembershipValue(T x) {
        for(int i = 0; i < points.size() - 1; i++) {
            T x1 = points.get(i);
            T x2 = points.get(i + 1);
            if(x.doubleValue() >= x1.doubleValue() && x.doubleValue() <= x2.doubleValue()) {
                IShape<T> line = equations.get(i);
                double y = line.getSlope() * x.doubleValue() + line.getIntercept();
                return (T) Double.valueOf(y);
            }
        }
        return (T) Double.valueOf(0);
    }

    @Override
    public void setY(List<T> yValues) {
        this.yValues = yValues;
        generateEquations();
    }
}
