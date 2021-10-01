package com.home.example.struts2.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
public class GridPageAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -3392473568599235389L;

	protected Map<String, Object> session;

	// get how many rows we want to have into the grid - rowNum attribute in the grid
	protected Integer rows = 0;

	// Get the requested page. By default grid sets this to 1.
	protected Integer page = 0;

	// sorting order - asc or desc
	protected String sord;

	// get index row - i.e. user click to sort.
	protected String sidx;

	// Search Field
	protected String searchField;

	// The Search String
	protected String searchString;

	// he Search Operation ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
	protected String searchOper;

	// Your Total Pages
	protected Integer total = 0;

	// All Record
	protected Integer records = 0;

	protected void printPageVars() {
		log.info(String.format("rows %d. page %d. sord %s. sidx %s. searchString %s. searchOper %s. total %d. records %d.", rows, page,
				sord, sidx, searchString, searchOper, total, records));
	}

	@Override
	public void setSession(final Map<String, Object> session) {
		this.session = session;
	}
}
