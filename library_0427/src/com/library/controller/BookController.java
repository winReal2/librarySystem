package com.library.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import com.library.service.BookService;
import com.library.vo.Book;
import com.library.vo.Criteria;
import com.oreilly.servlet.MultipartRequest;

import common.FileUtil;
import common.JSFunction;

@WebServlet("*.book")
public class BookController extends HttpServlet{

	BookService bs = new BookService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		System.out.println("요청 uri : " + uri);
		
		// 빈화면이라면 일단 uri로 들어온것! 그래서 요청 uri로 확인!
		// 404는 오류페이지기때문에 오타(대소문자) 등으로 발생한다.
		
		if(uri.indexOf("list") > 0) {
			
			// 검색조건 세팅 
			Criteria cri = new Criteria(
									req.getParameter("searchField")
									,req.getParameter("searchWord")
									,req.getParameter("pageNo"));
			
			// 리스트 조회및 요청객체에 저장
			//req.setAttribute("map", bs.getList(cri));
			Map<String, Object> map = bs.getList(cri);
			req.setAttribute("map", map);
			
			// 포워딩
			req.getRequestDispatcher("./list.jsp").forward(req, resp);  //("./list.jsp")
//===========================================================================================			
		} else if (uri.indexOf("delete") > 0) {
			int res = bs.delete(req.getParameter("delNo"));
			System.out.println("res : " + res);
			if(res>0) {
				req.setAttribute("message", res + "건 삭제 되었습니다.");
			} else {
				req.setAttribute("message", "삭제 실패! 관리자에게 문의해주세요.");
			}
			
			// 포워딩
			req.getRequestDispatcher("./list.book").forward(req, resp);
//===========================================================================================			
		} else if (uri.indexOf("write") > 0){
			resp.sendRedirect("./write.jsp");
		
//===========================================================================================			
		} else if(uri.indexOf("view") > 0) {
			Book book = bs.selectOne(req.getParameter("no"));
			req.setAttribute("dto", book);
			System.out.println("book : " + book);
			if(book != null) {
				//상세화면
				req.getRequestDispatcher("./view.jsp").forward(req, resp);
			} else {
				JSFunction.alertBack(resp, "도서번호에 해당하는 도서가 존재하지 않습니다");
			}
//===========================================================================================			
		} else {
			JSFunction.alertBack(resp, "url을 확인해주세요");
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		System.out.println("요청 uri : " + uri);
		
		//write를 포함하고 있으면~
		if(uri.indexOf("write") > 0) {
			
			//도서등록 (경로주기)
			String saveDirectory = "C:\\Users\\user\\git\\librarySystem\\library_0427\\webapp\\images\\bookImg";
			
			MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, 1024*1000);
			Book book = new Book(mr.getParameter("title")
								, mr.getParameter("author"));
			
			//원본의 이름
			String ofile = mr.getFilesystemName("bookImg");
			System.out.println("ofile : " + ofile);
			
			//파일을 등록할수도, 안할수도 있다
			if(ofile != null && !"".equals(ofile)) {
				String sfile = FileUtil.fileNameChange(saveDirectory, ofile);

			//다른 데이터 받아서 북에 담아준다
			book.setOfile(ofile);
			book.setSfile(sfile);
			}
		
			//도서등록까지
			int res = bs.insert(book);
			System.out.println("요청 uri : " + uri);
			System.out.println("res : " + res);
			
			if(res > 0) {
				JSFunction.alertLocation(resp, "./list.book", "등록되었슴다!");
			} else {
				JSFunction.alertBack(resp, "등록중 예외방생!");
			}
		
		} else if(uri.indexOf("rent") > 0 ) {
			//로그인 아이디 확인
			HttpSession session = req.getSession();
			if(session.getAttribute("userId") == null) {
				JSFunction.alertBack(resp, "로그인 후 이용가능한 메뉴입니다!");
				return;
			}
			
			//대여하기 - 책번호, 로그인 아이디
			Book book = new Book();
			book.setNo(req.getParameter("no"));
			book.setId(session.getAttribute("userId").toString());
			
			int res = bs.rentBook(book);
			
			if(res > 0) {					//컨트롤러 호출 .book
				JSFunction.alertLocation(resp, "./view.book?no=" + book.getNo(), "대여되었습니다.");
			} else {
				JSFunction.alertBack(resp, "대여중 오류가 발생하였습니다.");
			}
		}
	}
	
	public BookController() {
		// TODO Auto-generated constructor stub
	}

}
