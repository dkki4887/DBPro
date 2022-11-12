package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.dto.MenuDTO;
import persistence.dto.UserDTO;

import java.util.List;

public class MyUserDAO {
    private SqlSessionFactory sqlSessionFactory = null;

    public MyUserDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }
    public List<UserDTO> selectAll(){
        List<UserDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.UserMapper.selectAll");
        } finally {
            session.close();
        }
        return list;
    }

    public List<UserDTO> selectAllUserid(){
        List<UserDTO> list = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            list = session.selectList("mapper.UserMapper.selectAllUserid");
        } finally {
            session.close();
        }
        return list;
    }

    public void userAdd(UserDTO userDTO){
        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result = session.selectOne("mapper.MenuMapper.userAdd", userDTO);

            if (result==1){
                session.commit();
            }else {
                session.rollback();
            }
        } finally {
            session.close();
        }
    }
    public UserDTO selectById(Long id){return null;}
}

