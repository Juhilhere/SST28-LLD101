import java.util.HashMap;
import java.util.Map;

public class FixedWindowLimiter implements LimiterStrategy {

    private static class Window {
        long start;
        int count;

        Window(long start) {
            this.start = start;
            this.count = 0;
        }
    }

    private RateLimitConfig config;
    private Map<String, Window> store = new HashMap<>();

    public FixedWindowLimiter(RateLimitConfig config) {
        this.config = config;
    }

    @Override
    public boolean allow(String key) {
        long now = System.currentTimeMillis();

        synchronized (store) {
            Window window = store.get(key);

            if (window == null) {
                window = new Window(now);
                store.put(key, window);
            }

            if (now - window.start >= config.getWindowMillis()) {
                window.start = now;
                window.count = 0;
            }

            if (window.count < config.getMaxRequests()) {
                window.count++;
                return true;
            }

            return false;
        }
    }
}
