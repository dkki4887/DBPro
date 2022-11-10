import persistence.MyBatisConnectionFactory;

import java.util.List;

public class Main {
    public static void main(String args[]){
        /*BoardDAO boardDAO = new BoardDAO();
        BoardView boardView = new BoardView();
        BoardService boardService = new BoardService(boardDAO);
        List<BoardDTO> all = boardService.findAll();
        boardView.printAll(all);*/

        MyMenuDAO myBoardDAO = new MyMenuDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        List<MenuDTO> boardDTOS = myBoardDAO.selectAll();
        for(MenuDTO dto:boardDTOS){
            System.out.println("dto.toString() = " + dto.toString());
        }
    }
}