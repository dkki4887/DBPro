package Function;

import persistence.dto.*;
import service.*;
import view.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;

public class CustomerFunction {
    Scanner sc = new Scanner(System.in);

    public void writeReview(String customer_id) { //리뷰작성 메소드
        OrderService orderService = new OrderService();
        OrderView orderView = new OrderView();
        ReviewService reviewService = new ReviewService();

        List<OrderDTO> od = orderService.selectOrder_customer(customer_id);
        orderView.printOrderWithNumber(od);
        List<Review_omDTO> rv = reviewService.findReviewWithUserIdLike(customer_id);

        System.out.print("리뷰를 작성할 주문번호를 입력하십시오 : ");
        int inputNumber = sc.nextInt();
        String currrent_state = od.get(inputNumber-1).getOrder_state();
        boolean isDupl = false;

        for(Review_omDTO rvs : rv)
        {
            if(rvs.getOrder_id() == od.get(inputNumber-1).getOrder_id())
            {
                isDupl = true;
                break;
            }
        }

        if(currrent_state.equals("취소"))
            System.out.println("취소된 주문은 리뷰를 작성할 수 없습니다.");
        else if( isDupl == true)
            System.out.println("해당 주문은 이미 리뷰를 작성하셨습니다.");
        else
        {
            int store_id = od.get(inputNumber-1).getStore_id();
            int reviewResult = reviewService.insertReview(store_id, customer_id, od.get(inputNumber-1).getOrder_id() , od.get(inputNumber-1).getOrder_num());
            if (reviewResult == 1) System.out.println("리뷰 작성 성공");
            else System.out.println("리뷰 작성 실패");
        }
    }

    public void inquireReview(String user_id) {
        //완성
        ReviewService reviewService = new ReviewService();
        ReviewView reviewView = new ReviewView();

        reviewView.printMyReview(reviewService.findReviewWithUserIdLike(user_id));
    }

    public int createOrder(String user_id) //주문 생성 메소드
    {
        StoreService storeS = new StoreService();
        StoreView storeV = new StoreView();

        MenuService menuS = new MenuService();
        MenuView menuV = new MenuView();

        OptionService optionS = new OptionService();
        OptionView optionV = new OptionView();

        OrderService orderS = new OrderService();

        long order_price = 0;
        int selectMenuNum = -1;
        int store_id;
        String[] optionNames = {};

        boolean continueFlag;
        do {
            List<StoreDTO> sDtos = storeV.printAllStore(storeS.selectAllStoreNameAndId());
            System.out.print("주문할 가게의 번호를 선택하세요.(주문종료: 0): ");
            int selectStoreNum = sc.nextInt();
            if(selectStoreNum == 0)
            {
                System.out.println("주문이 종료되었습니다.");
                return -1;
            }
            store_id = storeV.selectStore(sDtos, selectStoreNum);
            continueFlag = storeS.isStoreOpen(store_id);

            if(!continueFlag)
                System.out.println("해당 가게의 영업 시간이 아닙니다. 가게를 다시 선택해주세요.");
            System.out.println();
        } while(continueFlag == false);

        int menuCnt = 0;
        while(selectMenuNum != 0)
        {
            String order_num = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss-")) + user_id; // 주문번호 생성
            List<MenuDTO> mDtos = menuV.printStoreAllMenu(menuS.selectStoreMenu(store_id), store_id);
            System.out.print("주문할 메뉴의 번호를 선택하세요.(1개 선택, 주문종료: 0): ");
            selectMenuNum = sc.nextInt();
            if(selectMenuNum == 0)
            {
                System.out.println("주문이 종료되었습니다.");
                return -1;
            }
            int menu_id = menuS.getMenuId(mDtos, selectMenuNum); //메뉴아이디 가져오기

            if(menu_id != -1) //옵션...
            {
                order_price = order_price + menuS.getMenuPrice(mDtos, selectMenuNum);
                System.out.println();

                List<OptionDTO> oDtos = optionV.printMenuAllOption(optionS.selectMenuOption(menu_id));
                if (oDtos.size() != 0)
                {
                    System.out.print("메뉴의 옵션을 선택하세요(여러개 선택가능, 띄어쓰기로 구분, 옵션선택종료: 0): ");
                    int[] options = new int[10];
                    int selectOptionNum = -1, size = 0;
                    do {
                        selectOptionNum = sc.nextInt();
                        if (selectOptionNum != 0)
                            options[size++] = selectOptionNum;
                    } while (selectOptionNum != 0);
                    optionNames = optionS.getOptionNames(oDtos, options, size);
                    order_price = order_price + optionS.getOptionPrice(oDtos, options, size);
                }

                if(menuCnt == 0)
                    orderS.insertOrder(user_id, store_id, order_price, LocalDateTime.now(), menu_id, order_num); //주문 생성

                menuS.updateMenuQuantity(menu_id);
                orderS.insertOrderMenuAndOption(order_num, menuS.getMenuName(mDtos, selectMenuNum),menuCnt, optionNames);
                System.out.println();
                System.out.println("해당 메뉴에 대한 주문이 완료되었습니다.");
                System.out.println(); menuCnt++;
            }
        }
        return -1;
    }

    public void selectOrder_customer(String user_id) {
        OrderService orderService = new OrderService();
        OrderView orderView = new OrderView();

        orderView.printOrder(orderService.selectOrder_customer(user_id));
    }

    public void cancleOrder(String user_id)
    {
        OrderService odService = new OrderService();
        OrderView odView = new OrderView();

        List<OrderDTO> od =odService.selectAllOrder_customer(user_id);
        odView.printOrderWithNumber(od);
        System.out.print("취소할 주문 번호를 입력하세요 : ");
        int inputNumber = sc.nextInt();
        String current_state = od.get(inputNumber-1).getOrder_state();
        if( current_state.equals("배달 완료") || current_state.equals("배달중"))
        {
            System.out.println("배달중이거나 완료된 주문은 취소할 수 없습니다.");
        }
        else if(current_state.equals("취소"))
        {
            System.out.println("이미 취소된 주문입니다.");
        }
        else
        {
            int order_id = od.get(inputNumber-1).getOrder_id();
            int result =odService.updateOrderState_Cancle(order_id);
            if(result == 1) System.out.println("주문 취소 성공");
            else System.out.println("주문 취소 실패");
        }
    }

    public void printAllStore()
    {
        StoreService ss = new StoreService();
        StoreView sv = new StoreView();

        System.out.println("================가게 목록================");

        sv.printAllStore(ss.findAll());

        System.out.println("========================================");

    }
}
