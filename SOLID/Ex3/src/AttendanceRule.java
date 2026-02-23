import java.util.Optional;

public class AttendanceRule implements EligibilityRule {
    @Override
    public Optional<String> evaluate(StudentProfile s, RuleInput input) {
        if (s.attendancePct < input.minAttendance) {
            return Optional.of("attendance below " + input.minAttendance);
        }
        return Optional.empty();
    }
}
