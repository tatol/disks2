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
    var self = this;
    self.disk={id:'',name:''};
    self.disks=[];
    self.disk.user={id:'',login:''};
    self.disk.fromuser={id:'',login:''};
    self.listOwnDisks = function(){
      DiskService.listOwnDisks()
          .then(
          function(d) {
            self.disks = d;
          },
          function(errResponse){
            console.error('Error while fetching Currencies');
          }
      );
    };

  }]);
