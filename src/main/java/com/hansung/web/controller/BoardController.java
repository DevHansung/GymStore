package com.hansung.web.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hansung.web.model.Board;
import com.hansung.web.model.BoardReply;
import com.hansung.web.service.BoardReplyService;
import com.hansung.web.service.BoardService;

//Controller(request) -> service -> dao -> mapper.xml -> dao -> service
//-> Controller(바인딩되서 return된 값을 model.addAttribute) -> view(response)
@Controller
public class BoardController {

	@Autowired
	private BoardService boardservice;

	@Autowired
	private BoardReplyService boardReplyService;

	@RequestMapping(value = "/viewBoard", method = RequestMethod.GET)
	public String getListPage(Model model, @RequestParam("page") int page) {
		// 페이징 처리
		// 게시물 총 갯수
		int count = boardservice.getBoardCount();
		// 한 페이지에 출력할 게시물 갯수 제한
		int postNum = 10;
		// 총 페이징 번호 ([ 게시물 총 갯수 ÷ 한 페이지에 출력할 갯수 ]의 올림)
		int pageNum = (int) Math.ceil((double) count / postNum);
		// 출력할 게시물
		int displayPost = (page - 1) * postNum;
		// 한번에 표시할 페이징 번호의 갯수
		int pageNum_cnt = 10;
		// 표시되는 페이지 번호 중 마지막 번호
		int endPageNum = (int) (Math.ceil((double) page / (double) pageNum_cnt) * pageNum_cnt);
		// 표시되는 페이지 번호 중 첫번째 번호
		int startPageNum = endPageNum - (pageNum_cnt - 1);
		// 마지막 번호 재계산
		int endPageNum_tmp = (int) (Math.ceil((double) count / (double) pageNum_cnt));
		if (endPageNum > endPageNum_tmp) {
			endPageNum = endPageNum_tmp;
		}

		boolean prev = startPageNum == 1 ? false : true;
		boolean next = endPageNum * pageNum_cnt >= count ? false : true;

		List<Board> boardList = null;
		boardList = boardservice.getBoardListPaging(displayPost, postNum);
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageNum", pageNum);

		// 게시물 총 개수/페이지당 글 개수로 연산을 했을때 시작 및 끝 번호 모델에 값을 담아줌
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);

		// 페이지의 이전 및 다음버튼 활성화를 위해 모델에 값을 담아줌
		model.addAttribute("prev", prev);
		model.addAttribute("next", next);

		// 현재 페이지
		model.addAttribute("page", page);

