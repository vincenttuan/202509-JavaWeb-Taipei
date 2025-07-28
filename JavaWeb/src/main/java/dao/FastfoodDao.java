package dao;

import java.util.List;

import model.Fastfood;

public interface FastfoodDao {
	Fastfood getFastfoodById(String productId);
	List<Fastfood> findAllFastfoods();
	void addFastfood(String productId, String productName, String productPrice, String productImage);
	void deleteFastfoodById(String productId);
}
