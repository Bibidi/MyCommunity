/**
 * 
 */


console.log("Post Module .......");

const postService = (function() {
	
	const csrfHeaderName = document.querySelector("meta[name=csrfHeaderName]").getAttribute("content");
	const csrfToken = document.querySelector("meta[name=csrfTokenValue]").getAttribute("content");
	const fakeForumSlug = "temp";
	
	
	function addPost(post, callback, error) {
		
	}
	
	function getPost(postNumber, callback, error) {
		
	}
	
	function getPosts(param, callback, error) {
		
	}
	
	function updatePost(post, callback, error) {
		$.ajax({
			type : "PATCH",
			url : '/posts/' + fakeForumSlug + '/' + post.number,
			beforeSend : function(xhr) {
				xhr.setRequestHeader(csrfHeaderName, csrfToken);
			},
			data : JSON.stringify(post),
			contentType : 'application/json',
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
		});
	}
	
	function deletePost(postNumber, callback, error) {
		
		$.ajax({
			type : 'DELETE',
			url : '/posts/' + fakeForumSlug + '/' + postNumber,
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
		});
	}
	
	return {
		addPost : addPost,
		getPost : getPost,
		getPosts : getPosts,
		updatePost : updatePost,
		deletePost : deletePost
	};
})();