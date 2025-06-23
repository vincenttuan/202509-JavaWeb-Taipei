package model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Guestbook {
	private Integer id;
	private String name;
	private String message;
	private Timestamp createAt;
}
