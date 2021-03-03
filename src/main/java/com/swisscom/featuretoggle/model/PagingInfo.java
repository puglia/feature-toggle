package com.swisscom.featuretoggle.model;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class PagingInfo {

	@QueryParam(value = "page")@DefaultValue(value = "0")
	private int page;
	@QueryParam(value = "pageSize")@DefaultValue(value = "10")
	private int pageSize;

	public PagingInfo() {

	}

	public PagingInfo(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
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

	public Pageable toPageable() {
		return PageRequest.of(page, pageSize);
	}

}
