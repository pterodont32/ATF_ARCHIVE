package utils;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private Map<String, Object> context;

    public ScenarioContext() {
        context = new HashMap<>();
    }

    public void setContext(String key, Object value) {
        context.put(key, value);
    }

    // clazz folosit pentru a fi sure ca obiectul retras este de tipul expectat
    public <T> T getContext(String key, Class<T> clazz) {
        return clazz.cast(context.get(key));
    }

    public void removeContext(String key) {
        context.remove(key);
    }
}
