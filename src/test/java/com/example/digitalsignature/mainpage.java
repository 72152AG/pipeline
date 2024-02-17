import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ControllerTest {

    @Test
    public void testHome() {
        // Przygotowanie kontrolera
        Controller controller = new Controller(new ServiceImpl());

        // Wywołanie metody testowanej
        ModelAndView modelAndView = controller.home();

        // Sprawdzenie, czy widok został poprawnie ustawiony
        assert modelAndView != null;
        assertEquals("Home", modelAndView.getViewName());
    }
}
