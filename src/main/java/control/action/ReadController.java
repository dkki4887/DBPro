package control.action;

import control.target.*;
import protocol.Header;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ReadController {

    private MenuController menuController;
    private MenuOptionController menuOptionController;
    private OptionController optionController ;
    private OrderController orderController;
    private OrderMenuController orderMenuController;
    private OrderOptionController orderOptionController;
    private Review_omController review_omController;
    private ReviewController reviewController;
    private StoreController storeController;
    private StoreMenuController storeMenuController;
    private UserController userController;

    public ReadController
            ( MenuController menuController, MenuOptionController menuOptionController,
    OptionController optionController ,OrderController orderController,
    OrderMenuController orderMenuController, OrderOptionController orderOptionController,
    Review_omController review_omController,ReviewController reviewController,
    StoreController storeController, StoreMenuController storeMenuController, UserController userController)
    {
        this.menuController=menuController;
        this.menuOptionController = menuOptionController;
        this.optionController = optionController;
        this.orderController = orderController;
        this.orderMenuController = orderMenuController;
        this.orderOptionController = orderOptionController;
        this.review_omController = review_omController;
        this.reviewController = reviewController;
        this.storeController = storeController;
        this.storeMenuController = storeMenuController;
        this.userController = userController;
    }


    public void handleRead(Header header, DataInputStream bodyReader, DataOutputStream outputStream) throws IOException {

        switch (header.) {

            case Header.TARGET_PLAYER:
                playerController.handleRead(header, bodyReader, outputStream);
                break;

            case Header.TARGET_TEAM:
                teamController.handleRead(header, bodyReader, outputStream);
                break;

        }
    }


}
