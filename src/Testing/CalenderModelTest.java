package src.Testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.Model.CalenderModel;
import src.View.WeekView;


import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class CalenderModelTest {
    CalenderModel test;
    @BeforeEach
    void init(){
        test = new CalenderModel();
    }

    @Test
    void addPropertyChangeListener() {
        test.addPropertyChangeListener(new WeekView());
    }

    @Test
    void addEvent() {
        test.addEvent(test.getViewTime(), test.getViewTime(), "test", "testplace");
        assertNotNull(test.getEvents().pop());
    }

    @Test
    void removeEvent() {
        test.addEvent(test.getViewTime(), test.getViewTime(), "test", "testplace");
        test.removeEvent(0);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () ->{
            test.removeEvent(0);
        });
    }

    @Test
    void getEvents() {
        int s1 = test.getEvents().size();
        test.addEvent(test.getViewTime(), test.getViewTime(), "test", "testplace");
        test.addEvent(test.getViewTime(), test.getViewTime(), "test", "testplace");
        test.addEvent(test.getViewTime(), test.getViewTime(), "test", "testplace");
        int s2 = test.getEvents().size();
        assertNotEquals(s1,s2);

    }

    @Test
    void getViewTime() {
        test.getViewTime();
    }

    @Test
    void canGetAndSetYear() {
        int y1 = test.getViewTime().getYear();
        test.setYear(1);
        int y2 = test.getViewTime().getYear();
        assertNotEquals(y1, y2);
        y1 = test.getViewTime().getYear();
        test.setYear(-1);
        y2 = test.getViewTime().getYear();
        assertNotEquals(y1, y2);
    }

    @Test
    void canGetAndSetMonth() {
        Month m1 = test.getMonth();
        test.setMonth(1);
        Month m2 = test.getMonth();
        assertNotEquals(m1,m2);
        m1 = test.getMonth();
        test.setMonth(-1);
        m2 = test.getMonth();
        assertNotEquals(m1,m2);
    }

    @Test
    void canGetAndSetDay() {
        int d1 = test.getDay();
        test.setDay(1);
        int d2 = test.getDay();
        assertNotEquals(d1,d2);
        d1 = test.getDay();
        test.setDay(-1);
        d2 = test.getDay();
        assertNotEquals(d1,d2);
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}