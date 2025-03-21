package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDto;
import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class TutorServiceTest {

    @InjectMocks
    private TutorService tutorService;

    @Mock
    private TutorRepository tutorRepository;

    @Mock
    private CadastroTutorDto cadastroTutorDto;

    @Mock
    private Tutor tutor;

    @Mock
    private AtualizacaoTutorDto atualizacaoTutorDto;

    @Test
    public void naoDeveCadastrarAbrigoJaCadastrado() {
        // Arrange
        given(tutorRepository.existsByTelefoneOrEmail(cadastroTutorDto.telefone(), cadastroTutorDto.email()))
                .willReturn(true);

        // Act + Assert
        assertThrows(ValidacaoException.class, () -> tutorService.cadastrar(cadastroTutorDto));
    }

    @Test
    public void deveCadastrarAbrigoNaoCadastrado() {
        // Arrange
        given(tutorRepository.existsByTelefoneOrEmail(cadastroTutorDto.telefone(), cadastroTutorDto.email()))
                .willReturn(false);

        // Act + Assert
        assertDoesNotThrow(() -> tutorService.cadastrar(cadastroTutorDto));
        then(tutorRepository).should().save(new Tutor(cadastroTutorDto));
    }

    @Test
    public void deveriaAtualizarDadosTutor() {
        // Arrange
        given(tutorRepository.getReferenceById(atualizacaoTutorDto.id()))
                .willReturn(tutor);

        // Act
        tutorService.atualizar(atualizacaoTutorDto);

        // Assert
        then(tutor).should().atualizarDados(atualizacaoTutorDto);
    }
}