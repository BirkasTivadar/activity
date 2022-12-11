package activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Track {

    private final List<TrackPoint> trackPoints = new ArrayList<>();

    public void addTrackPoint(TrackPoint trackPoint) {
        trackPoints.add(trackPoint);
    }

    public List<TrackPoint> getTrackPoints() {
        return new ArrayList<>(trackPoints);
    }

    public double getFullElevation() {
        double sumElevation = 0;
        for (int i = 1; i < trackPoints.size(); i++) {
            double actualElevation = trackPoints.get(i).elevation();
            double previousElevation = trackPoints.get(i - 1).elevation();

            if (actualElevation > previousElevation) {
                sumElevation += actualElevation - previousElevation;
            }
        }
        return sumElevation;
    }

    public double getFullDecrease() {
        double sumDecrease = 0;
        for (int i = 1; i < trackPoints.size(); i++) {
            double actualElevation = trackPoints.get(i).elevation();
            double previousElevation = trackPoints.get(i - 1).elevation();

            if (actualElevation < previousElevation) {
                sumDecrease += previousElevation - actualElevation;
            }
        }
        return sumDecrease;
    }

    public double getDistance() {
        double sumDistance = 0;
        for (int i = 1; i < trackPoints.size(); i++) {
            sumDistance += trackPoints.get(i).getDistanceFrom(trackPoints.get(i - 1));
        }
        return sumDistance;
    }

    public Coordinate findMinimumCoordinate() {
        double minLatitude = trackPoints.stream()
                .mapToDouble(trackPoint -> trackPoint.coordinate().latitude())
                .min()
                .orElseThrow(() -> new IllegalArgumentException("There is no one trackpoint."));
        double minLongitude = trackPoints.stream()
                .mapToDouble(trackPoint -> trackPoint.coordinate().longitude())
                .min()
                .orElseThrow(() -> new IllegalArgumentException("There is no one trackpoint."));
        return new Coordinate(minLatitude, minLongitude);
    }

    public Coordinate findMaximumCoordinate() {
        double maxLatitude = trackPoints.stream()
                .mapToDouble(trackPoint -> trackPoint.coordinate().latitude())
                .max()
                .orElseThrow(() -> new IllegalArgumentException("There is no one trackpoint."));
        double maxLongitude = trackPoints.stream()
                .mapToDouble(trackPoint -> trackPoint.coordinate().longitude())
                .max()
                .orElseThrow(() -> new IllegalArgumentException("There is no one trackpoint."));
        return new Coordinate(maxLatitude, maxLongitude);
    }


    public double getRectangleArea() {
        double latitude = findMaximumCoordinate().latitude() - findMinimumCoordinate().latitude();
        double longitude = findMaximumCoordinate().longitude() - findMinimumCoordinate().longitude();
        return latitude * longitude;
    }

    public void loadFromGpx(InputStream is) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                addTrackPointsFromfile(bufferedReader, line);
            }
        } catch (IOException ioException) {
            throw new IllegalStateException("Can not read file", ioException);
        }
    }

    private void addTrackPointsFromfile(BufferedReader bufferedReader, String line) throws IOException {
        if (line.contains("<trkpt ")) {
            String[] strArr = line.split("\"");
            Coordinate coordinate = new Coordinate(Double.parseDouble(strArr[1]), Double.parseDouble(strArr[3]));

            line = bufferedReader.readLine();
            double elevation = getElevation(line);

            trackPoints.add(new TrackPoint(coordinate, elevation));
        }
    }

    private double getElevation(String line) {
        String[] strArr;
        strArr = line.split("<");
        line = strArr[1];
        strArr = line.split(">");
        return Double.parseDouble(strArr[1]);
    }
}
