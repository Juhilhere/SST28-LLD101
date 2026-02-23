import java.util.*;

public class OnboardingService {
    private final StudentStore store;
    private final StudentInputParser parser;
    private final StudentInputValidator validator;
    private final OnboardingPrinter printer;

    public OnboardingService(StudentStore store) {
        this.store = store;
        this.parser = new StudentInputParser();
        this.validator = new StudentInputValidator();
        this.printer = new OnboardingPrinter();
    }

    public void registerFromRawInput(String raw) {
        printer.printInput(raw);

        ParsedStudentInput input = parser.parse(raw);
        List<String> errors = validator.validate(input);

        if (!errors.isEmpty()) {
            printer.printErrors(errors);
            return;
        }

        String id = IdUtil.nextStudentId(store.count());
        StudentRecord rec = new StudentRecord(
                id,
                input.name,
                input.email,
                input.phone,
                input.program);

        store.save(rec);

        printer.printSuccess(rec, store.count());
    }
}