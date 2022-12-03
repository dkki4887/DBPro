package control.action;

import control.target.*;
import protocol.Header;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ActionController {

    ReadController readController;
    MenuController menuController;
    MenuOptionController menuOptionController;
    OptionController optionController ;
    OrderController orderController;
    OrderMenuController orderMenuController;
    OrderOptionController orderOptionController;
    Review_omController review_omController;
    ReviewController reviewController;
    StoreController storeController;
    StoreMenuController storeMenuController;
    UserController userController;

    public ActionController() {
         menuController =new MenuController();
         menuOptionController = new MenuOptionController();
         optionController = new OptionController();
         orderController = new OrderController();
         orderMenuController = new OrderMenuController();
         orderOptionController = new OrderOptionController();
         review_omController = new Review_omController();
         reviewController = new ReviewController();
         storeController = new StoreController();
         storeMenuController = new StoreMenuController();
         userController = new UserController();

        readController = new ReadController(menuController, menuOptionController,optionController,
                orderController,orderMenuController,orderOptionController,review_omController
        ,reviewController,storeController,storeMenuController,userController);
    }

    public boolean handleRequest(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException {

        switch(header.action) {

            case Header.ACTION_QUIT:
                return false;

            case Header.ACTION_READ:
                readController.handleRead(header, bodyReader, outputStream);
                break;

            default:
                // BadRequest 알려주는 패킷 전송
                break;


        }

        return true;
    }


}
