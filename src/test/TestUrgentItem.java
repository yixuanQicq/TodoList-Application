package test;

import model.RegularItem;
import model.UrgentItem;
import org.junit.jupiter.api.BeforeEach;

public class TestUrgentItem extends TestItem{
    @BeforeEach
    void setUp(){
        testDate = "JUNE 8, 2017";
        testName = "CPSC210 Lecture ticket";
        testItem = new UrgentItem(testName, testDate);
    }
}
