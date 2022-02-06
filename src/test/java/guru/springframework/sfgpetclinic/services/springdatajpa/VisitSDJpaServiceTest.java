/*
 * Copyright 2022 Spring Framework Guru.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author miron.maksymiuk
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Test Visit service with mocked Visit repo")
public class VisitSDJpaServiceTest {
    
    @Mock
    private VisitRepository repository;
    
    @InjectMocks
    private VisitSDJpaService service;
    
    private Set<Visit> visits;
    private Visit visit;
    
    public VisitSDJpaServiceTest() {
    }
    
    @BeforeEach
    public void setUp() {
        visits = new HashSet<>();
        visits.add(new Visit(1L, LocalDate.now()));
        visits.add(new Visit(2L, LocalDate.now()));
        visits.add(new Visit(3L, LocalDate.now()));
        visits.add(new Visit(4L, LocalDate.now()));
        visit = new Visit(1L, LocalDate.now());
    }

    /**
     * Test of findAll method, of class VisitSDJpaService.
     */
    @Test
    @DisplayName("verifyRepoFIndAllGetsCalled")
    public void testFindAll() {
        when(repository.findAll()).thenReturn(visits);
        Set<Visit> found = service.findAll();
        
        verify(repository).findAll();
        assertEquals(visits.size(), found.size());
    }

    /**
     * Test of findById method, of class VisitSDJpaService.
     */
    @Test
    @DisplayName("verifyRepoFIndByIdGetsCalled")
    public void testFindById() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(visit));
        Visit found = service.findById(1L);
        
        assertEquals(visit.getId(), found.getId());
        verify(repository).findById(anyLong());
        assertNotNull(found);
    }

    /**
     * Test of save method, of class VisitSDJpaService.
     */
    @Test
    @DisplayName("verifyRepoSaveGetsCalled")
    public void testSave() {
        when(repository.save(any(Visit.class))).thenReturn(visit);
        Visit saved = service.save(visit);
        
        verify(repository).save(any(Visit.class));
        assertEquals(visit.getId(), saved.getId());
        assertNotNull(saved);
    }

    /**
     * Test of delete method, of class VisitSDJpaService.
     */
    @Test
    @DisplayName("verifyRepoDeleteGetsCalled")
    public void testDelete() {
        service.delete(visit);
        
        verify(repository).delete(any(Visit.class));
    }

    /**
     * Test of deleteById method, of class VisitSDJpaService.
     */
    @Test
    @DisplayName("verifyRepoDeleteByIdGetsCalled")
    public void testDeleteById() {
        service.deleteById(1l);
        
        verify(repository).deleteById(anyLong());
    }
    
}
