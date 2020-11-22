package com.wp.domain.searchword;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SqlResultSetMapping;

import com.wp.domain.searchword.dto.SearchWordGetDTO;
import com.wp.domain.student.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SqlResultSetMapping(
        name="SearchWordGetDTOMapping",
        classes = @ConstructorResult(
                targetClass = SearchWordGetDTO.class,
                columns = {
                		@ColumnResult(name="word", type = String.class),
                        @ColumnResult(name="rankingchange", type = Long.class),
                })
)

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "searchword")
public class SearchWord {
	@Id
	@Column(name = "idx", columnDefinition = "int")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idx;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sid", nullable = false)
	private Student studentForeignkey;
	
	@Column(name = "word", nullable = false, length = 100)
    private String word;
	
	@Column(name = "register_date", nullable = false)
	private LocalDateTime register_date;
}