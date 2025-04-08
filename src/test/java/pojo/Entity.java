package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class Entity {

    @Builder.Default
    private String title = "Заголовок сущности";

    @Builder.Default
    private boolean verified = true;

    @Builder.Default
    private Addition addition = new Addition("Заголовок сущности", 123);

    @JsonProperty("important_numbers")
    @Builder.Default
    private List<Integer> importantNumbers = Arrays.asList(42, 87, 15);
}
