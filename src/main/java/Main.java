import persistence.MyBatisConnectionFactory;
import persistence.dao.BoardDAO;
import persistence.dao.MyBoardDAO;
import persistence.dto.BoardDTO;
import service.BoardService;
import view.BoardView;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]){
        /*BoardDAO boardDAO = new BoardDAO();
        BoardView boardView = new BoardView();
        BoardService boardService = new BoardService(boardDAO);
        List<BoardDTO> all = boardService.findAll();
        boardView.printAll(all);*/

        MyBoardDAO myBoardDAO = new MyBoardDAO(MyBatisConnectionFactory.getSqlSessionFactory());

        List<BoardDTO> boardDTOS = myBoardDAO.selectAll();
        for(BoardDTO dto:boardDTOS){
            System.out.println("dto.toString() = " + dto.toString());
        }
    }
}