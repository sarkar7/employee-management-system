package com.codeonist.ems.services;

import com.codeonist.ems.beans.User;

public interface UserService {

	void save(User user);

	User findByUsername(String username);

}
