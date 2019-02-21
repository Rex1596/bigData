//服务层
var itemCatUrl='http://localhost:8999/shopping-sellergoods-ms/itemCat-ms'
app.service('itemCatService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get(itemCatUrl+'/findAll');		
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get(itemCatUrl+'/findPage?page='+page+'&rows='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get(itemCatUrl+'/findOne?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post(itemCatUrl+'/add',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post(itemCatUrl+'/update',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get(itemCatUrl+'/delete?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post(itemCatUrl+'/search?page='+page+"&rows="+rows, searchEntity);
	}    	
	
	this.findByParentId = function(parentId){
		return $http.get(itemCatUrl+'/findByParentId?parentId='+parentId);
	}
});
