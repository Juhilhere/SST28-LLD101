import java.util.Optional;

public class DisciplinaryFlagRule implements EligibilityRule {
    @Override
    public Optional<String> evaluate(StudentProfile s, RuleInput input) {
        if (s.disciplinaryFlag != LegacyFlags.NONE) {
            return Optional.of("disciplinary flag present");
        }
        return Optional.empty();
    }
}
