import java.util.concurrent.ThreadLocalRandom;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 2019136056 박세현 
 * 팩토리 메소드 패턴: Asteroid
 * AsteroidPolygonFactory.java: 팩토리 메소드 패턴에서 다각형으로 행성을 표
 */
public class AsteroidPolygonFactory extends AsteroidFactory {
	public static final int DEGREE_IN_CIRCLE = 360;
	
	@Override
	protected Double[] createPoints(Location centerLoc, int radius) {
		int numVertices = 10 + ThreadLocalRandom.current().nextInt(6);
		Double[] points = new Double[numVertices * 2];
		double x = 0, y = 0;
		double angle = DEGREE_IN_CIRCLE / numVertices;
		
		for (int i=0; i<numVertices; ++i) {
			x = Math.cos(Math.toRadians(angle * i)) * (radius + ThreadLocalRandom.current().nextInt(radius));
			y = Math.sin(Math.toRadians(angle * i)) * (radius + ThreadLocalRandom.current().nextInt(radius));
			points[i*2] = Double.valueOf(centerLoc.x() + x);
			points[i*2+1] = Double.valueOf(centerLoc.y() + y);
		}
		
		return points;
	}
}
