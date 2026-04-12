import java.util.*;

public class SlidingWindowLimiter implements LimiterStrategy {

    private RateLimitConfig config;
    private Map<String, Deque<Long>> store = new HashMap<>();

    public SlidingWindowLimiter(RateLimitConfig config) {
        this.config = config;
    }

    @Override
    public boolean allow(String key) {
        long now = System.currentTimeMillis();

        synchronized (store) {
            Deque<Long> q = store.get(key);

            if (q == null) {
                q = new LinkedList<>();
                store.put(key, q);
            }

            while (!q.isEmpty() && now - q.peekFirst() >= config.getWindowMillis()) {
                q.pollFirst();
            }

            if (q.size() < config.getMaxRequests()) {
                q.addLast(now);
                return true;
            }

            return false;
        }
    }
}
