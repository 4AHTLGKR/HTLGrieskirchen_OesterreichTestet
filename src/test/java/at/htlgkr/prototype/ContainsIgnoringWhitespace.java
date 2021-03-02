package at.htlgkr.prototype;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class ContainsIgnoringWhitespace extends BaseMatcher<String> {
    public String string;
    public static ContainsIgnoringWhitespace containsIgnoringWhitespace(String string) {
        return new ContainsIgnoringWhitespace(string);
    }

    private ContainsIgnoringWhitespace(String string) {
        this.string = string;
    }

    @Override
    public boolean matches(Object o) {
        return o.toString().replaceAll("\\s+", "").contains(string);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(String.format("the given String should contain '%s' without whitespaces", string));
    }
}
