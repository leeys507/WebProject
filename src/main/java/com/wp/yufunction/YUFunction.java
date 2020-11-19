package com.wp.yufunction;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class YUFunction {
	public String createSubFolderName(String sid) {
        String chgSid = "";
        char c;
        int val = -2;
        int res;
        
        for(int i = 0; i < sid.length(); i++) {
        	res = ((int)sid.charAt(i) + 60 + (i * (-val) + 3));
        	if(res > 122) res -= 12;
        	c = (char)res;
        	chgSid += c;
        }
        chgSid += (char)((int)sid.charAt(3) + (int)sid.charAt(5) + 1);
        chgSid += (char)((int)sid.charAt(4) + (int)sid.charAt(7) + 1);
        
        return chgSid;
    }
	
	public boolean boardTypeCheck(String boardtype) {
		switch(boardtype) {
		case "자유게시판":
			return true;
		case "중고게시판":
			return true;
		case "정보게시판":
			return true;
		default:
			return false;
		}
	}
	
	public boolean matchingTypeCheck(String boardtype) {
		switch(boardtype) {
		case "청소":
			return true;
		case "배달":
			return true;
		case "역할대행":
			return true;
		case "심부름":
			return true;
		default:
			return false;
		}
	}
}
