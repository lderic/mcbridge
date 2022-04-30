import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Date now = new Date();
        String str = String.format("ABAB%tF", now); // 2014-10-12
        System.out.println(str);
    }
}
