package control;

import persistence.dao.MyOrderDAO;
import persistence.dto.MenuDTO;
import persistence.dto.OptionDTO;
import persistence.dto.OrderDTO;
import protocol.BodyMaker;
import protocol.Header;
import protocol.ResponseReceiver;
import protocol.ResponseSender;
import service.MenuService;
import service.OptionService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public class RequestController {

    public static void handleRequest(Header header, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
        ResponseReceiver responseReceiver = new ResponseReceiver();
        ResponseSender responseSender = new ResponseSender();

        switch(header.code) {

            case Header.CODE_MENU_LIST:
                int store_id = responseReceiver.receiveStoreID(inputStream);
                List<MenuDTO> menuList = new MenuService().selectStoreMenu(store_id);
                responseSender.sendMenuListAns(menuList, outputStream);
                break;

            case Header.CODE_OPTION_LIST:
                int menu_id = responseReceiver.receiveMenuID(inputStream);
                List<OptionDTO> optionList = new OptionService().selectMenuOption(menu_id);
                responseSender.sendOptionListAns(optionList, outputStream);
                break;

            case Header.CODE_INSERT_ORDER:
                OrderDTO order = responseReceiver.receiveOrder(inputStream);
                MyOrderDAO myOrderDAO = new MyOrderDAO();
                myOrderDAO.insertOrder(order);
                break;

            case Header.TYPE_RES:
                //
                break;
        }
    }
}