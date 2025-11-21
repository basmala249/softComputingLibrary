package FuzzySet;

import java.util.ArrayList;
import java.util.List;

public class DefaultMF {
    public static DefaultMF (FuzzySet set,double lp,double up){
        List<IMemberFunction> mfs = set.getMemberFunctions();
        int n = mfs.size();

        for(int i = 0 ;i < n ;i++){
            IMemberFunction mf = mfs.get(i);

            if(mf.isDefault()){
                mf.setDefault(lb , ub ,i, n);
            }
        }
    }
}
