package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AbrigoServiceTest {

    @InjectMocks
    private AbrigoService abrigoService;

    @Mock
    private AbrigoRepository abrigoRepository;

    @Mock
    private Abrigo abrigo;

    @Mock
    private PetRepository petRepository;

    @Test
    void deveChamarListaDeTodosOsAbrigos() {
        // Act
        abrigoService.listar();

        // Assert
        then(abrigoRepository).should().findAll();
    }

    @Test
    void deveChamarListaDeTodosOsAbrigosPeloNome() {
        // Arrange
        var nome = "Miau";
        given(abrigoRepository.findByNome(nome)).willReturn(Optional.of(abrigo));

        // Act
        abrigoService.listarPetsDoAbrigo(nome);

        // Assert
        then(petRepository).should().findByAbrigo(abrigo);
    }
}