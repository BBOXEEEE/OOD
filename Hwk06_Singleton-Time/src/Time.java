import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 2019136056 박세현 
 * Singleton
 * 객체 풀 실습 - Time
 */
public class Time {
	public final int hour; 
	public final int minute; 
	public final int second;
	private static final Map<Integer, Time> timePool = new HashMap<>();
	
	private Time(int hour, int minute, int second) { 
		this.hour = (hour>=0&&hour<24)? hour: 0;
		this.minute = (minute>=0&&minute<60)? minute: 0;
		this.second = (second>=0&&second<60)? second: 0;
	}
	
	@Override
	public String toString() {
		return String.format("%02d:%02d:%02d", hour, minute, second); 
	}
	
	public static Time of(int hour) { 
		return of(hour, 0, 0);
	}
	
	public static Time of(int hour, int minute) {
		return of(hour, minute, 0);
	}
	
	public static Time of(int hour, int minute, int second) { 
		int key = Arrays.hashCode(new int[] {hour, minute, second});
		if(!timePool.containsKey(key)) {
			timePool.put(key, new Time(hour, minute, second));
		}
		return timePool.get(key);
	}
}