package Shape;

import java.util.List;

public interface IShape  {
    Double calculateY(Double x);
    void setSlope(Double slope);
    void setIntercept(Double intercept);
    Double getSlope();
    Double getIntercept();
    IShape createNewInstance(List<Double> points);
}
