'use strict';

/* Controllers */

var disks2Controllers = angular.module('disks2Controllers', []);

disks2Controllers.controller('ListOwnDisksCtrl', ['$scope', 'DisksService','TakeService',
  function($scope, DisksService, TakeService) {
      $scope.listOwnDisks= function(){
          DisksService.listOwnDisks().success(function(data) {
              $scope.disks = data;
          });
      };
      $scope.listOwnDisks();
    $scope.returnOwnDisk = function(id) {
      TakeService.returnOwnDisk(id).success(function () {
          $scope.listOwnDisks();
          }
      );
    };
  }]);

disks2Controllers.controller('ListFreeDisksCtrl', ['$scope', 'DisksService','TakeService',
  function($scope, DisksService, TakeService) {
      $scope.listFreeDisks = function() {
          DisksService.listFreeDisks().success(function (data) {
              $scope.disks = data;
          });
      };
      $scope.listFreeDisks();
    $scope.takeFreeDisk = function(id) {
      TakeService.takeFreeDisk(id).success(function () {
              $scope.listFreeDisks();
          }
      );
    };
  }]);

disks2Controllers.controller('ListOwnDisksFromAllUsersCtrl', ['$scope', 'DisksService','TakeService',
  function($scope, DisksService, TakeService) {
      $scope.listOwnDisksFromAllUsers = function() {
          DisksService.listOwnDisksFromAllUsers().success(function (data) {
              $scope.disks = data;
          });
      };
      $scope.listOwnDisksFromAllUsers();
    $scope.takeFreeDiskFromUser = function(id,fromId) {
      TakeService.takeFreeDiskFromUser(id,fromId).success(function () {
              $scope.listOwnDisksFromAllUsers();
          }
      );
    };
  }]);

disks2Controllers.controller('ListTakenDisksByUserCtrl', ['$scope', 'DisksService','TakeService',
  function($scope, DisksService, TakeService) {
      $scope.listTakenDisksByUser = function() {
          DisksService.listTakenDisksByUser().success(function (data) {
              $scope.disks = data;
          });
      };
      $scope.listTakenDisksByUser();
    $scope.returnDiskToUser = function(id,fromId) {
      TakeService.returnDiskToUser(id,fromId).success(function () {
              $scope.listTakenDisksByUser();
          }
      );
    };
  }]);

disks2Controllers.controller('ListTakenDisksFromUserCtrl', ['$scope', 'DisksService',
  function($scope, DisksService) {
      $scope.listTakenDisksFromUser = function () {
          DisksService.listTakenDisksFromUser().success(function (data) {
              $scope.disks = data;
          });
      };
      $scope.listTakenDisksFromUser();
  }]);

disks2Controllers.controller('MenuCtrl', ['$scope','$location','UserService',
  function($scope, $location, UserService) {
      $scope.user={id:null, login:"", roles:[]};
      $scope.go = function (path) {
        $location.path(path);
      };
      $scope.getUser = function() {
          UserService.getUser().success(function (data) {
              $scope.user = data;
          });
      };
      $scope.isAdmin = function() {
          var c = $scope.user;
          for(var i=0; i<$scope.user.roles.length; i++)
          {
              if ($scope.user.roles[i].role=="role_admin")
              {
                  return true;
              }
          }
          return false;
      };
      $scope.getUser();

  }]);

disks2Controllers.controller("LoginCtrl", function($scope, sessionService, $location) {
  $scope.login = function() {
          sessionService.login($scope.account).success(function() {
            $location.path( '/menu' )
          });
  };
});

disks2Controllers.controller('AdminCtrl', ['$scope', 'AdminService','UserService',
  function($scope, AdminService, UserService) {
      $scope.admin = function() {
          AdminService.admin().success(function (data) {
              $scope.users = data;
          });
      };

      $scope.admin();
     $scope.create = function(credentials) {
         UserService.createUser(credentials).success(function () {
                 $scope.admin()
             }
         );
      };

      $scope.credentials={login:"", password:"", role:""};

      $scope.roles = {
          availableOptions: [
              {id: "1", role: 'role_user'},
              {id: "2", role: 'role_admin'}
          ],
          selectedOption: {id: "1", role: 'role_user'} //This sets the default value of the select in the ui
      };
      $scope.submit = function() {
          $scope.credentials.role=$scope.roles.selectedOption.role;
          $scope.create($scope.credentials);
      };
  }]);

