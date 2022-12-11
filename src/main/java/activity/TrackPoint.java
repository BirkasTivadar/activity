package activity;

public record TrackPoint(Coordinate coordinate, double elevation) {

//    public double getDistanceFrom(TrackPoint otherTrackPoint) {
//        double latDistance = Math.toRadians(coordinate.latitude() - otherTrackPoint.getCoordinate().latitude());
//        double longDistance = Math.toRadians(coordinate.longitude() - otherTrackPoint.getCoordinate().longitude());
//        double a = Math.pow(Math.sin(latDistance / 2), 2) +
//                Math.cos(Math.toRadians(coordinate.latitude())) * Math.cos(Math.toRadians(otherTrackPoint.getCoordinate().latitude())) *
//                        Math.pow(Math.sin(longDistance / 2), 2);
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//        double distance = 6371 * c * 1000;
//        double height = elevation - otherTrackPoint.getElevation();
//        distance = Math.pow(distance, 2) + Math.pow(height, 2);
//        return Math.sqrt(distance);
//    }

    public double getDistanceFrom(TrackPoint otherPoint) {
        return getDistance(coordinate.latitude(), coordinate.longitude(), otherPoint.coordinate().latitude(), otherPoint.coordinate().longitude());
    }

    private double getDistance(double latitude, double longitude, double otherLatitude, double otherLongitude) {
        double theta = longitude - otherLongitude;
        double dist = Math.sin(deg2rad(latitude)) * Math.sin(deg2rad(otherLatitude)) + Math.cos(deg2rad(latitude)) * Math.cos(deg2rad(otherLatitude)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist *= 111194.9265;
        return dist;
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
