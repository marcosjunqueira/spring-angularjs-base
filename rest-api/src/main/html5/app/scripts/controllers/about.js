'use strict';

/**
 * @ngdoc function
 * @name valecardAngularApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the valecardAngularApp
 */
angular.module('valecardAngularApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
