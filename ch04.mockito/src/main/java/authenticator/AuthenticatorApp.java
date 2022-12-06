package authenticator;

public class AuthenticatorApp {
    private Authenticator authenticator;

    public AuthenticatorApp(Authenticator authenticator) {
        this.authenticator = authenticator;
    }
    public boolean authenticate(String username, String paswword) {
        authenticator.foo();
        return authenticator.authenticateUser(username, paswword);
    }
    public boolean authenticateWithException(String username, String paswword) throws EmptyCredentialsException {
        authenticator.foo();
        return authenticator.authenticateUserWithException(username, paswword);
    }

    public void authenticateVoid(String username, String paswword) {
        authenticator.foo();
        authenticator.authenticateUserVoid(username, paswword);
    }

    public void authenticateVoidWithException(String username, String paswword) throws EmptyCredentialsException {
        authenticator.foo();
        authenticator.authenticateUserVoidWithException(username, paswword);
    }
}
