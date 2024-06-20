import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class MainMenu extends AbstractMenu {
    private static final MainMenu instance = new MainMenu(null);
    private static final String MAIN_MENU_TEXT =
            "1: 영화 예매하기\n" +
            "2: 영화 확인하기\n" +
            "3: 영화 취소하기\n" +
            "4: 관리자 메뉴로 이동\n" +
            "q: 종료 \n\n" +
            "메뉴를 선택하세요";

    private MainMenu(Menu prevMenu){
        super(MAIN_MENU_TEXT, prevMenu);
    }
    public static MainMenu getInstance(){
        return instance;
    }

    public Menu next(){
        switch (scanner.nextLine()) {
            case "1" : movieReservation();
            return this;
            case "2" : checkReservation();
            return this;
            case "3" : cancelReservation();
            return this;

            case "4" :
                if (! checkAdminPassword()){
                    System.out.println("비밀번호가 일치하지 않습니다.");
                    return this;
                }
                AdminMenu adminMenu = AdminMenu.getInstance();
                adminMenu.setPrevMenu(this);
                return adminMenu;
            case "q" : return null;
            default: return this;
        }
    }
    private boolean checkAdminPassword() {
        System.out.println("관리자 비밀번호를 입력하세요 : ");
        return "1234".equals(scanner.nextLine());
    }
    private void movieReservation(){
        try{
            ArrayList<Movie> movies = Movie.findAll();
            for(int i=0; i<movies.size(); i++){
                System.out.printf("%s\n", movies.get(i).toString());
            }
            System.out.println("예매할 영화를 선택하세요 : ");
            String movieIdStr = scanner.nextLine();
            Movie m = Movie.findById(movieIdStr);
            ArrayList<Reservation> reservations = Reservation.findByMovieId(movieIdStr);
            Seats seats = new Seats(reservations);
            seats.show(); //예매 영화의 좌석 현황 출력
            System.out.println("좌석을 선택하세요(ex:E-9) : ");
            String seatName = scanner.nextLine();
            seats.mark(seatName);
            Reservation r = new Reservation(
                    Long.parseLong(movieIdStr),
                    m.getTitle(),
                    seatName
                    );
            r.save();
            System.out.println("예매가 완료되었습니다.");
            System.out.printf("발급번호 %d\n",r.getId());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            System.out.printf("예매에 실패 : %s\n", e.getMessage());
        }
    }
    private void checkReservation(){
        System.out.println("예매 확인할 발급번호를 입력하세요 : ");
        try{
            Reservation r = Reservation.findById(scanner.nextLine());
                if(r!= null){
                    System.out.printf("[확인 완료] \n%s\n", r.toString());
                }else {
                    System.out.println("예매 내역이 없습니다.");
                }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("파일 입출력에 문제가 생겼습니다.");
        }
    }

    private void cancelReservation() {
        System.out.println("예매 취소할 발급번호를 입력하세요 : ");
        try{
            Reservation canceled = Reservation.cancel(scanner.nextLine());
            if(canceled != null) {
                System.out.printf("[취소 완료] \n%s의 예매가 취소되었습니다.\n",
                        canceled.toString());
            } else{
                System.out.println("예매 내역이 없습니다.");
            }
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("파일 입출력에 문제가 생겼습니다.");
        }
    }

}
