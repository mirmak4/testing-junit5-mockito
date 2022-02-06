package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import static org.mockito.ArgumentMatchers.any;
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
        Owner foundOwner = service.findByLastName("Mercury");
        verify(repository).findByLastName(anyString());
        assertNotNull(foundOwner);
        assertEquals("Mercury", foundOwner.getLastName());
    }

    @Test
    void findAllByLastNameLike() {
        List<Owner> owners = new ArrayList<>();
        Owner freddy = new Owner(1L, "Freddy", "Mercury");
        Owner sting = new Owner(1L, "Sting", "Mercury");
        owners.add(freddy);
        owners.add(sting);
        when(repository.findAllByLastNameLike(anyString())).thenReturn(owners);
        List<Owner> foundOwners = service.findAllByLastNameLike("Mercury");
        verify(repository).findAllByLastNameLike(anyString());
        assertNotNull(foundOwners);
        assertEquals(2, foundOwners.size());
        assertEquals("Mercury", foundOwners.get(0).getLastName());
        assertEquals("Mercury", foundOwners.get(1).getLastName());
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        Owner freddy = new Owner(1L, "Freddy", "Mercury");
        Owner sting = new Owner(1L, "Sting", "Mercury");
        owners.add(freddy);
        owners.add(sting);
        when(repository.findAll()).thenReturn(owners);
        Set<Owner> foundOwners = service.findAll();
        verify(repository).findAll();
        assertNotNull(foundOwners);
        assertEquals(2, foundOwners.size());
    }

    @Test
    void findById() {
        Owner owner = new Owner(1L, "Fred", "Mercury");
        when(repository.findById(anyLong())).thenReturn(Optional.of(owner));
        Owner foundOwner = service.findById(1L);
        verify(repository).findById(anyLong());
        assertNotNull(foundOwner);
    }

    @Test
    void save() {
        Owner owner = new Owner(1L, "Freddy", "Mercury");
        when(repository.save(any(Owner.class))).thenReturn(owner);
        Owner saved = service.save(owner);
        verify(repository).save(any(Owner.class));
        assertNotNull(saved);
    }

    @Test
    void delete() {
        Owner owner = new Owner(1L, "Freddy", "Mercury");
        service.delete(owner);
        verify(repository).delete(any(Owner.class));
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(repository).deleteById(anyLong());
    }
}