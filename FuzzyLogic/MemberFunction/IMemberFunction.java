package MemberFunction;

import java.util.List;

import Shape.IShape;

public interface IMemberFunction <T extends Number> { 
    List<IShape<T>> getEquations();
    List<T> getPoints();
    boolean inRange(T x);
    T getMembershipValue(T x); 
    void setY(List<T> yValues);
}
