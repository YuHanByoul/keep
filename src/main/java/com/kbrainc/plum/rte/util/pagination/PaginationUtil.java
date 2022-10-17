package com.kbrainc.plum.rte.util.pagination;

import java.text.MessageFormat;

/**
 * 
 * 공통PaginationUtil 클래스.
 *
 * <pre>
 * com.kbrainc.plum.rte.util.pagination - PaginationUtil.java
 * </pre>
 *
 * @ClassName : PaginationUtil
 * @Description : 공통Util 클래스
 * @author : KBRAINC
 * @date : 2022. 10. 4
 * @Version :
 * @Company : Copyright KBRAIN Company. All Rights Reserved
 */
public class PaginationUtil {
	
	/**
	 * 페이징 처리 html 생성
	 *
	 * @Title : getPagingHtml
	 * @Description : 페이징 처리 html 생성 카운트가 일정한 경우
	 * @param totalPage 총페이지
	 * @param pageNumber  현재페이지 번호
	 * @param pageCount  로우당 노출되는 페이징 카운트의 수 ex:7) << < 1 2 3 4 5 6 7 > >>
	 * @return String 페이징 html 
	 * @throws Exception 예외
	 */
	public static String getPagingHtml(int totalPage, int pageNumber , int pageCount ) throws Exception {
		StringBuffer paginationStr = new StringBuffer();
		int tmpPageNumber = pageNumber;

		if (totalPage <= tmpPageNumber) {
			tmpPageNumber = totalPage;
		}
		
		int firstPageNum = ((tmpPageNumber - 1) / tmpPageNumber) * pageCount + 1;
		int lastPageNum = firstPageNum + pageCount - 1;
		
		if(Math.floorMod(tmpPageNumber,pageCount) > 0) {
			
			lastPageNum = (Math.floorDiv(tmpPageNumber,pageCount)+1)*pageCount;
			firstPageNum = (Math.floorDiv(tmpPageNumber,pageCount)+1)*pageCount -(pageCount-1);
		}
		
		if (lastPageNum > totalPage) {
			lastPageNum = totalPage;
		}
		
		if (tmpPageNumber > 1) {
			paginationStr.append("<a href=\"javascript:void(0)\" class=\"btn-paging-quick btn-paging-first\" onclick ='goPage(1)'><span class=\"ir\">처음</span></a>");
			paginationStr.append("<a href=\"javascript:void(0)\" class=\"btn-paging-quick btn-paging-prev\" onclick ='goPage(").append(tmpPageNumber - 1).append(")'><span class=\"ir\">처음</span></a>");
		}
		
		for (int i = firstPageNum; i <= lastPageNum; i++) {
			
			if (i == tmpPageNumber) {
				paginationStr.append("<a href='javascript:void(0)' class='btn-paging is-active'>").append(i).append("</a>");
			} else {
				paginationStr.append("<a href='javascript:void(0)' class='btn-paging'  onclick ='goPage(").append(i).append(")'>").append(i).append("</a>");
			}
		}
		if (tmpPageNumber < totalPage) {
			paginationStr.append("<a href=\"javascript:void(0)\" class=\"btn-paging-quick btn-paging-next\" onclick ='goPage(").append(tmpPageNumber + 1).append(")' ><span class=\"ir\">다음</span></a>");
			paginationStr.append("<a href=\"javascript:void(0)\" class=\"btn-paging-quick btn-paging-last\" onclick ='goPage(").append(totalPage).append(")'><span class=\"ir\">마지막</span></a>");
		} 
		
		return paginationStr.toString();
	}
	
	

	/**
	 * 페이징 처리 html 생성
	 *
	 * @Title : getPagingHtml
	 * @Description : 페이징 처리 html 생성 카운트가 항상 자신의페이지가 앞으로 오는 경우   
	 * @param totalPage 총페이지
	 * @param pageNumber  현재페이지 번호
	 * @param pageCount  로우당 노출되는 페이징 카운트의 수 ex:7) << < 1 2 3 4 5 6 7 > >>
	 * @return String 페이징 html 
	 * @throws Exception 예외
	 */
	public static String getPagingHtml2(int totalPage, int pageNumber , int pageCount ) throws Exception {

		String onclick = "";

		StringBuffer paginationStr = new StringBuffer();

		paginationStr.append("<div class=\"panel-body center-align-text\">");
		paginationStr.append("<ul class=\"pagination no-margin\">");

		String previousPageTag = "<li class=\"{2}\"><a href=\"javascript:{0}({1});\">&laquo;</a></li>";
		String currentPageTag = "<li class=\"active\"><a href=\"#\">{0}</a>";
		String otherPageTag = "<li><a href=\"javascript:{0}({1});\">{2}</a>";
		String nextPageTag = "<li class=\"{2}\"><a href=\"javascript:{0}({1});\">&raquo;</a></li>";
		int tmpPageNumber = pageNumber;

		if (totalPage <= tmpPageNumber) {
			tmpPageNumber = totalPage;
		}

		int firstPageNum = ((tmpPageNumber - 1) / pageCount) * pageCount + 1;
		int lastPageNum = firstPageNum + pageCount - 1;

		if (lastPageNum > totalPage) {
			lastPageNum = totalPage;
		}

		if (tmpPageNumber > 1) {
			paginationStr.append(MessageFormat.format(previousPageTag,
					new Object[] { onclick, Integer.toString(tmpPageNumber - 1), "" }));
		} /*
			 * else { result.append(MessageFormat.format(previousPageTag, new Object[] { "",
			 * 1, "disabled" })); }
			 */

		for (int i = firstPageNum; i <= lastPageNum; i++) {
			if (i == tmpPageNumber) {
				paginationStr.append(MessageFormat.format(currentPageTag, new Object[] { Integer.toString(i) }));
			} else {
				paginationStr.append(MessageFormat.format(otherPageTag,
						new Object[] { onclick, Integer.toString(i), Integer.toString(i) }));
			}
		}

		if (tmpPageNumber < totalPage) {
			paginationStr.append(
					MessageFormat.format(nextPageTag, new Object[] { onclick, Integer.toString(tmpPageNumber + 1), "" }));
		} /*
			 * else { result.append(MessageFormat.format(nextPageTag, new Object[] { "",
			 * totalPage, "disabled" })); }
			 */

		paginationStr.append("</ul>");
		paginationStr.append("</div>");

		return paginationStr.toString();
	}
	

}