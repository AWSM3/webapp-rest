package com.lanit.webapprest.integration;

import com.lanit.webapprest.repository.CarRepositoryInterface;
import com.lanit.webapprest.repository.PersonRepositoryInterface;
import com.lanit.webapprest.web.controller.UtilsController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UtilsController.class)
public class UtilsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepositoryInterface personRepository;
    @MockBean
    private CarRepositoryInterface carRepository;

    @Test
    public void testStatistics() throws Exception {
        mockMvc.perform(get("/statistics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.personcount").value(0))
                .andExpect(jsonPath("$.carcount").value(0))
                .andExpect(jsonPath("$.uniquevendorcount").value(0));
    }

    @Test
    public void testClear() throws Exception {
        mockMvc.perform(get("/clear"))
                .andExpect(status().isOk());
    }
}
