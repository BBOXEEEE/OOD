import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진
 * 0, 1, 2, 3을 이용하여 구성된 격자 맵이 주어짐
 * 0: 통로
 * 1: 시작위치
 * 2: 보석
 * 3: 벽
 * 목적: 시작 위치에서 보석까지의 최단 경로 찾기 
 * 이동은 상하좌우로만 할 수 있음
 * 맵에는 항상 보석이 하나 주어짐
 * 보석을 찾을 수 없으면 최단 경로의 길이는 -1을 출력해야 함
 * 주어진 해결책에서 코드 냄새를 찾아 리펙토링하세요.
 * 요구사항. 주석에 다음을 포함하여 주세요.
 * 1) 리펙토링한 순서
 * 2) 각 코드 냄새를 제거하기 위해 리펙토링한 방법
 * 강의 슬라이드에 제시된 코드 냄새와 정확한 매칭이 되지 않지만
 * 코드를 개선할 수 있는 부분을 발견하여 개선하면 그것도
 * 제시한 순서에 포함해야 함
 */

public class CodeSmell {
	
	// 시작 위치를 찾는 메소드를 추가한 데이터 클래스 Point
	public static class Point{
		private int row;
		private int col;
		private int length;
		
		public Point(int row, int col, int length) {
			this.row = row;
			this.col = col;
			this.length = length;
		}
		
		public Point(int row, int col) {
			this(row, col, 0);
		}
		
		public Point findStart(int[][] map) {
			for(int r = 0; r < map.length; ++r)
				for(int c = 0; c < map[0].length; ++c) {
					if(map[r][c] == 1) {
						row = r;
						col = c;
						break;
					}
				}
			return this;
		}
	}
	
	// 이동할 셀이 유효한 범위의 셀인지 검사하는 메소드
	public static boolean isValid(int row, int col, int[][] map, boolean[][] visited) {
		return (row >= 0 && row < map.length) && (col >= 0 && col < map[0].length)
				&& !visited[row][col] && map[row][col] != 3;
	}
	
	// BFS를 이용하여 시작 위치부터 보석을 찾는다.
	public static int solve(int[][] map) {
		// 시작 위치 탐색
		Point start = new Point(0,0);
		start.findStart(map);
		
		// 시작 위치부터 BFS 수행
		int minLength = -1;
		boolean[][] visited = new boolean[map.length][map[0].length];
		// 큐를 3개 정의하는 대신 유지해야하는 정보를 하나로 모은 Point 클래스 활용
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start.row][start.col] = true;
		
		// 방향 배열 추가
		int[][] direction = {{-1,0}, {1,0}, {0,-1}, {0,1}};
		while(!queue.isEmpty()) {
			Point curr = queue.poll();
			
			if(map[curr.row][curr.col] == 2) {
				minLength = curr.length;
				break;
			}
			for(int[] dir: direction) {
				Point next = new Point(curr.row+dir[0], curr.col+dir[1], curr.length+1);
				if(isValid(next.row, next.col, map, visited)) {
					queue.add(next);
					visited[next.row][next.col] = true;
				}
			}
		}
		return minLength;
	}

	public static void main(String[] args) {
		int[][] map = {
			{3, 0, 3, 0, 3, 1, 3},
			{3, 0, 0, 0, 3, 0, 3},
			{3, 0, 3, 0, 0, 0, 3},
			{3, 0, 3, 3, 3, 0, 3},
			{3, 0, 0, 2, 3, 0, 0},
			{3, 3, 3, 3, 3, 3, 3}
		};
		System.out.println(solve(map));
		map = new int[][]{
			{3, 3, 3, 0, 3, 0, 3},
			{3, 0, 0, 0, 3, 0, 3},
			{1, 0, 3, 0, 3, 0, 3},
			{3, 0, 3, 3, 3, 0, 3},
			{3, 0, 3, 2, 0, 0, 0},
			{3, 3, 3, 3, 3, 3, 3}
		};
		System.out.println(solve(map));
		map = new int[][]{
			{3, 3, 3, 0, 3, 3, 3},
			{3, 0, 0, 0, 3, 2, 3},
			{1, 0, 3, 0, 0, 0, 3},
			{3, 0, 3, 3, 3, 0, 3}
		};
		System.out.println(solve(map));
	}
}

/**
 * @version 2023학년도 2학기
 * @author 2019136056 박세현
 * Code Smell 리팩토링 순서
 * 1) 데이터 덩어리, 데이터 클래스 문제 해결
 * 		- 반복적으로 사용하는 데이터인 row, col을 데이터 클래스인 Point로 모델링하면서 startR, startC, currR, currC와 같은 변수 이름보다는 start.row와 같은 직관적인 이름으로 가독성을 확보.
 * 		- 데이터 클래스로 모델링할 경우 클래스의 덩치가 작은 문제점과 직결되어 시작 위치를 찾는 행위를 데이터 클래스에 추가해 클래스의 덩치를 키움.
 * 2) 반복적인 조건문의 사용
 * 		- 4가지 방향으로 탐색하는 과정에서 반복적인 조건문이 발생된다.
 *		- 이것을 해결하기 위해 상하좌우를 나타내는 방향 배열을 만들고, 반복문을 돌면서 상하좌우로 탐색하도록 변경하였다.
 * 3) 코드 중복
 * 		- 4가지 방향으로 탐색하는 과정 안에서 각각 불필요한 코드 중복이 발생한다.
 * 		- 첫 번째는 이동할 방향이 보석이라면 최단 경로를 저장하고 탐색을 중지하는 것이다.
 * 		- 두 번째는 이동할 방향이 통로라면 방문한 경로에 추가하는 것이다.
 * 		- 이것을 2번 리팩토링 과정과 함께 4번의 코드중복을 1번으로 줄일 수 있다.
 * 4) 임시 변수
 * 		- 다음 탐색할 방향이 격자 맵의 영역에 벗어나지 않는지, 방문한 셀이 아닌지 여부를 조건문에서 검사한다.
 * 		- 이러한 경우 임시 변수를 통해 조건문 내 비교하는 부분을 깔끔하게 줄일 수 있지만, 임시 변수는 코드 냄새에 해당하므로 를 사용하지 않을 수 있다.
 * 		- 방법은 isValid라는 별도의 메소드를 만들어 이 메소드를 활용하는 것이다.
 * 5) Point 클래스에 길이 정보를 추가로 유지
 * 		- 4번까지 진행한 과정에서 3가지 큐를 사용해야하는 의문점이 들었다.
 * 		- 이것을 해결하기 위해 길이 정보를 Point 클래스에 추가로 유지하고, 생성자를 중복 정의했다.
 * 		- 또한, 큐의 자료형을 Point로 선언하였다.
 * 		- 결과적으로 중복된 큐를 사용하지 않을 수 있고, 좌표 및 최단 경로를 하나의 클래스로 유지해 불필요한 큐의 연산을 줄일 수 있다.
 */
