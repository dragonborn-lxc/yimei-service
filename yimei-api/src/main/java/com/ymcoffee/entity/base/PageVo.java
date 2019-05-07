package com.ymcoffee.entity.base;

import lombok.Getter;
import lombok.Setter;

/**
 * File Name: Page
 * General Description: Copyright and file header.
 * Revision History:
 * Modification
 * Author                Date(MM/DD/YYYY)   JiraID           Description of Changes
 * ---------------------   ------------    ----------     -----------------------------
 * Le xing chen            2019/5/5
 */
@Getter
@Setter
public class PageVo {
	private Integer pageNumber;
	private Integer pageSize;
	private Integer sort;
}
