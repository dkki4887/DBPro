package view;

import persistence.dto.MenuDTO;

import java.util.List;

public class MenuView {

    public List<MenuDTO> printStoreAllMenu(List<MenuDTO> dtos)
    {
        int i = 0;
        System.out.println("======================================================");
        for(MenuDTO dto: dtos) {
            System.out.println((i + 1) + ". " + dto.getMenu_name() + " | " + dto.getMenu_price() + "원");
            i++;
        }
        System.out.println("======================================================");
        return dtos;
    }
}
