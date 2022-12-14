package activity;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActivitiesTest {
    Track track = new Track();

    Activities activities = new Activities(Arrays.asList(
            new ActivityWithTrack(track, ActivityType.RUNNING),
            new ActivityWithoutTrack(ActivityType.BASKETBALL),
            new ActivityWithTrack(track, ActivityType.RUNNING)));

    @Test
    void testNumberOfWithTrack() {

        assertEquals(2, activities.numberOfTrackActivities());
    }

    @Test
    void testNumberOfWithoutTrack() {
        assertEquals(1, activities.numberOfWithoutTrackActivities());
    }

    @Test
    void testGetReportType() {
        track.addTrackPoint(new TrackPoint(new Coordinate(12.5, 34.89), 123));
        track.addTrackPoint(new TrackPoint(new Coordinate(-12.5, 45.7), 124));
        track.addTrackPoint(new TrackPoint(new Coordinate(13.7, -6.0), 0));
        track.addTrackPoint(new TrackPoint(new Coordinate(3.67, -42.789), 200));

        List<Report> result = activities.distancesByTypes();

        assertEquals(0.0, result.get(1).distance());

        assertEquals(0.0, result.get(0).distance());

        assertEquals(ActivityType.RUNNING, result.get(2).activityType());
        assertEquals(27223158, result.get(2).distance(), 5);

        assertEquals(ActivityType.BASKETBALL, result.get(3).activityType());
    }
}