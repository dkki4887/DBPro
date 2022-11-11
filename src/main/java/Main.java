import persistence.MyBatisConnectionFactory;
import persistence.dao.MyStoreDAO;
import persistence.dto.StoreDTO;

import java.util.List;

public class Main {
    public static void main(String args[]){
        /*BoardDAO boardDAO = new BoardDAO();
        BoardView boardView = new BoardView();
        BoardService boardService = new BoardService(boardDAO);
        List<BoardDTO> all = boardService.findAll();
        boardView.printAll(all);*/

        MyStoreDAO myStoreDAO = new MyStoreDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        StoreDTO storeDTO = myStoreDAO.selectById(1);
        System.out.println("dto.toString() = " + storeDTO.toString());
    }
}