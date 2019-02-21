//服务层
var url='http://localhost:8999/shopping-sellergoods-ms/brand-Ms';
app.service('brandService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get(url+'/findAll');		
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get(url+'/findPage?pageNum='+page+'&pageSize='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get(url+'/findBrandDetail?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post(url+'/add',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post(url+'/update',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get(url+'/delete?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		return $http.post(url+'/search?page='+page+"&rows="+rows, searchEntity);
	}    
	//下拉列表数据
	this.selectOptionList=function(){
		return $http.get(url+'/selectOptionList');
	}
	
});
