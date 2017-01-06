package org.sample.ssm.web.form;

/**
 * 分页查询基础对象
 */
public class PageQuery {
	private int page;
	private int pageSize;

	public PageQuery() {
	}

	public PageQuery(int page, int pageSize) {
		setPage(page);
		setPageSize(pageSize);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
