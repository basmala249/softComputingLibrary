package Rule;

import Rule.*;
import com.google.gson.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class RuleStorage {
    private final String filePath ;
    private final Gson gson;
    public RuleStorage(String filePath){
        this.filePath=filePath;
        this.gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(IRule.class,new RuleAdapter()).create();
    }
    public List<IRule> loadRules(){
        try (FileReader reader = new FileReader(filePath) ){
            return gson.fromJson(reader,new TypeToken<List<IRule>>(){}.getType());
        }catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void saveRules (List<IRule> rules) {
        try (FileWriter writer = new FileWriter(filePath)){
            gson.toJson(rules,writer);
        }catch (Exception e){
            System.out.println("error saving rules: " + e.getMessage());
        }
    }

}
