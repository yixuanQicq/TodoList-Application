package test;

import model.BusinessItem;
import org.junit.jupiter.api.BeforeEach;

public class TestBusinessItem extends TestItem{
    @BeforeEach
    void setUp(){
        testDate = "JUNE 8, 2017";
        testName = "CPSC210 Lecture ticket";
        testItem = new BusinessItem(testName, testDate);
    }
}
