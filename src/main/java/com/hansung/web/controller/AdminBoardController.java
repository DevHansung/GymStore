package com.hansung.web.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hansung.web.model.Board;
import com.hansung.web.service.BoardService;

@Controller
@RequestMapping("/admin")
public class AdminBoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/adminViewBoard", method = RequestMethod.GET)
	public String getListPage(Model model, @RequestParam("page") int page) {

		int count = boardService.getBoardCount();
		int postNum = 10;
		int pageNum = (int) Math.ceil((double) count / postNum);
		int displayPost = (page - 1) * postNum;
		int pageNum_cnt = 10;
		int endPageNum = (int) (Math.ceil((double) page / (double) pageNum_cnt) * pageNum_cnt);
		int startPageNum = endPageNum - (pageNum_cnt - 1);
		int endPageNum_tmp = (int) (Math.ceil((double) count / (double) pageNum_cnt));
		if (endPageNum > endPageNum_tmp) {
			endPageNum = endPageNum_tmp;
		}

		boolean prev = startPageNum == 1 ? false : true;
		boolean next = endPageNum * pageNum_cnt >= count ? false : true;

		List<Board> boardList = null;
		boardList = boardService.getBoardListPaging(displayPost, postNum);
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageNum", pageNum);

		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);

		model.addAttribute("prev", prev);
		model.addAttribute("next", next);
		model.addAttribute("page", page);

		return "adminViewBoard";
	}

	// 글쓰기 폼
	@RequestMapping(value = "adminViewBoard/adminWriteBoard/{page}", method = RequestMethod.GET)
	public String writeBoard(@PathVariable int page, Model model) {

		Board board = new Board();
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		return "adminWriteBoard";
	}

	@RequestMapping(value = "adminViewBoard/adminWriteBoard/{page}", method = RequestMethod.POST)
	public String writeBoardPost(@PathVariable int page, @Valid Board board, BindingResult result,
			RedirectAttributes redirectAttributes, Principal principal) {

		board.setWriter(principal.getName());

		// valid에 의해 검증된 후 결과가 bindingResult로 넘어오게 된다.
		if (result.hasErrors()) {
			System.out.println("form data has some errors ");
			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage()); // Product.java에 선언해놓은 message들이 출력될것
			}
			return "writeBoard";
		}

		boardService.addBoard(board);
		redirectAttributes.addAttribute("page", page);
		return "redirect:/admin/adminViewBoard";
	}


	@RequestMapping(value = "/adminViewBoard/adminDeleteBoard/{page}/{boardNum}", method = RequestMethod.GET)
	public String deleteBoard(@PathVariable int page, @PathVariable int boardNum, RedirectAttributes redirectAttributes) {

		Board board = boardService.getBoardByNumber(boardNum); // 한 product를 가져온다음에

		boardService.deleteBoard(board);
		redirectAttributes.addAttribute("page", page);
		return "redirect:/admin/adminViewBoard";
	}


}