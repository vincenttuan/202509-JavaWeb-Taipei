package model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 -- 建立 guestbook 資料表
use web; -- 使用 web 資料庫
create table guestbook (
	id int auto_increment primary key,
    name varchar(50) not null,
    message varchar(255) not null,
    create_at timestamp default current_timestamp
);
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guestbook {
	private Integer id;
	private String name;
	private String message;
	private Timestamp createAt;
}
