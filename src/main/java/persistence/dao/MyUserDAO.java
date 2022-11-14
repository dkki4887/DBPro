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

    public UserDTO selectUserpw(UserDTO useridDTO){
        UserDTO userDTO = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            userDTO = session.selectOne("mapper.UserMapper.selectUserpw", useridDTO);
        } finally {
            session.close();
        }
        return userDTO;
    }

    public void userAdd(UserDTO userDTO){
        SqlSession session = sqlSessionFactory.openSession();
        int result = -1;
        try{
            result = session.insert("mapper.UserMapper.userAdd", userDTO);

            if (result==1){
                session.commit();
            }else {
                session.rollback();
            }
        } finally {
            session.close();
        }
    }
    public UserDTO selectUserById(String user_id)
    {
        UserDTO userDTO = null;
        SqlSession session = sqlSessionFactory.openSession();
        try{
            userDTO = session.selectOne("mapper.UserMapper.selectUserById", user_id);
        } finally {
            session.close();
        }
        return userDTO;
    }
}

