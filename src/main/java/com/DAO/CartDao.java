package com.DAO;

import java.util.List;

import com.entity.BookDtls;
import com.entity.Cart;

public interface CartDao {
 
	public boolean addCart(Cart c);
	
	public List<Cart> getBookByUser(int userId);
	
	public boolean deletebook(int bid,int uid,int cid);
	
}
