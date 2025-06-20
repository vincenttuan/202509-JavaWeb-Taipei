package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Breakfast {
	private String id; // B01
	private String name; // 漢堡
	private Integer price; // 40
	private Category category; // 分類
	
}
