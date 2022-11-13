package persistence.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.OrderDTO;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class MyOrderDAO {
    private final SqlSessionFactory sqlSessionFactory;

    public MyOrderDAO(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<OrderDTO> selectAll(){
        List<OrderDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.OrderMapper.selectAll");
        }finally {
            session.close();
        }
        return list;
    }

    public int insertOrder(OrderDTO od) {
        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result = session.insert("mapper.OrderMapper.insertOrder", od);

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

    public List<OrderDTO> findOrderWithCustomerIdLike(Long customer_id)
    {
        List<OrderDTO> dtos = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            dtos = session.selectList("mapper.OrderMapper.findOrderWithCustomerIdLike", customer_id);
        }finally {
            session.close();
        }
        return dtos;
    }

    public int updateOrder(OrderDTO orderDTO) {

        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result =session.update("mapper.OrderMapper.updateOrder",orderDTO);

            if (result==1){
                session.commit();
            }else {
                session.rollback();
            }

        }finally {
            session.close();
        }
        return result;

    }//updateMember


    public List<OrderDTO> selectOrder_store(int store_id)
    {
        List<OrderDTO> dtos = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            dtos = session.selectList("mapper.OrderMapper.selectOrder_store", store_id);
        }finally {
            session.close();
        }
        return dtos;
    }

    public List<OrderDTO> selectOrder_customer(String user_id)
    {
        List<OrderDTO> dtos = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            dtos = session.selectList("mapper.OrderMapper.selectOrder_customer", user_id);
        }finally {
            session.close();
        }
        return dtos;
    }
}