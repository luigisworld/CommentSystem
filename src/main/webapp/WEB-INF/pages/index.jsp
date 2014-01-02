<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html ng-app>
 <head>
     <title>Comments system Luigi's world</title>
     <link href="css/style.css" rel="stylesheet">
     <script src="//ajax.googleapis.com/ajax/libs/angularjs/1.2.4/angular.min.js"></script>  
     <script src="js/controller.js"></script><!-- our controller --> 
 </head>
 <body ng-controller="commentsCtrl">
 
  <div class="commentsFrame">
   <!-- JSTL loads all the comments when user enters in the site -->
         <c:forEach var="comment" items="${comments}">
             <div>
                 <h4>${comment.nick}</h4>
                 <h5>${comment.date}</h5>
                 <p>${comment.text}</p>
             </div>
         </c:forEach>
         
         <!-- Angular sets the comments added by the user -->
         <div ng-repeat="comment in comments">
             <h4>{{comment.nick}}</h4>
             <h5>{{comment.date}}</h5>
             <p>{{comment.text}}</p>
         </div>
     </div>
     
     <form ng-submit="addComment('${pageContext.request.contextPath}')" 
      name="commentForm">
      <input placeholder="Nick" ng-model="comment.nick" type="text"/><br>
      <textarea placeholder="Comment" cols="72" ng-model="comment.text"></textarea><br>
      <input type="submit" id="submit" value="Send" />
     </form>
 </body>
</html>
