package InferenceEngine;

import java.util.Map;


public class MamdaniEngine extends IEngine {
   protected Map<String , Double> newMap = new java.util.HashMap<>();

   @Override
   public void setConsequenceValue(String conseq ,  Double value) {
         newMap.put(conseq, value);
   }
    
    
}
