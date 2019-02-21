//服务层
var SpeciUrl='http://localhost:8999/shopping-sellergoods-ms/specification-Ms';
app.service('specificationService',function($http){
	    	
	//读取列表数据绑定到表单中
	this.findAll=function(){
		return $http.get(SpeciUrl+'/findAll');		
	}
	//分页 
	this.findPage=function(page,rows){
		return $http.get(SpeciUrl+'/findPage?page='+page+'&rows='+rows);
	}
	//查询实体
	this.findOne=function(id){
		return $http.get(SpeciUrl+'/findOne?id='+id);
	}
	//增加 
	this.add=function(entity){
		return  $http.post(SpeciUrl+'/add',entity );
	}
	//修改 
	this.update=function(entity){
		return  $http.post(SpeciUrl+'/update',entity );
	}
	//删除
	this.dele=function(ids){
		return $http.get(SpeciUrl+'/delete?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,searchEntity){
		//alert("specificationservice:search:"+page+","+rows+","+searchEntity);
		return $http.post(SpeciUrl+'/search?page='+page+"&rows="+rows, searchEntity);
	}    	
	//下拉列表
	this.selectOptionList=function(){
		return $http.get(SpeciUrl+'/selectOptionList');
	}
	
});
