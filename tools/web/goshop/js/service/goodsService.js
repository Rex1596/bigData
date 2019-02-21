//服务层
var GoodsUrl='http://localhost:8999/shopping-sellergoods-ms/goods-ms';
app.service('goodsService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get(GoodsUrl+'/findAll');		
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get(GoodsUrl+'/findPage?page='+page+'&rows='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get(GoodsUrl+'/findOne?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post(GoodsUrl+'/add',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post(GoodsUrl+'/update',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get(GoodsUrl+'/delete?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post(GoodsUrl+'/search?page='+page+"&rows="+rows, searchEntity);
	}    
	
	this.updateStatus = function(ids,status){
		return $http.get(GoodsUrl+'/updateStatus?ids='+ids+"&status="+status);
	}
});
