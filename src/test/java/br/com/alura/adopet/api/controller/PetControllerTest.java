package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.service.PetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class PetControllerTest {

    @Autowired
    private MockMvc mvc;

    @InjectMocks
    private PetService petService;

    @Test
    @DisplayName("Deve retornar código 200 para requisição de listar todos os pets disponíveis")
    void deveRetornar200Cenario01() throws Exception {
        // Act
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get("/pets")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();


        // Assert
        assertEquals(200, response.getStatus());

    }


}