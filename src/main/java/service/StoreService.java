package service;

import persistence.dao.MyStoreDAO;
import persistence.dto.StoreDTO;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class StoreService
{
    private final MyStoreDAO myStoreDAO;

    public StoreService(MyStoreDAO myStoreDAO)
    {
        this.myStoreDAO = myStoreDAO;
    }
    public List<StoreDTO> findAll()
    {
        List<StoreDTO> all = myStoreDAO.selectAll();
        return all;
    }

    public void storeAdd()
    {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Object> storeMap = new HashMap<String,Object>();

        storeMap.put("user_id", getUser_id(sc));
        storeMap.put("store_name", getStore_Name(sc));
        storeMap.put("store_address", getStore_Address(sc));
        storeMap.put("store_phone", getStore_Phone(sc));
        storeMap.put("store_category", getStore_Category(sc));
        storeMap.put("store_time", getStore_Time(sc));
        storeMap.put("store_info", getStore_info(sc));

        myStoreDAO.storeAdd(storeMap);
    }

    private String getUser_id(Scanner sc)
    {
        System.out.println("사장님 아이디를 입력해주세요 : ");
        return sc.nextLine();
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
            String store_time = "",input;
            while(true)
            {
                System.out.println("가게 오픈시간을 입력해주세요(00:00): ");
                input = sc.nextLine();
                if (isTime(input)) {
                    store_time += input;
                    while(true)
                    {
                        System.out.println("가게 마감시간을 입력해주세요(00:00): ");
                        input = sc.nextLine();
                        if(isTime(input)) {
                            store_time += "~" + input;
                            return store_time;
                        }
                        else
                            System.out.println("입력 값이 형식에 맞지 않습니다. (00:00) ");
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
            if(!isdigit(temp[i]))
                return false;
        }

        return true;
    }
}
