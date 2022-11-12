package view;

import persistence.dto.MenuDTO;

import java.util.List;

public class MenuView {

    public void printAllStore(List<MenuDTO> dtos)
    {
        int i = 0;
        for(MenuDTO dto: dtos) {
            System.out.println((i + 1) + ". " + dto.getMenu_name());
            i++;
        }
    }
}
