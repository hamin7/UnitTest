package authenticator;
public interface Authenticator {
    boolean authenticateUser(String username, String password);
    boolean authenticateUserWithException(String username, String password) throws EmptyCredentialsException;
    void authenticateUserVoid(String username, String password);
    void authenticateUserVoidWithException(String username, String password) throws EmptyCredentialsException;
    void foo();
}
