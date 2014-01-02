function commentsCtrl($scope, $http){  
    //when the user enters in the site the comments are loaded through SpringMVC  
    //by default AngularJS comments are empty  
    $scope.comments = [];  
             
    //that is the way for bindding 'submit' event to a AngularJS function  
    //javascript cannot know the context, so we give it as a parameter  
    $scope.addComment = function(context){  
    
    if($scope.comment.length != 0){
      //Asynchronous request  
      $http({  
             url: context+'/add',  
             method: "POST", 
             data: $scope.comment, 
             headers: {'Content-Type': 'application/json'}  
      }).success(function (data, status, headers, config) {  
             //data contains the model which is provided in JSON format by Spring
             //$scope.comments.push is the way to add new comments into $scope.comments array
             $scope.comments.push(data);
             $scope.comment = ''; //reset form  
                                                                                               
       }).error(function (data, status, headers, config) {  
             alert(status);  
       });   
        }else
        alert("You must write a nickname or comment");          
    }  
}  