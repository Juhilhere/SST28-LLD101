import java.util.Optional;

public class CreditsRule implements EligibilityRule {
    @Override
    public Optional<String> evaluate(StudentProfile s, RuleInput input) {
        if (s.earnedCredits < input.minCredits) {
            return Optional.of("credits below " + input.minCredits);
        }
        return Optional.empty();
    }
}
