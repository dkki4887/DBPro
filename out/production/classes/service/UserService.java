package service;

import persistence.dao.MyMenuDAO;
import persistence.dao.MyUserDAO;
import persistence.dto.MenuDTO;
import persistence.dto.UserDTO;

import java.util.List;
import java.util.Scanner;

public class UserService
{
    private final int ID_AND_PW_MIN_LENGTH = 3;
    private final MyUserDAO myUserDAO;

    public UserService(MyUserDAO myUserDAO)
    {
        this.myUserDAO = myUserDAO;
    }

    public List<UserDTO> findAll()
    {
        List<UserDTO> all = myUserDAO.selectAll();
        return all;
    }

    public void userAdd()
    {
        Scanner sc = new Scanner(System.in);
        UserDTO addUserDTO = new UserDTO();

        String user_id = getUser_id(sc, myUserDAO);
        String user_pw = getUser_pw(sc);
        String user_name = getUser_name(sc);
        String user_address = getUser_address(sc);
        String user_phone = getUser_phone(sc);
        int user_category = getUser_category(sc);


        addUserDTO.setUser_id(user_id);
        addUserDTO.setUser_pw(user_pw);
        addUserDTO.setUser_name(user_name);
        addUserDTO.setUser_address(user_address);
        addUserDTO.setUser_phone(user_phone);
        addUserDTO.setUser_category(user_category);

        myUserDAO.userAdd(addUserDTO);
    }

    private String getUser_id(Scanner sc, MyUserDAO myUserDAO) {
        String input = null;
        boolean result = true;
        List<UserDTO> userDTOS = myUserDAO.selectAllUserid();

        while(true)
        {
            result = true;
            System.out.print("아이디를 입력해주세요. : ");
            input = sc.nextLine();

            if(input.length() < ID_AND_PW_MIN_LENGTH)
            {
                System.out.println("사용하실 수 없는 아이디입니다. (최소 세 글자)");
                result = false;
            }
            else
            {
                for(UserDTO userDTO : userDTOS)
                {
                    if(input.equals(userDTO.getUser_id()))
                    {
                        System.out.println("사용하실 수 없는 아이디입니다. (중복 아이디)");
                        result = false;
                    }
                }

                if(result)
                    return input;
            }
        }
    }

    private String getUser_pw(Scanner sc)
    {
        String input = null;

        while(true)
        {
            System.out.print("비밀번호를 입력하세요. : ");
            input = sc.nextLine();

            if(input.length() >= ID_AND_PW_MIN_LENGTH)
                return input;
            else
                System.out.println("입력 형식에 맞지 않습니다(최소 세 글자). ");

        }
    }
    private String getUser_name(Scanner sc)
    {
        String input;

        while(true)
        {
            System.out.print("성함을 입력해주세요 : ");
            input = sc.nextLine();

            if(input.length() > 0)
            {
                return input;
            }
            else
                System.out.println("입력 형식에 맞지 않습니다(최소 한 글자). ");
        }

    }

    private String getUser_address(Scanner sc)
    {
        String input;

        while(true)
        {
            System.out.print("주소를 입력해주세요 : ");
            input = sc.nextLine();

            if(input.length() > 0)
            {
                return input;
            }
            else
                System.out.println("입력 형식에 맞지 않습니다(최소 한 글자). ");
        }
    }

    private String getUser_phone(Scanner sc)
        {
            String input = null;
            while(true)
            {
                System.out.print("전화번호를 입력해주세요 : ");
                input = sc.nextLine();

                if (isdigit(input))
                    return input;
                else
                    System.out.println("입력 형식에 맞지 않습니다. ");
            }

        }

    private int getUser_category(Scanner sc)
    {
        int input;
        while(true)
        {
            System.out.print("계정 유형를 입력해주세요(0:관리자, 1:점주, 2:개인) : ");
            input = sc.nextInt();

            if (0 <= input && input <= 2)
                return input;
            else
                System.out.println("입력 형식에 맞지 않습니다. ");
        }

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
