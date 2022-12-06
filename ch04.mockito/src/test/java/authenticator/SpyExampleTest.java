package authenticator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpyExampleTest {
     @Test
    public void spyExampleTest() {
         Map<String, String> hashMap = new HashMap<String, String>();
         Map<String, String> hashMapSpy = spy(hashMap);
         assertNull(hashMapSpy.get("key"));
         hashMapSpy.put("key", "A value");
         assertEquals("A value", hashMapSpy.get("key"));
         when(hashMapSpy.get("key")).thenReturn("Another value");
         assertEquals("Another value", hashMapSpy.get("key"));
     }
    @Test
    public void spyDoNothingTest() {
        Map<String, String> hashMap = new HashMap<String, String>();
        Map<String, String> hashMapSpy = spy(hashMap);
        doNothing().when(hashMapSpy).clear();
        hashMapSpy.put("key1", "A value");
        hashMapSpy.put("key2", "Another value");
        hashMapSpy.clear();
        assertEquals("A value", hashMapSpy.get("key1"));
    }
}
