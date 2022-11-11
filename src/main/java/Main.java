import persistence.MyBatisConnectionFactory;
import persistence.dao.MyStoreDAO;
import persistence.dto.StoreDTO;
import service.StoreService;

import java.util.List;

public class Main {
    public static void main(String args[]){
        /*BoardDAO boardDAO = new BoardDAO();
        BoardView boardView = new BoardView();
        BoardService boardService = new BoardService(boardDAO);
        List<BoardDTO> all = boardService.findAll();
        boardView.printAll(all);*/

        MyStoreDAO myStoreDAO = new MyStoreDAO(MyBatisConnectionFactory.getSqlSessionFactory());
        StoreService ss = new StoreService(myStoreDAO);

        ss.storeAdd();
        List<StoreDTO> storeDTOS = ss.findAll();

        for(StoreDTO storeDTO : storeDTOS)
            System.out.println("dto.toString() = " + storeDTO.toString());
    }
}