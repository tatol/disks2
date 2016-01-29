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

phonecatControllers.controller('OWN', ['$scope', 'DiskService',
  function($scope, DiskService) {
    $scope.disk={id:'',name:''};
    $scope.disks=[];
    $scope.disk.user={id:'',login:''};
    $scope.disk.fromuser={id:'',login:''};
    $scope.listOwnDisks = function(){
      DiskService.listOwnDisks()
          .success(
          function(response) {
            console.log(response);
            console.log(response.data);
            $scope.disks = response;
          }
      )
          .error(
          console.log('error')
      );
    };
  }]);
