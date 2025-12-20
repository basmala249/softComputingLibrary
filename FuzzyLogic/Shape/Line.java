package FuzzyLogic.Shape;

import java.util.List;

public class Line implements IShape {
    private Double slope;
    private Double intercept;

    @Override
    public Double calculateY(Double x) {
        Double val = Double.valueOf(x.doubleValue() * slope + intercept);
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
    public IShape createNewInstance(List<Double> points) {
        Double x1 = points.get(0);
        Double y1 = points.get(1);
        Double x2 = points.get(2);
        Double y2 = points.get(3);
        Double val1 = 0.0;
        Double val2 = 0.0;
        if(x2.doubleValue() - x1.doubleValue() != 0) {
            val1 = (y2.doubleValue() - y1.doubleValue()) / (x2.doubleValue() - x1.doubleValue());
            val2 = (y1.doubleValue() - (val1 * x1.doubleValue()));
        }
        IShape line = new Line();
        line.setSlope(val1);
        line.setIntercept(val2);
        return line;
    }

}
