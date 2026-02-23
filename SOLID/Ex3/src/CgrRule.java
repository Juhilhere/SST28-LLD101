import java.util.Optional;

public class CgrRule implements EligibilityRule {
    @Override
    public Optional<String> evaluate(StudentProfile s, RuleInput input) {
        if (s.cgr < input.minCgr) {
            String threshold = String.format("%.1f", input.minCgr);
            return Optional.of("CGR below " + threshold);
        }
        return Optional.empty();
    }
}
