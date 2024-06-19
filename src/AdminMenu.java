import java.io.IOException;
import java.util.ArrayList;

public class AdminMenu extends AbstractMenu{
    private static final AdminMenu instance = new AdminMenu(null);
    private static final String ADMIN_MENU_TEXT = // 기본문구
            "1: 영화 등록하기\n" +
            "2: 영화 목록보기\n" +
            "3: 영화 삭제하기\n" +
            "b: 메인 메뉴로 이동\n" +
            "q: 종료\n\n" +
            "메뉴를 선택하세요 : ";

    private AdminMenu(Menu prevMenu){
        super(ADMIN_MENU_TEXT, prevMenu); // 부모 생성자 호출
    }

    public static AdminMenu getInstance() {
        return instance; // 메뉴 객체 반환
    }

    @Override
    public Menu next() {
        switch (scanner.next()){
            case "1" :
                createMovie(); // 영화 등록 진행
                return this; // 관리자 메뉴 객체 반환
            case "2" : printAllMovies();
            return this;
            case "3" :
                deleteMovie();
                return this;

            case "b" : return prevMenu;

            case"q" : return null;
            default : return this;

        }
    }
    private void printAllMovies() {
        try{
            ArrayList<Movie> movies = Movie.findAll();
            System.out.println();
            for(int i=0; i<movies.size();i++){
                System.out.printf("%s\n", movies.get(i).toString());
            }
        }catch (IOException e) {
            System.out.println("데이터 접근에 실패하였습니다.");
            e.printStackTrace();
        }
    }
    private void createMovie() {
        System.out.println("제목을 입력해주세요:");
        String title = scanner.nextLine().trim();
        while(title.isEmpty()){
            title = scanner.nextLine().trim();
        }
        System.out.println("장르를 입력해주세요 : ");
        String genre = scanner.nextLine().trim();
        while(genre.isEmpty()){
            System.out.println("장르를 입력해주세요 : ");
            genre = scanner.nextLine().trim();
        }
        Movie movie = new Movie(title, genre);
        try{
            movie.save(); // 영화 객체 저장 메소드
            System.out.println("정상적으로 등록되었습니다.");
        }catch (IOException e) {
            System.out.println("영화 등록에 실패했습니다.");
            e.printStackTrace();
        }
    }

    private void deleteMovie(){
        printAllMovies(); // 영화 목록 출력
        System.out.print("삭제할 영화를 선택하세요 : ");
        String movieName = scanner.nextLine().trim();
        while (movieName.isEmpty()){
           // System.out.print("삭제할 영화를 선택하세요 : ");
            movieName = scanner.nextLine().trim();
        }
        try{
            Movie.delete(movieName);
            System.out.println(movieName+" 이 삭제되었습니다.");
        } catch (IOException e) {
            System.out.println("삭제에 실패하였습니다.");
        }
    }

}
