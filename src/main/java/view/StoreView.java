package view;

import persistence.dto.StoreDTO;
import persistence.dto.UserDTO;
import service.UserService;

import java.util.List;

public class StoreView {

    public List<StoreDTO> printAllStore(List<StoreDTO> dtos)
    {
        int i = 0;
        System.out.println("=======================================================");
        for(StoreDTO dto: dtos) {
            System.out.println((i + 1) + ". " + dto.getStore_name() + " \" " + dto.getStore_info() + " \" |" + ((dto.getStore_state()) ? "(영업중)" : "(영업종료)") +
                    "\n | 주소 : " + dto.getStore_address() + " | 영업시간 : " + dto.getStore_time() +
                    "\n | 전화번호 : " + dto.getStore_phone());
            i++;
        }
        System.out.println("=======================================================");

        return dtos;
    }

    public void printAllStore_manager(List<StoreDTO> dtos)
    {
        UserService us = new UserService();
        int i = 0;
        System.out.println("=======================================================");

        for(StoreDTO dto: dtos) {
            System.out.println((i + 1) + ". " + dto.getStore_name() + " \" " + dto.getStore_info() + " \" |" + ((dto.getStore_state()) ? "(영업중)" : "(영업종료)") +
                    "\n | 주소 : " + dto.getStore_address() + " | 영업시간 : " + dto.getStore_time() +
                    "\n | 매장 전화번호 : " + dto.getStore_phone() + " | 점주 전화번호 : " + dto.getUser_id() +
                    "\n | 점주");
            i++;
        }
        System.out.println("=======================================================");

    }

    public int selectStore(List<StoreDTO> dtos, int selectNum)
    {
        if(dtos.size() < selectNum || selectNum < 1)
        {
            System.out.println("잘못된 가게번호입니다.");
            return -1;
        }
        else
            return dtos.get(selectNum - 1).getStore_id();
    }

}
