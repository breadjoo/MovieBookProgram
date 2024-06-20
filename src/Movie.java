import javax.annotation.processing.Filer;
import java.io.*;
import java.time.Instant;
import java.util.ArrayList;

public class Movie {
    private long id;
    private String title;
    private String genre;
    private static final File movieList = new File("movies.txt");

    public Movie(long id, String title, String genre) {
        this.id = id;
        this.title = title;
        this.genre = genre;
    }
    public static ArrayList<Movie> findAll() throws IOException{
        ArrayList<Movie> movies = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(movieList));
        String line = null;

        while((line = br.readLine()) !=null) { // 파일을 한 행씩 읽어와 반복
            String[] temp = line.split(","); //메모장에 있는 내용을 , 를 기준으로 나눔
            Movie m = new Movie(
                    Long.parseLong(temp[0]), // 영화 코드
                    temp[1], //영화 제목
                    temp[2]  //영화 장르
            );
            movies.add(m); // 영화 객체를 생성해서 ArrayList에 추가
        }
        br.close();
        return movies; // 영화 객체가 담긴 ArrayList 반환
    }
    public String toString() {
        return String.format("[%d]:%s(%s)", id, title, genre);
    }

    public Movie(String title, String genre){
        this.id = Instant.now().getEpochSecond(); //
        this.title = title;
        this.genre = genre;
    }
    public void save() throws IOException{
        FileWriter fw = new FileWriter(movieList, true);
        fw.write(this.toFileString() + "\n");
        fw.close();
    }
    private String toFileString() {
        return String.format("%d,%s,%s", id, title, genre);
    }
    public static void delete(String movieIdStr) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(movieList));
        String text = "";
        String line = null;
        //영화제목으로 삭제하기
        while((line = br.readLine())!=null){
            String[] temp = line.split(",");
            if(movieIdStr.equals(temp[1])){
                continue;
            }
            text += line + "\n";
        }
        /* id로 삭제하기
        while((line = br.readLine())!=null) {
         String[] temp = line.split(",");
         if(movieIdStr.equals(temp[0])){
             continue;
         }
         text += line + "\n"; //읽은 문자열을 누적하여 복사
        }*/
        br.close();

        FileWriter fw = new FileWriter(movieList);
        fw.write(text);
        fw.flush();
        fw.close();
    }
    public static Movie findById(String movieIdStr) throws IOException{
        Movie movie = null;
        BufferedReader br = new BufferedReader(new FileReader(movieList));
        String line = null;
        while((line=br.readLine())!=null){
            String[] temp = line.split(",");
            if(movieIdStr.equals(temp[0])){
                movie = new Movie(
                        Long.parseLong(temp[0]),
                        temp[1],
                        temp[2]
                );
                break;
            }
        }
        br.close();
        return movie;
    }
    public String getTitle(){
        return title;
    }
}
