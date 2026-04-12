public class RateLimitConfig {

    private int maxRequests;
    private long windowMillis;

    public RateLimitConfig(int maxRequests, long windowMillis) {
        this.maxRequests = maxRequests;
        this.windowMillis = windowMillis;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public long getWindowMillis() {
        return windowMillis;
    }
}
