package persistence.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.session.ResultHandler;

@Getter
@Setter
@ToString
public class UserDTO{
    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_address;
    private String user_phone;
    private int user_category;
}
