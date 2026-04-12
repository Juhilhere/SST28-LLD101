public class LimiterFactory {

    public static LimiterStrategy create(String type, RateLimitConfig config) {

        if ("FIXED".equalsIgnoreCase(type)) {
            return new FixedWindowLimiter(config);
        } else if ("SLIDING".equalsIgnoreCase(type)) {
            return new SlidingWindowLimiter(config);
        }

        throw new IllegalArgumentException("Unknown type");
    }
}
