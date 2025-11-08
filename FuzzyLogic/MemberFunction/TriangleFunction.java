package MemberFunction;

import java.util.ArrayList;
import java.util.List;

import Shape.IShape;
import Shape.Line;

public class TriangleFunction<T extends Number> implements IMemberFunction<T> {
    List <T> points;
    List<T> yValues;
    List<IShape<T>> equations;

    public TriangleFunction(List<T> points) {
        this.points = points;
        this.yValues = new ArrayList<>();
        this.equations = new ArrayList<>(); 
        generateEquations();
    }
    public List<IShape<T>> getEquations() {
        return equations;
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
            line.setSlope((y2.doubleValue() - y1.doubleValue()) / (x2.doubleValue() - x1.doubleValue()));
            line.setIntercept(y1.doubleValue() - line.getSlope() * x1.doubleValue());
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
    }

    @Override
    public List<T> getPoints() {
        return points;
    }
}