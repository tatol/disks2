'use strict';

/* Services */

var phonecatServices = angular.module('phonecatServices', ['ngResource']);

phonecatServices.factory('Phone', ['$resource',
  function($resource){
    return $resource('app/phones/:phoneId.json', {}, {
      query: {method:'GET', params:{phoneId:'phones'}, isArray:true}
    });
  }]);
phonecatServices.factory('DiskService', ['$http',
  function($http){
return{
          listOwnDisks:function() {
             return $http.get('http://localhost:8081/listOwnDisks')
          }
      };
}]);
