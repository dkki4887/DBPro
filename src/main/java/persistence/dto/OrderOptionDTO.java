package persistence.dto;

import lombok.*;
import org.apache.ibatis.type.Alias;
import protocol.MySerializableClass;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Getter
@Setter
@ToString
@NoArgsConstructor

@Alias("orderOptionList")
public class OrderOptionDTO implements MySerializableClass {
    private String orderMenu_id;
    private String option_name;

    public OrderOptionDTO(String orderMenu_id, String option_name)
    {
        this.orderMenu_id = orderMenu_id;
        this.option_name = option_name;
    }

    public String getOrderMenu_id() {
        return orderMenu_id;
    }

    public String getOption_name() {
        return option_name;
    }

    public void setOrderMenu_id(String orderMenu_id) {
        this.orderMenu_id = orderMenu_id;
    }

    public void setOption_name(String option_name) {
        this.option_name = option_name;
    }

    public static OrderOptionDTO read(DataInputStream bodyReader) throws IOException
    {
        String orderMenu_id = bodyReader.readUTF();
        String option_name = bodyReader.readUTF();

        return new OrderOptionDTO(orderMenu_id, option_name);
    }

    @Override
    public byte[] getBytes() throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buf);

        dos.writeUTF(orderMenu_id);
        dos.writeUTF(option_name);
        return buf.toByteArray();
    }
}
