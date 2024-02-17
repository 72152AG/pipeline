import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;

public class ControllerTest {

    @InjectMocks
    private Controller controller;

    @Mock
    private ServiceImpl service;

    @Test
    public void testSend() {
        MockitoAnnotations.initMocks(this);

        // Przygotowanie danych testowych
        MockMultipartFile[] files = {new MockMultipartFile("file", "test.txt", "text/plain", "test content".getBytes())};
        String email = "test@example.com";
        Model model = mock(Model.class);

        // Wywołanie metody testowanej
        ModelAndView modelAndView = controller.send(model, files, email);

        // Sprawdzenie, czy metoda sendDokumentMail z service została wywołana poprawnie
        verify(service, times(1)).sendDokumentMail(files, email);

        // Sprawdzenie, czy widok został poprawnie ustawiony
        assert modelAndView != null;
        assert modelAndView.getViewName().equals("Send");
    }
}
