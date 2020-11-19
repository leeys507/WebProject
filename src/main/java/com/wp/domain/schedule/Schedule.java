package com.wp.domain.schedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;

@Data//getter,setter 자동으로 만들어주기
@AllArgsConstructor//해당 클래스내의 모든멤버변수가 담긴 생성자만들기
@NoArgsConstructor//해당 클래스의 디폴트 생성자를 만들어줌.
@Builder//이거는 이 클래스를 다른 클래스내에서 부를 때 굳이 new User(?,?,?)처럼 생성자내의 파라메타에 해당하는 값들을 일일히 전부다 맞출필요없이 원하는 순서대로 내마음대로 넣어서 만들 수 있다는장점이있음.
@Entity
//@DynamicInsert
public class Schedule {

    @Id
    @Column(name = "id", columnDefinition = "int")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name = "part", nullable = false, length=5)
    private String part;
    
    @Column(name = "coursenum",nullable = false,length=30)
    private String coursenum;

    //unique = true --> 이름에서도 알 수 있듯이 중복된 username을 설정할 수 없게 만들어줌.
    @Column(name ="title",nullable = false,length=30)
    private String title;

    @Column(name="profname",nullable=false,length=30)
    private String profname;

    @Column(name="placename",nullable = false,length=50)
    private String placename;

    @Column(name="colornum",nullable = false,length=50)
    private String colornum;

    @Column(name="topnum",nullable = false,length=50)
    private String topnum;

    @Column(name="heightnum",nullable = false,length=50)
    private String heightnum;

    @Column(name="dayid" ,nullable = false,length=50)
    private String dayid;

    @Column(name="sid",nullable = false,length=50)
    private String sid;

    /*
    @PostMapping으로 클라이언트에게 insert를 요청 받을 때
    실질적인 쿼리문은
    insert into User values(id,username,password,email,role,createDate)
    인데 이때 role은 '권한 설정'으로써 사실 상 클라이언트에게 값을 받는 것이 아닌 관리자가 설정해야하기 때문에
    클라이언트는 이 value에 대해서 값을 설정할 수 없다 그러므로 클라이언트로부터 아무값도 받지 않아 Controller에서 받은
    User user에 대한 자동화 된 Mapping의 값은'Null' 값이 저장.

    내 뇌피셜이지만

    아마 DB에서는 'Null'값도 'value'로 인정하여 @ColumnDefalut가 작동하지 않고 그대로 'Null'값으로 저장 되는듯함.
    @ColumnDefalut 아마 이거는 'Null'이 아닌 아예 ''을 써야 작동 하는듯?

    그래서 이것을 해결하고자

    브라우저에서 클라이언트로 부터 insert요청을 받을 때 입력 하지 않는 값 즉, 'Null'값을 받는 것은 자동으로 제외 해주는
    기능이 @Dynamicinsert 어노테이션이다.

    예를 들자면 이렇게 되는것이다.

    원래 @PostMapping으로 실행 되는 쿼리문은

    insert into User values(id,username,password,email,role,createDate)

    이것인데

    이것을 insert into User values(username,password,email)

    이런식으로 바꿔주는 역활을 한다.

    But 이게 좋은 방법은 아니라고 함.

*/
/*
    @CreationTimestamp
    private Timestamp createDate;
*/

}
