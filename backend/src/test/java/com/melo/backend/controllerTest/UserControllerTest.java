package com.melo.backend.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.melo.backend.controller.UserController;
import com.melo.backend.dto.auth.AuthRegisterRequestDTO;
import com.melo.backend.dto.auth.AuthRegisterResponseDTO;
import com.melo.backend.security.SecurityConfig;
import com.melo.backend.security.SecurityFilter;
import com.melo.backend.service.UserService;

@WebMvcTest(
    controllers = UserController.class,
    excludeAutoConfiguration = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
        org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration.class
    },
    excludeFilters = @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = {SecurityFilter.class, SecurityConfig.class}
    )
)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService service;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void testRegisterUser() throws JsonProcessingException, Exception {
        AuthRegisterRequestDTO dto = new AuthRegisterRequestDTO("Jose", "jose@email.com", "12345678");

        when(service.registerUser(dto)).thenReturn(any(AuthRegisterResponseDTO.class));

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto))
                .accept(MediaType.APPLICATION_JSON)
            )
            .andExpect(status().isOk());

        verify(service, times(1)).registerUser(any(AuthRegisterRequestDTO.class));
    }

}
