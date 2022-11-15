package service;

import persistence.dao.MyMenuDAO;
import persistence.dao.MyStoreDAO;
import persistence.dto.MenuDTO;
import persistence.dto.StoreDTO;

import java.util.List;
import java.util.Scanner;

public class MenuService
{
    private final MyMenuDAO myMenuDAO;
    private final MyStoreDAO myStoreDAO;

    public MenuService()
    {
        myMenuDAO = new MyMenuDAO();
        myStoreDAO = new MyStoreDAO();
    }

    public List<MenuDTO> findAll()
    {
        List<MenuDTO> all = myMenuDAO.selectAll();
        return all;
    }

    public List<MenuDTO> selectStoreMenu(int store_id)
    {
        List<MenuDTO> menu = myMenuDAO.selectStoreMenu(store_id);
        return menu;
    }

    public int getMenuId(List<MenuDTO> dtos, int selectNum)
    {
        if(dtos.size() < selectNum || selectNum < 1)
        {
            System.out.println("잘못된 메뉴번호 선택입니다.");
            return -1;
        }

        if(dtos.get(selectNum - 1).getMenu_quantity() == 0)
        {
            System.out.println("선택하신 메뉴의 수량이 소진되어 주문이 불가합니다.");
            return -1;
        }
        return dtos.get(selectNum - 1).getMenu_id();
    }

    public void updateMenuQuantity(int menu_id)
    {
        myMenuDAO.updateMenuQuantity(menu_id);
    }

    public long getMenuPrice(List<MenuDTO> dtos, int selectMenuNum)
    {
        return dtos.get(selectMenuNum - 1).getMenu_price();
    }

    public String getMenuName(List<MenuDTO> dtos, int selectMenuNum)
    {
        return dtos.get(selectMenuNum - 1).getMenu_name();
    }

    public void menuAdd(int store_id)
    {
        Scanner sc = new Scanner(System.in);
        MenuDTO addMenuDTO = new MenuDTO();

        int checked_Store_Id = checkStore_id(store_id);
        String menu_name = inputMenu_name(sc);
        long menu_price = inputMenu_price(sc);
        int menu_quantity = inputMenu_quantity(sc);
        String menu_category = inputMenu_category(sc);

        addMenuDTO.setStore_id(checked_Store_Id);
        addMenuDTO.setMenu_name(menu_name);
        addMenuDTO.setMenu_price(menu_price);
        addMenuDTO.setMenu_quantity(menu_quantity);
        addMenuDTO.setMenu_category(menu_category);

        myMenuDAO.menuAdd(addMenuDTO);
    }

    public void menuUpdate(int menu_id, int store_id)
    {
        Scanner sc = new Scanner(System.in);
        MenuDTO addMenuDTO;
        addMenuDTO = new MenuDTO();

        String menu_name = inputMenu_name(sc);
        long menu_price = inputMenu_price(sc);
        int menu_quantity = inputMenu_quantity(sc);
        String menu_category = inputMenu_category(sc);

        addMenuDTO.setStore_id(store_id);
        addMenuDTO.setMenu_id(checkMenu_id(menu_id));
        if(menu_name != "")
            addMenuDTO.setMenu_name(menu_name);
        if(menu_price != -1)
            addMenuDTO.setMenu_price(menu_price);
        if(menu_quantity != -1)
            addMenuDTO.setMenu_quantity(menu_quantity);
        if(menu_category != "")
            addMenuDTO.setMenu_category(menu_category);

        myMenuDAO.menuUpdate(addMenuDTO);
    }

    private int checkStore_id(int store_id)
    {
        List<StoreDTO> storeDTOS = myStoreDAO.selectAllStoreId();
        for(StoreDTO storeDTO: storeDTOS) {
            if (store_id == storeDTO.getStore_id())
                return store_id;
        }

        System.out.println("매장이 존재하지 않음.");
        return store_id;
    }

    private int checkMenu_id(int menu_id)
    {
        List<MenuDTO> menuDTOS = myMenuDAO.selectStoreMenu(menu_id);
        for(MenuDTO menuDTO: menuDTOS) {
            if (menu_id == menuDTO.getMenu_id())
                return menu_id;
        }

        System.out.println("해당 가게에 다음과 같은 메뉴가 존재하지 않음.");
        return menu_id;
    }

    private String inputMenu_name(Scanner sc)
    {
        String input;
        System.out.println("메뉴 이름을 입력해주세요.(변경하지 않는다면 enter) : ");
        input = sc.nextLine();
        return input;
    }
    private long inputMenu_price(Scanner sc)
    {
        String input;

        while(true)
        {
            System.out.println("메뉴 가격을 입력해주세요. (변경하지 않는다면 enter) : ");
            input = sc.nextLine();

            if(input.equals(""))
                return -1;

            if(isdigit(input))
            {
                return Long.parseLong(input);
            }

            else
                System.out.println("입력 형식에 맞지 않습니다. ");
        }

    }

    private int inputMenu_quantity(Scanner sc)
    {
        String input;

        while(true)
        {
            System.out.println("메뉴 제고를 입력해주세요.(변경하지 않는다면 enter) : ");
            input = sc.nextLine();

            if(input.equals(""))
                return -1;

            if(isdigit(input))
            {
                return Integer.parseInt(input);
            }

            else
                System.out.println("입력 형식에 맞지 않습니다. ");
        }
    }

    private String inputMenu_category(Scanner sc)
    {
        String input;
        System.out.println("메뉴 카테고리를 입력해주세요.\n (변경하지 않는다면 enter): ");
        input = sc.nextLine();
        return input;
    }

    private boolean isdigit(String input)
    {
        char tmp;

        for(int i = 0; i<input.length(); i++)
        {
            tmp = input.charAt(i);
            if(!('0' <= tmp && tmp <= '9'))
                return false;
        }

        return true;
    }

}
