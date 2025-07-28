package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Fastfood;

public class FastfoodDaoMySQL extends BaseDao implements FastfoodDao {

	@Override
	public Fastfood getFastfoodById(String productId) {
		String sql = "select product_id, product_name, product_price, product_image from fastfood where product_id = ?";
		try(PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setString(1, productId);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					Fastfood fastfood = new Fastfood();
					fastfood.setProductId(rs.getString("product_id"));
					fastfood.setProductName(rs.getString("product_name"));
					fastfood.setProductPrice(rs.getInt("product_price"));
					fastfood.setProductImage(rs.getString("product_image"));
					return fastfood;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Fastfood> findAllFastfoods() {
		List<Fastfood> fastfoods = new ArrayList<>();
		String sql = "select product_id, product_name, product_price, product_image from fastfood";
		try(Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);) {
			
			while (rs.next()) {
				Fastfood fastfood = new Fastfood();
				fastfood.setProductId(rs.getString("product_id"));
				fastfood.setProductName(rs.getString("product_name"));
				fastfood.setProductPrice(rs.getInt("product_price"));
				fastfood.setProductImage(rs.getString("product_image"));
				// 加入到 fastfoods 集合
				fastfoods.add(fastfood);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fastfoods;
	}

	@Override
	public void addFastfood(String productId, String productName, String productPrice, String productImage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFastfoodById(String productId) {
		// TODO Auto-generated method stub
		
	}
	
}
