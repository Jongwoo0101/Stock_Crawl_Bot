import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args){

        String URL = "https://finance.naver.com/item/main.nhn?code=005930";
        String[] data = {"주가 :", "등락률 :", "시가 :", "고가 :", "저가 :", "거래량 :", "타입 :", "전일대비 :", "가져오는 시간 :"};
        Document doc;

        try {//예외가 발생할 수 있는 모든 매소드들은 try & catch 사용
            doc = Jsoup.connect(URL).get();
            Elements elem = doc.select(".date");
            String[] str = elem.text().split(" ");

            Elements todayList =doc.select(".new_totalinfo dl>dd");

            String price = todayList.get(3).text().split(" ")[1];
            String Fluctuation_Rate = todayList.get(3).text().split(" ")[6];
            String openingPrice =  todayList.get(5).text().split(" ")[1];
            String highPrice = todayList.get(6).text().split(" ")[1];
            String lowPrice = todayList.get(8).text().split(" ")[1];
            String trading_Volume = todayList.get(10).text().split(" ")[1];

            String s_Type = todayList.get(3).text().split(" ")[3]; //상한가,상승,보합,하한가,하락 구분

            String vs_Yesterday = todayList.get(3).text().split(" ")[4];

            System.out.println("삼성전자 주가------------------");
            System.out.println(data[0]+price);
            System.out.println(data[1]+Fluctuation_Rate);
            System.out.println(data[2]+openingPrice);
            System.out.println(data[3]+highPrice);
            System.out.println(data[4]+lowPrice);
            System.out.println(data[5]+trading_Volume);
            System.out.println(data[6]+s_Type);
            System.out.println(data[7]+vs_Yesterday);
            if(Fluctuation_Rate.contains("1") && vs_Yesterday.contains("상승")) {
                System.out.println("WJW PICK :"+"매수 타이밍 입니다.");
            }
            else{
                System.out.println("WJW PICK :"+"아직은 매수하지 마세요");
            }
            System.out.println(data[8]+str[0]+str[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}