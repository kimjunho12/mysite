package com.poscoict.web.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	// ActionFactory를 통해 결정된 요청의 실제 동작 과정
	// (Interface로 구현하여 요청마다 다른 동작을 처리하지만 Controller에서는 Action Interface로 묶어서 참조하여 실제 class의 전부를 알 필요 없음 -> 확장성 증대 및 의존성 감소)
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
