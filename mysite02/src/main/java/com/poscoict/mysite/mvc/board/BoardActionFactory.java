package com.poscoict.mysite.mvc.board;

import com.poscoict.mysite.mvc.main.MainAction;
import com.poscoict.web.mvc.Action;
import com.poscoict.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		switch (actionName) {
//		case "list":
//			action = new ListAction();
//			break;
//		case "view":
//			action = new ViewAction();
//			break;
//		case "addform":
//			action = new AddFormAction();
//			break;
//		case "add":
//			action = new AddAction();
//			break;
//		case "delete":
//			action = new DeleteAction();
//			break;
//		case "updateform":
//			action = new UpdateFormAction();
//			break;
//		case "update":
//			action = new UpdateAction();
//			break;
		default:
			action = new ListAction();
			break;
		}

		return action;
	}

}
