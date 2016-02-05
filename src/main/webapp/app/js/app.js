'use strict';

/* App Module */

var disks2App = angular.module('disks2App', [
  'ngRoute',
  'disks2Controllers',
  'disks2Filters',
  'disks2Services'
]);

disks2App.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
        when('/listOwnDisks', {
            templateUrl: 'app/partials/own.html',
            controller: 'ListOwnDisksCtrl'
        }).
        when('/listFreeDisks', {
                  templateUrl: 'app/partials/free.html',
                  controller: 'ListFreeDisksCtrl'
        }).
        when('/listTakenDisksByUser', {
                  templateUrl: 'app/partials/takenBy.html',
                  controller: 'ListTakenDisksByUserCtrl'
        }).
        when('/listTakenDisksFromUser', {
                  templateUrl: 'app/partials/takenFrom.html',
                  controller: 'ListTakenDisksFromUserCtrl'
        }).
        when('/menu', {
                  templateUrl: 'app/partials/menu.html',
                  controller: 'MenuCtrl'
        }).
        when('/login', {
            templateUrl: 'app/partials/login.html',
            controller: 'LoginCtrl'
        }).
        when('/admin', {
            templateUrl: 'app/partials/admin.html',
            controller: 'AdminCtrl'
        }).
      otherwise({
        redirectTo: '/login'
      });
  }]);
