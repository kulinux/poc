'use strict';

/**
 * @ngdoc overview
 * @name weightApp
 * @description
 * # weightApp
 *
 * Main module of the application.
 */
angular
  .module('weightApp', [
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
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .when('/weight', {
        templateUrl: 'views/weight.html',
        controller: 'WeightCtrl',
        controllerAs: 'weight'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
