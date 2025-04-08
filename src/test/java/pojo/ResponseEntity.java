package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResponseEntity {

    private String id;
    private String title;
    private boolean verified;
    private ResponseAddition addition;
    @JsonProperty("important_numbers")
    private List<Integer> importantNumbers;
}
