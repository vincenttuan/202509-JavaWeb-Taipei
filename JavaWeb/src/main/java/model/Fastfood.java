package model;

import lombok.Data;
/*
use web;
create table if not exists fastfood(
	product_id varchar(10) primary key,
    product_name varchar(50) not null,
    product_price int not null,
    product_image text
);
 * */
@Data
public class Fastfood {
	private String productId;
	private String productName;
	private int productPrice;
	private String productImage;
	
}
