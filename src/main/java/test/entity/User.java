package test.entity;

import lombok.*;

import java.io.Serializable;


//@Getter
//@Setter
@Data
@AllArgsConstructor //有参的构造方法
@NoArgsConstructor //无参的构造方法
//Data setter/getter、equals、canEqual、hashCode、toString
//@ToString //toString ，@ToString(exclude = {"id"})
//@Data,Data注解在类上，会为类的所有属性自动生成setter/getter、equals、
//canEqual、hashCode、toString方法，如为final属性，则不会为该属性生成setter方法
public class User implements Serializable {
    private Integer uid;
    private String username;
    private String sex;
    private String birthday;
    private String address;
}
