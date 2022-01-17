package com.poscoict.mysite.mvc.user;

import com.poscoict.mysite.mvc.main.MainAction;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.mvc.ActionFactory;

public class UserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		switch (actionName) {
		case "joinform":
			action = new JoinFormAction();
			break;
		case "join":
			action = new JoinAction();
			break;
		case "joinsuccess":
			action = new JoinSuccessAction();
			break;
		case "loginform" :
			action = new LoginFormAction();
			break;
		case "login" :
			action = new LoginAction();
			break;
		case "logout" :
			action = new LogoutAction();
			break;
		case "updateform" :
			action = new UpdateFormAction();
			break;
		default:
			action = new MainAction();
			break;
		}

		return action;
	}

}
