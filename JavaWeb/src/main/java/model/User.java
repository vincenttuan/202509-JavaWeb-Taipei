package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
use web;
create table if not exists user (
	id int auto_increment primary key,
    username varchar(50) not null unique,
    salt varchar(50) not null,
    hash varchar(255) not null
);

insert into user (username, salt, hash) values
('admin', 'oKbdpbcvQqP+Oa+Uy0XjdA==', 'e8c929460c099c457a06a70050e5b0e339bf3cd9841acb2ce57765a1b837f856'),
('john', 'rLky4w7gGHvtwy7TCklMOA==', '6d894de2d714f161cc943c841717f5dfb1c37f63b235e1e6efe0d49559affb8e'),
('mary', 'FZWu03S4tEt/S1gZ63J89Q==', '2fdaed2d97b89351858d82a6358cb8f6e759ef1e7c1bb59d783851f542f5abcb');

 * */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private String username;
	private String salt;
	private String hash;
	
}
