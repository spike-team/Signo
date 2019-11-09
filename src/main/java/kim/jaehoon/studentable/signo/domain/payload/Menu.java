package kim.jaehoon.studentable.signo.domain.payload;

import kr.go.neis.api.SchoolMenu;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class Menu {

    private List<String> breakfast;
    private List<String> lunch;
    private List<String> dinner;

    public Menu(String breakfast, String lunch, String dinner) {
        this.breakfast = toList(breakfast
                .replaceAll("[0-9.&amp*]", "")
                .replaceAll(";", "/"));
        this.lunch = toList(lunch
                .replaceAll("[0-9.&amp*]", "")
                .replaceAll(";", "/"));
        this.dinner = toList(dinner
                .replaceAll("[0-9.&amp*]", "")
                .replaceAll(";", "/"));

    }

    private List<String> toList(String menu) {
        return menu.equals("") ? List.of("급식이 없습니다.") : List.of(menu.split("\n"));
    }

}
