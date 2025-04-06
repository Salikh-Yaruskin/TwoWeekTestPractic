package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Addition {

    @JsonProperty("additional_info")
    @Builder.Default
    private String additionalInfo = "Дополнительные сведения";

    @JsonProperty("additional_number")
    @Builder.Default
    private int additionalNumber = 123;
}
