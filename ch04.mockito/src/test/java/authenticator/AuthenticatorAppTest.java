package authenticator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthenticatorAppTest {
    @Mock
    private Authenticator authenticatorMock;
    @InjectMocks
    private AuthenticatorApp authenticatorApp;
    @Test
    public void testAuthenticator() {
        String username = "user";
        String password = "unsafepassword";

        Authenticator authenticatorMock = mock(Authenticator.class);
        AuthenticatorApp authenticatorApp = new AuthenticatorApp(authenticatorMock);
        when(authenticatorMock.authenticateUser(username, password)).thenReturn(false);

        boolean actual = authenticatorApp.authenticate(username, password);
        assertFalse(actual);
    }
    @Test
    public void testAuthenticatorVerify() {
        String username = "user";
        String password = "password";

        Authenticator authenticatorMock = mock(Authenticator.class);
        AuthenticatorApp authenticatorApp = new AuthenticatorApp(authenticatorMock);
        when(authenticatorMock.authenticateUser(username, password)).thenReturn(true);
        authenticatorApp.authenticate(username, password);
        verify(authenticatorMock).authenticateUser(username, password);
//        verify(authenticatorMock).authenticateUser(username, "wrong password"); // fail!
    }
    @Test
    public void testAuthenticatorVerifyTimes() {
        String username = "user";
        String password = "password";

        Authenticator authenticatorMock = mock(Authenticator.class);
        AuthenticatorApp authenticatorApp = new AuthenticatorApp(authenticatorMock);
        when(authenticatorMock.authenticateUser(username, password)).thenReturn(true);
        authenticatorApp.authenticate(username, password);
        authenticatorApp.authenticate(username, password);
        authenticatorApp.authenticate(username, password);
        verify(authenticatorMock, times(3)).authenticateUser(username, password);
        verify(authenticatorMock, atLeastOnce()).authenticateUser(username, password);
        verify(authenticatorMock, atLeast(1)).authenticateUser(username, password);
        verify(authenticatorMock, atMost(3)).authenticateUser(username, password);
//        verify(authenticatorMock, never()).authenticateUser(username, password);    // fail : 이미 위에서 세 번이나 호출함...
    }

    @Test
    public void testAuthenticatorVerifyArgumentMatching() {
        String username = "user";
        String password = "password";

        Authenticator authenticatorMock = mock(Authenticator.class);
        AuthenticatorApp authenticatorApp = new AuthenticatorApp(authenticatorMock);
        when(authenticatorMock.authenticateUser(username, password)).thenReturn(true);
        authenticatorApp.authenticate(username, password);

        verify(authenticatorMock).authenticateUser(anyString(), eq("password"));
    }

    @Test
    public void testAuthenticatorVerifyInOrder() {
        String username = "user";
        String password = "password";

        Authenticator authenticatorMock = mock(Authenticator.class);
        AuthenticatorApp authenticatorApp = new AuthenticatorApp(authenticatorMock);
        when(authenticatorMock.authenticateUser(username, password)).thenReturn(true);
        authenticatorApp.authenticate(username, password);

        // 메서드 호출 순서 확인...
        InOrder inOrder = inOrder(authenticatorMock);
        inOrder.verify(authenticatorMock).foo();
        inOrder.verify(authenticatorMock).authenticateUser(username, password);

//        inOrder.verify(authenticatorMock).authenticateUser(username, password);
//        inOrder.verify(authenticatorMock).foo();
    }
    @Test
    public void testAuthenticatorVerifyTimeout() {
        String username = "user";
        String password = "password";

        Authenticator authenticatorMock = mock(Authenticator.class);
        AuthenticatorApp authenticatorApp = new AuthenticatorApp(authenticatorMock);
        when(authenticatorMock.authenticateUser(username, password)).thenReturn(true);
        authenticatorApp.authenticate(username, password);

        verify(authenticatorMock, timeout(100)).authenticateUser(username, password);
        verify(authenticatorMock, timeout(100).atLeast(1)).authenticateUser(username, password);
    }
    @Test
    public void testAuthenticatorException() throws EmptyCredentialsException {
        Authenticator authenticatorMock = mock(Authenticator.class);
        AuthenticatorApp authenticatorApp = new AuthenticatorApp(authenticatorMock);
        when(authenticatorMock.authenticateUserWithException("", "")).thenThrow(new EmptyCredentialsException());
        Throwable exeption = assertThrows(EmptyCredentialsException.class, () -> authenticatorApp.authenticateWithException("", ""));
        assertEquals("Empty credentials!", exeption.getMessage());
    }
    @Test
    public void testAuthenticatorMockInjection() {
        String username = "user";
        String password = "password";

        when(authenticatorMock.authenticateUser(username, password)).thenReturn(true);
        boolean actual = authenticatorApp.authenticate("user", "password");
        assertTrue(actual);
    }
    @Test
    public void testAuthenticatorDoThrow() throws EmptyCredentialsException {
        doThrow(new EmptyCredentialsException()).when(authenticatorMock).authenticateUserVoidWithException("", "");
        Throwable exeption = assertThrows(EmptyCredentialsException.class, () -> authenticatorApp.authenticateVoidWithException("", ""));
        assertEquals("Empty credentials!", exeption.getMessage());
    }
    @Test
    public void testAuthenticatorDoAnswer() {
        String username = "user";
        String password = "password";

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object [] args = invocationOnMock.getArguments();
                assertEquals(username, args[0]);
                assertEquals(password, args[1]);
                return null;
            }
        }).when(authenticatorMock).authenticateUserVoid(username, password);
        authenticatorApp.authenticateVoid(username, password);
    }

    @Test
    public void testAuthenticatorDoNothing() throws EmptyCredentialsException {
        doNothing().doThrow(new EmptyCredentialsException()).when(authenticatorMock).authenticateUserVoidWithException("", "");
        authenticatorApp.authenticateVoidWithException("", "");
        Throwable exeption = assertThrows(EmptyCredentialsException.class, () -> authenticatorApp.authenticateVoidWithException("", ""));
        assertEquals("Empty credentials!", exeption.getMessage());
    }
}
