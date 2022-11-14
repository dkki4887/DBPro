package view;

import persistence.dto.OptionDTO;

import java.util.List;

public class OptionView {

    public List<OptionDTO> printMenuAllOption(List<OptionDTO> dtos)
    {
        int i = 0;
        for(OptionDTO dto: dtos) {
            System.out.println((i + 1) + ". " + dto.getOption_name() + "  " + dto.getOption_price() + "원");
            i++;
        }
        return dtos;
    }

}
