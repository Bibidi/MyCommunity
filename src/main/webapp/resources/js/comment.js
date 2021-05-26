/**
 * 
 */


console.log("Comment Module..........");


const commentService = (function() {
	
	const csrfHeaderName = document.querySelector("meta[name=csrfHeaderName]").getAttribute("content");
	const csrfToken = document.querySelector("meta[name=csrfTokenValue]").getAttribute("content");
	const fakePostNumber = 0;
	
	function addComment(comment, callback, error) {
		console.log("reply........");
		
		$.ajax({
			type : 'POST',
			url : '/comments/' + comment.postNumber,
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeaderName, csrfToken);
			},
			data : JSON.stringify(comment),
			contentType : "application/json",
			success : function(data, status, xhr) {
				if (callback) {
					callback(data);
				}
			},
			error : function(xhr, status, errorThrown) {
				if (error) {
					error(errorThrown);
				}
			}
		})
	}
	
	function getComments(param, callback, error) {
		
		const postNumber = param.postNumber;
		const pageNumber = param.pageNumber;
				
		let paramString = "";
		if (pageNumber) paramString += "?pageNumber=" + pageNumber;
		
		$.ajax({
			type : 'GET',
			url : '/comments/' + postNumber + ".json" + paramString,
			success : function(data, status, xhr) {
				if (callback) {
					callback(data);
				}
			},
			error : function(xhr, status, errorThrown) {
				if (error) {
					error(errorThrown);
				}
			}
		})
	}
	
	function updateComment(comment, callback, error) {
		
		$.ajax({
			type : 'PATCH',
			url : '/comments/' + fakePostNumber + '/' + comment.number,
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeaderName, csrfToken);
			},
			data : JSON.stringify(comment),
			contentType : "application/json",
			success : function(data, status, xhr) {
				if (callback) {
					callback(data);
				}
			},
			error : function(xhr, status, errorThrown) {
				if (error) {
					error(errorThrown);
				}
			}
		})
	}
	
	function removeComment(commentNumber, callback, error) {
		
		$.ajax({
			type : 'DELETE',
			url : '/comments/' +  fakePostNumber + '/' + commentNumber,
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeaderName, csrfToken);
			},
			success : function(data, status, xhr) {
				if (callback) {
					callback(data);
				}
			},
			error : function(xhr, status, errorThrown) {
				if (error) {
					error(errorThrown);
				}
			}
		})
	}
	
	
	return {
		addComment : addComment,
		getComments : getComments,
		updateComment : updateComment,
		removeComment : removeComment
	};
})();

