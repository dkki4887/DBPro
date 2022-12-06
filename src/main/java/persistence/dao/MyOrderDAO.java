package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.MyBatisConnectionFactory;
import persistence.dto.OrderDTO;

import java.util.List;

public class MyOrderDAO {
    private final SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();

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
            if (result == 1){
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

    public int updateOrderPrice(long order_price, String order_num)
    {
        OrderDTO od = new OrderDTO(order_price, order_num);
        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result =session.update("mapper.OrderMapper.updateOrderPrice", od);

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

    public int updateOrderState_Complete(int order_id) {

        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result =session.update("mapper.OrderMapper.updateOrderState_Complete",order_id);

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

    public int updateOrderState_Cancle(int order_id) {

        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result =session.update("mapper.OrderMapper.updateOrderState_Cancle",order_id);

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

    public int updateOrderState_Delivery(int order_id) {

        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result =session.update("mapper.OrderMapper.updateOrderState_Delivery",order_id);

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

    public List<OrderDTO> selectOrder_store_Waiting(int store_id)
    {
        List<OrderDTO> dtos = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            dtos = session.selectList("mapper.OrderMapper.selectOrder_store_Waiting", store_id);
        }finally {
            session.close();
        }
        return dtos;
    }
    public List<OrderDTO> selectOrder_store_Delivery(int store_id)
    {
        List<OrderDTO> dtos = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            dtos = session.selectList("mapper.OrderMapper.selectOrder_store_Delivery", store_id);
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

    public List<OrderDTO> selectAllOrder_customer(String user_id)
    {
        List<OrderDTO> dtos = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            dtos = session.selectList("mapper.OrderMapper.selectAllOrder_customer", user_id);
        }finally {
            session.close();
        }
        return dtos;
    }

    public List<OrderDTO> selectAllCustomerOrder(String user_id)
    {
        List<OrderDTO> dtos = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            dtos = session.selectList("mapper.OrderMapper.selectAllCustomerOrder", user_id);
        }finally {
            session.close();
        }
        return dtos;
    }
}

