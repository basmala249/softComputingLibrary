package FuzzyLogic.FuzzySet;


import java.util.List;

import FuzzyLogic.MemberFunction.IMemberFunction;

public class DefaultMF {
    public  DefaultMF (FuzzySet set,double lp,double up){
        List<IMemberFunction> mfs = set.getMemberFunctions();
        int n = mfs.size();

        for(int i = 0 ;i < n ;i++){
            IMemberFunction mf = mfs.get(i);

            if(mf.isDefault()){
                mf.setDefault(lp , up ,i, n);
            }
        }
    }
}
