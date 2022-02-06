package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

//@Disabled(value = "Disabled until we learn Mocking")
class OwnerSDJpaServiceTest {

    @InjectMocks
    private OwnerSDJpaService service;
    @Mock
    private OwnerRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

//    @Disabled
    @Test
    public void findByLastName() {
        Owner owner = new Owner(1L, "Fred", "Mercury");
        when(repository.findByLastName(anyString())).thenReturn(owner);
        Owner foundOwner = service.findByLastName("Buck");
        verify(repository).findByLastName(anyString());
        assertNotNull(foundOwner);
    }

    @Test
    void findAllByLastNameLike() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }
}