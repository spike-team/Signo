package kim.jaehoon.studentable.signo.domain.payload;

import kr.go.neis.api.SchoolMenu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private List<String> breakfast;
    private List<String> lunch;
    private List<String> dinner;

    public Menu fromSchoolMenu(SchoolMenu schoolMenu) {

        this.breakfast = toList(schoolMenu.breakfast
                .replaceAll("[0-9.&amp]", "")
                .replaceAll(";", "/"));
        this.lunch = toList(schoolMenu.lunch
                .replaceAll("[0-9.&amp]", "")
                .replaceAll(";", "/"));
        this.dinner = toList(schoolMenu.dinner
                .replaceAll("[0-9.&amp]", "")
                .replaceAll(";", "/"));

        return this;
    }

    private List<String> toList(String menu) {
        return new ArrayList<>(Arrays.asList(menu.split("\n")));
    }
}
