package view;

import persistence.dto.StoreDTO;
import persistence.dto.UserDTO;
import service.UserService;

import java.util.List;

public class StoreView {

    public void printAllStore(List<StoreDTO> dtos)
    {
        int i = 0;
        for(StoreDTO dto: dtos) {
            System.out.println((i + 1) + ". " + dto.getStore_name() + " \" " + dto.getStore_info() + " \"  | 주소 : " + dto.getStore_address() + " | 영업시간 : " + dto.getStore_time() + " | 전화번호 : " + dto.getStore_phone());
            i++;
        }
    }

    public void printAllStore_manager(List<StoreDTO> dtos)
    {
        UserService us = new UserService();
        int i = 0;

    }

    public int selectStore(List<StoreDTO> dtos, int selectNum)
    {
        if(dtos.size() < selectNum || selectNum < 1)
        {
            System.out.println("잘못된 선택입니다.");
            return -1;
        }
        else
            return dtos.get(selectNum - 1).getStore_id();
    }

}
