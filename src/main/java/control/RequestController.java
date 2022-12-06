package control;

import persistence.dao.MyMenuDAO;
import persistence.dao.MyMenuOptionDAO;
import persistence.dao.MyOrderDAO;
import persistence.dao.MyOrderMenuDAO;
import persistence.dto.*;
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
   private ResponseReceiver responseReceiver;
   private ResponseSender responseSender;

   public RequestController()
   {
       responseReceiver = new ResponseReceiver();
       responseSender = new ResponseSender();
   }

    public void handleRequest(Header header, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {

        switch(header.code) {

            case Header.CODE_MENU_LIST:
                int store_id = inputStream.readInt();
                List<MenuDTO> menuList = new MenuService().selectStoreMenu(store_id);
                responseSender.sendMenuListAns(menuList, outputStream);
                System.out.println("메뉴리스트 보내줌!!");
                break;

            case Header.CODE_OPTION_LIST:
                int menu_id = inputStream.readInt();
                List<OptionDTO> optionList = new OptionService().selectMenuOption(menu_id);
                responseSender.sendOptionListAns(optionList, outputStream);
                System.out.println("옵션리스트 보내줌!!");
                break;

            case Header.CODE_INSERT_ORDER:
                OrderDTO order = new OrderDTO().read(inputStream);
                MyOrderDAO myOrderDAO = new MyOrderDAO();
                myOrderDAO.insertOrder(order);
                System.out.println("주문 넣음!!");
                break;

            case Header.CODE_UPDATE_MENU_QUANTITY:
                int updateMenu_id = inputStream.readInt();
                MyMenuDAO myMenuDAO = new MyMenuDAO();
                myMenuDAO.updateMenuQuantity(updateMenu_id);
                System.out.println("메뉴 수량 바꿈!!");
                break;

            case Header.CODE_INSERT_ORDER_MENU:
                OrderMenuDTO orderMenuDTO = new OrderMenuDTO().read(inputStream);
                MyOrderMenuDAO myOrderMenuDAO = new MyOrderMenuDAO();
                myOrderMenuDAO.insertOrderMenu(orderMenuDTO);
                System.out.println("오더 메뉴 넣음!!");
                break;

            case Header.CODE_INSERT_ORDER_OPTION:
                OrderOptionDTO orderOptionDTO = new OrderOptionDTO().read(inputStream);
                myOrderMenuDAO = new MyOrderMenuDAO();
                myOrderMenuDAO.insertOrderOption(orderOptionDTO);
                System.out.println("오더 옵션 넣음!!");
                break;

            case Header.TYPE_RES:
                //
                break;
        }
    }
}