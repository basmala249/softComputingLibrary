package MemberFunction;

import java.util.List;

import Shape.IShape;

public interface IMemberFunction{ 
    List<IShape> getEquations();
    List<Double> getPoints();
    boolean inRange(Double x);
    Double getMembershipValue(Double x); 
    void setY(List<Double> yValues);
    void setfinalY(Double y);
    Double getMembership();
    String getName();
    void setName(String name);
    void setDefault(double lb,double up ,int index , int n);
    boolean isDefault();
}
