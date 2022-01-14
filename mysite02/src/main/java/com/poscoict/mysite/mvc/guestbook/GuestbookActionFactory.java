package com.poscoict.mysite.mvc.guestbook;

import com.poscoict.web.mvc.Action;
import com.poscoict.web.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		switch (actionName) {
		case "deleteform":
			action = new DeleteFormAction();

			break;
		case "delete":
			break;
		case "add":
			break;
		default:
			action = new IndexAction();
			break;
		}

		return action;
	}

}
