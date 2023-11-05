/**
 * @copyright 한국기술교육대학교 컴퓨터공학부 객체지향개발론및실습
 * @version 2023년도 2학기 
 * @author 김상진
 * @file Stero.java
 * Head First Design Pattern 예제: 명령 패턴, 만능 리모컨 
 * Stero: 오디오 기기, 명령 패턴에서 receiver 객체
 * 입력: CD, RADIO, USB 제공
 */
public class Stero {
	public enum InputType {CD, RADIO, USB};
	private boolean isOn = false;
	private InputType currentInput = InputType.CD;
	private int volume = 0;
	
	public void on(){
		isOn = true;
		System.out.println("스테레오 전원 켜짐");
	}
	public void off(){
		isOn = false;
		System.out.println("스테레오 전원 꺼짐");
	}
	public boolean isOn() {
		return isOn;
	}
	public InputType getCurrentInput() {
		return currentInput;
	}
	public int getVolume() {
		return volume;
	}
	public void setInput(InputType inputType) {
		if(currentInput != inputType) {
			currentInput = inputType;
			switch(currentInput) {
			case CD: setCD(); break;
			case RADIO: setRadio(); break;
			default: setUSB(); break;
			}
		}
	}
	private void setCD(){
		System.out.println("스테리오 입력 CD로 바꿈");
	}
	private void setRadio(){
		System.out.println("스테리오 입력 라디오로 바꿈");
	}
	private void setUSB(){
		System.out.println("스테리오 입력 USB로 바꿈");
	}
	public void setVolume(int volume){
		this.volume = volume;
		System.out.printf("볼륨 조정 %d%n", volume);
	}
}
