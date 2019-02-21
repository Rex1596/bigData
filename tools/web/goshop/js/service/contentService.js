//服务层
var contentUrl='http://localhost:8999/shopping-content-ms/content';
app.service('contentService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=	function(){
		return $http.get(contentUrl+'/findAll');		
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get(contentUrl+'/findPage?page='+page+'&rows='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get(contentUrl+'/findOne?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post(contentUrl+'/add',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post(contentUrl+'/update',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get(contentUrl+'/delete?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post(contentUrl+'/search?page='+page+"&rows="+rows, searchEntity);
	}    	
});
