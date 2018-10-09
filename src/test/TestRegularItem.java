package test;

import model.RegularItem;
import org.junit.jupiter.api.BeforeEach;

public class TestRegularItem extends TestItem {
    @BeforeEach
    void setUp(){
        testDate = "JUNE 8, 2017";
        testName = "CPSC210 Lecture ticket";
        testItem = new RegularItem(testName, testDate);
    }

}
