//服务层
app.service('goodsService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get('http://localhost:8999/shopping-sellergoods-ms/goods-ms/findAll');		
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get('http://localhost:8999/shopping-sellergoods-ms/goods-ms/findPage?page='+page+'&rows='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get('http://localhost:8999/shopping-sellergoods-ms/goods-ms/findOne?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post('http://localhost:8999/shopping-sellergoods-ms/goods-ms/add',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post('http://localhost:8999/shopping-sellergoods-ms/goods-ms/update',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get('http://localhost:8999/shopping-sellergoods-ms/goods-ms/delete?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post('http://localhost:8999/shopping-sellergoods-ms/goods-ms/search?page='+page+"&rows="+rows, searchEntity);
	}    	
});
