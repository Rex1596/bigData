//服务层
var TypeUrl='http://localhost:8999/shopping-sellergoods-ms/typeTemplate-Ms';
app.service('typeTemplateService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get(TypeUrl+'/findAll');		
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get(TypeUrl+'/findPage?page='+page+'&rows='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get(TypeUrl+'/findOne?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post(TypeUrl+'/add',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post(TypeUrl+'/update',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get(TypeUrl+'/delete?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post(TypeUrl+'/search?page='+page+"&rows="+rows, searchEntity);
	}    	
});
