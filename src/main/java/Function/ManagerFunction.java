package Function;

import persistence.dto.StoreDTO;
import service.StoreService;
import view.StoreView;

import java.util.List;
import java.util.Scanner;

public class ManagerFunction {
    private Scanner sc;

    public ManagerFunction()
    {
        sc = new Scanner(System.in);
    }


    public void storeAccept()
    {
        StoreService storeService = new StoreService();
        StoreView storeView = new StoreView();
        List<StoreDTO> storeDTOS =storeService.selectStore_WaitingAccept();

        System.out.println("<승인 대기 중인 가게>");
        storeView.printStoreWithNumber(storeDTOS);
        System.out.print("승인/거절 할 가게 번호를 입력해주세요 :");
        int inputNum = sc.nextInt();
        int store_id = storeDTOS.get(inputNum-1).getStore_id();

        System.out.print("승인(1), 거절(2) : ");
        int selectNum = sc.nextInt();

        if(selectNum ==1)
        {
            int result =storeService.updateStore_Accept(store_id);
            if(result ==1)
                System.out.println("가게 승인 성공");
            else
                System.out.println("가게 승인 실패");
        }
        else if( selectNum ==2)
        {
            int cancle = storeService.deleteStore(store_id);
            if(cancle ==1)
                System.out.println("가게 거절 성공");
            else
                System.out.println("가게 거절 성공");
        }
    }

    public void selectStore_Accepted()
    {
        StoreService storeService = new StoreService();
        StoreView storeView = new StoreView();
        List<StoreDTO> storeDTOS =storeService.selectStore_Accepted();
        storeView.printStoreWithNumber(storeDTOS);
    }


}
