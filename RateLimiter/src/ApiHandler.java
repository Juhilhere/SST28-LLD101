public class ApiHandler {

    private RequestService service;

    public ApiHandler(RequestService service) {
        this.service = service;
    }

    public boolean execute(String userId, boolean needsExternalCall) {
        return service.process(userId, needsExternalCall);
    }
}
