package com.wp.domain.searchword.dto;

import java.time.LocalDateTime;

import com.wp.domain.searchword.SearchWord;
import com.wp.domain.student.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchWordInsertDTO {
	private String sid;
	private String word;
	
	public SearchWord toEntity() {
		SearchWord swData = new SearchWord();
        Student sData = new Student();
        
        sData.setSid(sid);
		
        swData.setStudentForeignkey(sData);
        swData.setWord(word);
        swData.setRegister_date(LocalDateTime.now());
        return swData;
	}
}
