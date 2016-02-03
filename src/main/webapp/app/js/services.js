'use strict';

/* Services */

var phonecatServices = angular.module('phonecatServices', ['ngResource']);

phonecatServices.factory('Phone', ['$resource',
  function($resource){
    return $resource('app/phones/:phoneId.json', {}, {
      query: {method:'GET', params:{phoneId:'phones'}, isArray:true}
    });
  }]);

phonecatServices.factory('ListOwnDisksService', ['$http',
function($http){
    return $http.get('http://localhost:8081/listOwnDisks');
}]);

phonecatServices.factory('ListFreeDisksService', ['$http',
function($http){
    return $http.get('http://localhost:8081/listFreeDisks');
}]);

phonecatServices.factory('ListOwnDisksFromAllUsersService', ['$http',
function($http){
    return $http.get('http://localhost:8081/listOwnDisksFromAllUsers');
}]);

phonecatServices.factory('ListTakenDisksByUserService', ['$http',
function($http){
    return $http.get('http://localhost:8081/listTakenDisksByUser');
}]);

phonecatServices.factory('ListTakenDisksFromUserService', ['$http',
function($http){
    return $http.get('http://localhost:8081/listTakenDisksFromUser');
}]);

phonecatServices.factory('TakeService', ['$http',
    function($http){
        return {
            takeFreeDisk:function(id)
            {
              return  $http.put('http://localhost:8081/takeFreeDisk/'+id);
            }
        }

    }]);

phonecatServices.factory('sessionService', function($http) {
    var session = {};
    session.login = function(data) {
        return $http.post("http://localhost:8081/login", "username=" + data.login +
            "&password=" + data.password, {
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        } ).success(function(data) {
            alert("login successful");
            localStorage.setItem("session", {});
        }, function(data) {
            alert("error logging in");
        });
    };
    session.logout = function() {
        localStorage.removeItem("session");
    };
    session.isLoggedIn = function() {
        return localStorage.getItem("session") !== null;
    };
    return session;
});

phonecatServices.factory('accountService', function($resource) {
    var service = {};
    service.register = function(account, success, failure) {
        var Account = $resource("/basic-web-app/rest/accounts");
        Account.save({}, account, success, failure);
    };
    service.getAccountById = function(accountId) {
        var Account = $resource("/basic-web-app/rest/accounts/:paramAccountId");
        return Account.get({paramAccountId:accountId}).$promise;
    };
    service.getAllAccounts = function() {
        var Account = $resource("/login");
        return Account.get().$promise.then(function(data) {
            return data.accounts;
        });
    };
    return service;
});