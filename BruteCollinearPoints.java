import java.util.ArrayList;
import java.util.List;

public class BruteCollinearPoints {
    private int segments;
    private LineSegment[] lineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) throws Exception {
        segments = 0;
        lineSegments = new ArrayList<>();
        List<Point> auxPoints = new ArrayList<>();

        if (points == null ){
            throw new IllegalArgumentException();
        }

        for (Point point : points) {
            if (point == null){
                throw new IllegalArgumentException();
            }
            else if(auxPoints != null && auxPoints.contains(point)) {
                throw new IllegalArgumentException();
            } else {
                auxPoints.add(point);
            }

        }

    }
    // the number of line segments
    public int numberOfSegments()        {
        return segments;
    }
    public LineSegment[] segments() {
        return lineSegments;
    }
}
