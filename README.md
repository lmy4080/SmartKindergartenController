# SmartKindergartenController

<div>
  <img width="300" hspace="20" src="https://user-images.githubusercontent.com/42701193/69752477-9f14e480-1194-11ea-9018-5089795e0f88.png">
  <img width="300" src="https://user-images.githubusercontent.com/42701193/69752489-a76d1f80-1194-11ea-95bd-ddaaaf0ba993.png">
</div>

#### 프로젝트 설명(Project Description)

- 유치원 내부에 부착된 IoT 센서들을 사용자 모바일 어플리케이션을 통해 원격으로 제어 및 관리하는 시스템

#### 프로젝트 구성(Project Composition)

- Android app(Smart Kindergarten Controller), Lollipop v22
- Raspberry Pi(Smart Kindergarten/IoT Sensors), v2 B+ Model
  [link](https://github.com/lmy4080/SmartKindergarten)
- Cloud Mqtt Server [link](mqtt.eclipse.org)
  
#### SmartKindergartenController App 기능(SmartKindergartenController App Features)

 - 온도를 IoT 디바이스 센서로부터 전달받아 화면에 실시간 표시 및 갱신
 - 습도를 IoT 디바이스 센서로부터 전달받아 화면에 실시간 표시 및 갱신
 - Light 버튼 클릭 시 IoT 디바이스 LED 소등 및 점등
 - Curtains 버튼 클릭 시 IoT 디바이스 Motor 센서 동작
 - CCTV 버튼 클릭 시 SmartKindergarten의 내부 실시간 감시 사이트로 이동
 - SmartKindergarten 주차장의 상태 정보를 화면에 실시간 표시 및 갱신
 
#### SmartKindergartenController App 활용 기술 스택(SmartKindergartenController Used-Technical Stack Features)

 - MVP Design Pattern
 - Paho Mqtt Library(IoT Communication Protocol)
 
#### SmartKindergartenController App 시퀀스 다이어그램(SmartKindergartenController Sequence Diagram)

<div>
  <img width="700" src="https://user-images.githubusercontent.com/42701193/69820056-962f1c00-1243-11ea-8325-a8a00c978661.JPG">
</div>
