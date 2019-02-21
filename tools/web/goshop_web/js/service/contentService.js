app.service("contentService",function($http){
	this.findByCategoryId = function(categoryId){
		return $http.get("http://localhost:8999/shopping-content-ms/content/findByCategory?categoryId="+categoryId);
	}
});