package activity;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoadFileTest {
    @Test
    void testLoadGpx() throws IOException {
        Track track = new Track();
        try (InputStream is = LoadFileTest.class.getResourceAsStream("/track.gpx")) {
            track.loadFromGpx(is);
        }

        assertEquals(2801, track.getTrackPoints().size());
        assertEquals(18.541194, track.getTrackPoints().get(0).coordinate().longitude(), 0.000005);
        assertEquals(47.218102, track.getTrackPoints().get(0).coordinate().latitude(), 0.000005);
        assertEquals(134.2, track.getTrackPoints().get(0).elevation(), 0.05);
    }
}
