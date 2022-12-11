package activity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrackPointTest {
    TrackPoint trackPoint = new TrackPoint(new Coordinate(12.5, 34.89), 123);
    TrackPoint trackPoint2 = new TrackPoint(new Coordinate(12.6, 34.123), 200);

    @Test
    void testCreateTrackPoint() {

        assertEquals(34.89, trackPoint.coordinate().longitude());
        assertEquals(12.5, trackPoint.coordinate().latitude());
        assertEquals(123.0, trackPoint.elevation());
    }

    @Test
    void testGetDistance() {
        assertTrue(trackPoint.getDistanceFrom(trackPoint2) > 83988.01 && trackPoint.getDistanceFrom(trackPoint2) < 83988.11);
    }

}