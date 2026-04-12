public class Limiter {

    private LimiterStrategy strategy;

    public Limiter(LimiterStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean check(String key) {
        return strategy.allow(key);
    }
}
