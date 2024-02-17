package com.example.digitalsignature;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import static org.mockito.Mockito.*;
@SpringBootTest
public class sendDokumentMail {

    @InjectMocks
    private Controller controller;

    @Mock
    private ServiceImpl service;

    @Test
    public void testSend() {
        MockitoAnnotations.initMocks(this);

        MockMultipartFile[] files = {new MockMultipartFile("file", "test.txt", "text/plain", "test content".getBytes())};
        String email = "test@example.com";
        Model model = mock(Model.class);

        ModelAndView modelAndView = controller.send(model, files, email);

        verify(service, times(1)).sendDokumentMail(files, email);

        assert modelAndView != null;
        assert modelAndView.getViewName().equals("Send");
    }
}
