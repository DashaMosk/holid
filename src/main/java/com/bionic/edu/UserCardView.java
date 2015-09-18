package com.bionic.edu;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class UserCardView {
	
	private UserCardBean userCard;
	
	public String showUserCard(Users user) {
		userCard = new UserCardBean();
		userCard.setIdUser(user.getIdUser());
		userCard.setLogin(user.getLogin());
		userCard.setRights(user.getRights());
		return "userCard.xhtml?faces-redirect=true";
	}

}
