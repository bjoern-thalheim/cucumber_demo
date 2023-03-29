package de.adesso.thalheim.gtd.controller;

import de.adesso.thalheim.gtd.repository.ThoughtRepository;
import org.aspectj.weaver.patterns.IVerificationRequired;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InboxControllerTest {

    @InjectMocks
    InboxController controller;

    @Mock
    ThoughtRepository repository;

    @Captor
    ArgumentCaptor<Thought> thoughtArgumentCaptor;

    @Test
    public void testPutThoughtIntoRepository() throws UnsupportedEncodingException {
        // given
        String thoughtDescription = "foiaxöniso";
        // when
        controller.collect(thoughtDescription);
        // then
        verify(repository).save(thoughtArgumentCaptor.capture());
        assertThat(thoughtArgumentCaptor.getValue().getDescription()).isEqualTo(thoughtDescription);
    }

    @Test
    public void testGetAllThoughts() {
        // given
        String thoughtDescription = "foiaxöniso";
        Thought thought = new Thought(UUID.randomUUID(), thoughtDescription);
        when(repository.findAll()).thenReturn(Set.of(thought));
        // when
        List<Thought> thoughts = controller.get();
        // then
        assertThat(thoughts).hasSize(1);
        assertThat(thoughts.iterator().next()).isEqualTo(thought);
    }
}