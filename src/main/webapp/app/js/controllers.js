'use strict';

/* Controllers */

var phonecatControllers = angular.module('phonecatControllers', []);

phonecatControllers.controller('PhoneListCtrl', ['$scope', 'Phone',
  function($scope, Phone) {
    $scope.phones = Phone.query();
    $scope.orderProp = 'age';
  }]);

phonecatControllers.controller('PhoneDetailCtrl', ['$scope', '$routeParams', 'Phone',
  function($scope, $routeParams, Phone) {
    $scope.phone = Phone.get({phoneId: $routeParams.phoneId}, function(phone) {
      $scope.mainImageUrl = phone.images[0];
    });

    $scope.setImage = function(imageUrl) {
      $scope.mainImageUrl = imageUrl;
    };
  }]);

phonecatControllers.controller('ListOwnDisksCtrl', ['$scope', 'ListOwnDisksService',
  function($scope, ListOwnDisksService) {
    ListOwnDisksService.success(function(data) {
      $scope.disks = data;
    });
  }]);

phonecatControllers.controller('ListFreeDisksCtrl', ['$scope', 'ListFreeDisksService','TakeService',
  function($scope, ListFreeDisksService, TakeService) {
    ListFreeDisksService.success(function(data) {
      $scope.disks = data;
    });
    $scope.takeFreeDisk = function(id) {
      TakeService.takeFreeDisk(id).success();
    }
  }]);

phonecatControllers.controller('ListOwnDisksFromAllUsersCtrl', ['$scope', 'ListOwnDisksFromAllUsersService',
  function($scope, ListOwnDisksFromAllUsersService) {
    ListOwnDisksFromAllUsersService.success(function(data) {
      $scope.disks = data;
    });
  }]);

phonecatControllers.controller('ListTakenDisksByUserCtrl', ['$scope', 'ListTakenDisksByUserService',
  function($scope, ListTakenDisksByUserService) {
    ListTakenDisksByUserService.success(function(data) {
      $scope.disks = data;
    });
  }]);

phonecatControllers.controller('ListTakenDisksFromUserCtrl', ['$scope', 'ListTakenDisksFromUserService',
  function($scope, ListTakenDisksFromUserService) {
    ListTakenDisksFromUserService.success(function(data) {
      $scope.disks = data;
    });
  }]);

phonecatControllers.controller('MenuCtrl', ['$scope','$location',
  function($scope, $location) {
    $scope.go= function (path){
      $location.path( path );
    }
  }]);

phonecatControllers.controller("LoginCtrl", function($scope, sessionService, $location,  accountService) {
  $scope.login = function() {
          sessionService.login($scope.account).success(function() {
            $location.path( '/menu' )
          });
  };
});

