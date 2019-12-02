package com.ll;
import java.io.InputStream;
public class MyNovel {

	private int NovelID;
	public String getNovelName() {
		return NovelName;
	}
	public void setNovelName(String novelName) {
		NovelName = novelName;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public int getNovelType() {
		return NovelType;
	}
	public void setNovelType(int novelType) {
		NovelType = novelType;
	}
	public String getPath() {
		return Path;
	}
	public void setPath(String path) {
		Path = path;
	}
	public InputStream getIs() {
		return Is;
	}
	public void setIs(InputStream is) {
		Is = is;
	}
	public int getNovelID() {
		return NovelID;
	}
	private String NovelName;
	private String Author;
	private int NovelType;
	private String Path;
	private InputStream Is;
	
	
	
}
