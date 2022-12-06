package email;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {
    @Mock
    DeliveryPlatform platform;

    @InjectMocks
    EmailService emailService;

    @Captor
    ArgumentCaptor<Email> emailCaptor;

    @Test
    public void emailArgumentTest() {
        String to = "user@mycompany.com";
        String subject = "ArgumentCapture 사용";
        String body = "ArgumentCapture를 사용하고 있습니다.";

        emailService.send(to, subject, body, false);
        verify(platform).deliver(argThat(email -> email.getAddress().equals(to) && email.getSubject().equals(subject) && email.getBody().endsWith(body)));
    }

    @Test
    public void emailArgumentTestWithArgmentMatcher() {
        String to = "user@mycompany.com";
        String subject = "ArgumentCapture 사용";
        String body = "ArgumentCapture를 사용하고 있습니다.";

        emailService.send(to, subject, body,false);

        Email correct = new Email(to, subject, body);
        Email wrong = new Email("test@mycompany.com", subject, body);
        verify(platform).deliver(argThat(new EmailMatcher(correct)));
    }

    @Test
    public void emailArgumentTestWithArgmentCaptor() {
        String to = "user@mycompany.com";
        String subject = "ArgumentCaptor 사용";
        String body = "ArgumentCaptor를 사용하고 있습니다.";

        ArgumentCaptor<Email> emailCaptor = ArgumentCaptor.forClass(Email.class);

        emailService.send(to, subject, body, false);

        verify(platform).deliver(emailCaptor.capture());
        Email emailCaptorValue = emailCaptor.getValue();
        assertEquals("user@mycompany.com", emailCaptorValue.getAddress());
    }
    @Test
    public void whenDoesNotSupportHtml_expectTextOnlyEmailFormat() {
        String to = "user@mycompany.com";
        String subject = "ArgumentCaptor 사용";
        String body = "ArgumentCaptor를 사용하고 있습니다.";

        emailService.send(to, subject, body, false);

        verify(platform).deliver(emailCaptor.capture());
        Email emailCaptorValue = emailCaptor.getValue();
        assertEquals(Format.TEXT_ONLY, emailCaptorValue.getFormat());
    }

    @Test
    public void whenDoesSupportHtml_expectHTMLEmailFormat() {
        String to = "user@mycompany.com";
        String subject = "ArgumentCaptor 사용";
        String body = "<html><body>ArgumentCaptor를 사용하고 있습니다.</body></html>";

        emailService.send(to, subject, body, true);

        verify(platform).deliver(emailCaptor.capture());
        Email value = emailCaptor.getValue();
        assertEquals(Format.HTML, value.getFormat());
    }
}
