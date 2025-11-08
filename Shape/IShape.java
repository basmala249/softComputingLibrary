package Shape;

import java.util.List;

public interface IShape <T extends Number> {
    T calculateY(T x);
    void setSlope(Double slope);
    void setIntercept(Double intercept);
    Double getSlope();
    Double getIntercept();
    IShape<T> createNewInstance(List<T> points);
}
