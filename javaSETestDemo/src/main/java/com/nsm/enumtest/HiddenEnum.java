package com.nsm.enumtest;
/** 
 * @author nasm 
 * @date 创建时间：2019年5月20日 下午5:08:39 
 * @version 1.0  
 */
public enum HiddenEnum {
	ZBMZ(new String[]{"1","2","3"});
	private String[] ids;
	private HiddenEnum(String[] ids){
		this.ids = ids;
	}
	public String[] getIds() {
		String[] tempIds = new String[this.ids.length];
		System.arraycopy(ids, 0, tempIds, 0, this.ids.length);
		return tempIds;
	}
}
