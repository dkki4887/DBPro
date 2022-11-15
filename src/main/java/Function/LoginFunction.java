package Function;

import persistence.MyBatisConnectionFactory;
import persistence.dao.MyMenuDAO;
import persistence.dao.MyReviewDAO;
import persistence.dao.MyStoreDAO;
import persistence.dao.MyUserDAO;
import persistence.dto.UserDTO;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class LoginFunction
{
    private String userid;

    public void userAdd(){
        UserService us = new UserService();
        us.userAdd();
    }

    public String Login(Scanner sc)
    {
        UserService us = new UserService();
        String id, pw;

        System.out.println("===================================");
        while(true)
        {
            id = "0"; pw = "0";

            System.out.print("아이디를 입력하세요. (프로그램 종료 : -1):");
            id = sc.nextLine();

            if(id.equals("-1"))
                return null;
            else if(us.idCheck(id))
            {
                while(true)
                {
                    System.out.print("비밀번호를 입력하세요.(아이디 입력으로 : 0, 프로그램 종료 : -1) :");
                    pw = sc.nextLine();

                    if(pw.equals("0"))
                        break;
                    else if(pw.equals("-1"))
                        return null;
                    else if (us.pwCheck(id, pw)) {
                        this.userid = id;
                        System.out.println("===================================");
                        return id;
                    }
                    else
                        System.out.print("비밀번호가 일치하지 않습니다.");
                }
            }
        }

    }
}
