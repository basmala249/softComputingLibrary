package FuzzyLogic.Rule;
import com.google.gson.*;

import java.lang.reflect.Type;

public class RuleAdapter implements JsonSerializer<IRule>, JsonDeserializer<IRule> {

    @Override
    public JsonElement serialize(IRule rule, Type type, JsonSerializationContext context) {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", rule.getClass().getSimpleName());
        obj.addProperty("condition", rule.getCondition());
        obj.addProperty("consequence", rule.getConsequence());
        obj.addProperty("enabled", rule.isEnabled());
        return obj;
    }

    @Override
    public IRule deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        JsonObject obj = json.getAsJsonObject();
        String type = obj.get("type").getAsString();
        String condition = obj.get("condition").getAsString();
        String consequence = obj.get("consequence").getAsString();

        IRule rule;

        if (type.equals("MamdaniRule"))
            rule = new MamdaniRule(condition, consequence);
        else
            rule = new SugenoRule(condition, consequence);

        if (!obj.get("enabled").getAsBoolean())
            rule.setDisabled();

        return rule;
    }
}
