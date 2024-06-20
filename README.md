
# 영화 예매 관리 프로그램 만들기 JAVA _ DAY1 

## Class 
* 1.Main : 프로그램 시작, Menu 인터페이스를 통해 MainMenu를 띄워줌
* 2.AbstractMenu : menuText,prevMenu 정의, Scanner 정의 Menu를 implements 함
* 3.MainMenu : 기본페이지, 관리자페이지, 종료 버튼 만들기 (Scanner,switch문 활용)
* 4.AdminMenu :영화 등록,목록보기,목록삭제,메인메뉴로가기,종료 버튼 만들기 (Scanner,switch문 활용)<br>
  return null : 프로그램 종료 ,<br>
  return this : AdminMenu의 메뉴들 보여주기<br>
  default : return this 사용<br>
  오류 :<br>
  코드를 똑같이 따라썼는데 Scanner가 Enter인식을 이상하게 하는것인지, 한번에 2개씩 입력되는? 오류가 있어서
  trim()메소드 사용, while문을 이용해서
  ```
    String title = scanner.nextLine().trim();
    while(title.isEmpty()){
    title = scanner.nextLine().trim();}
  ```
  title이 비어있으면 scanner가 작동하게 해야 정상적으로 작동되었다.<br>
  trim()메소드를 잘 활용해야 한다는것을 배운듯.
* 5.Movie : 관리자페이지의 기능 만드는 클래스
  영화코드(id),제목,장르 를 txt파일로 저장하고<br>
  FileReader를 활용해 불러오고<br>
  BufferedReader로 받은값을 String 배열<br>
  temp[0] : id / temp[1] : title / temp[2] : genre 식으로 저장할수있었다.<br>
  또한 while문에서 if~() {continue;} 가 해당 조건을 무시하는 식이라는 것을 처음 알았다.<br>
  잘 정리된 객체들을 FileWriter 를 통해 다시 txt로 저장할수 있었다.<br>


# 영화 예매 관리 프로그램 만들기 JAVA _ DAY2

## Class
*6. Seats : int 상수를 이용해서 좌석 행,열 정의<br>
public seats(ArrayList<Reservation> reservations) throws Exception{}
<br> 안에 2중 for문 활용, map[i][j]로 초기 좌석상태 설정.<br>
show() : 좌석배열 보여주기<br>
mark(String seatName) : 좌석번호 확인 후 예매가능여부 반환<br>
*7. Reservation : <br>
findById(String resevationId) : BufferedReader와 FileReader를 이용해 reservation.txt 파일을 불러온 후 <br>
예매번호,영화번호,영화제목,좌석번호 를 배열로 저장 후 예약하는 메소드 생성<br>
<br>
```
toString() {
return String.format("영화 :%s, 좌석 :%s", movieTitle, seatName);}
```
해당 영화,좌석번호 출력을 위한 toString 재정의 <br>
FindByMovieId역시 비슷한 방법으로 파일을 불러온 후, 클라이언트에게 예매가능한 영화내역을 출력.<br>
save() : FileWriter 를 통해서 필드에 저장된 값 txt파일로 저장,
<br>new File Writer(file, true) // true:이어쓰기 모드 (기존 데이터에 이어서 추가로 쓰는방식)<br>

