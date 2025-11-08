package MemberFunction;

import java.util.List;

public interface IMemberFunction <T extends Number> { 
    boolean inRange(T x);
    T getMembershipValue(T x); 
    void setY(List<T> yValues);
}
