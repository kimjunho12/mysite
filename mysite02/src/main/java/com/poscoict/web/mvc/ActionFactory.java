package com.poscoict.web.mvc;

public abstract class ActionFactory {
	// actionName을 통해 client가 어떤 요청을 요구하는지 구분
    public abstract Action getAction(String actionName);
}
