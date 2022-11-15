package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.MyBatisConnectionFactory;
import persistence.dto.MenuDTO;
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

    public List<OptionDTO> selectAllByStoreId(int store_id){
        List<OptionDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.OptionMapper.selectAllByStoreId", store_id);
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

    public void optionAdd(OptionDTO optionDTO){
        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result = session.insert("mapper.OptionMapper.optionAdd", optionDTO);

            if (result==1){
                session.commit();
            }else {
                session.rollback();
            }
        } finally {
            session.close();
        }
    }

    public int deleteOption(MenuOptionDTO menuOptionDTO)
    {
        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result = session.delete("mapper.OptionMapper.deleteOption", menuOptionDTO.getOption_id());
            if (result==1){
                session.commit();
            }else {
                session.rollback();
            }

        } finally {
            session.close();
        }
        return result;
    }
}

