package com.lbs.mongo.app.map.entity;

import org.bson.types.ObjectId;

import com.lbs.mongo.app.map.entity.emb.Colum;
import com.lbs.mongo.app.map.entity.emb.Loc;

public class Position {
	
	private ObjectId id;
	
	private int no;				//일련번호
	private String name;
	private int flag = 0;
	private String regDate;		//생성날짜 yyyyMMddHHmmss
	private String modDate;		//수정날짜 yyyyMMddHHmmss

	private Colum colum;
	private Loc loc;			//좌표

	public Position(){}
	
	public Position(ObjectId id, int no, String name, int flag, String regDate,
			String modDate, Colum colum, Loc loc) {
		super();
		this.id = id;
		this.no = no;
		this.name = name;
		this.flag = flag;
		this.regDate = regDate;
		this.modDate = modDate;
		this.colum = colum;
		this.loc = loc;
	}
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public Loc getLoc() {
		return loc;
	}
	public void setLoc(Loc loc) {
		this.loc = loc;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public Colum getColum() {
		return colum;
	}
	public void setColum(Colum colum) {
		this.colum = colum;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getModDate() {
		return modDate;
	}
	public void setModDate(String modDate) {
		this.modDate = modDate;
	}
	@Override
	public String toString() {
		return "Position [colum=" + colum + ", flag=" + flag + ", id=" + id
				+ ", loc=" + loc + ", modDate=" + modDate + ", name=" + name
				+ ", no=" + no + ", regDate=" + regDate + "]";
	}
	
	
	
	
	
}
