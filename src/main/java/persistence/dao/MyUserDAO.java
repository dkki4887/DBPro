package persistence.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.MyBatisConnectionFactory;
import persistence.dto.MenuDTO;
import persistence.dto.UserDTO;

import java.util.List;

public class MyUserDAO {
    private SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();

    public MyUserDAO() {
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
            userDTO = session.selectOne("mapper.UserMapper.selectUserpw", useridDTO.getUser_id());
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
    public List<UserDTO> selectUser_WaitingAccept() {
            List<UserDTO> list = null;
                    SqlSession session = sqlSessionFactory.openSession();
                    try{
                        list  = session.selectList("mapper.UserMapper.selectUser_WaitingAccept");
                    } finally {
                        session.close();
                    }
                    return list;
                }

        public int updateUser_Accept(String user_id) {
            SqlSession session = sqlSessionFactory.openSession();
                   int result = -1;
                   try{
                       result =session.update("mapper.UserMapper.updateUser_Accept",user_id);

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

        public int deleteUser(String user_id) {
            SqlSession session = sqlSessionFactory.openSession();
                   int result = -1;
                   try{
                       result = session.delete("mapper.UserMapper.deleteUser", user_id);

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

