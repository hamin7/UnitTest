package email;

import org.mockito.ArgumentMatcher;

public class EmailMatcher implements ArgumentMatcher<Email> {
    private Email left;
    public EmailMatcher(Email email) {
        this.left = email;
    }
    @Override
    public boolean matches(Email right) {
        return left.getAddress().equals(right.getAddress()) &&
                left.getSubject().equals(right.getSubject()) &&
                left.getBody().equals(right.getBody()) &&
                left.getFormat().equals(right.getFormat());
    }
}
