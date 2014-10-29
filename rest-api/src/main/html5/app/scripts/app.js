'use strict';

/**
 * @ngdoc overview
 * @name valecardAngularApp
 * @description
 * # valecardAngularApp
 *
 * Main module of the application.
 */
angular
  .module('valecardAngularApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .when('/juca', {
        templateUrl: 'views/juca.html',
        controller: 'JucaCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
