package activity;

import java.util.ArrayList;
import java.util.List;

public class Activities {

    private final List<Activity> activities;

    public Activities(List<Activity> activities) {
        this.activities = new ArrayList<>(activities);
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public long numberOfTrackActivities() {
        return activities.stream()
                .filter(activity -> activity.getType().isHasTrack())
                .count();
    }

    public long numberOfWithoutTrackActivities() {
        return activities.stream()
                .filter(activity -> !activity.getType().isHasTrack())
                .count();
    }

    public List<Report> distancesByTypes() {
        List<Report> result = new ArrayList<>();
        for (ActivityType activityType : ActivityType.values()) {
            if (activityType.isHasTrack()) {
                double distance = activities.stream()
                        .filter(activity -> activity.getType() == activityType)
                        .mapToDouble(Activity::getDistance)
                        .sum();
                result.add(new Report(activityType, distance));
            } else {
                result.add(new Report(activityType, 0));
            }
        }
        return result;
    }
}
