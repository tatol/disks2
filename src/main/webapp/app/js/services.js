'use strict';

/* Services */

var disks2Services = angular.module('disks2Services', ['ngResource']);

disks2Services.factory('ListOwnDisksService', ['$http',
function($http){
    return $http.get('http://localhost:8081/listOwnDisks');
}]);

disks2Services.factory('ListFreeDisksService', ['$http',
function($http){
    return $http.get('http://localhost:8081/listFreeDisks');
}]);

disks2Services.factory('ListOwnDisksFromAllUsersService', ['$http',
function($http){
    return $http.get('http://localhost:8081/listOwnDisksFromAllUsers');
}]);

disks2Services.factory('ListTakenDisksByUserService', ['$http',
function($http){
    return $http.get('http://localhost:8081/listTakenDisksByUser');
}]);

disks2Services.factory('ListTakenDisksFromUserService', ['$http',
function($http){
    return $http.get('http://localhost:8081/listTakenDisksFromUser');
}]);

disks2Services.factory('TakeService', ['$http',
    function($http){
        return {
            takeFreeDisk:function(id)
            {
              return  $http.put('http://localhost:8081/takeFreeDisk/'+id);
            },
            takeFreeDiskFromUser:function(id,fromId)
            {
                return  $http.put('http://localhost:8081/takeFreeDiskFromUser/'+id+'/'+fromId);
            },
            returnOwnDisk:function(id)
            {
                return  $http.put('http://localhost:8081/returnOwnDisk/'+id);
            },
            returnDiskToUser:function(id,fromId)
            {
                return  $http.put('http://localhost:8081/returnDiskToUser/'+id+'/'+fromId);
            }
        }

    }]);

disks2Services.factory('ListTakenDisksFromUserService', ['$http',
    function($http){
        return $http.get('http://localhost:8081/listTakenDisksFromUser');
    }]);

disks2Services.factory('AdminService', ['$http',
    function($http){
        return $http.get('http://localhost:8081/admin');
    }]);

disks2Services.factory('CreateUserService', ['$http',
    function($http){
        return{
        createUser:function(user) {
          return  $http.post('http://localhost:8081/admin/',user);
        }
        }
    }]);

disks2Services.factory('sessionService', function($http) {
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
