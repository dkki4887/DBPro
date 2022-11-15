package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.MyBatisConnectionFactory;
import persistence.dto.MenuOptionDTO;
import persistence.dto.OptionDTO;
import persistence.dto.OrderMenuDTO;
import persistence.dto.OrderOptionDTO;

import java.util.ArrayList;
import java.util.List;

public class MyOptionDAO {
    private SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();

    public List<OptionDTO> selectAll(){
        List<OptionDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.OptionMapper.selectAll");
        } finally {
            session.close();
        }
        return list;
    }

    public List<OptionDTO> selectMenuOption(List<MenuOptionDTO> dtos) {
        List<OptionDTO> list = new ArrayList<>();
        SqlSession session;
        for(MenuOptionDTO dto: dtos)
        {
            session = sqlSessionFactory.openSession();
            try{
                list.add(session.selectOne("mapper.OptionMapper.selectMenuOption", dto.getOption_id()));
            } finally {
                session.close();
            }
        }
        return list;
    }
}

