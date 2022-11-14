package view;

import persistence.dto.StoreDTO;

import java.util.List;

public class StoreView {

    public List<StoreDTO> printAllStore(List<StoreDTO> dtos)
    {
        int i = 0;
        for(StoreDTO dto: dtos) {
            System.out.println((i + 1) + ". " + dto.getStore_name());
            i++;
        }
        return dtos;
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
