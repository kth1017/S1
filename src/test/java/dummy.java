import org.junit.jupiter.api.Test;

public class dummy {
    @Test
    public void 더미데이터() {
        for (int i = 1; i <= 151; ++i) {
            System.out.println("insert into POSTS (AUTHOR, CONTENT, TITLE) values ("+i+","+i+","+i+");");
        }
    }
}
