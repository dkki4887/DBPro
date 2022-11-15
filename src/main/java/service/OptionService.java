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

    public int[] getOptionIds(List<OptionDTO> dtos, int[] options, int size)
    {
        int[] optionIds = new int[size];
        for(int i = 0; i < size; i++)
            optionIds[i] = dtos.get(options[i] - 1).getOption_id();
        return optionIds;
    }

    public long getOptionPrice(List<OptionDTO> dtos, int[] options, int size)
    {
        long resultOptionPrice = 0;
        for(int i = 0; i < size; i++)
            resultOptionPrice = resultOptionPrice + dtos.get(options[i] - 1).getOption_price();
        return resultOptionPrice;
    }
}
