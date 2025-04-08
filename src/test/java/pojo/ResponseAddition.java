package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResponseAddition {
    private int id;
    @JsonProperty("additional_info")
    private String additionalInfo;
    @JsonProperty("additional_number")
    private int additionalNumber;
}
