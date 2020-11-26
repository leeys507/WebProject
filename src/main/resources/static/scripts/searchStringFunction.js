	function searchStringArrange(str) {
		str = str.trim();
		str = str.replace(/ +/g, " ");
		
		str = str.replace(/\%/gi, '%25');
		
		str = str.replace(/\`/gi, '%60');
		str = str.replace(/\!/gi, '%21');
		str = str.replace(/\@/gi, '%40');
		str = str.replace(/\#/gi, '%23');
		str = str.replace(/\$/gi, '%24');
		str = str.replace(/\^/gi, '%5E');
		str = str.replace(/\&/gi, '%26');
		str = str.replace(/\(/gi, '%28');
		str = str.replace(/\)/gi, '%29');
		str = str.replace(/\=/gi, '%3D');
		str = str.replace(/\\/gi, '%5C');
		str = str.replace(/\|/gi, '%7C');
		str = str.replace(/\[/gi, '%5B');
		str = str.replace(/\]/gi, '%5D');
		str = str.replace(/\{/gi, '%7B');
		str = str.replace(/\}/gi, '%7D');
		str = str.replace(/\,/gi, '%2C');
		str = str.replace(/\//gi, '%2F');
		str = str.replace(/\;/gi, '%3B');
		str = str.replace(/\?/gi, '%3F');
		str = str.replace(/\:/gi, '%3A');
		str = str.replace(/\'/gi, '%27');
		
		return str;
	}
	
	function searchStringChange(str) {
		var regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-+<>@\#$%&\\\=\(\'\"]/gi;
		var chgString = str;
		chgString = chgString.replace(regExp, "");
		
		return chgString;
	}