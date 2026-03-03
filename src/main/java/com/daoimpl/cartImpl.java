package com.daoimpl;

import java.util.HashMap;
import java.util.Map;

import com.dao.cartDao;
import com.daomodel.cart_item;

public class cartImpl implements  cartDao  {

	Map<Integer, cart_item> map	=new HashMap<Integer, cart_item>();
	@Override
	public void addToCart(cart_item cartItem) {
		// TODO Auto-generated method stub
		int menuid=cartItem.getMenu_id();
		if(map.containsKey(menuid)){
			cart_item existing_item=map.get(menuid);
			existing_item.setQuantity(existing_item.getQuantity()+cartItem.getQuantity());
		}
		else {
			map.put(menuid, cartItem);
		}
			
	}

	@Override
	public void  updateTheCart(int menu_id, int quantity) {
		// TODO Auto-generated method stub
		if(map.containsKey(menu_id)) {
			if(quantity<=0) {
				map.remove(menu_id);
			}
			else {
				map.get(menu_id).setQuantity(quantity);
			}
			
		}
		
	
	}

	@Override
	public void  deleteTheCart(int menu_id) {
		map.remove(menu_id);
		
	}
	
	public Map<Integer, cart_item> getCartItems() {
		return map;
	}

}

