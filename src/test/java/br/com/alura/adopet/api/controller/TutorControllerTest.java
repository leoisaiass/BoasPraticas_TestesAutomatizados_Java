package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.service.TutorService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
class TutorControllerTest {

    @MockBean
    private TutorService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve retornar 200 para cadastro válido de tutor")
    void deveRetornar200Cenario01() throws Exception {

        // Arrange
        var json = """
                {
                    "nome": "Ricardo",
                    "telefone": "(11)91234-2121",
                    "email": "r1qu3@gmail.com"
                }
                """;

        // Act
        MockHttpServletResponse response = mockMvc.perform(
                post("/tutores")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // Assert
        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Deve retornar 404 para cadastro inválido de tutor")
    void deveRetornar404Cenario01() throws Exception {

        // Arrange
        var json = """
                {
                    "email": "r1qu3@gmail.com"
                }
                """;

        // Act
        MockHttpServletResponse response = mockMvc.perform(
                post("/tutores")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // Assert
        assertEquals(404, response.getStatus());
    }

    @Test
    @DisplayName("Deve retornar 200 para cadastro inválido de tutor")
    void deveRetornar200Cenario02() throws Exception {

        // Arrange
        // Arrange
        var json = """
                {
                    "id" : "1",
                    "nome" : "Ricardo",
                    "telefone" : "(11)91234-2121",
                    "email" : "r1qu3@gmail.com"
                }
                """;

        // Act
        MockHttpServletResponse response = mockMvc.perform(
                put("/tutores")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // Assert
        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Deve retornar 400 para cadastro inválido de tutor")
    void deveRetornar400Cenario01() throws Exception {

        // Arrange
        var json = """
                {
                    "id": "1",
                    "nome" : "Ricardo",
                    "telefone" : "(11)91234-212121",
                    "email" : "r1qu3@gmail.com"
                }
                """;

        // Act
        MockHttpServletResponse response = mockMvc.perform(
                put("/tutores")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // Assert
        assertEquals(400, response.getStatus());
    }

}