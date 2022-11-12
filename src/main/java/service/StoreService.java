package service;

import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dao.MyStoreDAO;
import persistence.dao.MyUserDAO;
import persistence.dto.StoreDTO;
import persistence.dto.UserDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StoreService
{
    private final MyStoreDAO myStoreDAO;
    private final MyUserDAO myUserDAO;

    public StoreService(SqlSessionFactory sqlSessionFactory)
    {
        myStoreDAO = new MyStoreDAO(sqlSessionFactory);
        myUserDAO = new MyUserDAO(sqlSessionFactory);
    }

    public List<StoreDTO> findAll()
    {
        List<StoreDTO> all = myStoreDAO.selectAll();
        return all;
    }

    public void storeAdd()
    {
        Scanner sc = new Scanner(System.in);
        StoreDTO addStoreDTO = new StoreDTO();

        String user_id = getUser_id(sc);
        String store_name = getStore_Name(sc);
        String store_address = getStore_Address(sc);
        String store_phone = getStore_Phone(sc);
        int store_category = getStore_Category(sc);
        String store_time = getStore_Time(sc);
        String store_info = getStore_info(sc);

        addStoreDTO.setUser_id(user_id);
        addStoreDTO.setStore_name(store_name);
        addStoreDTO.setStore_address(store_address);
        addStoreDTO.setStore_phone(store_phone);
        addStoreDTO.setStore_category(store_category);
        addStoreDTO.setStore_time(store_time);
        addStoreDTO.setStore_info(store_info);

        myStoreDAO.storeAdd(addStoreDTO);
    }

    private String getUser_id(Scanner sc)
    {
        List<UserDTO> userDTOS = myUserDAO.selectAllUserid();
        String input;

        while(true)
        {
            System.out.println("사장님 아이디를 입력해주세요 : ");
            input = sc.nextLine();

            for(UserDTO userDTO : userDTOS)
            {
                if (input.equals(userDTO.getUser_id()))
                {
                    return input;
                }
            }

            System.out.println("아이디가 유효하지 않습니다.");

        }
    }
    private String getStore_Phone(Scanner sc)
    {
        String input = new String();

        while(true)
        {
            System.out.println("가게 전화번호를 입력해주세요 : ");
            input = sc.nextLine();

            if(isdigit(input))
                return input;
        }
    }

    private String getStore_Name(Scanner sc)
    {
        System.out.println("가게 이름를 입력해주세요 : ");
        return sc.nextLine();
    }

    private String getStore_Address(Scanner sc)
        {
            System.out.println("가게 주소를 입력해주세요 : ");
            return sc.nextLine();
        }
    private int getStore_Category(Scanner sc)
        {
            String input;
            while(true)
            {
                System.out.println("가게 카테고리를 입력해주세요 (1: 뭐, 2 : 뭐, 3, 뭐): ");
                input = sc.nextLine();

                if (isdigit(input))
                    return Integer.parseInt(input);
                else
                    System.out.println("입력 값이 형식에 맞지 않습니다.");
            }
        }
    private String getStore_Time(Scanner sc)
        {
            String store_time = "",open_input, close_input;
            while(true)
            {
                System.out.println("가게 오픈시간을 입력해주세요(00:00): ");
                open_input = sc.nextLine();

                if (isTime(open_input)) {
                    while(true)
                    {
                        System.out.println("가게 마감시간을 입력해주세요(00:00): ");
                        close_input = sc.nextLine();
                        if(isTime(close_input) && compareTime(open_input, close_input)) {
                            store_time += open_input + "~" + close_input;
                            return store_time;
                        }
                        else {
                            System.out.println("입력 값이 형식에 맞지 않습니다. (00:00) ");
                            break;
                        }

                    }

                }
                else
                    System.out.println("입력 값이 형식에 맞지 않습니다. (00:00) ");

            }
        }
    private String getStore_info(Scanner sc)
        {
            System.out.println("가게 한 줄 소개를 입력해주세요 : ");
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

    private boolean isTime(String input)
    {
        String[] temp = input.split(":");
        for(int i = 0; i < temp.length; i++)
        {
            if(!(isdigit(temp[i]) && temp[i].length() == 2))
                return false;
        }
        if(0 <= Integer.parseInt(temp[0]) && Integer.parseInt(temp[0]) <= 24)
            if(0 <= Integer.parseInt(temp[1]) && Integer.parseInt(temp[1]) < 60)
                return true;

        return false;
    }

    private boolean compareTime(String open, String close)
    {
        String[] open_temp = open.split(":");
        String[] close_temp = close.split(":");

        if(Integer.parseInt(open_temp[0]) < Integer.parseInt(close_temp[0]))
            return true;
        else if(Integer.parseInt(open_temp[0]) == Integer.parseInt(close_temp[0]))
        {
            if(Integer.parseInt(open_temp[1]) <= Integer.parseInt(close_temp[1]))
                return true;
        }

        return false;
    }

}
