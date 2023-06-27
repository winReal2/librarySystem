package com.library.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.library.dao.BookDao;
import com.library.vo.Book;
import com.library.vo.Criteria;
import com.library.vo.PageDto;

public class BookService {
	BookDao dao = new BookDao();
	
	/**
	 * 책 리스트를 조회 합니다.
	 * @param cri 
	 * @return
	 */
	public Map<String, Object> getList(Criteria cri){
		Map<String, Object> map = new HashMap<>();

		// 리스트 조회
		List<Book> list = dao.getList(cri);
		System.out.println("list : " + list);
		
		// 총 건수
		int totalCnt = dao.getTotalCnt(cri);
		
		// 페이지DTO
		PageDto pageDto = new PageDto(totalCnt, cri);
		
		
		map.put("list", list);
		map.put("totalCnt", totalCnt);
		map.put("pageDto", pageDto);
		
		
		return map;
	}

	/**
	 * 도서 정보 입력
	 */
	public void insert(String title, String author) {
		Book book = new Book(title, author);
		int res = dao.insert(book);
		if(res > 0) {
			System.out.println(res + "건 입력 되었습니다.");
		} else {
			System.err.println("입력중 오류가 발생 하였습니다.");
			System.err.println("관리자에게 문의 해주세요");
		}
	}

	public int delete(String noStr) {
		int res = dao.delete(noStr);
		/*
		if(res>0) {
			System.out.println(res+"건 삭제되었습니다.");
		} else {
			System.err.println("삭제중 오류가 발생 하였습니다.");
			System.err.println("관리자에게 문의 해주세요");
		}
		 => 컨트롤러로 이동*/
		return res;
	}

	public void rentBook(int bookNo) {
		// 대여가능한 도서인지 확인
		String rentYN = dao.getRentYN(bookNo);
		if("Y".equals(rentYN)) {
			System.err.println("이미 대여중인 도서 입니다.");
		} else if ("".equals(rentYN)) {
			System.out.println("없는 도서 번호 입니다.");
		}
		
		// 대여처리
		int res = dao.update(bookNo, "Y");
		
		if(res>0) {
			System.out.println(res + "건 대여 되었습니다.");
		}else {
			System.out.println("대여중 오류가 발생 하였습니다.");
			System.out.println("관리자에게 문의 해주세요");
		}
	}

	public void returnBook(int bookNo) {
		// 반납가능한 도서인지 확인
		String rentYN = dao.getRentYN(bookNo);
		if("N".equals(rentYN)) {
			System.err.println("반납 가능한 상태가 아닙니다.");
		} else if ("".equals(rentYN)) {
			System.out.println("없는 도서 번호 입니다.");
		}
		
		// 반납처리
		int res = dao.update(bookNo, "N");
		
		if(res>0) {
			System.out.println(res + "건 반납 되었습니다.");
		}else {
			System.out.println("반납 처리 중 오류가 발생 하였습니다.");
			System.out.println("관리자에게 문의 해주세요");
		}
	}
	
}













