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
      otherwise({
        redirectTo: '/phones'
      });
  }]);
