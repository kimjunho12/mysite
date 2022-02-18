package com.poscoict.mysite.vo;

public class GalleryVo {
	private Long no;
	private String url;
	private String comment;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", url=" + url + ", comment=" + comment + "]";
	}

}
