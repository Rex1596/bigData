app.service('searchService',function($http){
	
	
	this.search=function(searchMap){
		return $http.post('http://localhost:8999/shopping-search-ms/itemsearch-ms/search',searchMap);
	}
	
});