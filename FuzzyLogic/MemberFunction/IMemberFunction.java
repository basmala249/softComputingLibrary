package MemberFunction;

import java.util.List;

import Shape.IShape;

public interface IMemberFunction{ 
    List<IShape> getEquations();
    List<Double> getPoints();
    boolean inRange(Double x);
    Double getMembershipValue(Double x); 
    void setY(List<Double> yValues);
    String getName();
    void setName(String name);
}
