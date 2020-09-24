package com.andreahowes.dive_db.logic;

import com.andreahowes.dive_db.logic.weather.Calculations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CalculationsTest {

    private Long sunrise;
    private Double temp;

    @BeforeEach
    public void setUp() {
        temp = 300.0;
    }

    @Test
    public void whenGettingTheSunriseTime_shouldReturnsSunriseTime() {
        sunrise = 1539949510L;
        String theSunriseTime = Calculations.getFormattedTime(sunrise);

        assertThat(theSunriseTime).isEqualTo("06:45");
    }

    @Test
    public void whenGettingTheTempInF_shouldReturnTheTempInF() {
        Double theTempInF = Calculations.getTheTempInF(temp);

        assertThat(theTempInF).isEqualTo(80.60);
    }

}
