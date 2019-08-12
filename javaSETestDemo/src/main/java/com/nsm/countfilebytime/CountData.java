package com.nsm.countfilebytime;

import java.time.Instant;
import java.time.LocalDateTime;

/** 
 * @author nasm 
 * @date 创建时间：2018年11月28日 下午6:27:06 
 * @version 1.0  
 */
public class CountData {
	private LocalDateTime time;
	private Integer number;
	private long size;
	
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "CountData [time=" + time + ", number=" + number + ", size="
				+ size/1024 +"kb"+ "]";
	}

	
}
