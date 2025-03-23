package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class AbrigoControllerTest {

    @MockBean
    private AbrigoService abrigoService;

    @MockBean
    private PetService petService;

    @Mock
    private Abrigo abrigo;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Deve devolver código 200 para requisição de listar abrigos")
    void deveRetornar200Cenario01() throws Exception {
        // Act
        MockHttpServletResponse response = mockMvc.perform(
                get("/abrigos")
        ).andReturn().getResponse();

        // Assert
        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Deve devolver código 200 para requisição de cadastrar abrigos")
    void deveRetornar200Cenario02() throws Exception {
        // Arrange
        String json = """
                {
                    "nome" : "Abrigo Feliz",
                    "telefone" : "(11) 2302-6795",
                    "email" : "email@example.com"
                }
                """;

        // Act
        MockHttpServletResponse response = mockMvc.perform(
                post("/abrigos")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // Assert
        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Deve devolver código 400 para requisição de cadastrar abrigos")
    void deveRetornar400Cenario01() throws Exception {
        // Arrange
        String json = """
                {
                    "nome" : "Abrigo Feliz",
                    "telefone" : "(11) 2302-6795",
                    "email" : "email@example.com"
                }
                """;

        // Act
        MockHttpServletResponse response = mockMvc.perform(
                post("/abrigos")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        // Assert
        assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Deve devolver código 200 para requisição de listar pets do abrigo por nome")
    void deveRetornar200Cenario03() throws Exception {
        // Arrange
        String nome = "Abrigo Feliz";

        // Act
        MockHttpServletResponse response = mockMvc.perform(
                get("/abrigos/{nome}/pets", nome)
        ).andReturn().getResponse();

        // Assert
        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Deve devolver código 200 para requisição de listar pets do abrigo por id")
    void deveRetornar200Cenario04() throws Exception {
        // Arrange
        String id = "1";

        // Act
        MockHttpServletResponse response = mockMvc.perform(
                get("/abrigos/{id}/pets", id)
        ).andReturn().getResponse();

        // Assert
        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Deve devolver código 400 para requisição de listar pets do abrigo por nome")
    void deveRetornar400Cenario02() throws Exception {
        // Arrange
        String nome = "Abrigo Feliz";
        given(abrigoService.listarPetsDoAbrigo(nome)).willThrow(ValidacaoException.class);

        // Act
        MockHttpServletResponse response = mockMvc.perform(
                get("/abrigos/{nome}/pets", nome)
        ).andReturn().getResponse();

        // Assert
        assertEquals(404, response.getStatus());
    }

    @Test
    @DisplayName("Deve devolver código 400 para requisição de listar pets do abrigo por ID")
    void deveRetornar400Cenario03() throws Exception {
        // Arrange
        String id = "1";
        given(abrigoService.listarPetsDoAbrigo(id)).willThrow(ValidacaoException.class);

        // Act
        MockHttpServletResponse response = mockMvc.perform(
                get("/abrigos/{id}/pets", id)
        ).andReturn().getResponse();

        // Assert
        assertEquals(404, response.getStatus());
    }

    @Test
    @DisplayName("Deve retornar código 200 para cadastrar pet pelo ID do abrigo")
    void deveRetornar200Cenario05() throws Exception {
        //Arange
        String json = """
                {
                    "tipo": "GATO",
                    "nome": "Miau",
                    "raca": "padrao",
                    "idade": "5",
                    "cor" : "Parda",
                    "peso": "6.4"
                }
                """;

        String abrigoId = "1";

        //Act
        MockHttpServletResponse response = mockMvc.perform(
                post("/abrigos/{abrigoId}/pets",abrigoId)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //Assert
        assertEquals(200,response.getStatus());
    }

    @Test
    @DisplayName("Deve retornar código 200 para cadastrar pet pelo NOME do abrigo")
    void deveRetornar200Cenario06() throws Exception {
        //Arrange
        String json = """
                {
                    "tipo": "GATO",
                    "nome": "Miau",
                    "raca": "padrao",
                    "idade": "5",
                    "cor" : "Parda",
                    "peso": "6.4"
                }
                """;

        String abrigoNome = "Abrigo feliz";

        //Act
        MockHttpServletResponse response = mockMvc.perform(
                post("/abrigos/{abrigoNome}/pets",abrigoNome)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //Assert
        assertEquals(200,response.getStatus());
    }

    @Test
    @DisplayName("Deve retornar código 404 para cadastrar pet pelo ID do abrigo não encontrado")
    void deveRetornar404Cenario01() throws Exception {
        //Arrange
        String json = """
                {
                    "tipo": "GATO",
                    "nome": "Miau",
                    "raca": "padrao",
                    "idade": "5",
                    "cor" : "Parda",
                    "peso": "6.4"
                }
                """;

        String abrigoId = "1";

        given(abrigoService.carregarAbrigo(abrigoId)).willThrow(ValidacaoException.class);

        //Act
        MockHttpServletResponse response = mockMvc.perform(
                post("/abrigos/{abrigoId}/pets",abrigoId)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //Assert
        assertEquals(404,response.getStatus());
    }

    @Test
    @DisplayName("Deve retornar código 404 para cadastrar pet pelo NOME do abrigo não encontrado")
    void deveRetornar404Cenario02() throws Exception {
        //Arrange
        String json = """
                {
                    "tipo": "GATO",
                    "nome": "Miau",
                    "raca": "padrao",
                    "idade": "5",
                    "cor" : "Parda",
                    "peso": "6.4"
                }
                """;

        String abrigoNome = "Abrigo legal";

        given(abrigoService.carregarAbrigo(abrigoNome)).willThrow(ValidacaoException.class);

        //Act
        MockHttpServletResponse response = mockMvc.perform(
                post("/abrigos/{abrigoNome}/pets",abrigoNome)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        //Assert
        assertEquals(404,response.getStatus());
    }



}