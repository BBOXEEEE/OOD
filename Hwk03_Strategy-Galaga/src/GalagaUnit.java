import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기
 * @author 김상진 
 * @File GalagaUnit.java 
 * 픽셀 아트를 이용하여 나타내는 모든 유니트를 위한 추상 클래스
 */
public abstract class GalagaUnit extends Group {
	private int[][] imageMap;
	private int[][] auxMap;
	protected int x = 0;
	protected int y = 0;
	protected Set<Missile> oppositeMissiles;
	protected Consumer<Missile> explodeAction;
	
	public GalagaUnit(int[][] bitmap) {
		setImageMap(bitmap);
		constructNodes();
	}
	
	private void constructNodes() {
		if(getChildren().isEmpty()) {
			for(int r = 0; r < imageMap.length; ++r) {
				for(int c = 0; c < imageMap[r].length; ++c) {
					Rectangle box = new Rectangle(
						x + GalagaUtility.PIXELSIZE * c,
						y + GalagaUtility.PIXELSIZE * r,
						GalagaUtility.PIXELSIZE, GalagaUtility.PIXELSIZE);
					getChildren().add(box);
				}
			}
		}
	}
	
	protected void setImageMap(int[][] bitmap) {
		imageMap = Objects.requireNonNull(bitmap);
	}
	
	protected void setAuxMap(int[][] bitmap) {
		auxMap = bitmap;
	}
	
	public void setOppositeMissiles(Set<Missile> oppositeMissiles) {
		this.oppositeMissiles = oppositeMissiles;
	}
	
	public void setOnExplode(Consumer<Missile> explodeAction) {
		this.explodeAction = explodeAction;
	}
	
	public Set<Missile> getOppositeMissiles(){
		return oppositeMissiles;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void build() {
		for(var node: getChildren()) {
			Rectangle box = (Rectangle)node;
			int c = (int)(box.getX() - x) / GalagaUtility.PIXELSIZE;
			int r = (int)(box.getY() - y) / GalagaUtility.PIXELSIZE;
			box.setFill(GalagaUtility.colorMap.get(imageMap[r][c]));
		}
	}
	
	public void toggleMap() {
		if(auxMap != null) {
			int[][] tmp = imageMap;
			imageMap = auxMap;
			auxMap = tmp;
			build();
		}
	}
	
	public void moveTo(int destX, int destY) {
		int deltaX = destX - x;
		int deltaY = destY - y;
		x = destX;
		y = destY;
		for(var node: getChildren()) {
			Rectangle box = (Rectangle)node;
			box.setX(box.getX() + deltaX);
			box.setY(box.getY() + deltaY);
		}
	}
	
	public int getWidth() {
		return imageMap[0].length * GalagaUtility.PIXELSIZE;
	}
	
	public int getHeight() {
		return imageMap.length * GalagaUtility.PIXELSIZE;
	}
	
	public abstract void explode(Missile missile);
	public abstract void stopMovement();

}
