'use strict';

/* Filters */

angular.module('disks2Filters', []).filter('checkmark', function() {
  return function(input) {
    return input ? '\u2713' : '\u2718';
  };
});
