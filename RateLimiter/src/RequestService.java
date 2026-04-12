public class RequestService {

    private Limiter limiter;

    public RequestService(Limiter limiter) {
        this.limiter = limiter;
    }

    public boolean process(String userId, boolean needsExternalCall) {

        if (!needsExternalCall) {
            System.out.println("no external call needed");
            return true;
        }

        boolean allowed = limiter.check(userId);

        if (allowed) {
            callExternal(userId);
            return true;
        } else {
            System.out.println("limit hit for user " + userId);
            return false;
        }
    }

    private void callExternal(String userId) {
        System.out.println("external call done for " + userId);
    }
}
