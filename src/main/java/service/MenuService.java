package service;

import org.apache.ibatis.session.SqlSessionFactory;
import persistence.MyBatisConnectionFactory;
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
        myMenuDAO = new MyMenuDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        myStoreDAO = new MyStoreDAO(MyBatisConnectionFactory.getSqlSessionFactory());
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

    public void menuUpdate(int menu_id)
    {
        Scanner sc = new Scanner(System.in);
        MenuDTO addMenuDTO, exMenuDTO;
        addMenuDTO = new MenuDTO();

        String menu_name = inputMenu_name(sc);
        long menu_price = inputMenu_price(sc);
        int menu_quantity = inputMenu_quantity(sc);
        String menu_category = inputMenu_category(sc);

        addMenuDTO.setMenu_id(checkMenu_id(menu_id));
        addMenuDTO.setMenu_name(menu_name);
        addMenuDTO.setMenu_price(menu_price);
        addMenuDTO.setMenu_quantity(menu_quantity);
        addMenuDTO.setMenu_category(menu_category);

        myMenuDAO.menuAdd(addMenuDTO);
    }

    private int checkStore_id(int store_id)
    {
        List<StoreDTO> storeDTOS = myStoreDAO.selectAllStoreId();
        for(StoreDTO storeDTO: storeDTOS) {
            if (store_id == storeDTO.getStore_id())
                return store_id;
        }

        System.out.println("메장 아이디가 잘못됨.");
        return store_id;
    }

    private int checkMenu_id(int menu_id)
    {
        List<MenuDTO> menuDTOS = myMenuDAO.selectAllMenuId();
        for(MenuDTO menuDTO: menuDTOS) {
            if (menu_id == menuDTO.getMenu_id())
                return menu_id;
        }

        System.out.println("메뉴 아이디가 잘못됨.");
        return menu_id;
    }

    private String inputMenu_name(Scanner sc)
    {
        System.out.println("메뉴 이름을 입력해주세요 : ");
        return sc.nextLine();
    }
    private long inputMenu_price(Scanner sc)
    {
        String input;

        while(true)
        {
            System.out.println("메뉴 가격을 입력해주세요 : ");
            input = sc.nextLine();

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
            System.out.println("메뉴 제고를 입력해주세요 : ");
            input = sc.nextLine();

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
            System.out.println("메뉴 카테고리를 입력해주세요 (1: 뭐, 2 : 뭐, 3, 뭐): ");
            return sc.nextLine();
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
