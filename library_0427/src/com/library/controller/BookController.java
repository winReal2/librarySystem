package com.library.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.service.BookService;
import com.library.vo.Criteria;

@WebServlet("*.book")
public class BookController extends HttpServlet{

	BookService bs = new BookService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		System.out.println("요청 uri : " + uri);
		
		
		if(uri.indexOf("list") > 0) {
			
			// 검색조건 세팅 
			Criteria cri = new Criteria(
									req.getParameter("searchField")
									,req.getParameter("searchWord")
									,req.getParameter("pageNo"));
			
			// 리스트 조회및 요청객체에 저장
			req.setAttribute("map", bs.getList(cri));
			
			// 포워딩
			req.getRequestDispatcher("./list.jsp").forward(req, resp);  //("./list.jsp")
			
		} else if (uri.indexOf("delete") > 0) {
			int res = bs.delete(req.getParameter("delNo"));
			
			if(res>0) {
				req.setAttribute("message", res + "건 삭제 되었습니다.");
			} else {
				req.setAttribute("message", "삭제 실패! 관리자에게 문의해주세요.");
			}
			
			// 포워딩
			req.getRequestDispatcher("./list.book").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	public BookController() {
		// TODO Auto-generated constructor stub
	}

}
