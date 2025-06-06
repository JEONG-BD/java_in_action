package part4.chapter11;

import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    @Test
    public void testMap() {
        Properties props = new Properties();
        props.setProperty("a", "5");
        props.setProperty("b", "true");
        props.setProperty("c", "-3");

        assertEquals(5, Property.readDurationImperative(props, "a"));
        assertEquals(0, Property.readDurationImperative(props, "b"));
        assertEquals(0, Property.readDurationImperative(props, "c"));
        assertEquals(0, Property.readDurationImperative(props, "d"));

        assertEquals(5, Property.readDurationImperative(props, "a"));
        assertEquals(0, Property.readDurationImperative(props, "b"));
        assertEquals(0, Property.readDurationImperative(props, "c"));
        assertEquals(0, Property.readDurationImperative(props, "d"));
    }

}