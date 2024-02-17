package com.example.digitalsignature;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public class ControllerUnitTest {

    private ServiceImpl service;
    private Controller controller;

    @BeforeEach
    public void setUp() {
        service = mock(ServiceImpl.class);
        controller = new Controller(service);
    }

    @Test
    public void testHome() {
        ModelAndView modelAndView = controller.home();
        assert(modelAndView.getViewName().equals("Home"));
    }

    @Test
    public void testSend() {
        Model model = mock(Model.class);
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Spring Framework".getBytes());
        String email = "test@example.com";

        ModelAndView modelAndView = controller.send(model, new MockMultipartFile[]{file}, email);

        assert(modelAndView.getViewName().equals("Send"));
        verify(service, times(1)).sendDokumentMail(any(), anyString());
    }

    @Test
    public void testSendNoFiles() {
        Model model = mock(Model.class);
        String email = "test@example.com";

        ModelAndView modelAndView = controller.send(model, new MockMultipartFile[]{}, email);

        assert(modelAndView.getViewName().equals("Send"));
        verify(service, never()).sendDokumentMail(any(), anyString());
    }
}
