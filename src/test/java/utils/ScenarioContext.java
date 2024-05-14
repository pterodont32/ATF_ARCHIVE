package utils;

import java.util.HashMap;
import java.util.Map;

// TODO diferenta dintre clazz si simplu
public class ScenarioContext {
    private Map<String, Object> context;

    public ScenarioContext() {
        context = new HashMap<>();
    }

    public void setContext(String key, Object value) {
        context.put(key, value);
    }

    public Object getContext(String key) {
        return context.get(key);
    }

    public <T> T getContext(String key, Class<T> clazz) {
        return clazz.cast(context.get(key));
    }

    public void removeContext(String key) {
        context.remove(key);
    }
}
