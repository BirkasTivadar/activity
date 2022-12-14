package activity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivityWithoutTrackTest {
    ActivityWithoutTrack activityWithoutTrack = new ActivityWithoutTrack(ActivityType.BASKETBALL);

    @Test
    void testGetDistance() {
        assertEquals(0.0, activityWithoutTrack.getDistance());
    }

    @Test
    void testGetType() {
        assertEquals(ActivityType.BASKETBALL, activityWithoutTrack.getType());
    }
}