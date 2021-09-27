package grid.example;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;

public class GridViewAction extends ActionSupport {
	private static final long serialVersionUID = 5647458607414204420L;

	// Your grid result List
	private List<StudentBean> gridModel = new ArrayList<StudentBean>();

	// get how many rows we want to have into the grid - rowNum attribute in the grid
	private Integer rows = 0;

	// Get the requested page. By default grid sets this to 1.
	private Integer page = 0;

	// sorting order - asc or desc
	private String sord;

	// get index row - i.e. user click to sort.
	private String sidx;

	// Search Field
	private String searchField;

	// The Search String
	private String searchString;

	// he Search Operation ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
	private String searchOper;

	// Your Total Pages
	private Integer total = 0;

	// All Record
	private Integer records = 0;

	// This is temporary data set for test
	private List<StudentBean> createGridList() {
		System.out.println("searchField: " + searchField);
		List<StudentBean> gridModel = new ArrayList<StudentBean>();
		StudentBean student = new StudentBean();
		student.setName("Malith");
		student.setAge(20);
		student.setEmail("mailtomalith@yahoo.com");
		student.setAddress("Kandy");
		student.setContact("1234");
		gridModel.add(student);
		student = new StudentBean();
		student.setName("Nilantha");
		student.setAge(21);
		student.setEmail("mailthmeee@gmail.com");
		student.setAddress("Kandy");
		student.setContact("56789");
		gridModel.add(student);
		if (searchField != null && !"".equals(searchField)) {
			student = new StudentBean();
			student.setName("DANI");
			student.setAge(40);
			student.setEmail("DANI@gmail.com");
			student.setAddress("SAN DIEGO");
			student.setContact("56789");
			gridModel.add(student);
		}
		return gridModel;
	}

	public List<StudentBean> getGridModel() {
		return gridModel;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getRecords() {
		return records;
	}

	public Integer getRows() {
		return rows;
	}

	public String getSearchField() {
		return searchField;
	}

	public String getSearchOper() {
		return searchOper;
	}

	public String getSearchString() {
		return searchString;
	}

	public String getSidx() {
		return sidx;
	}

	public String getSord() {
		return sord;
	}

	public Integer getTotal() {
		return total;
	}

	public void setGridModel(final List<StudentBean> gridModel) {
		this.gridModel = gridModel;
	}

	public void setPage(final Integer page) {
		this.page = page;
	}

	public void setRecords(final Integer records) {
		this.records = records;
	}

	public void setRows(final Integer rows) {
		this.rows = rows;
	}

	public void setSearchField(final String searchField) {
		this.searchField = searchField;
	}

	public void setSearchOper(final String searchOper) {
		this.searchOper = searchOper;
	}

	public void setSearchString(final String searchString) {
		this.searchString = searchString;
	}

	public void setSidx(final String sidx) {
		this.sidx = sidx;
	}

	public void setSord(final String sord) {
		this.sord = sord;
	}

	public void setTotal(final Integer total) {
		this.total = total;
	}

	@SkipValidation
	public String viewGrid() {
		int to = rows * page;
		int from = to - rows;

		// Count Rows (select count(*) from custumer)
		records = gridModel.size();
		// Your logic to search and select the required data.
		// gridModel = studentService.getStudentList(from, to);
		gridModel = createGridList();

		// calculate the total pages for the query
		total = (int) Math.ceil((double) records / (double) rows);
		return SUCCESS;
	}
}