package com.andreahowes.dive_db.logic;

import com.andreahowes.dive_db.logic.dive.Dive;
import com.andreahowes.dive_db.logic.dive.DivesService;
import com.andreahowes.dive_db.logic.dive.StatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class StatisticsServiceTest {
    private List<Dive> diveList = new ArrayList<>();
    private StatisticsService statisticsService;
    private DivesService divesServiceMock;
    private String user = "Howes";


    @BeforeEach
    public void setUp() throws Exception {
        Dive dive1 = new Dive();
        Dive dive2 = new Dive();
        dive1.setDurationInMinutes(60.0);
        dive2.setDurationInMinutes(40.0);
        dive1.setMaxDepthInMeters(18.0);
        dive2.setMaxDepthInMeters(30.0);
        diveList.add(dive1);
        diveList.add(dive2);
        divesServiceMock = Mockito.mock(DivesService.class);
        statisticsService = new StatisticsService(divesServiceMock);
    }

    @Test
    public void whenGettingTotalNumberOfDives_shouldReturnTotalNumberOfDives() {
        when(divesServiceMock.getAllDives(user)).thenReturn(diveList);

        double totalNumberOfDives = statisticsService.getTotalNumberOfDives(user);

        assertThat(totalNumberOfDives).isEqualTo(2);
    }

    @Test
    public void whenGettingTotalTimeInWater_shouldReturnTotalTimeInWaterInMinutes() {
        when(divesServiceMock.getAllDives(user)).thenReturn(diveList);

        double totalTimeInWaterInMinutes = statisticsService.getTotalTimeInWaterInMinutes(user);

        assertThat(totalTimeInWaterInMinutes).isEqualTo(100);
    }

    @Test
    public void whenGettingMaxDepth_shouldReturnMaxDepthInMeters() {
        when(divesServiceMock.getAllDives(user)).thenReturn(diveList);

        double maxDepthInMetersForAllDives = statisticsService.getMaxDepthInMetersForAllDives(user);

        assertThat(maxDepthInMetersForAllDives).isEqualTo(30);
    }


    @Test
    public void whenGettingMinDepth_shouldReturnMinDepthInMeters() {
        when(divesServiceMock.getAllDives(user)).thenReturn(diveList);

        double minDepthInMetersForAllDives = statisticsService.getMinDepthInMetersForAllDives(user);

        assertThat(minDepthInMetersForAllDives).isEqualTo(18);
    }


    @Test
    public void whenGettingAverageDepthInMetersForAllDives_shouldReturnAverageDepthInMeters() {
        when(divesServiceMock.getAllDives(user)).thenReturn(diveList);

        double averageDepthInMetersForAllDives = statisticsService.getAverageDepthInMetersForAllDives(user);

        assertThat(averageDepthInMetersForAllDives).isEqualTo(24);
    }

}
