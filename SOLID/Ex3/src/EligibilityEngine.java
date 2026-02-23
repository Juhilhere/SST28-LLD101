import java.util.*;

public class EligibilityEngine {
    private final FakeEligibilityStore store;
    private final RuleInput input;
    private final List<EligibilityRule> rules;

    public EligibilityEngine(FakeEligibilityStore store) {
        this(store, new RuleInput(), defaultRules());
    }

    public EligibilityEngine(FakeEligibilityStore store, RuleInput input, List<EligibilityRule> rules) {
        this.store = store;
        this.input = input;
        this.rules = rules;
    }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s); // giant conditional inside
        p.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {
        List<String> reasons = new ArrayList<>();
        String status = "ELIGIBLE";

        for (EligibilityRule rule : rules) {
            Optional<String> reason = rule.evaluate(s, input);
            if (reason.isPresent()) {
                status = "NOT_ELIGIBLE";
                reasons.add(reason.get());
                break;
            }
        }

        return new EligibilityEngineResult(status, reasons);
    }

    private static List<EligibilityRule> defaultRules() {
        List<EligibilityRule> rules = new ArrayList<>();
        rules.add(new DisciplinaryFlagRule());
        rules.add(new CgrRule());
        rules.add(new AttendanceRule());
        rules.add(new CreditsRule());
        return rules;
    }
}

class EligibilityEngineResult {
    public final String status;
    public final List<String> reasons;

    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}
