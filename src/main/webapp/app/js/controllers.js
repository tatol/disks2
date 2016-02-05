'use strict';

/* Controllers */

var disks2Controllers = angular.module('disks2Controllers', []);

disks2Controllers.controller('ListOwnDisksCtrl', ['$scope', 'ListOwnDisksService','TakeService',
  function($scope, ListOwnDisksService, TakeService) {
    ListOwnDisksService.success(function(data) {
      $scope.disks = data;
    });
    $scope.returnOwnDisk = function(id) {
      TakeService.returnOwnDisk(id).success(function (data) {
            $scope.disks = data;
          }
      );
    };
  }]);

disks2Controllers.controller('ListFreeDisksCtrl', ['$scope', 'ListFreeDisksService','TakeService',
  function($scope, ListFreeDisksService, TakeService) {
    ListFreeDisksService.success(function (data) {
        $scope.disks = data;
      });

    $scope.takeFreeDisk = function(id) {
      TakeService.takeFreeDisk(id).success(function (data) {
            $scope.disks = data;
          }
      );
    };
  }]);

disks2Controllers.controller('ListOwnDisksFromAllUsersCtrl', ['$scope', 'ListOwnDisksFromAllUsersService','TakeService',
  function($scope, ListOwnDisksFromAllUsersService, TakeService) {
    ListOwnDisksFromAllUsersService.success(function(data) {
      $scope.disks = data;
    });

    $scope.takeFreeDiskFromUser = function(id,fromId) {
      TakeService.takeFreeDiskFromUser(id,fromId).success(function (data) {
            $scope.disks = data;
          }
      );
    };
  }]);

disks2Controllers.controller('ListTakenDisksByUserCtrl', ['$scope', 'ListTakenDisksByUserService','TakeService',
  function($scope, ListTakenDisksByUserService, TakeService) {
    ListTakenDisksByUserService.success(function(data) {
      $scope.disks = data;
    });
    $scope.returnDiskToUser = function(id,fromId) {
      TakeService.returnDiskToUser(id,fromId).success(function (data) {
            $scope.disks = data;
          }
      );
    };
  }]);

disks2Controllers.controller('ListTakenDisksFromUserCtrl', ['$scope', 'ListTakenDisksFromUserService',
  function($scope, ListTakenDisksFromUserService) {
    ListTakenDisksFromUserService.success(function(data) {
      $scope.disks = data;
    });
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
    AdminService.success(function(data) {
      $scope.users = data;
    });
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

