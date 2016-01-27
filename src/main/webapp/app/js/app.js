'use strict';

/* App Module */

var phonecatApp = angular.module('phonecatApp', [
  'ngRoute',
  'phonecatControllers',
  'phonecatFilters',
  'phonecatServices'
]);

phonecatApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/phones', {
        templateUrl: 'app/partials/phone-list.html',
        controller: 'PhoneListCtrl'
      }).
      when('/phones/:phoneId', {
        templateUrl: 'app/partials/phone-detail.html',
        controller: 'PhoneDetailCtrl'
      }).
        when('/listOwnDisks', {
          templateUrl: 'app/partials/own.html',
          controller: 'OWN'
        }).
      otherwise({
        redirectTo: '/phones'
      });
  }]);
