package com.andreahowes.dive_db.logic;

import com.andreahowes.dive_db.data.dives.MySqlDiveRepository;
import com.andreahowes.dive_db.logic.SecurityModule.User;
import com.andreahowes.dive_db.logic.dive.Dive;
import com.andreahowes.dive_db.logic.dive.DivesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DivesServiceTest {
    final static String LOCATION = "Bull shark dive";
    final static LocalDate DATE = LocalDate.of(2014, Month.JANUARY, 1);
    DivesService divesService;
    MySqlDiveRepository mySqlDiveRepositoryMock;
    Dive dive1;
    Dive dive2;
    Dive diveFromRepository;
    List<Dive> diveList = new ArrayList<>();
    int dive2Id = 2;
    int dive3Id = 3;
    User user;

    @BeforeEach
    public void setUp() {

        mySqlDiveRepositoryMock = Mockito.mock(MySqlDiveRepository.class);
        divesService = new DivesService(mySqlDiveRepositoryMock);
        user.setLastName("Howes");

        dive1 = new Dive();
        dive1.setLocation("Bull shark dive");
        dive1.setDate(DATE);
        dive1.setUser("Howes");
        diveList.add(dive1);

        dive2 = new Dive();
        dive2.setId(dive2Id);

        diveFromRepository = new Dive();
        diveFromRepository.setId(dive3Id);

    }

    @Test
    public void whenGettingAllDives_shouldReturnAllDives() {
        when(mySqlDiveRepositoryMock.getAllDives("Howes")).thenReturn(Arrays.asList(dive1, dive2));

        List<Dive> allDives = divesService.getAllDives("Howes");

        assertThat(allDives).hasSize(2);
        assertThat(allDives).contains(dive1, dive2);
    }

    @Test
    public void whenGettingDiveByLocation_shouldReturnListOfDivesByLocation() {
        when(mySqlDiveRepositoryMock.getDiveByLocation(LOCATION)).thenReturn(diveList);

        List<Dive> diveListByLocation = divesService.getDiveByLocation(LOCATION);
//
//        assertThat(diveListByLocation)
//                .extracting(dive -> dive.getLocation())
//                .allMatch(location -> location.equals(LOCATION));

        assertThat(diveListByLocation)
                .extracting(dive -> dive.getLocation()).containsOnly(LOCATION);
    }

    @Test
    public void whenGettingDiveByDate_shouldReturnDiveListForThatDate() {
        when(mySqlDiveRepositoryMock.getDiveByDate(DATE)).thenReturn(diveList);

        List<Dive> diveListByDate = divesService.getDiveByDate(DATE);


        assertThat(diveListByDate)
                .extracting("date").containsOnly(DATE);
//        assertThat(diveListByDate)
//            .extracting(diveInList->diveInList.getDate()).containsOnly(DATE);
    }

    @Test
    public void whenGettingDiveById_shouldReturnDiveById() {
        when(mySqlDiveRepositoryMock.getDiveById(dive2Id)).thenReturn(dive2);
        Dive diveById = divesService.getDiveById(dive2Id);

        assertThat(diveById).extracting("id").isEqualTo(dive2Id);

    }

    @Test
    public void whenSavingADive_shouldSaveADive() {
        //roi changed to make it return something different than parameters gave it
        when(mySqlDiveRepositoryMock.save(dive1)).thenReturn(diveFromRepository);

        Dive savedDive = divesService.save(dive1);

        verify(mySqlDiveRepositoryMock).save(dive1);
        assertThat(savedDive).isEqualTo(diveFromRepository);
    }

    @Test
    public void whenUpdatingDiveById_shouldUpdateDiveById() {
        when(mySqlDiveRepositoryMock.updateDiveById(dive2Id, dive2)).thenReturn(diveFromRepository);

        Dive updatedDive = divesService.updateDiveById(dive2Id, dive2);

        assertThat(updatedDive).isEqualTo(diveFromRepository);
        verify(mySqlDiveRepositoryMock).updateDiveById(dive2Id, dive2);
    }

    @Test
    public void whenDeletingDiveById_shouldDeleteDiveById() {
        when(mySqlDiveRepositoryMock.delete(dive3Id)).thenReturn(diveFromRepository);

        Dive deletedDive = divesService.delete(dive3Id);

        assertThat(deletedDive).isEqualTo(diveFromRepository);
        assertThat(deletedDive.getId()).isEqualTo(dive3Id);
        verify(mySqlDiveRepositoryMock).delete(dive3Id);

    }
}
