package com.poscoict.mysite.mvc.board;

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
		case "view":
			action = new ViewAction();
			break;
		case "writeform":
			action = new WriteFormAction();
			break;
		case "write":
			action = new WriteAction();
			break;
		case "reply":
			action = new ReplyAction();
			break;
		case "delete":
			action = new DeleteAction();
			break;
		case "modifyform":
			action = new ModifyFormAction();
			break;
		case "modify":
			action = new ModifyAction();
			break;
		default:
			action = new ListAction();
			break;
		}

		return action;
	}

}
