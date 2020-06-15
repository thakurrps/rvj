package com.dishu.shoppingbackend.daoimpl;

import java.util.List;

import com.dishu.shoppingbackend.dao.UserDAO;
import com.dishu.shoppingbackend.dto.Address;
import com.dishu.shoppingbackend.dto.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addAddress(Address address) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Address getBillingAddress(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> listShippingAddresses(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
