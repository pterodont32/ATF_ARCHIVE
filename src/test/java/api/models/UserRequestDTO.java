package api.models;


// TODO  de explicat amanunt pagina
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder // @Builder: Activează un builder pattern, facilitând instanțierea obiectelor în mod flexibil și clar.
@Data //@Data: Este o adnotare care combină mai multe adnotări Lombok, generând metode getter și setter pentru toate proprietățile, plus toString, equals, hashCode, etc.
@AllArgsConstructor // Generează un constructor care acceptă toți parametrii specificați în clasă.
@NoArgsConstructor // Creează un constructor fără parametri.
public class UserRequestDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private int age;

    @JsonProperty("id")
    private String id;
}
