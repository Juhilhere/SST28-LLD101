public class Main {

    public static void main(String[] args) throws Exception {

        RateLimitConfig config = new RateLimitConfig(5, 10_000);

        LimiterStrategy strategy = LimiterFactory.create("SLIDING", config);

        Limiter limiter = new Limiter(strategy);
        RequestService service = new RequestService(limiter);
        ApiHandler api = new ApiHandler(service);

        String user = "user1";

        for (int i = 1; i <= 10; i++) {

            boolean needsExternalCall = true;

            boolean result = api.execute(user, needsExternalCall);

            System.out.println("Request " + i + " allowed: " + result);

            Thread.sleep(1000);
        }
    }
}
