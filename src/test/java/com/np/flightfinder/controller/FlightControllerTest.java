package com.np.flightfinder.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("shortest paths between 2 cities")
    void testFlightsBetweenCities() throws Exception{

        mockMvc.perform(get("/flight/ATQ/BLR/5/"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("[{\"ATQ_BLR\":{\"6845\":185}},{\"ATQ_DEL_BLR\":{\"2057_819\":365}},{\"ATQ_BOM_BLR\":{\"6261_283\":475}},{\"ATQ_CCU_BLR\":{\"5926_932\":555}},{\"ATQ_BOM_HYD_BLR\":{\"6261_5361_167\":620}}]"));

    }
}