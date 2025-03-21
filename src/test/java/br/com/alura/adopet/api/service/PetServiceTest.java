package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {

    @InjectMocks
    private PetService petService;

    @Mock
    private PetRepository petRepository;

    @Mock
    private CadastroPetDto cadastroPetDto;

    @Mock
    private Abrigo abrigo;

    @Mock
    private PetDto dto;

    @Test
    public void deveCadastrarPet() {
        // Act
        petService.cadastrarPet(abrigo, cadastroPetDto);

        // Assert
        then(petRepository).should().save(new Pet(cadastroPetDto, abrigo));
    }

    @Test
    public void deveRetornarPetDisponivel() {
        // Act
       petService.buscarPetsDisponiveis();

       // Assert
        then(petRepository).should().findAllByAdotadoFalse();

    }
}