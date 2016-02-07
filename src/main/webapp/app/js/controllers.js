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
      TakeService.takeFreeDisk(id).success(function (data) {
            $scope.disks = data;
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
      TakeService.takeFreeDiskFromUser(id,fromId).success(function (data) {
            $scope.disks = data;
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
      TakeService.returnDiskToUser(id,fromId).success(function (data) {
            $scope.disks = data;
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

disks2Controllers.controller('MenuCtrl', ['$scope','$location',
  function($scope, $location) {
      $scope.go = function (path) {
        $location.path(path);
      }
  }]);

disks2Controllers.controller("LoginCtrl", function($scope, sessionService, $location) {
  $scope.login = function() {
          sessionService.login($scope.account).success(function() {
            $location.path( '/menu' )
          });
  };
});

disks2Controllers.controller('AdminCtrl', ['$scope', 'AdminService','CreateUserService',
  function($scope, AdminService, CreateUserService) {
      $scope.admin = function() {
          AdminService.admin().success(function (data) {
              $scope.users = data;
          });
      };
      $scope.admin();
     $scope.create = function(user) {
         CreateUserService.createUser(user).success(function (data) {
                  $scope.users = data;
              }
          );
      };
      $scope.submit = function() {
          var role={id:null,role:''};
          var roleMas=[];
          role.role=$scope.role;
          roleMas.push(role);
          $scope.user.roles=roleMas;
          $scope.create($scope.user);
      };
  }]);

