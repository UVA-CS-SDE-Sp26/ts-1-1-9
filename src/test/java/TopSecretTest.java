import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
public class TopSecretTest {
    @Test
    void shouldCallRunOnProgramController(){

        ProgramController mockController = mock(ProgramController.class);
        when(mockController.run(any())).thenReturn("Test Output");

        TopSecret topSecret = new TopSecret(mockController);
        String[] args = {"1"};
        topSecret.start(args);
        verify(mockController).run(args);
    }
}
