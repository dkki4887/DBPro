package service;

import persistence.dao.MyMenuOptionDAO;
import persistence.dao.MyOptionDAO;
import persistence.dto.OptionDTO;

import java.util.List;

public class OptionService {
    private final MyOptionDAO myOptionDAO;
    private final MyMenuOptionDAO myMenuOptionDAO;

    public OptionService()
    {
        this.myOptionDAO = new MyOptionDAO();
        this.myMenuOptionDAO = new MyMenuOptionDAO();
    }

    public List<OptionDTO> selectMenuOption(int menu_id)
    {
        List<OptionDTO> dtos = myOptionDAO.selectMenuOption(myMenuOptionDAO.selectMenuOptionId(menu_id));
        return dtos;
    }
}
