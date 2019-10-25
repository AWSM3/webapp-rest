package com.lanit.webapprest.unit;

import com.lanit.webapprest.web.controller.UtilsController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilsControllerTest {
    @Autowired
    private UtilsController utilsController;

    @Test
    public void testClear() {
        assertEquals(utilsController.clear().getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testStatistics() {
        assertEquals(utilsController.statistics().getStatusCode(), HttpStatus.OK);
    }
}
