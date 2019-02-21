//服务层
var contentCategoryUrl='http://localhost:8999/shopping-content-ms/contentCategory';
app.service('contentCategoryService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get(contentCategoryUrl+'/findAll');		
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get(contentCategoryUrl+'/findPage?page='+page+'&rows='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get(contentCategoryUrl+'/findOne?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post(contentCategoryUrl+'/add',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post(contentCategoryUrl+'/update',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get(contentCategoryUrl+'/delete?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post(contentCategoryUrl+'/search?page='+page+"&rows="+rows, searchEntity);
	}    	
});
