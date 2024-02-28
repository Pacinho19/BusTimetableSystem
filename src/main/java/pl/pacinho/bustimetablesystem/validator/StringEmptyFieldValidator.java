package pl.pacinho.bustimetablesystem.validator;

public class StringEmptyFieldValidator {
    public static boolean isNonNullAndNotEmpty(String value) {
        return value!=null && !value.isEmpty();
    }
}
