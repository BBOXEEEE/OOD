import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.AudioClip;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * mp3 파일을 재생해 주는 클래스
 */
public class Sound {
	public static String dir;
    static {
        dir = "file:///" + System.getProperty("user.dir").replace('\\', '/').replaceAll(" ", "%20");
        try {
            dir = new java.net.URI(dir).toString();
        } 
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
		
	private static Map<String, AudioClip> soundBox = new HashMap<>();
	static{
		soundBox.put("alienDestroyed", new AudioClip(dir + "/alien_destroyed.mp3"));
		soundBox.put("alienFlying", new AudioClip(dir + "/alien_flying.mp3"));
		soundBox.put("alienMissile", new AudioClip(dir + "/alien_missile.mp3"));
		soundBox.put("userShipDestroyed", new AudioClip(dir + "/fighter_destroyed.mp3"));
		soundBox.put("userShipMissile", new AudioClip(dir + "/fighter_missile.mp3"));
	}
	
	public static void play(String key){	
		AudioClip clip = soundBox.get(key);
		if(clip != null){
			clip.play();
		}
	}
	
	public static void stop(String key){
		AudioClip clip = soundBox.get(key);
		if(clip != null){
			clip.stop();
		}
	}
}
