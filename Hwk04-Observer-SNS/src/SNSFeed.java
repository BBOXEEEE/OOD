import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file SNSFeed.java
 * SNS 서비스에서 게시글 정보
 */
public record SNSFeed(
	int posterID,			// 게시자 ID
	String posterName,		// 게시자 이름
	String content,			// 게시글
	String imageFileName,	// 첨부된 image (null이면 첨부된 image가 없는 경우)
	LocalDateTime postTime){	// 게시 시간
	
	public SNSFeed{
		Objects.requireNonNull(posterName);
		Objects.requireNonNull(content);
		Objects.requireNonNull(postTime);
	}
}

