package com.cg.usermanagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

//	public static void main(String[] args) {
//		UserDao dao = new UserDao();
//
//		List<User> userList = dao.getUsers();
//		System.out.println(userList);
//	}

	File file = new File("UserFile.txt");

	// fetches the user details from the list
	public List<User> getAllUsers() {

		List<User> userList = null;

		if (!file.exists()) {

			User user = new User(1, "Monika", "SE");
			userList = new ArrayList<User>();
			userList.add(user);
			saveUser(userList);

		} else {

			try {
				FileInputStream fs = new FileInputStream(file);
				ObjectInputStream obj = new ObjectInputStream(fs);
				userList = (List<User>) obj.readObject();
				obj.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return userList;

	}

	// get user
	public User getUser(int id) {

		List<User> userList = getAllUsers();

		for (User user : userList) {
			if (user.getId() == id) {
				return user;
			} else {
				continue;
			}
		}
		return null;
	}

// add User into List

	public int addUser(User newUser) {

		boolean userExists = false;
		List<User> usersList = getAllUsers();

		for (User user : usersList) {
			if (user.getId() == newUser.getId()) {
				userExists = true;
				break;
			} else {
				usersList.add(newUser);
				saveUser(usersList);
				return 1;
			}

		}

		return 0;
	}

	// Update User
	public int updateUser(User updatedUser) {

		List<User> usersList = getAllUsers();

		for (User user : usersList) {
			if (user.getId() == updatedUser.getId()) {

				int index = usersList.indexOf(user);
				usersList.set(index, updatedUser);
				saveUser(usersList);
				return 1;
			}
		}

		return 0;
	}

	// Delete User from List

	public void deleteUser(int id) {

		List<User> usersList = getAllUsers();

		for (User user : usersList) {
			if (user.getId() == id) {
				int index = usersList.indexOf(user);
				usersList.remove(index);
				saveUser(usersList);

			}
		}

	}
	
	

	// save user details into file
	public void saveUser(List<User> userList) {

		try {
			FileOutputStream fs = new FileOutputStream(file);
			ObjectOutputStream obj = new ObjectOutputStream(fs);
			obj.writeObject(userList);
			obj.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
