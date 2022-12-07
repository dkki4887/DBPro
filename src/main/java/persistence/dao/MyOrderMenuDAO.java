package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.MyBatisConnectionFactory;
import persistence.dto.OrderMenuDTO;
import persistence.dto.OrderOptionDTO;

import java.util.List;

public class MyOrderMenuDAO {
    private SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();

    public List<OrderMenuDTO> selectAll(){
        List<OrderMenuDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.OrderMenuMapper.selectAll");
        } finally {
            session.close();
        }
        return list;
    }

    public List<OrderMenuDTO> selectAllMenu(){
        List<OrderMenuDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.OrderMenuMapper.selectAllOrderMenu");
        } finally {
            session.close();
        }
        return list;
    }

    public List<OrderOptionDTO> selectAllOption(){
        List<OrderOptionDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.OrderOptionMapper.selectAllOrderOption");
        } finally {
            session.close();
        }
        return list;
    }

    public int insertOrderMenu(OrderMenuDTO omd)
    {
        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result = session.insert("mapper.OrderMenuMapper.insertOrderMenu", omd);

            if (result==1){
                session.commit();
            }else {
                session.rollback();
            }

        }finally {
            session.close();
        }
        return result;
    }

    public int insertOrderOption(OrderOptionDTO ood)
    {
        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result = session.insert("mapper.OrderOptionMapper.insertOrderOption", ood);

            if (result==1){
                session.commit();
            }else {
                session.rollback();
            }

        }finally {
            session.close();
        }
        return result;
    }
}

