package Shape;

import java.util.List;

public class Line<T extends Number> implements IShape<T> {
    private Double slope;
    private Double intercept;

    @Override
    public T calculateY(T x) {
        T val = (T) (Double.valueOf(x.doubleValue() * slope + intercept));
        return val;
    }

    public void setSlope(Double slope) {
        this.slope = slope;
    }
    public void setIntercept(Double intercept) {
        this.intercept = intercept;
    }
    public Double getSlope() {
        return slope;
    }
    public Double getIntercept() {
        return intercept;
    }
    @Override
    public IShape<T> createNewInstance(List<T> points) {
        T x1 = points.get(0);
        T y1 = points.get(1);
        T x2 = points.get(2);
        T y2 = points.get(3);
        Double val1 = 0.0;
        Double val2 = 0.0;
        if(x2.doubleValue() - x1.doubleValue() != 0) {
            val1 = (y2.doubleValue() - y1.doubleValue()) / (x2.doubleValue() - x1.doubleValue());
            val2 = (y1.doubleValue() - (val1 * x1.doubleValue()));
        }
        IShape<T> line = new Line<>();
        line.setSlope(val1);
        line.setIntercept(val2);
        return line;
    }

}
