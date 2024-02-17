package com.example.digitalsignature;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class nofile {

    @Test
    public void testHome() {
        Controller controller = new Controller(new ServiceImpl());

        ModelAndView modelAndView = controller.home();

        assert modelAndView != null;
        assertEquals("Home", modelAndView.getViewName());
    }
}
