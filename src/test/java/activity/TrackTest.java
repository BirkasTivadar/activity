package activity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrackTest {
    public Track track = new Track();

    @Test
    void testAddItemGetPoinsts() {
        assertEquals(0, track.getTrackPoints().size());
        track.addTrackPoint(new TrackPoint(new Coordinate(12.5, 34.89), 123));
        assertEquals(1, track.getTrackPoints().size());
        assertEquals(34.89, track.getTrackPoints().get(0).coordinate().longitude());
    }

    @Test
    void testFullElevation() {
        track.addTrackPoint(new TrackPoint(new Coordinate(12.5, 34.89), 123));
        track.addTrackPoint(new TrackPoint(new Coordinate(12.5, 34.89), 124));
        track.addTrackPoint(new TrackPoint(new Coordinate(12.5, 34.89), 0));
        track.addTrackPoint(new TrackPoint(new Coordinate(12.5, 34.89), 200));

        assertEquals(201.0, track.getFullElevation());
    }

    @Test
    void testFullDecrease() {
        track.addTrackPoint(new TrackPoint(new Coordinate(12.5, 34.89), 123));
        track.addTrackPoint(new TrackPoint(new Coordinate(12.5, 34.89), 124));
        track.addTrackPoint(new TrackPoint(new Coordinate(12.5, 34.89), 0));
        track.addTrackPoint(new TrackPoint(new Coordinate(12.5, 34.89), 200));

        assertEquals(124.0, track.getFullDecrease());
    }

    @Test
    void testGetDistance() {
        track.addTrackPoint(new TrackPoint(new Coordinate(12.5, 34.89), 123));
        track.addTrackPoint(new TrackPoint(new Coordinate(-12.5, 45.7), 124));
        track.addTrackPoint(new TrackPoint(new Coordinate(13.7, -6.0), 0));
        track.addTrackPoint(new TrackPoint(new Coordinate(3.67, -42.789), 200));

        assertTrue(track.getDistance() > 13611579.56 && track.getDistance() < 13611579.66);
    }

    @Test
    void testFindMinimumCoordinate() {
        track.addTrackPoint(new TrackPoint(new Coordinate(12.5, 34.89), 123));
        track.addTrackPoint(new TrackPoint(new Coordinate(-12.5, 45.7), 124));
        track.addTrackPoint(new TrackPoint(new Coordinate(13.7, -6.0), 0));
        track.addTrackPoint(new TrackPoint(new Coordinate(3.67, -42.789), 200));

        assertEquals(-12.5, track.findMinimumCoordinate().latitude());
        assertEquals(-42.789, track.findMinimumCoordinate().longitude());
    }

    @Test
    void testFindMaximumCoordinate() {
        track.addTrackPoint(new TrackPoint(new Coordinate(12.5, 34.89), 123));
        track.addTrackPoint(new TrackPoint(new Coordinate(-12.5, 45.7), 124));
        track.addTrackPoint(new TrackPoint(new Coordinate(13.7, -6.0), 0));
        track.addTrackPoint(new TrackPoint(new Coordinate(3.67, -42.789), 200));

        assertEquals(13.7, track.findMaximumCoordinate().latitude());
        assertEquals(45.7, track.findMaximumCoordinate().longitude());
    }

    @Test
    void testGetRectangleArea() {
        track.addTrackPoint(new TrackPoint(new Coordinate(12.5, 34.89), 123));
        track.addTrackPoint(new TrackPoint(new Coordinate(-12.5, 45.7), 124));
        track.addTrackPoint(new TrackPoint(new Coordinate(13.7, -6.0), 0));
        track.addTrackPoint(new TrackPoint(new Coordinate(3.67, -42.789), 200));

        assertEquals(2318.4118, track.getRectangleArea());
    }
}