package com.andreahowes.dive_db.presentation.web;

import com.andreahowes.dive_db.logic.SecurityModule.JWT.InvalidTokenException;
import com.andreahowes.dive_db.logic.SecurityModule.JWT.MyTokenService;
import com.andreahowes.dive_db.logic.dive.Dive;
import com.andreahowes.dive_db.logic.dive.DivesService;
import com.andreahowes.dive_db.presentation.api.DivesApiController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DivesApiControllerTest {

    static final String INVALID_TOKEN = "12345";

    MockMvc mvc;

    DivesApiController controller;

    @Mock
    DivesService divesService;

    @Mock
    MyTokenService myTokenService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new DivesApiController(divesService, myTokenService);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void givenAllDives_whenGivenAllDives_thenReturnJsonArray() throws Exception {
        Dive dive1 = new Dive();
        dive1.setLocation("Playa");
        dive1.setUser("Howes");

        List<Dive> allDives = Arrays.asList(dive1);

        given(divesService.getAllDives("Howes")).willReturn(allDives);

        mvc.perform(get("/api/user/logbook/dives/Howes?token=12345")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())

                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].location", is(dive1.getLocation())));

    }

    @Test
    public void whenGettingAllDivesWithInvalidToken_thenReturnUnauthorizedResponse() throws Exception {

        doThrow(new InvalidTokenException("")).when(myTokenService).validateTokenByValue(INVALID_TOKEN);
        mvc.perform(get("/api/user/logbook/dives/Howes?token=" + INVALID_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(status().reason(containsString("Invalid Token")));

    }

    @Test
    public void whenGettingAllDivesForLocation_thenReturnAllDivesForLocation() throws Exception{
        Dive dive1 = new Dive();
        String location = "playa";
        dive1.setLocation(location);
        List<Dive> playaDives = Arrays.asList(dive1);

        given(divesService.getDiveByLocation("playa")).willReturn(playaDives);

        mvc.perform(get("/api/user/logbook/dives/location/playa?token=712052887")
                .contentType(MediaType.APPLICATION_JSON))
                //.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].location", is(dive1.getLocation())));
    }

    @Test
    public void whenGettingDiveByLocationWithInvalidToken_thenReturnUnauthorizedUser() throws Exception {
        /* prevent the tokenService from having its normal behaviour
           --- why ?? ---
        doThrow(new InvalidTokenException("A new InvalidTokenException is thrown"))
                .when(myTokenService).validateTokenByValue(INVALID_TOKEN);
         */

        mvc.perform(get("/api/user/logbook/dives/location/playa?token=" + INVALID_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(status().reason(containsString("Invalid Token")));
    }
    @Test
    public void whenGettingAllDivesByDate_thenReturnAllDivesForDate() throws Exception{
        Dive dive1 = new Dive();
        LocalDate date = LocalDate.of(2019, 11, 25);
        dive1.setDate(date);
        List<Dive> dives = Arrays.asList(dive1);

        given(divesService.getDiveByDate(date)).willReturn(dives);

        mvc.perform(get("/api/user/logbook/dives/date/2019-11-25?token=712052887")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].date", is(dive1.getDate().toString())));
    }

    @Test
    public void whenGettingDiveByDateWithInvalidToken_thenReturnUnauthorizedUser() throws Exception{
        /* prevent the tokenService from having its normal behaviour
           --- why ?? ---
        doThrow(new InvalidTokenException("")).when(myTokenService).validateTokenByValue(INVALID_TOKEN);
         */

        mvc.perform(get("/api/user/logbook/dives/date/2019-11-25?token=" + INVALID_TOKEN)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(status().reason(containsString("Invalid Token")));
    }


}