		return "viewBoard";
	}

	@RequestMapping(value = "viewBoard/writeBoard/{page}", method = RequestMethod.GET)
	public String writeBoard(@PathVariable int page, Model model) {

		Board board = new Board();
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		return "writeBoard";
	}

	@RequestMapping(value = "viewBoard/writeBoard/{page}", method = RequestMethod.POST)
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

		boardservice.addBoard(board);
		redirectAttributes.addAttribute("page", page);
		return "redirect:/viewBoard";
	}

	@RequestMapping(value = "/viewBoard/updateBoard/{page}/{boardNum}", method = RequestMethod.GET)
	public String updateBoard(@PathVariable int page, @PathVariable int boardNum, Model model,
			RedirectAttributes redirectAttributes, Principal principal) {

		String writer = boardservice.getBoardByWriter(boardNum);
		String userId = principal.getName();
		System.out.println(writer);
		System.out.println(userId);
		// 로그인 유저와 게시글 작성자가 같다면 글 수정 form return
		if (userId.equals(writer)) {
			Board board = boardservice.getBoardByNumber(boardNum);
			model.addAttribute("board", board);
			model.addAttribute("page", page);
			return "updateBoard";
		}

		redirectAttributes.addAttribute("page", page);
		return "redirect:/viewBoard";
	}

	// 수정된 글을 post형식으로 request
	@RequestMapping(value = "/viewBoard/updateBoard/{page}", method = RequestMethod.POST)
	public String updateBoardPost(@PathVariable int page, @Valid Board board, BindingResult result,
			RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			System.out.println("form data has some errors ");
			List<ObjectError> errors = result.getAllErrors();

			for (ObjectError error : errors) {
				System.out.println(error.getDefaultMessage()); // Product.java에 선언해놓은 message들이 출력될것
			}
			return "updateBoard";
		}
		boardservice.updateBoard(board);
		redirectAttributes.addAttribute("page", page);
		return "redirect:/viewBoard";
	}

	@RequestMapping(value = "/viewBoard/deleteBoard/{page}/{boardNum}", method = RequestMethod.GET)
	public String deleteBoard(@PathVariable int page, @PathVariable int boardNum, RedirectAttributes redirectAttributes,
			Principal principal) {
		// loginuser와 boardwriter를 대조하여 검증하는 작업
		String writer = boardservice.getBoardByWriter(boardNum);
		String userId = principal.getName();
		if (userId.equals(writer)) {
			Board board = boardservice.getBoardByNumber(boardNum); // 한 product를 가져온다음에
			boardservice.deleteBoard(board); // 삭제 실행
			redirectAttributes.addAttribute("page", page);
			return "redirect:/viewBoard";
		}
		redirectAttributes.addAttribute("page", page);
		return "redirect:/viewBoard";
	}

	// 글 상세보기
	@RequestMapping("/detailBoard/{page}/{boardNum}")
	public String viewBoard(@PathVariable int page, @PathVariable int boardNum, Model model) {

		Board board = boardservice.getBoardByNumber(boardNum);
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		boardservice.updateViewCount(boardNum); // 게시판 글 조회수 counter

		List<BoardReply> replyList = boardReplyService.getReplyList(boardNum);
		model.addAttribute("replyList", replyList);
		return "detailBoard";
	}

	// 댓글 삭제(ajax)
	@RequestMapping(value = "/board/deleteReply")
	@ResponseBody
	public String deleteReply(@RequestBody Map<String, Integer> json, Principal principal) {
		int replyNum = json.get("replyNum");
		String result = "error";
		String boardWriter = boardReplyService.getReplyByWriter(replyNum);
		String userId = principal.getName();

		if (boardWriter.equals(userId)) {
			boardReplyService.deleteReply(replyNum); // 삭제 실행
			result = "success";
		}
		return result;
	}

	// 댓글 작성 ajax
	@RequestMapping(value = "/board/addReply")
	@ResponseBody
	public String ajax_addReply(@ModelAttribute("BoardReply") BoardReply boardReply, Principal principal)
			throws Exception {

		try {

			boardReply.setReplyWriter(principal.getName());
			boardReplyService.addReply(boardReply);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	// 게시물 댓글 불러오기(Ajax)
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/board/replyList", produces = "application/json; charset=utf8")
	@ResponseBody
	public ResponseEntity ajax_replyList(@ModelAttribute("BoardReply") BoardReply boardReply,
			HttpServletRequest request) throws Exception {

		HttpHeaders responseHeaders = new HttpHeaders();
		ArrayList<HashMap> hmlist = new ArrayList<HashMap>();
		int boardNum = boardReply.getBoardNum();
		// 해당 게시물 댓글
		List<BoardReply> replyList = boardReplyService.getReplyList(boardNum);

		if (replyList.size() > 0) {
			for (int i = 0; i < replyList.size(); i++) {
				HashMap hm = new HashMap();
				hm.put("replyNum", replyList.get(i).getReplyNum());
				hm.put("replyText", replyList.get(i).getReplyText());
				hm.put("replyWriter", replyList.get(i).getReplyWriter());
				hm.put("replyDate", replyList.get(i).getReplyDate());

				hmlist.add(hm);
			}

		}

		JSONArray json = new JSONArray(hmlist);
		return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
	}
}
