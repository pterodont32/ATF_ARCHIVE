package api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;


@Builder // @Builder: Activează un builder pattern, facilitând instanțierea obiectelor în mod flexibil și clar.
@Data //@Data: Este o adnotare care combină mai multe adnotări Lombok, generând metode getter și setter pentru toate proprietățile, plus toString, equals, hashCode, etc.

public class UserRequestDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private int age;

    @JsonProperty("id")
    private String id;
}
