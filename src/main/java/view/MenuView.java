package view;

import persistence.dto.MenuDTO;

import java.util.List;

public class MenuView {

    public void printStoreAllMenu(List<MenuDTO> dtos)
    {
        int i = 0;
        for(MenuDTO dto: dtos) {
            System.out.println((i + 1) + ". " + dto.getMenu_name() + "  " + dto.getMenu_price() + "원");
            i++;
        }
    }

    public int selectMenu(List<MenuDTO> dtos, int selectNum)
    {
        if(dtos.size() < selectNum || selectNum < 1)
        {
            System.out.println("잘못된 메뉴번호 선택입니다.");
            return -1;
        }

        if(dtos.get(selectNum - 1).getMenu_quantity() == 0)
        {
            System.out.println(dtos.get(selectNum - 1).getMenu_quantity());
            System.out.println("선택하신 메뉴의 수량이 소진되어 주문이 불가합니다.");
            return -1;
        }
        return dtos.get(selectNum - 1).getMenu_id();
    }

    public long getMenuPrice(List<MenuDTO> dtos)
    {
        return dtos.get(0).getMenu_price();
    }
}
