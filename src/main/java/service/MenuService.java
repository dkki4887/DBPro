package service;

import persistence.dao.MyMenuDAO;
import persistence.dto.MenuDTO;

import java.util.List;
import java.util.Scanner;

public class MenuService
{
    private final MyMenuDAO myMenuDAO;

    public MenuService(MyMenuDAO myMenuDAO)
    {
        this.myMenuDAO = myMenuDAO;
    }

    public List<MenuDTO> findAll()
    {
        List<MenuDTO> all = myMenuDAO.selectAll();
        return all;
    }

    public void menuAdd()
    {
        Scanner sc = new Scanner(System.in);
        MenuDTO addMenuDTO = new MenuDTO();

        int store_id = 1;//임시로1
        String menu_name = getMenu_name(sc);
        long menu_price = getMenu_price(sc);
        int menu_quantity = getMenu_quantity(sc);
        String menu_category = getMenu_category(sc);


        addMenuDTO.setStore_id(store_id);
        addMenuDTO.setMenu_name(menu_name);
        addMenuDTO.setMenu_price(menu_price);
        addMenuDTO.setMenu_quantity(menu_quantity);
        addMenuDTO.setMenu_category(menu_category);

        myMenuDAO.menuAdd(addMenuDTO);
    }

    private String getMenu_name(Scanner sc)
    {
        System.out.println("메뉴 이름을 입력해주세요 : ");
        return sc.nextLine();
    }
    private long getMenu_price(Scanner sc)
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

    private int getMenu_quantity(Scanner sc)
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

    private String getMenu_category(Scanner sc)
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
