package pracs1.springboot.seach;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {
    private String keyword;
    private String type;
    private int pageNum= 1;

}
